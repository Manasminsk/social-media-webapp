
package com.social.service;

import com.social.web.backend.model.Notification;
import com.social.web.backend.model.User;
import com.social.repositories.NotificationRepository;
import com.social.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public void createFollowNotification(Long recipientId, String followerName) {
        userRepository.findById(recipientId).ifPresent(user -> {
            Notification notification = new Notification();
            notification.setRecipient(user);
            notification.setMessage(followerName + " started following you.");
            notification.setTimestamp(LocalDateTime.now());
            notificationRepository.save(notification);
        });
    }

    public List<Notification> getNotifications(Long userId) {
        return userRepository.findById(userId)
            .map(notificationRepository::findByRecipientOrderByTimestampDesc)
            .orElse(List.of());
    }
}
