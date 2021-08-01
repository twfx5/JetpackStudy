package com.wang.kotlin

/**
 * Created at 2021/8/1 上午8:31.
 * @author wang
 *
 *  kotlin 高阶函数：
 * 2 kotlin 中的 函数类型
 * 作为函数参数或者函数返回值
 */

fun b(param: Int) : String{
    return param.toString()
}

fun main() {
    /**
     * f 的类型为函数类型：
     * 有两个参数，分别是int 和 String 类型
     * 返回值是 void
     */
    val f : (Int, String) -> Unit

    val d = ::doSomeThing
    val t = d

    val a = ::b
    b(1) // 调用函数
    a(1) // 对象 a 后面加上括号，来实现 b() 的等价操作
    (::b)(1) // 对象 ::b 后面加上括号，来实现 b() 的等价操作

    // 上面的函数对象的写法，相当于 函数对象.invoke()方法
    a.invoke(1)
    (::b).invoke(1)
    // 但是函数本身不能调用invoke方法
    // b.invoke(1) 错误写法

    // 给变量赋返回值，变量是函数类型，所以类型是 (Int) -> String
    val c1 : (Int) -> String = { m: Int ->
        m.toString()
    }

    // 如果给变量赋返回值，就能省略lambda中的参数，用it 代替
    val c2 : (Int) -> String = {
        it.toString()
    }

    val num1 = 10
    val num2 = 20
    // 调用函数参数的方式1 ：匿名函数
    val result1 = num1AndNum2(num1, num2, fun (num1, num2):Int {
        return num1 + num2
    })
    println(result1)

    // 调用函数参数的方式1 ：lambda表达式
    val result2 = num1AndNum2(num1, num2) {
        a: Int, b: Int -> // lambda 的参数
        a + b // 最后一行作为 lambda 返回值
    }
    println(result2)

    // kotlin 的类型推导，省略 a，b 的参数类型
    val result3 = num1AndNum2(num1, num2) { a, b ->
        a + b
    }
    println(result3)
}

/**
 * 第三个参数是函数类型的参数
 * 参数名是 operation，参数类型是 (Int, Int) -> Int)
 * 返回值是通过 operation 操作后的 num1, num2
 */
fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int) :Int {
    return operation(num1, num2)
}

fun doSomeThing(): Int{
    return 0
}

fun test(t : Int) {
    val a = fun (b : Int) {
        b.toString()
    }
}




