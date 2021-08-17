package com.wang.kotlin

import android.util.Log

/**
 * Created at 2021/8/13 上午8:42.
 * @author wang
 * 内联函数
 */

fun main() {
    /**
     * 这里调用 inline 函数 log
     * 在编译时，把 log 里面的代码拷贝到了这里
     * 但是，所以如果 log 里面代码行数很多，或者 log 在多处地方调用了
     * 那么对编译的速度有影响
     *
     * 只是仅仅减少了调用栈，这不是inline关键字的最佳用法
     */
    log()

    /**
     * 调用 test
     * inline 关键字推荐和 参数 是函数类型的 参数 一起使用
     * 这样避免在调用时，创建多余的对象
     * （通过反编译可以看出，没有加inline关键字的 函数类型的 参数，会生成多余的对象）
     */
    test {
        it.toString()
    }
}

/**
 * 这里编辑器会给出警告，提示我们这里不需要使用 inline 关键字
 * inline 关键字最佳使用应该是和 参数 是 函数类型的 参数 一起使用
 * Expected performance impact from inlining is insignificant.
 * Inlining works best for functions with parameters of functional types
 */
inline fun log() {
    Log.e("TAG", "log: ")
    Log.e("TAG", "log: ")
    Log.e("TAG", "log: ")
    Log.e("TAG", "log: ")
}

inline fun test(action:(Int) -> String) {
    action(1)
}

fun doSomething(action: () -> Unit) {
    action()
}