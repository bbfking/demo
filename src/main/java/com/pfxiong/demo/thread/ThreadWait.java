package com.pfxiong.demo.thread;

import org.junit.Test;

/**
 * @author: xiongpengfei
 * @datetime: 2020/3/26 19:24
 * @description:
 */
public class ThreadWait {

    private volatile Object lock1 = new Object();
    private volatile int i = 0;
    Thread thread1 = new Thread(){
        @Override
        public void run() {
            while (i <= 100) {
                synchronized (lock1) {
                    System.out.println("T1:" + i++);
                    lock1.notify();
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    };

    Thread thread2 = new Thread(){
        @Override
        public void run() {

            while (i <= 100) {
                synchronized (lock1) {
                    System.out.println("T2:" + i++);
                    lock1.notify();
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    @Test
    public void testRun() throws InterruptedException {
        thread1.start();
        thread2.start();
        Thread.sleep(30000);
    }
}