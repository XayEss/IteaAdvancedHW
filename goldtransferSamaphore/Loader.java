package goldtransferSamaphore;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Loader implements Runnable {
	private GoldHeap goldHeap;
	private Cart cart;
	private Semaphore semaphoreL;
	private Semaphore semaphoreT;
	private Interchanger<Cart> interchanger;
	private RunChecker rc;
	private int loadPower = 3;

	public Loader(GoldHeap goldHeap, Cart cart, Semaphore semaphore, Semaphore semaphoreT, Interchanger<Cart> interchanger,
			RunChecker rc) {
		this.goldHeap = goldHeap;
		this.cart = cart;
		this.semaphoreL = semaphore;
		this.semaphoreT = semaphoreT;
		this.interchanger = interchanger;
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
				interchanger.setCart(cart);
				cart = null;
				semaphoreT.release();
				semaphoreL.acquire();
				cart = interchanger.getCart();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Loader finish");
	}
}
