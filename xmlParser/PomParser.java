package xmlParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PomParser {
	Project project;
	Document document;

	public Project parsePom() throws ParserConfigurationException, SAXException, IOException {
		project = Project.newInstance();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		document = builder.parse(new File("resources/pom.xml"));
		parseProjectSettings();
		parseDependencies();
		parseProperties();
		parseBuild();
		return project;
	}

	private void parseProjectSettings() {
		NodeList modelNode = document.getElementsByTagName("modelVersion");
		project.setModelVersion(modelNode.item(0).getTextContent());
		NodeList groupNode = document.getElementsByTagName("groupId");
		project.setGroupId(groupNode.item(0).getTextContent());
		NodeList artifactNode = document.getElementsByTagName("artifactId");
		project.setGroupId(artifactNode.item(0).getTextContent());
		NodeList versionNode = document.getElementsByTagName("version");
		project.setGroupId(versionNode.item(0).getTextContent());
		NodeList nameNode = document.getElementsByTagName("name");
		project.setGroupId(nameNode.item(0).getTextContent());
		NodeList urlNode = document.getElementsByTagName("url");
		project.setGroupId(urlNode.item(0).getTextContent());
	}

	private void parseProperties() {
		NodeList encodingNode = document.getElementsByTagName("project.build.sourceEncoding");
		NodeList sourceNode = document.getElementsByTagName("maven.compiler.source");
		NodeList targetNode = document.getElementsByTagName("maven.compiler.target");
		project.setProperties(Properties.createProperties(encodingNode.item(0).getTextContent(),
				sourceNode.item(0).getTextContent(), targetNode.item(0).getTextContent()));
	}

	private void parseDependencies() {
		NodeList nodeList = document.getElementsByTagName("dependency");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			NodeList nodes = node.getChildNodes();
			Dependency dependency = new Dependency(nodes.item(1).getTextContent(), nodes.item(3).getTextContent(),
					nodes.item(5).getTextContent(), nodes.item(7) != null ? nodes.item(7).getTextContent() : null);
			project.addDependency(dependency);
		}

	}

	private void parseBuild() {
		Build build = Build.newInstance();
		project.setBuild(build);
		NodeList nodeList = document.getElementsByTagName("plugin");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Plugin plugin = Plugin.createPlugin();
			build.addPlugin(plugin);
			Node node = nodeList.item(i);
			NodeList nodes = node.getChildNodes();
			plugin.setArtifactId(nodes.item(1).getTextContent());
			plugin.setVersion(nodes.item(3).getTextContent());
			if (nodes.item(5).getNodeName().equals("configuration")) {
				NodeList configNode = nodes.item(5).getChildNodes();
				Configuration config = Configuration.newInstance();
				DescriptionRef descRef = DescriptionRef.newInstance();
				descRef.setDescriptinRef(configNode.item(1).getChildNodes().item(1).getTextContent());
				config.addDescritionRef(descRef);
				Archive archive = Archive.newInstance();
				config.setArchive(archive);
				Manifest manifest = Manifest.newInstance();
				NodeList manifestNode = configNode.item(3).getChildNodes().item(1).getChildNodes();
				manifest.setAddClasspath(Boolean.valueOf(manifestNode.item(1).getTextContent()));
				manifest.setMainClass(manifestNode.item(3).getTextContent());
				archive.setManifest(manifest);
				plugin.setConfig(config);
			}
			NodeList executionList = nodes.item(7).getChildNodes();
			for (int j = 0; j < executionList.getLength()/2; j++) {
				NodeList executionPropertiesList = executionList.item(j + 1).getChildNodes();
				Execution execution = Execution.newInstance();
				execution.setId(executionPropertiesList.item(1).getTextContent());
				execution.setPhase(executionPropertiesList.item(3).getTextContent());
				NodeList goalNode = executionPropertiesList.item(5).getChildNodes();
				Goal goal = Goal.newInstance();
				goal.setGoal(goalNode.item(1).getTextContent());
				execution.addGoal(goal);
				plugin.addExecution(execution);
			}
		}

	}

}
