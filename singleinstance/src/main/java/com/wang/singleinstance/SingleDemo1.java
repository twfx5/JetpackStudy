package com.wang.singleinstance;

/**
 * Created at 2021/8/20 上午6:25.
 *
 * @author wang
 * 双重检查的方式，创建单例
 */
public class SingleDemo1 {
    /**
     * java有时候创建对象时，在还未创建完毕时，就对外界标记为可用了，
     * 但是这时对象的内部还未初始化完毕，如果有另一个线程去使用这个对象，则会出现问题。
     * singleDemo1的初始化也是如此：
     * 它的初始化可能是个复杂的过程，极端情况下，假设线程1中的singleDemo1创建完毕，并在虚拟机中被标记为可用，
     * 但是其初始化过程并没有结束。这就导致线程2在获取singleDemo1对象时，拿到了一个并不完全正确的对象。
     * 加上volatile关键字后，对象singleDemo1初始化完毕后，才会对外标记singleDemo1可用。
     */
    public volatile static SingleDemo1 singleDemo1;

    private void SingleDemo1(){

    }

    public static SingleDemo1 getInstance() {
        if (singleDemo1 == null) { // 当singleDemo1为空时，才创建，否则直接返回
            // 使用synchronized代码块，这样比整个方法加锁，性能要好。只在singleDemo1为空时，创建的过程需要加锁。
            synchronized (SingleDemo1.class) {
                /**
                 * 需要判空，保证第一个线程创建singleDemo1之后，后面等待的线程不会重复创建。
                 * synchronized只是同步了创建singleDemo1的过程
                 * 但是假如有两个线程A、B在创建singleDemo1时，一开始就排队竞争锁。
                 * A先获得锁，然后创建了singleDemo1；然后B获得锁，这时如果不判空，那么B也会接着创singleDemo1。
                 */
                if (singleDemo1 == null) {
                    singleDemo1 = new SingleDemo1();
                }
            }
        }
        return singleDemo1;
    }
}
