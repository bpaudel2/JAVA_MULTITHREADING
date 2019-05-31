package com.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{
	
	private int id;
	
	public Processor(int id) {
		this.id = id;
	}
	public void run() {
		System.out.println("Starting "+id);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed "+id);
	}
}
//Thread pool is a way of managing lots of thread at the same time
public class App {
	
	public static void main(String[] args) {
		
		//Thread pool is like having a number of workers in a factory
		ExecutorService executor = Executors.newFixedThreadPool(5);
		
		for(int i=0; i<5; i++) {
			executor.submit(new Processor(i));
		}
		
		//This will wait for all the thread to complete doing what they are doing 
		//and then shutdown
		executor.shutdown();
		System.out.println("All task submitted.");
		
		//This will wait for the thread to finish until the timeout happens
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All task completed.");
	}

}
