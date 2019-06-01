package lock.reentrant;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	//every object in java has wait and notify method in them.
	private int count = 0;
	private Lock lock = new ReentrantLock();//this one calls await and signal, very similar to wait and notify.
	private Condition cond = lock.newCondition();
	private void increment() {
		for(int i=0;i<10000; i++) {
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException{
		lock.lock();
		System.out.println("Waiting");
		cond.await();//unlocks the lock so that another thread can get in and lock it
		System.out.println("woken up");
		increment();//if this throws an exception lock.unlock will never be called, so use try catch around instead
		lock.unlock();
	}

	public void secondThread() throws InterruptedException{
		Thread.sleep(1000);
		lock.lock();
		System.out.println("Press the return key");
		new Scanner(System.in).nextLine();
		System.out.println("Got returned key");
		cond.signal();//remeber to call unlock after this
		try {
			increment();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			lock.unlock();
		}
		
	}
	
	public void finished() {
		System.out.println("Count is :"+count);
	}
}
