package saxParserXml;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class SaxTest {
	SaxConfig saxConfig;
	private static final String NODE_NAME1 = "version";
	private static final String NODE_NAME2 = "name";
	private static final String NODE_PROPERTIES = "properties";
	private static final String NODE_DEPENDENCY = "dependency";
	private static final String NODE_PLUGIN = "plugin";
	private static final String NODE_CONFIG = "configuration";
	private static final String NODE_ARCHIVE = "archive";
	private static final String NODE_EXECUTION = "execution";
	private static final String PARENT_NODE = "dependency";
	
	@Before
	public void setUp() {
		saxConfig = new SaxConfig();
	}

	@Test
	public void testStartElementNode() {
		try {
			saxConfig.startElement("blala", "somename", NODE_NAME1, null);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(NODE_NAME1, saxConfig.getCurrentNode());
	}
	
	@Test
	public void testStartElementParentNode() {
		try {
			saxConfig.startElement("blala", "somename", PARENT_NODE, null);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(PARENT_NODE, saxConfig.getCurrentParentNode());
	}
	
	@Test
	public void testCharacters() {
		String expected = "BEST";
		char[] ch = expected.toCharArray();
		try {
			saxConfig.startElement("blala", "somename", NODE_NAME2, null);
			saxConfig.characters(ch, 0, ch.length);
		} catch (SAXException e) {
			e.printStackTrace();
		}
		assertEquals(expected, saxConfig.getProject().getName());
	}
	
	@Test
	public void testCharactersProperties() {
		String expected = "UTF-8";
		char[] ch = expected.toCharArray();
		try {
			saxConfig.startElement("blala", "somename", NODE_PROPERTIES, null);
			saxConfig.startElement("blala", "somename", "project.build.sourceEncoding", null);
			saxConfig.characters(ch, 0, ch.length);
		} catch (SAXException e) {
			e.printStackTrace();
		}
		assertEquals(expected, saxConfig.getProject().getProperties().getSourceEncoding());
	}
	
	@Test
	public void testCharactersDependencies() {
		String expected = "Spring";
		char[] ch = expected.toCharArray();
		try {
			saxConfig.startElement("blala", "somename", NODE_DEPENDENCY, null);
			saxConfig.startElement("blala", "somename", "groupId", null);
			saxConfig.characters(ch, 0, ch.length);
		} catch (SAXException e) {
			e.printStackTrace();
		}
		assertEquals(expected, saxConfig.getProject().getDependencies().get(0).getGroupId());
	}
	
	@Test
	public void testCharactersPlugins() {
		String expected = "Sample";
		char[] ch = expected.toCharArray();
		try {
			saxConfig.startElement("blala", "somename", NODE_PLUGIN, null);
			saxConfig.startElement("blala", "somename", "artifactId", null);
			saxConfig.characters(ch, 0, ch.length);
		} catch (SAXException e) {
			e.printStackTrace();
		}
		assertEquals(expected, saxConfig.getProject().getBuild().getPlugins().get(0).getArtifactId());
	}
	
	@Test
	public void testCharactersPluginsDescription() {
		String expected = "Object";
		char[] ch = expected.toCharArray();
		try {
			saxConfig.startElement("blala", "somename", NODE_PLUGIN, null);
			saxConfig.startElement("blala", "somename", NODE_CONFIG, null);
			saxConfig.startElement("blala", "somename", "descriptorRef", null);
			saxConfig.characters(ch, 0, ch.length);
		} catch (SAXException e) {
			e.printStackTrace();
		}
		assertEquals(expected, saxConfig.getProject().getBuild().getPlugins().get(0).getConfig().getDescriptionRefs().get(0).getDescriptinRef());
	}
	
	@Test
	public void testCharactersPluginsManifest() {
		String expected = "Prototype";
		char[] ch = expected.toCharArray();
		try {
			saxConfig.startElement("blala", "somename", NODE_PLUGIN, null);
			saxConfig.startElement("blala", "somename", NODE_CONFIG, null);
			saxConfig.startElement("blala", "somename", NODE_ARCHIVE, null);
			saxConfig.startElement("blala", "somename", "mainClass", null);
			saxConfig.characters(ch, 0, ch.length);
		} catch (SAXException e) {
			e.printStackTrace();
		}
		assertEquals(expected, saxConfig.getProject().getBuild().getPlugins().get(0).getConfig().getArchive().getManifest().getMainClass());
	}
	
	@Test
	public void testCharactersPluginsExecution() {
		String expected = "Prototype";
		char[] ch = expected.toCharArray();
		try {
			saxConfig.startElement("blala", "somename", NODE_PLUGIN, null);
			saxConfig.startElement("blala", "somename", NODE_EXECUTION, null);
			saxConfig.startElement("blala", "somename", "id", null);
			saxConfig.characters(ch, 0, ch.length);
		} catch (SAXException e) {
			e.printStackTrace();
		}
		assertEquals(expected, saxConfig.getProject().getBuild().getPlugins().get(0).getExecutions().get(0).getId());
	}
	
	@Test
	public void testEndDocument() {
		String expected = "null;null;null;1.0.0;yahoo;null;null;null;null";
		try (InputStream is = new FileInputStream("Resources/project.csv")){
			saxConfig.startElement("blala", "somename", NODE_NAME1, null);
			String version = "1.0.0";
			char[] ch = version.toCharArray();
			saxConfig.characters(ch, 0, ch.length);
			saxConfig.startElement("blala", "somename", NODE_NAME2, null);
			String name = "yahoooo";
			char[] ch1 = name.toCharArray();
			saxConfig.characters(ch1, 0, ch.length);
			saxConfig.endDocument();
			byte[] projectData = is.readAllBytes();
			String projectString = new String(projectData, StandardCharsets.UTF_8);
			System.out.println(saxConfig.getProject().toString());
			assertEquals(expected, projectString);
		} catch (IOException | SAXException e) {
			e.printStackTrace();
		}
	}

}
