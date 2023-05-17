# 1. Eventstorming Model
![image](https://github.com/secucen-wbkim/food-delivery/assets/117430227/c5d9630f-1556-4c3e-b0d1-6591af9525af)


# 2. Saga (Pub/Sub) 확인 (클러스터에 Kafka 설치 후)


# 3. Service Router 설치


# 4. Zero downtime Deployment



# 

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

