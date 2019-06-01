package wait.notify;

import java.util.Scanner;

public class Worker {
	
	public void produce() throws InterruptedException{
		synchronized(this){
			System.out.println("Producer thread running ....");
			//there is also wait with timeout, the wait is
			//very resource efficient and you can only call it 
			//within synchronized block, wait() loosen the lock 
			wait(); 
			System.out.println("Resumed.");
		}
	}
	
	public void consume() throws InterruptedException{
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		synchronized(this) {
			System.out.println("Waiting for return key");
			scanner.nextLine();
			System.out.println("Retrun key pressed");
			//notify can only be called within synchronized code block
			//notify one other thread to waiting on lock
			//can use notifyall to notify all the thread that are waiting
			notify();
			Thread.sleep(5000);
		}
	}
}
