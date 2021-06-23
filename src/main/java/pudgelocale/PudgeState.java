package pudgelocale;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Locale;
import java.util.ResourceBundle;

public class PudgeState implements Externalizable{
	private String phrase1;
	private String phrase2;
	private String phrase3;
	
	public PudgeState() {
	}	

	public String getPhrase1() {
		return phrase1;
	}

	public String getPhrase2() {
		return phrase2;
	}

	public String getPhrase3() {
		return phrase3;
	}

	public void setPhrase1(String phrase1) {
		this.phrase1 = phrase1;
	}

	public void setPhrase2(String phrase2) {
		this.phrase2 = phrase2;
	}

	public void setPhrase3(String phrase3) {
		this.phrase3 = phrase3;
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(phrase1);
		out.writeObject(phrase2);
		out.writeObject(phrase3);
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		phrase1 = (String)in.readObject();
		phrase2 = (String)in.readObject();
		phrase3 = (String)in.readObject();
	}

}
