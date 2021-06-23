package pudgelocale;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Locale;
import java.util.ResourceBundle;

public class PudgeStateController{
	private Locale locale;
	private ResourceBundle rb;
	private PudgeState ps;
	
	public PudgeStateController(PudgeState ps) {
		this.ps = ps;
		setLocale("en", "EN");
	}

	public void setLocale(String lang, String country) {
		locale = new Locale(lang, country);
		updateResourceBundle();
		changePhrases();
	}
	
	private void updateResourceBundle() {
		rb = ResourceBundle.getBundle("Phrase", locale);
	}
	
	public void changePhrases() {
		ps.setPhrase1(rb.getString("phrase1"));
		ps.setPhrase2(rb.getString("phrase2"));
		ps.setPhrase3(rb.getString("phrase3"));
	}

	public void setPs(PudgeState ps) {
		this.ps = ps;
	}
	
}
