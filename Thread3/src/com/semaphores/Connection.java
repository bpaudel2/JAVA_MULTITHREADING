package com.semaphores;

import java.util.concurrent.Semaphore;

public class Connection {

	private static Connection instance = new Connection();
	private int connection = 0;
	//using Semaphore to limit connection to only 10
	//before it was 200 while running from 200 thread
	//private Semaphore sem = new Semaphore(10,true);
	//setting it to true means whichever thread to call acquire first will be the 
	//first one to get permit when permit is available. So, you probably want to be 
	//true
	private Semaphore sem = new Semaphore(10);
	private Connection() {
		
	}
	
	protected static Connection getInstance() {
		return instance;
	}
	
	public void connect() {
		try {
			sem.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			doConnect();
		}finally {
			sem.release();
		}
	}
	
	public void doConnect() {
		
		synchronized(this){
			connection++;
			System.out.println("Current connection "+connection);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		synchronized(this){
			connection--;
		}
	}
}

