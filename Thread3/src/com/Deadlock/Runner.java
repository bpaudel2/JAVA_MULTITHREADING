package com.Deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//using tryLock to avoid deadlock
public class Runner {

	private Account acc1 = new Account();
	private Account acc2 = new Account();
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	private void acquiredLock(Lock firstLock, Lock secondLock) throws InterruptedException {
		while(true) {
			//try to acquire the lock
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			try {
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			}finally {
				if(gotFirstLock&&gotSecondLock) {
					return;
				}
				if(gotFirstLock) {
					firstLock.unlock();
				}
				if(gotSecondLock) {
					secondLock.unlock();
				}
				
			}
			//locks not acquired
			Thread.sleep(1);
		}
	}
	
	public void firstThread() throws InterruptedException{
		Random random = new Random();
		//this can lead us to deadlock
		//to avoid: always lock your lock in same order
		/*
		lock1.lock();
		lock2.lock();
		*/
		acquiredLock(lock1,lock2);
		try {
			for(int i=0; i<10000; i++) {
				Account.transfer(acc1,acc2,random.nextInt(100));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			lock1.unlock();
			lock2.unlock();
		}
		
	}
	
	public void secondThread() throws InterruptedException{
		Random random = new Random();
		/*
		lock2.lock();
		lock1.lock();
		*/
		acquiredLock(lock2,lock1);
		try {
			for(int i=0; i<10000; i++) {
				Account.transfer(acc2,acc1,random.nextInt(100));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			lock1.unlock();
			lock2.unlock();
		}
	}
	
	public void finished() {
		System.out.println("Account 1 balance "+acc1.getBalance());
		System.out.println("Account 2 balance "+acc2.getBalance());
		System.out.println("Total balance "+ (acc1.getBalance() + acc2.getBalance()));
	}
}

