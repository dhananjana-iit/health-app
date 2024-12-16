package org.iit.cc.notificationservice.scheduler;

import org.iit.cc.notificationservice.model.Notification;
import org.iit.cc.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationScheduler {

    @Autowired
    private NotificationService notificationService;

    @Scheduled(fixedRate = 60000) // Runs every minute
    public void processNotifications() {
        List<Notification> pendingNotifications = notificationService.getPendingNotifications();

        for (Notification notification : pendingNotifications) {
            // Simulate sending notification
            System.out.println("Sending notification to patient: " + notification.getPatientId());
            notificationService.updateNotificationStatus(notification.getId(), "Sent");
        }
    }
}

