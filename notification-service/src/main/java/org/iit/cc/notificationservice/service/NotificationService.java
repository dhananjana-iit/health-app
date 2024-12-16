package org.iit.cc.notificationservice.service;

import org.iit.cc.notificationservice.model.Notification;
import org.iit.cc.notificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    /**
     * Create a new notification.
     *
     * @param notification Notification details to create
     * @return The created notification
     */
    public Notification createNotification(Notification notification) {
        notification.setStatus("Pending");
        return repository.save(notification);
    }

    /**
     * Retrieve all pending notifications before the current time.
     *
     * @return List of pending notifications
     */
    public List<Notification> getPendingNotifications() {
        return repository.findByScheduledTimeBeforeAndStatus(LocalDateTime.now(), "Pending");
    }


    /**
     * Update the status of a notification by ID.
     *
     * @param id Notification ID
     * @param status New status to set (e.g., Sent, Failed)
     */
    @Transactional
    public void updateNotificationStatus(String id, String status) {
        Notification notification = repository.findById(id).orElseThrow(() -> new IllegalStateException("Notification not found"));
        notification.setStatus(status);
        notification.setSentTime(LocalDateTime.now());
        repository.save(notification);
    }
}

