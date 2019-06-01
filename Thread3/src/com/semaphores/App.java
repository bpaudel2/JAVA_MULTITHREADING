package com.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class App {
	//Semaphore is an object that maintain the count
	public static void main(String[] args) throws InterruptedException {
		Semaphore sem = new Semaphore(1);
		//release and acquire is more like lock and unlock but you can do it 
		//from any thread unlike lock and unlock
		//can be used to control access to some resources
		sem.release();
		sem.acquire();//acquire will wait until there are any to acquire
		sem.release();
		System.out.println("Available permits: "+sem.availablePermits());

		
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i=0; i<200; i++) {
			executor.submit(new Runnable() {

				@Override
				public void run() {
					
					Connection.getInstance().connect();
				}
				
			});
		}
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
	}
	

}
