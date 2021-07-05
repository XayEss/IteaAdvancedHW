package mcDonalds;

public class CashBox {
	private Client client;
	private boolean isCashBoxOpen = true;

	public synchronized void serveClient() {
		if (isCashBoxOpen) {
			int time = (int) (Math.random() * 6 + 3);
			try {
				Thread.sleep(time*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			checkCashBoxClosing();
		}
	}

	public void checkCashBoxClosing() {
		int time = (int) (Math.random() * 10 + 1);
		if (time > 5) {
			closeCashBox();
		}
	}

	public void closeCashBox() {
		isCashBoxOpen = false;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public boolean isCashBoxOpen() {
		return isCashBoxOpen;
	}

	public void setCashBoxOpen(boolean isCashBoxOpen) {
		this.isCashBoxOpen = isCashBoxOpen;
	}

}
