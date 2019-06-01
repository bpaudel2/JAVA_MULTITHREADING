package lowlevel.synchronization;


public class Application {
	public static void main(String[] args) throws InterruptedException {
		
		final Worker processor = new Worker();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.produce();
				}catch(InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					processor.consume();
				}catch(InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

}

