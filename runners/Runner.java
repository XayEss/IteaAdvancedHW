package runners;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Runner implements Runnable {
	private String name;
	private CountDownLatch cdl;
	public static String winner = "";
	private volatile static int place = 0;

	public Runner(String name, CountDownLatch cdl) {
		this.cdl = cdl;
		this.name = name;
		new Thread(this).start();
	}
	
	private  void addPlace() {
		synchronized (winner) {
		winner += ++place + ": " + name + " ";
		}
	}

	@Override
	public void run() {
		String length = "";
		while (length.length() < 20) {
			int step = (int) (Math.random() * 2 + 1);
			length += "_".repeat(step);
			System.out.println(name + " " + length);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		addPlace();
		cdl.countDown();
	}

}
