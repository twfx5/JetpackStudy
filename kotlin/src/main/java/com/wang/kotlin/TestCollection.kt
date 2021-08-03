package com.wang.kotlin

/**
 * Created at 2021/8/2 下午10:35.
 * @author wang
 * 集合的一些常见函数 API
 */
fun main() {
    //testMap()
    //testFilter()
    //testFilterAndMap()
    //testAny()
    //testAll()
    testToSet()
}

/**
 *  toSet 对集合中元素 去重
 *  返回值 Set
 */
private fun testToSet() {
    // mutableListOf可变集合
    val list = mutableListOf("apple", "orange")
    list.add("apple")
    list.add("apple")
    // 去重
    val result = list.toSet()
    println(result)
    // 结果 [apple, orange]
}

/**
 * map 映射
 * 将字符串转为大写
 */
private fun testMap() {
    val list = listOf("apple", "orange", "watermelon", "pear")
    // 转为新list
    val newList = list.map { it.uppercase() }
    println(newList)
    // 结果 [APPLE, ORANGE, WATERMELON, PEAR]
}

/**
 * filter 过滤集合中的数据
 */
private fun testFilter() {
    val list = listOf("apple", "orange", "watermelon", "pear")
    val newList = list.filter { it.length < 5 }
    println(newList)
    // 结果 [pear]
}

/**
 *  先用 filter 过滤，再 map 转换
 */
private fun testFilterAndMap() {
    val list = listOf("apple", "orange", "watermelon", "pear")
    val newList = list.filter { it.length < 5 }
        .map { it.uppercase() }
    println(newList)
    // 结果 [PEAR]
}

/**
 *  any 是否有一个元素满足指定条件
 */
private fun testAny() {
    val list = listOf("apple", "orange", "watermelon", "pear")
    val result = list.any { it.length < 5 }
    println(result)
    // 结果 true
}

/**
 *  all 是否所有元素满足指定条件
 */
private fun testAll() {
    val list = listOf("apple", "orange", "watermelon", "pear")
    val result = list.all { it.length < 5 }
    println(result)
    // 结果 false
}

