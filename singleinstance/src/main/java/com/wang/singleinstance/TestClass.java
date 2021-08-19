package com.wang.singleinstance;

/**
 * Created at 2021/8/20 上午6:48.
 *
 * @author wang
 */
public class TestClass {

    public static void main(String[] args) {
        SingleDemo1 instance1 = SingleDemo1.getInstance();

        SingleDemo2 instance2 = SingleDemo2.getInstance();

        SingleDemo3 instance3 = SingleDemo3.INSTANCE;
        instance3.whateverMethod();

        // doSomeThing 方法上不加 @JvmStatic，只能这样调用
        SingleDemo4.INSTANCE.doSomeThing();
        // doSomeThing 方法 加上 @JvmStatic，可以真正像单例一样调用
        SingleDemo4.doSomeThing();
    }
}
