package com.wj.extension

import com.wj.util.Logger

/**
 * @author wj
 * @date 2023/4/3
 * @description：调用外部命令
 */
fun String.exec(): Process {
    return Runtime.getRuntime().exec(arrayOf("/bin/sh", "-c", this)).apply {
        inputStream.reader().use {
            Logger.d(it.readText())
        }
        // waitFor 阻塞等待 异步进程结束，并返回执行状态，0代表命令执行正常结束。
        waitFor()
    }
}