package com.interruptedthread;

import java.util.Random;

public class App {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Startng...");
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				Random ran = new Random();
				for(int i=0; i<1E7;i++) {
					/*
					if(Thread.currentThread().isInterrupted()) {
						System.out.println("Interruped");
						break;
					}
					*/
					
					try {
						Thread.sleep(1);//this will detect interrupt call and throw an exception
					} catch (InterruptedException e) {
						System.out.println("Interrrupted");
						break;
					}
					Math.sin(ran.nextDouble());
				}
			}
		});
		t1.start();
		Thread.sleep(500);
		t1.interrupt();//thread.interrupt does not actually stop the thread, it just sets the flag
		t1.join();
		System.out.println("Finished");
	}
}
