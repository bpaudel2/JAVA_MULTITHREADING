package com.Deadlock;


public class Main {
	public static void main(String[] args) throws InterruptedException {
		
		final Runner runner = new Runner();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					runner.firstThread();;
				}catch(InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					runner.secondThread();
				}catch(InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		
		runner.finished();
	}

}