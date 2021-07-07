package goldtransfer;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		GoldHeap gh = new GoldHeap();
		Cart cart = new Cart();
		Exchanger<Cart> exLT = new Exchanger<>();
		Exchanger<Cart> exTU = new Exchanger<>();
		RunChecker rc = new RunChecker();
		Loader loader = new Loader(gh, cart, exLT, rc);
		Transporter transporter = new Transporter(cart, exLT, exTU, rc);
		Unloader unloader = new Unloader(cart, exTU, rc);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.execute(loader);
		executor.execute(transporter);
		executor.execute(unloader);
		executor.shutdown();
	}

}
