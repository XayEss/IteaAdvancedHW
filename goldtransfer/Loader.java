package goldtransfer;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class Loader implements Runnable {
	private GoldHeap goldHeap;
	private Cart cart;
	private Exchanger<Cart> exLT;
	private RunChecker rc;
	private int loadPower = 3;

	public Loader(GoldHeap goldHeap, Cart cart, Exchanger<Cart> exLT, RunChecker rc) {
		super();
		this.goldHeap = goldHeap;
		this.cart = cart;
		this.exLT = exLT;
		this.rc = rc;
	}

	@Override
	public void run() {
		while (!goldHeap.isEmpty()) {
			System.out.println("Loader start");
			while (!cart.isCartLoaded() && !goldHeap.isEmpty()) {
				cart.loadCart(goldHeap.takeGold(loadPower));
				System.out.println(cart.getGoldLoaded());
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				System.out.println("Loader end");
				System.out.println("Gold left: " + goldHeap.getGold());
				if(goldHeap.isEmpty()) {
					rc.setRunning(false);
				}
				cart = exLT.exchange(cart);
				cart = exLT.exchange(null);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Loader finish");
	}
}
