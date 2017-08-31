import java.util.concurrent.TimeUnit;

public class DeadLockDemo
{
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args)
    {
        deadLockDemo();
    }

    public static void deadLockDemo()
    {
        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                synchronized (lock1)
                {
                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e)
                    {
                        e.getStackTrace();
                    }
                    synchronized (lock2)
                    {
                        System.out.println("In thread 1");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                synchronized (lock2)
                {
                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e)
                    {
                        e.getStackTrace();
                    }
                    synchronized (lock1)
                    {
                        System.out.println("In thread 2");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
