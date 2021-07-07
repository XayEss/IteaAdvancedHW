package goldtransfer;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Transporter implements Runnable {
	private Cart cart;
	private Exchanger<Cart> exLT;
	private Exchanger<Cart> exTU;
	private RunChecker rc;
	private boolean directionLToU = true;

	public Transporter(Cart cart, Exchanger<Cart> exLT, Exchanger<Cart> exTU, RunChecker rc) {
		super();
		this.cart = cart;
		this.exLT = exLT;
		this.exTU = exTU;
		this.rc = rc;
	}

	private void transport(Exchanger<Cart> first, Exchanger<Cart> second) {
		try {
			cart = first.exchange(null);
			System.out.println("Transport start");
			TimeUnit.SECONDS.sleep(5);
			System.out.println("Transport end");
			cart = second.exchange(cart);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			if (directionLToU) {
				transport(exLT, exTU);
			} else {
				transport(exTU, exLT);
			}
			directionLToU = !directionLToU;
			if (!rc.isRunning()) {
				transport(exTU, exLT);
				break;
			}
		}

	}

}
