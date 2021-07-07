package goldtransferSamaphore;

public class Cart {
	
	private int goldLoaded = 0;
	private int capacity = 6;
	private boolean isLastRide = false;
	
	public boolean isCartLoaded() {
		if(goldLoaded == capacity) {
			return true;
		}
		return false;
	}
	
	public boolean isCartEmpty() {
		if(goldLoaded == 0) {
			return true;
		}
		return false;
	}
	
	public void loadCart(int goldLoaded) {
		this.goldLoaded += goldLoaded;
	}
	
	public void unloadCart(int goldLoaded) {
		this.goldLoaded -= goldLoaded;
	}
	
	public int getGoldLoaded() {
		return goldLoaded;
	}
	public void setGoldLoaded(int goldLoaded) {
		this.goldLoaded = goldLoaded;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	

}
