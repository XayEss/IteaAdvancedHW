package pudgelocale;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PudgePhrases extends JFrame {
	private static final String SAVELOC = "StateSave.txt";
	private ImageIcon ii;
	private JButton buttonEn;
	private JButton buttonRu;
	private JButton buttonFr;
	private JButton save;
	private JButton load;
	private JLabel phrase1;
	private JLabel phrase2;
	private JLabel phrase3;
	private PudgeState pudgeState;
	private PudgeStateController psc;

	public PudgePhrases() {
		super("Pudge");
		pudgeState = new PudgeState();
		psc = new PudgeStateController(pudgeState); 
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMainView();
		updatePhrases();
		initButtons();
		setVisible(true);
	}
	
//	public static void main(String[] args) {
//		PudgePhrases pf = new PudgePhrases();
//
//	}

	private void setMainView() {
		JPanel contents = new JPanel();
		contents.setAlignmentX(RIGHT_ALIGNMENT);
		JPanel leftContents = new JPanel();
		JPanel rightContents = new JPanel(new GridBagLayout());
		rightContents.setAlignmentX(CENTER_ALIGNMENT);
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
		ii = new ImageIcon(getClass().getResource("/pudge avatar.gif"));
		buttonEn = new JButton("EN");
		buttonFr = new JButton("FR");
		buttonRu = new JButton("RU");
		phrase1 = new JLabel("1. " + pudgeState.getPhrase1());
		phrase2 = new JLabel("2. " + pudgeState.getPhrase2());
		phrase3 = new JLabel("3. " + pudgeState.getPhrase3());
		leftContents.add(new JLabel(ii), gbc);
		rightContents.add(buttonEn, gbc);
		rightContents.add(buttonFr, gbc);
		rightContents.add(buttonRu, gbc);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridy++;
		gbc.gridy++;
		gbc.gridwidth = 4;
		rightContents.add(phrase1, gbc);
		gbc.gridy++;
		rightContents.add(phrase2, gbc);
		gbc.gridy++;
		rightContents.add(phrase3, gbc);
		gbc.gridy++;
		save = new JButton("Save");
		rightContents.add(save, gbc);
		gbc.gridy++;
		load = new JButton("Load");
		rightContents.add(load, gbc);
		contents.add(leftContents);
		contents.add(rightContents);
		setContentPane(contents);
	}

	private void changeLocale(String lang, String country) {
		psc.setLocale(lang, country);
		updatePhrases();
	}
	
	private void updatePhrases() {
		phrase1.setText("1. " + pudgeState.getPhrase1());
		phrase2.setText("2. " + pudgeState.getPhrase2());
		phrase3.setText("3. " + pudgeState.getPhrase3());
	}

	private void initButtons() {
		buttonEn.addActionListener((e)->changeLocale("en", "EN"));

		buttonFr.addActionListener((e)->changeLocale("fr", "FR"));

		buttonRu.addActionListener((e)->changeLocale("ru", "RU"));
		
		save.addActionListener((e)->save());
		
		load.addActionListener((e)->load());
	}
	
	public void setPudgeState(PudgeState pudgeState) {
		this.pudgeState = pudgeState;
	}
	

	private void save() {
		FileOutputStream fos = null;
		try {
		fos = new FileOutputStream(SAVELOC);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(pudgeState);
		fos.write(baos.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void load() {
		FileInputStream fis = null;
		PudgeState ps;
		try {
		fis = new FileInputStream(SAVELOC);
		ByteArrayInputStream bais = new ByteArrayInputStream(fis.readAllBytes());
		ObjectInputStream ois = new ObjectInputStream(bais);
		ps = (PudgeState) ois.readObject();
		setPudgeState(ps);
		psc.setPs(ps);
		updatePhrases();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
