package demo.threadsafety1;

public class App {
	
	private int count = 0;
	
	// This solves the problem because with synchronized keyword,
	// the increment method is atomic. So, there is no race between 
	// thread for trying to read and write count variable
	// Every object in JAVA has intransic lock or monitor lock. If you call synchronized of an object
	// ,you have to acquire intransic lock and only one thread can acquire intransic lock at a time. If one 
	// thread acquire the intransic lock and second thread trying to run the method has to wait until the first
	// thread removes the intransic lock on object. 
	public synchronized void increment() {
		count++;
	}
	
	public static void main(String[] args) {
		App app = new App();
		app.doWork();
	}

	public void doWork() {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<10000; i++) {
					increment();
				}
			}
		});
		
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				for(int i=0; i<10000; i++) {
					increment(); //This is not atomic, has three steps
				}
			}
		});
		
		//These returns before other two finishes
		t1.start(); //returns immediately, executes in main thread
		t2.start();//returns immediately, executes in main thread
		
		//join waits for thread to finish,
		//This still does not give expected output because there are two threads
		//reading and writing the same variable. The volatile keyword does not help 
		//here because the cause is variable not leaving Thread, not caused by caching.
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Count is "+count);
		
	}
}
