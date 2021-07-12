package xmlParser;

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

	@Override
	public String toString() {
		return "Dependency [groupId=" + groupId + ", artifactId=" + artifactId + ", version=" + version + ", scope="
				+ scope + "]";
	}
	

}
