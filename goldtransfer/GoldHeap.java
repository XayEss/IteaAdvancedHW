package goldtransfer;

public class GoldHeap {

	private int gold = 100;

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		if (this.gold - gold < 0) {
			gold = 0;
		} else {
			this.gold = gold;
		}
	}
	
	public boolean isEmpty() {
		if(gold == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public int takeGold(int gold) {
		if (this.gold - gold < 0) {
			int returnGold = gold - this.gold;
			this.gold = 0;
			return gold - returnGold;
		} else {
			this.gold -= gold;
			return gold;
		}
	}

}
