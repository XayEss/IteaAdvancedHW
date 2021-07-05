package goldmines;

import java.util.ArrayList;

public class Barracks extends Thread{
	private GoldMine mine;
	private ArrayList<Miner> miners;

	public Barracks(GoldMine mine, ArrayList<Miner> miners) {
		this.mine = mine;
		this.miners = miners;
	}
	
	public void run() {
		createMiner();
	}

	public void createMiner() {
		while (mine.getMaxGold() > 0) {
			Miner miner = new Miner(mine, "Miner" + miners.size() + 1);
			miners.add(miner);
			miner.start();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
