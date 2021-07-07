package goldtransferSamaphore;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Transporter implements Runnable {
	private Cart cart;
	private Semaphore semaphoreL;
	private Semaphore semaphoreT;
	private Semaphore semaphoreU;
	private Interchanger<Cart> interchanger;
	private RunChecker rc;
	private boolean directionLToU = true;

	
	public Transporter(Cart cart, Semaphore semaphoreL, Semaphore semaphoreT, Semaphore semaphoreU,
			Interchanger<Cart> interchanger, RunChecker rc) {
		this.cart = cart;
		this.semaphoreL = semaphoreL;
		this.semaphoreT = semaphoreT;
		this.semaphoreU = semaphoreU;
		this.interchanger = interchanger;
		this.rc = rc;
	}

	private void transport() {
		try {
			semaphoreT.acquire();
			cart = interchanger.getCart();
			System.out.println("Transport start");
			TimeUnit.SECONDS.sleep(5);
			System.out.println("Transport end");
			interchanger.setCart(cart);
			cart = null;
			semaphoreU.release();
			semaphoreT.acquire();
			cart = interchanger.getCart();
			System.out.println("Transport start");
			TimeUnit.SECONDS.sleep(5);
			System.out.println("Transport end");
			interchanger.setCart(cart);
			cart = null;
			semaphoreL.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void transport(Semaphore s1, Semaphore s2) {
		try {
			s1.acquire();
			cart = interchanger.getCart();
			System.out.println("Transport start");
			TimeUnit.SECONDS.sleep(5);
			System.out.println("Transport end");
			interchanger.setCart(cart);
			cart = null;
			s2.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			transport();
			if (directionLToU) {
				transport(semaphoreT, semaphoreU);
			} else {
				transport(semaphoreT, semaphoreL);
			}
			directionLToU = !directionLToU;
			if (!rc.isRunning()) {
				transport(semaphoreT, semaphoreL);
				break;
			}
		}

	}

}
