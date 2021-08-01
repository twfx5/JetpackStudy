package com.wang.kotlin

/**
 * Created at 2021/7/31 下午10:31.
 * @author wang
 * kotlin 高阶函数：
 * 1 kotlin 中的 lambda 表达式
 */

/**
 * Android工程快速使用 kotlin:
 * 直接创建一个 kotlin 文件 TestLambda.kt
 * 定义一个 main 函数，就能在左边看到三角（点击能运行）
 */
fun main() {
    getMaxJava()
    getMaxKotlin()
}

// 找出最长的字符串 java 写法
fun getMaxJava() {
    val list = listOf("apple", "orange", "watermelon", "pear")
    var result = ""
    // 遍历
    for (fruit in list) {
        if (fruit.length > result.length) {
            result = fruit
        }
    }
    println(result)
}

// 找出最长的字符串 kotlin 写法
fun getMaxKotlin() {
    val list = listOf("apple", "orange", "watermelon", "pear")

    /**
     * maxByOrNull 是 kotlin 的函数
     * { it.length } 是lambda表达式
     */
    val result = list.maxByOrNull { it.length }
    println(result)
}

// 下面演示怎么一步步得到 lambda 的：

// 步骤1 和 步骤2 构建一个lambda{} ，然后传给 maxByOrNull
fun getMaxKotlin1() {
    val list = listOf("apple", "orange", "watermelon", "pear")

    /**
     * 构建一个变量 lambda：
     * 用 { }括起来
     * 最后一行作为 lambda 的返回值，不用写 return
     */
    val lambda = { fruit: String ->
        fruit.length
    }
    val result = list.maxByOrNull(lambda)
    println(result)
}

// 步骤2
fun getMaxKotlin2() {
    val list = listOf("apple", "orange", "watermelon", "pear")
    val result = list.maxByOrNull({ fruit: String ->
        fruit.length
    })
    println(result)
}

// 步骤3，当lambda是方法最后一个参数时，可以将 lambda 的 {}，移到外面 ()的后面
fun getMaxKotlin3() {
    val list = listOf("apple", "orange", "watermelon", "pear")
    val result = list.maxByOrNull() { fruit: String ->
        fruit.length
    }
    println(result)
}

// 步骤4，当lambda是方法的唯一参数是，时将 lambda 签名的() 省略
fun getMaxKotlin4() {
    val list = listOf("apple", "orange", "watermelon", "pear")
    val result = list.maxByOrNull { fruit: String ->
        fruit.length
    }
    println(result)
}

// 步骤5，kotlin支持推导参数类型，将 lambda 的参数类型省略
fun getMaxKotlin5() {
    val list = listOf("apple", "orange", "watermelon", "pear")
    val result = list.maxByOrNull { fruit ->
        fruit.length
    }
    println(result)
}

// 步骤6，lambda 参数只有一个，可以用 it 代替参数 fruit
fun getMaxKotlin6() {
    val list = listOf("apple", "orange", "watermelon", "pear")
    val result = list.maxByOrNull { it ->
        it.length
    }
    println(result)
}

// 步骤7，lambda 参数只有一个，可以将 lambda 参数it 省略
fun getMaxKotlin7() {
    val list = listOf("apple", "orange", "watermelon", "pear")
    val result = list.maxByOrNull {
        it.length
    }
    println(result)
}