package com.wang.jetpackstudy;

/**
 * Created at 2021/8/16 上午8:12.
 *
 * @author wang
 */
public class WaitNotifyDemo {

    private String name;
    // 自己构建锁对象
    private final Object monitor = new Object();

    private void printName() {
        // 使用 synchronized 代码块
        synchronized (monitor) {
            while (name == null) {
                try {
                    // 使用这时的 monitor 来调用 wait 方法
                    monitor.wait();
                } catch (InterruptedException e) {

                }
            }
        }

        System.out.println("name = " + name);
    }

    private void initName() {
        name = "wzh";
        // 使用 synchronized 代码块
        synchronized (monitor) {
            // 使用这时的 monitor 来调用 notifyAll 方法
            monitor.notifyAll();
        }
    }

    public void run() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 先 打印 name
                printName();
            }
        };
        t1.start();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 后 初始化 name
                initName();
            }
        };
        t2.start();
    }
}
