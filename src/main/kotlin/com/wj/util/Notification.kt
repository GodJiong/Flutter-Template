package com.wj.util

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.util.NlsContexts.*

/**
 * @author wangjiong
 * @date 2023/1/12
 * @description: 气泡通知
 */
internal class Notification private constructor() {

    /**
     * Double Check Instance
     */
    companion object {
        val instance: Notification by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Notification()
        }
    }

    /**
     * 获取通知组管理器
     */
    private val manager by lazy {
        NotificationGroupManager.getInstance()
    }

    /**
     * 获取注册的通知组
     */
    private val balloon by lazy {
        manager.getNotificationGroup("power_command.notification.balloon")
    }

    /**
     * 获取注册的通知组
     */
    private val stickyBalloon by lazy {
        manager.getNotificationGroup("power_command.notification.sticky.balloon")
    }

    /**
     * 气泡通知
     */
    @JvmOverloads
    fun notifyBalloon(
        @NotificationContent content: String,
        type: NotificationType = NotificationType.INFORMATION,
        isSticky: Boolean = false
    ) {
        // 使用通知组创建通知
        val balloonNotification = if (isSticky) {
            stickyBalloon.createNotification(content, type)
        } else {
            balloon.createNotification(content, type)
        }
        // 将通知放入通知总线
        Notifications.Bus.notify(balloonNotification)
    }
}