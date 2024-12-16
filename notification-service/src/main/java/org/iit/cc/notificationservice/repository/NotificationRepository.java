package org.iit.cc.notificationservice.repository;

import org.iit.cc.notificationservice.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByScheduledTimeBeforeAndStatus(LocalDateTime time, String status);
}

