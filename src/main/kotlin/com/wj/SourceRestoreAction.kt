package com.wj

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnActionEvent
import com.wj.base.BaseAction
import com.wj.extension.exec

/**
 * @author wj
 * @date 2023/4/3
 * @description：SourceRestoreAction
 */
internal class SourceRestoreAction : BaseAction() {
    override fun onPerformed(e: AnActionEvent) {
        "power_command s r".exec()
    }


    override fun onUpdate(e: AnActionEvent) {
        e.presentation.icon = AllIcons.General.Reset
    }
}