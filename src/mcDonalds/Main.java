package mcDonalds;

public class Main {

	public static void main(String[] args) {
		CashBox cb = new CashBox();
		Client c1 = new Client(cb);
		Client c2 = new Client(cb);
		Client c3 = new Client(cb);
		Client c4 = new Client(cb);
		Client c5 = new Client(cb);
		c1.start();
		c2.start();
		c3.start();
		c4.start();
		c5.start();
		while(cb.isCashBoxOpen()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(cb.isCashBoxOpen());
		}
	}

}
