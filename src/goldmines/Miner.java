package goldmines;

public class Miner extends Thread{

	
	public String name;
	private GoldMine mine;

	public Miner(GoldMine mine, String name) {
		this.mine = mine;
	}
	
	@Override
	public void run() {
		digGold();
	}

	public void digGold() {
		while (mine.getMaxGold() > 0) {
			mine.digGold();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

}
