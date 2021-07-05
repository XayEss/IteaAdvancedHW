package goldmines;

public class GoldMine {
	
	private int maxGold = 1000;
	
	public synchronized void digGold() {
		maxGold -= 3;
		//printGold();		
	}

	public int getMaxGold() {
		return maxGold;
	}

	public void setMaxGold(int maxGold) {
		this.maxGold = maxGold;
	}
	
	public void printGold() {
		System.out.println(maxGold);

	}

}
