package com.wj.util

import com.intellij.notification.*
import com.intellij.notification.Notification

/**
 * @author wangjiong
 * @date 2023/1/12
 * @description: 日志
 */

const val DEBUG: Int = 3
const val INFO: Int = 2
const val WARN: Int = 1
const val ERROR: Int = 0
const val NONE: Int = -1

object Logger {
    private lateinit var NAME: String

    /**
     * 日志级别，默认关闭日志
     */
    private var LEVEL: Int = NONE

    fun init(name: String = "power_command", level: Int = NONE) {
        NAME = name
        LEVEL = level
        // 动态注册
        NotificationsConfiguration.getNotificationsConfiguration().register(NAME, NotificationDisplayType.NONE)
    }

    fun d(text: String) {
        if (LEVEL >= DEBUG) {
            Notifications.Bus.notify(
                Notification(NAME, "$NAME [DEBUG]", text, NotificationType.INFORMATION)
            )
        }
    }

    fun i(text: String) {
        if (LEVEL >= INFO) {
            Notifications.Bus.notify(
                Notification(NAME, "$NAME [INFO]", text, NotificationType.INFORMATION)
            )
        }
    }

    fun w(text: String) {
        if (LEVEL >= WARN) {
            Notifications.Bus.notify(
                Notification(NAME, "$NAME [WARN]", text, NotificationType.WARNING)
            )
        }
    }

    fun e(text: String) {
        if (LEVEL >= ERROR) {
            Notifications.Bus.notify(
                Notification(NAME, "$NAME [ERROR]", text, NotificationType.ERROR)
            )
        }
    }
}