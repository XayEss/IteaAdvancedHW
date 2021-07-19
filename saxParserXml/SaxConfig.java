package saxParserXml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxConfig extends DefaultHandler {
	private String currentParentNode;
	private String currentNode;
	Project project;
	Properties properties;
	Dependency dependency;
	Build build;
	Plugin plugin;
	Configuration configuration;
	Archive archive;
	Manifest manifest;
	Execution execution;
	private static final String PROPERTIES = "properties";
	private static final String DEPENDENCY = "dependency";
	private static final String PLUGIN = "plugin";

	public SaxConfig() {
		super();
		project = Project.newInstance();
		build = Build.newInstance();
		project.setBuild(build);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentNode = qName;
		if (qName.equals(PROPERTIES)) {
			currentParentNode = currentNode;
			properties = Properties.newInstance();
			project.setProperties(properties);
		} else if (qName.equals(DEPENDENCY)) {
			currentParentNode = currentNode;
			dependency = Dependency.newInstance();
			project.addDependency(dependency);
		} else if (qName.equals(PLUGIN)) {
			currentParentNode = currentNode;
			plugin = Plugin.newInstance();
			build.addPlugin(plugin);
		}else if(qName.equals("configuration")) {
			configuration = Configuration.newInstance();
			plugin.setConfig(configuration);
		}else if(qName.equals("archive")) {
			archive = Archive.newInstance();
			configuration.setArchive(archive);
			manifest = Manifest.newInstance();
			archive.setManifest(manifest);
		}else if(qName.equals("execution")) {
			execution = Execution.newInstance();
			plugin.addExecution(execution);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String word = "";
		for (int i = start; i < start + length; i++) {
			word += ch[i];
		}
		if (!word.trim().isEmpty()) {
			if (currentParentNode == null) {
				switch (currentNode) {
				case "modelVersion":
					project.setModelVersion(word);
					break;
				case "groupId":
					project.setGroupId(word);
					break;
				case "artifactId":
					project.setArtifactId(word);
					break;
				case "version":
					project.setVersion(word);
					break;
				case "name":
					project.setName(word);
					break;
				case "url":
					project.setUrl(word);
					break;
				}
			} else if (currentParentNode.equals(PROPERTIES)) {
				switch (currentNode) {
				case "project.build.sourceEncoding":
					properties.setSourceEncoding(word);
					break;
				case "maven.compiler.source":
					properties.setCompilerSource(word);
					break;
				case "maven.compiler.target":
					properties.setTarget(word);
					break;
				}
			} else if (currentParentNode.equals(DEPENDENCY)) {
				switch (currentNode) {
				case "scope":
					dependency.setScope(word);
					break;
				case "groupId":
					dependency.setGroupId(word);
					break;
				case "artifactId":
					dependency.setArtifactId(word);
					break;
				case "version":
					dependency.setVersion(word);
					break;
				}
			} else if (currentParentNode.equals(PLUGIN)) {
				switch (currentNode) {
				case "phase":
					execution.setPhase(word);
					break;
				case "descriptorRef":
					DescriptionRef dcR = DescriptionRef.newInstance();
					configuration.addDescritionRef(dcR);
					dcR.setDescriptinRef(word);
					break;
				case "artifactId":
					plugin.setArtifactId(word);
					break;
				case "version":
					plugin.setVersion(word);
					break;
				case "id":
					execution.setId(word);
					break;
				case "addClasspath":
					manifest.setAddClasspath(Boolean.valueOf(word));
					break;
				case "mainClass":
					manifest.setMainClass(word);
					break;
				case "goal":
					Goal goal = Goal.newInstance();
					goal.setGoal(word);
					execution.addGoal(goal);
					break;
				}
			}

		}

	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println(project);
		writeToCSV();
	}

	public Project getProject() {
		return project;
	}
	
	private void writeToCSV() {
		try (OutputStream os = new FileOutputStream("Resources/project.csv")){
			os.write(makeBytesFromProject());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private byte[] makeBytesFromProject() {
		StringBuilder sb = new StringBuilder(project.toString());
		String pts = project.toString();

		sb.append(project.toString());
		pts = pts.replace("[", "");
		pts = pts.replace("]", "");
		pts = pts.replace(",", ";");
		return pts.getBytes();
	}

	public String getCurrentParentNode() {
		return currentParentNode;
	}

	public String getCurrentNode() {
		return currentNode;
	}
	
}
