package goldtransferSamaphore;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		GoldHeap gh = new GoldHeap();
		Cart cart = new Cart();
		Semaphore semaphoreL = new Semaphore(0);
		Semaphore semaphoreT = new Semaphore(0);
		Semaphore semaphoreU = new Semaphore(0);
		Interchanger<Cart> interchanger = new Interchanger<>();
		RunChecker rc = new RunChecker();
		Loader loader = new Loader(gh, cart, semaphoreL, semaphoreT, interchanger, rc);
		Transporter transporter = new Transporter(cart, semaphoreL, semaphoreT, semaphoreU, interchanger, rc);
		Unloader unloader = new Unloader(cart, semaphoreT, semaphoreU, interchanger, rc);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.execute(loader);
		executor.execute(transporter);
		executor.execute(unloader);
		executor.shutdown();
	}

}
