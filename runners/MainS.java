package runners;

import java.util.concurrent.Semaphore;

public class MainS {

	public static void main(String[] args) {
		int numberOfRunners = 3;
		Semaphore semaphore = new Semaphore(numberOfRunners);
		new RunnerS("Andrew", semaphore);
		new RunnerS("Markov", semaphore);
		new RunnerS("Akakiy", semaphore);
		try {
			semaphore.acquire(numberOfRunners);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(RunnerS.winner);
	}

}
