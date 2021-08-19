package com.wang.singleinstance;

/**
 * Created at 2021/8/20 上午6:33.
 *
 * @author wang
 * 使用静态内部类的方式，创建单例
 */
public class SingleDemo2 {

    private void SingleDemo2() {

    }

    private static class SingleInstance {
        /**
         * 将类实例化的过程放在了静态代码块中，
         * 也是在类装载的时候，就执行静态代码块中的代码，初始化类的实例。
         *
         * 类的静态属性只会在第一次加载类的时候初始化，所以在这里，JVM帮助我们保证了线程的安全性，
         * 在类进行初始化时，别的线程是无法进入的。
         *
         * 静态内部类方式在SingleDemo2类被装载时并不会立即实例化，而是在需要实例化时，
         * 调用getInstance方法，才会装载SingleInstance类，从而完成SingleDemo2的实例化。
         */
        private static final SingleDemo2 singleDemo2 = new SingleDemo2();
    }

    // 静态内部类方式的懒加载，只有调用getInstance方法时，才开始加载SingleInstance类
    public static SingleDemo2 getInstance() {
        return SingleInstance.singleDemo2;
    }
}
