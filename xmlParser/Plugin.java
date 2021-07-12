package xmlParser;

import java.util.ArrayList;
import java.util.List;

public class Plugin {
	private String artifactId;
	private String version;
	private Configuration config;
	List<Execution> executions;

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

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	public List<Execution> getExecutions() {
		return executions;
	}

	public void setExecutions(List<Execution> executions) {
		this.executions = executions;
	}

	public static Plugin createPlugin() {
		return new Plugin();
	}

	@Override
	public String toString() {
		return "Plugin [artifactId=" + artifactId + ", version=" + version + ", config=" + config + ", executions="
				+ executions + "]";
	}

	public void addExecution(Execution execution) {
		if (executions == null) {
			executions = new ArrayList<>();
			executions.add(execution);
		} else {
			executions.add(execution);
		}
	}
}
