package runners;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class RunnerS implements Runnable{
	private String name;
	private Semaphore semaphore;
	public static String winner = "";
	private volatile static int place = 0;

	public RunnerS(String name, Semaphore semaphore) {
		this.semaphore = semaphore;
		this.name = name;
		new Thread(this).start();
	}
	
	private void addPlace() {
		synchronized (winner) {
		winner += ++place + ": " + name + " ";
		}
	}

	@Override
	public void run() {
		try {
			semaphore.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		semaphore.release();
	}
}
