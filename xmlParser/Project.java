package xmlParser;

import java.util.ArrayList;
import java.util.List;

public class Project {
	private String modelVersion;
	private String groupId;
	private String artifactId;
	private String version;
	private String name;
	private String url;
	private Properties properties;
	private List<Dependency> dependencies;
	private Build build;
	
	public String getModelVersion() {
		return modelVersion;
	}
	public void setModelVersion(String modelVersion) {
		this.modelVersion = modelVersion;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Dependency> getDependencies() {
		return dependencies;
	}
	public void setDependencies(List<Dependency> dependencies) {
		this.dependencies = dependencies;
	}
	public void addDependency(Dependency dependency) {
		if(dependencies == null) {
			dependencies = new ArrayList<>();
			dependencies.add(dependency);
		}else {
			dependencies.add(dependency);
		}
	}
	public Build getBuild() {
		return build;
	}
	public void setBuild(Build build) {
		this.build = build;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	public static Project newInstance() {
		return new Project();
	}
	
	@Override
	public String toString() {
		return "Project [modelVersion=" + modelVersion + ", groupId=" + groupId + ", artifactId=" + artifactId
				+ ", version=" + version + ", name=" + name + ", url=" + url + ", properties=" + properties
				+ ", dependencies=" + dependencies + ", build=" + build + "]";
	}
	
}
