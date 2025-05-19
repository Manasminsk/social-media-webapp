
package com.social.repositories;

import com.social.web.backend.model.Notification;
import com.social.web.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientOrderByTimestampDesc(User recipient);
}
