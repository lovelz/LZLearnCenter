package com.lovelz.lzlearncenter.thread.synchronization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lovelz
 * @date on 2018/10/23.
 */
public class Bank {

    private double[] accounts;
    private Lock bankLock;
    private Condition bankCondition;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        bankLock = new ReentrantLock();

        //条件对象
        bankCondition = bankLock.newCondition();

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = initialBalance;
        }
    }

    public void transfer(int from, int to, int amount) throws InterruptedException {
        bankLock.lock();
        try {
            while (accounts[from] < amount) {
                //阻塞当前线程，并放弃锁
                bankCondition.await();
            }

        } finally {
            bankLock.unlock();
        }
    }

}
