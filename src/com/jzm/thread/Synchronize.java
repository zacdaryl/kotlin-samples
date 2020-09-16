package com.jzm.thread;

import com.jzm.util.Console;

import java.time.chrono.MinguoDate;

public class Synchronize {
    public static int svar = 0;

    /**
     * synchronized static 锁住的是类对象本身，多线程调用此静态方法互斥。
     */
    public synchronized static void staticFun() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        svar++;
        String curName = Thread.currentThread().getName();
        Console.Companion.print(curName + ":"  + Synchronize.svar);
    }

    public synchronized static void staticFun2() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        svar++;
        String curName = Thread.currentThread().getName();
        Console.Companion.print(curName + "-fun2:"  + Synchronize.svar);
    }

    /**
     * synchronized 修饰的非静态变量，锁定的是类对象的一个实例，多线程中同一个调用同一个实例的此方法互斥，非同一个实例的方法不互斥
     */
    public synchronized void fun() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        svar++;
        String curName = Thread.currentThread().getName();
        Console.Companion.print(curName + ":"  + Synchronize.svar);
    }

    public static void main(String[] args) {
        Synchronize.svar = 0;
        Synchronize synchronize = new Synchronize();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            new Thread(() -> {
//                不同的synchronized static方法互斥
                if (index % 2 == 0) {
                    Synchronize.staticFun2();
                    return;
                }

//                测试 synchronized static 方法互斥
                Synchronize.staticFun();

                //测试不同类实例的非静态synchronized方法互斥
//                new Synchronize().fun();

                //同一个实例的非静态synchronized方法互斥
//                synchronize.fun();
            }).start();
        }
    }
}

