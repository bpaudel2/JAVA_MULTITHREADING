package demo.threadsafety;
import java.util.Scanner;

/*
 * Two problems if more than one thread sharing data
 * 1) Data being cached
 * 2) Thread leaving
 */
class Processor extends Thread{
	//using volatile for Thread safety because two thread are using 
	//running variable, The volatile prevents thread caching variable.
	private volatile boolean running = true;
	public void run() {
		while(running) {
			System.out.println("Hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		running = false;
	}
}
public class App {
	public static void main(String[] args) {
		Processor proc1 = new Processor();
		proc1.start();//running in separate thread
		System.out.println("Press return to stop...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine(); 
		proc1.shutdown();//running in main thread
		
	}

}
