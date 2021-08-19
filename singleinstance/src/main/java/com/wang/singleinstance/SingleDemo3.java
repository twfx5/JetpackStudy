package com.wang.singleinstance;

/**
 * Created at 2021/8/20 上午6:47.
 *
 * @author wang
 * 枚举，创建单例
 */
public enum SingleDemo3 {
    // 枚举来实现单例模式。不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象。
    INSTANCE;

    public void whateverMethod() {

    }

}
