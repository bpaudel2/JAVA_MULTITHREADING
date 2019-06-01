package callable.future;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class App {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		//if you don't want the future to return, do the following 
		//you can use ? as a wild card for your return type and in callable use
		//Void like this
		//Future<?> future = executor.submit(new Callable<Void>() {
		Future<Integer> future = executor.submit(new Callable<Integer>() {
			public Integer call() throws Exception {
				Random random = new Random();
				int duration = random.nextInt(4000);
				if(duration>2000) {
					throw new IOException("Sleepign for too long.");
				}
				System.out.println("Starting..");
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Finished.");
				return duration;
			}
		});
		executor.shutdown();
		//even not using awaitTermination works because future.get waits until it is done anyway.
		try {
			executor.awaitTermination(1,TimeUnit.DAYS);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			//exception from call method will be throw here at future.get()s
			System.out.println("The result is: "+future.get());
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(e.getMessage());
			IOException ex = (IOException) e.getCause();
			System.out.println(ex.getMessage());
			e.printStackTrace();
		}
	}
}
