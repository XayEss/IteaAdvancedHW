package goldtransferSamaphore;

public class Interchanger<T> {
	
	private T object;
	
	public T getCart() {
		T obj = object;
		object = null;
		return obj;
	}
	public void setCart(T object) {
		this.object = object;
	}
	
}
