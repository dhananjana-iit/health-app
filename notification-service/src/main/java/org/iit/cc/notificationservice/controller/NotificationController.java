package org.iit.cc.notificationservice.controller;

import org.iit.cc.notificationservice.model.Notification;
import org.iit.cc.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * Create a new notification.
     *
     * @param notification Notification details
     * @return ResponseEntity with the created notification
     */
    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.createNotification(notification));
    }

    /**
     * Retrieve pending notifications.
     *
     * @return ResponseEntity with the list of pending notifications
     */
    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications() {
        return ResponseEntity.ok(notificationService.getPendingNotifications());
    }

    /**
     * Simple health check endpoint.
     * Used to verify the service is up and running.
     *
     * @return ResponseEntity with OK status and a health message
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Notification Service is UP");
    }
}
