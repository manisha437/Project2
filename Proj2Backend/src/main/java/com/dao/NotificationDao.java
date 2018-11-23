package com.dao;

import java.util.List;

import com.models.Notification;
import com.models.User;

public interface NotificationDao {
void addNotification(Notification notification);
List<Notification>  getAllNotification(String email);
Notification getNotification(int notificationId);
void updateNotification(int notificationId);
}


