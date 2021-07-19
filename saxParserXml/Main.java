package saxParserXml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) {
		SAXParserFactory saxPF = SAXParserFactory.newInstance();
		SaxConfig saxConfig = new SaxConfig();
		try {
			SAXParser saxP = saxPF.newSAXParser();
			File file = new File("Resources/pom.xml");
			saxP.parse(file, saxConfig);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
