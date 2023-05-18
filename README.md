# 1. Eventstorming Model
![image](https://github.com/secucen-wbkim/food-delivery/assets/117430227/111b32fd-6456-40bb-ab97-7fbfd08d551d)


# 2. Saga (Pub/Sub) 확인 (클러스터에 Kafka 설치 후)
클러스터에 helm으로 kafka를 설치하고 kubenetes/templete.yml 을 apply 하였을 때
![image](https://github.com/secucen-wbkim/food-delivery/assets/117430227/ac59f858-7177-4321-b2da-ed674eb8ef64)

클러스터에 kafka 클라이언트를 설치하여 접속하고 배달 시작부터 완료까지 수행하여 나타난 메시지들
![image](https://github.com/secucen-wbkim/food-delivery/assets/117430227/0a91a6fb-b2dc-471e-af92-730ba416f84e)

# 3. Service Router 설치
![image](https://github.com/secucen-wbkim/food-delivery/assets/117430227/ac59f858-7177-4321-b2da-ed674eb8ef64)

# 4. Zero downtime Deployment
zero downtime을 위해 설정된 redinessProbe(패치시 딜레이와 상태체크를 하여 새로운 pod가 정상적으로 동작할때 패킷을 릴레이)와 livenessProbe(pod가 정상적으로 동작하는지를 주기적으로 체크하여 셀프힐링)

![image](https://github.com/secucen-wbkim/food-delivery/assets/117430227/82408cd3-9761-47bd-a59e-4195660d1d19)

readinessProbe의 동작확인을 위해 kubernetes에 siege pod를 생성하여 주기적으로 서비스 호출
siege -c1 -t360S -v http://orderfront:8080/orders --delay=1S

readinessProbe 옵션이 없는 orderfront/kubernetes/deployment.yml 를 apply
![image](https://github.com/secucen-wbkim/food-delivery/assets/117430227/eebace04-31bd-4a1f-80b3-afefc2e0b761)

readinessProbe 옵션이 없을 때 다운타임이 발생
![image](https://github.com/secucen-wbkim/food-delivery/assets/117430227/dd2f532e-8a8a-47bb-8ef0-af503b0c0a93)


# 정상 배달 시나리오
주문
http :8081/orders foodId="뿌링클치킨" customerId="wbkim" options="문앞에 놓고 벨눌러주세요" address="영등포구 가마산로 343" qty=2 status="주문됨"

결제
http :8081/payments orderId=1

주문 확인
http :8082/storeOrders

주문 승인
http PUT :8082/storeOrders/1/accept

조리 시작
http PUT :8082/storeOrders/1/startcook

조리 완료
http PUT :8082/storeOrders/1/finish-cook

배달 확인
http :8083/deliveries

배달 시작
http PUT :8083/deliveries/1/accept

배달 완료
http PUT :8083/deliveries/1/completeDelivery

# 주문 취소 시나리오
주문
http :8081/orders foodId="뿌링클치킨" customerId="wbkim" options="문앞에 놓고 벨눌러주세요" address="영등포구 가마산로 343" qty=2 status="주문됨"

결제
http :8081/payments orderId=2

주문 취소
http DELETE :8081/orders/2

# 주문취소 불가 시나리오
주문
http :8081/orders foodId="뿌링클치킨" customerId="wbkim" options="문앞에 놓고 벨눌러주세요" address="영등포구 가마산로 343" qty=2 status="주문됨"

결제
http :8081/payments orderId=3

주문 확인
http :8082/storeOrders

주문 승인
http PUT :8082/storeOrders/3/accept

조리 시작
http PUT :8082/storeOrders/3/startcook

주문 취소 (에러발생)
http DELETE :8081/orders/3


# kubenates kafka 설치

curl -fsSL -o get_helm.sh https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3
chmod 700 get_helm.sh
./get_helm.sh

helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update
helm install my-kafka bitnami/kafka

kafka 메시지 확인
kubectl run my-kafka-client --restart='Never' --image docker.io/bitnami/kafka:2.8.0-debian-10-r0 --command -- sleep infinity
kubectl exec --tty -i my-kafka-client -- bash

# CONSUMER:
kafka-console-consumer.sh --bootstrap-server my-kafka:9092 --topic fooddelivery --from-beginning



## Model
www.msaez.io/#/storming/delivery-foods

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd kafka
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- orderfront
- store
- delivery
- customer


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- orderfront
```
 http :8088/orders id="id" foodId="foodId" customerId="customerId" options="options" address="address" status="status" qty="qty" 
 http :8088/payments id="id" orderId="orderId" 
```
- store
```
 http :8088/storeOrders id="id" foodId="foodId" orderId="orderId" status="status" address="address" qty="qty" 
```
- delivery
```
 http :8088/deliveries id="id" address="address" orderId="orderId" status="status" 
```
- customer
```
 http :8088/notificationLogs id="id" customerId="customerId" message="message" 
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```

