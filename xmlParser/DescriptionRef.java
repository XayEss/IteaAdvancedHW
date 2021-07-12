package xmlParser;

public class DescriptionRef {
	public String descriptinRef;

	public String getDescriptinRef() {
		return descriptinRef;
	}

	public void setDescriptinRef(String descriptinRef) {
		this.descriptinRef = descriptinRef;
	}
	
	public static DescriptionRef newInstance() {
		return new DescriptionRef();
	}

	@Override
	public String toString() {
		return "DescriptionRef [descriptinRef=" + descriptinRef + "]";
	}
	
}
