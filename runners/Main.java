package runners;

import java.util.concurrent.CountDownLatch;

public class Main {

	public static void main(String[] args) {
		CountDownLatch cdl = new CountDownLatch(3);
		new Runner("Andrew", cdl);
		new Runner("Markov", cdl);
		new Runner("Akakiy", cdl);
		try {
			cdl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Runner.winner);
	}

}
