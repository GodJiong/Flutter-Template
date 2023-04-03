package com.wj.base

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.wj.extension.exec
import com.wj.util.DEBUG
import com.wj.util.Logger

/**
 * @author wj
 * @date 2023/4/3
 * @description：Group基类
 */
internal class BaseGroup : DefaultActionGroup() {

    // IDE每次打开加载插件时触发
    init {
        // 初始化日志
        Logger.init(level = DEBUG)
        // 初始化power_command库
//        initPowerCommand()
    }

    /**
     *  初始化power_command库
     */
    private fun initPowerCommand() {
        // 获取 application 级别的 PropertiesComponent
        PropertiesComponent.getInstance().run {
            if (getValue("power_command_is_init") == "true") {
                return@run
            }
            "dart pub global activate power_command".exec()
            setValue("power_command_is_init", "true")
            Logger.d("首次初始化完成")
        }
    }
}