package goldtransferSamaphore;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Unloader implements Runnable {
	private Cart cart;
	private Semaphore semaphoreT;
	private Semaphore semaphoreU;
	private Interchanger<Cart> interchanger;
	private RunChecker rc;
	private int unloadPower = 2;

	public Unloader(Cart cart, Semaphore semaphoreT, Semaphore semaphoreU, Interchanger<Cart> interchanger, RunChecker rc) {
		super();
		this.cart = cart;
		this.semaphoreT = semaphoreT;
		this.semaphoreU = semaphoreU;
		this.interchanger = interchanger;
		this.rc = rc;
	}

	@Override
	public void run() {
		while (true) {
			try {
				semaphoreU.acquire();
				cart = interchanger.getCart();
				System.out.println("Unload start");
				while (!cart.isCartEmpty()) {
					cart.unloadCart(unloadPower);
					System.out.println(cart.getGoldLoaded());
					TimeUnit.SECONDS.sleep(1);
				}
				System.out.println("Unload end");
				interchanger.setCart(cart);
				cart = null;
				semaphoreT.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(!rc.isRunning()) {
				break;
			}
		}

	}

}
