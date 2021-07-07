package goldtransfer;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Unloader implements Runnable {
	private Cart cart;
	private Exchanger<Cart> exTU;
	private RunChecker rc;
	private int unloadPower = 2;

	public Unloader(Cart cart, Exchanger<Cart> exTU, RunChecker rc) {
		super();
		this.cart = cart;
		this.exTU = exTU;
		this.rc = rc;
	}

	private void unLoad() {

	}

	@Override
	public void run() {
		while (true) {
			try {
				cart = exTU.exchange(null);
				System.out.println("Unload start");
				while (!cart.isCartEmpty()) {
					cart.unloadCart(unloadPower);
					System.out.println(cart.getGoldLoaded());
					TimeUnit.SECONDS.sleep(1);
				}
				System.out.println("Unload end");
				cart = exTU.exchange(cart);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!rc.isRunning()) {
				break;
			}
		}

	}

}
