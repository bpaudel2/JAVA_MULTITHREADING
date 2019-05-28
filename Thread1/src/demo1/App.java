package demo1;
//to use multithread you need to extends Thread class or implements Runnable
class Runner extends Thread{

	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			System.out.println("Hello "+i);
			try {
				//static method of Thread class
				Thread.sleep(1000); //this makes the program sleep for 100 miliseconds
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
public class App {
	public static void main(String[] args) {
		/*
		We need to use start() method which will call run() method.
		If we use run() method, it will RUN run() method in main Thread of your application
		Using start() method, Thread class will look for run method and run that in its own special Thread 
		 */
		//They will run in parallel
		Runner runner1 = new Runner();
		runner1.start();
		
		Runner runner2 = new Runner();
		runner2.start();
	}
	
}
