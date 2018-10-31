package com.lovelz.lzlearncenter.thread.know;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lovelz.lzlearncenter.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 线程的状态

 （1）. 新建状态（New）：新创建了一个线程对象。
 （2）. 就绪状态（Runnable）：线程对象创建后，其他线程调用了该对象的start()方法。该状态的线程位于可运行线程池中，变得可运行，
        等待获取CPU的使用权。
 （3）. 运行状态（Running）：就绪状态的线程获取了CPU，执行程序代码。
 （4）. 阻塞状态（Blocked）：阻塞状态是线程因为某种原因放弃CPU使用权，暂时停止运行。直到线程进入就绪状态，才有机会转到运行状态。
        阻塞的情况分三种：

        等待阻塞：运行的线程执行wait()方法，JVM会把该线程放入等待池中。
        同步阻塞：运行的线程在获取对象的同步锁时，若该同步锁被别的线程占用，则JVM会把该线程放入锁池中。
        其他阻塞：运行的线程执行sleep()或join()方法，或者发出了I/O请求时，JVM会把该线程置为阻塞状态。当sleep()状态超时、
                join()等待线程终止或者超时、或者I/O处理完毕时，线程重新转入就绪状态。
 （5）. 死亡状态（Dead）：线程执行完了或者因异常退出了run()方法，该线程结束生命周期。

 * @author lovelz
 * @date on 2018/10/23.
 */
public class ThreadKnowActivity extends AppCompatActivity {

    private static final String TAG = ThreadKnowActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_thread_know);

        //启动方式一
        new TestThread().start();

        //启动方式二(推荐)
        new Thread(new TestRunnable()).start();

        //启动方式三
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new TestCallable());
        try {
            Log.d(TAG, String.valueOf(future.get()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class TestThread extends Thread {
        @Override
        public void run() {
            Log.d(TAG, "启动方式一");
        }
    }

    private class TestRunnable implements Runnable {
        @Override
        public void run() {
            Log.d(TAG, "启动方式二");
        }
    }

    /**
     实现Callable接口，重写call()方法
     Callable接口实际是属于Executor框架中的功能类，Callable接口与Runnable接口的功能类似，但提供了比Runnable更强大的功能，
     主要表现为以下的3点：
     （1）Callable可以在任务接受后提供一个返回值，Runnable无法提供这个功能。
     （2）Callable中的call()方法可以抛出异常，而Runnable的run()方法不能抛出异常。
     （3）运行Callable可以拿到一个Future对象，Future对象表示异步计算的结果，他提供了检查计算是否完成的方法。
        由于线程属于异步计算模型，因此无法从别的线程中得到函数的返回值，在这种情况下就可以使用Future来监视目标线程调用call()方法的情况，
        但调用Future的get()方法以获取结果时，当前线程就会阻塞，直到call()方法的返回结果。
     */
    private class TestCallable implements Callable {
        @Override
        public Object call() {
            return "启动方式三";
        }
    }
}
