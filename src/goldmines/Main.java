package goldmines;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Miner> minerList = new ArrayList<>();
		GoldMine gm = new GoldMine();
		Miner miner1 = new Miner(gm, "Miner1");
		Miner miner2 = new Miner(gm, "Miner2");
		Miner miner3 = new Miner(gm, "Miner3");
		minerList.add(miner1);
		minerList.add(miner2);
		minerList.add(miner3);
		Barracks barracks = new Barracks(gm, minerList);
		miner1.start();
		miner2.start();
		miner3.start();
		barracks.start();
		while(gm.getMaxGold() > 0) {
			System.out.println("Amount of gold: " + (1000-gm.getMaxGold()));
			System.out.println("Amount of miners: " + minerList.size());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
