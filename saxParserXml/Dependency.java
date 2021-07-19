package saxParserXml;

public class Dependency {
	private String groupId;
	private String artifactId;
	private String version;
	private String scope;
	
	public Dependency(String groupId, String artifactId, String version, String scope) {
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
		this.scope = scope;
	}
	
	public Dependency() {

	}

	public static Dependency newInstance() {
		return new Dependency();
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

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return  groupId + "," + artifactId + "," + version + ","
				+ scope;
	}
	

}
