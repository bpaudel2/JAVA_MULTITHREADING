package demo3;

public class App {
	public static void main(String[] args) {
		//We can use anonymous class to run one single method of a class.
		Thread t1 = new Thread(new Runnable() {
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
		});
		t1.start();
	}
}
