package mcDonalds;

public class Client extends Thread {
	public CashBox cb;
	
	public Client(CashBox cb) {
		this.cb = cb;
	}

	public void run() {
		makeAnOrder();
	}

	public void makeAnOrder() {
		if(cb.isCashBoxOpen()) {
		cb.serveClient();
		}
	}
}
