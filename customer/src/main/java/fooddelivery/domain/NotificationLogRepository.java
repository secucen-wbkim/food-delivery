package fooddelivery.domain;

import fooddelivery.domain.*;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "notificationLogs",
    path = "notificationLogs"
)
public interface NotificationLogRepository
    extends PagingAndSortingRepository<NotificationLog, Long> {
        Optional<NotificationLog>findByOrderId(Long orderId);
    }
