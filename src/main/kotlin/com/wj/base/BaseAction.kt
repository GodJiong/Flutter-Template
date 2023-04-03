package com.wj.base

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

/**
 * @author wj
 * @date 2023/4/3
 * @description：Action基类
 */
internal abstract class BaseAction : AnAction() {

    abstract fun onPerformed(e: AnActionEvent)
    abstract fun onUpdate(e: AnActionEvent)

    override fun update(e: AnActionEvent) {
        super.update(e)
        onUpdate(e)
    }

    override fun actionPerformed(e: AnActionEvent) {
        onPerformed(e)
    }
}