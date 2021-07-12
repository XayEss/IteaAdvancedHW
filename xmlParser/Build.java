package xmlParser;

import java.util.ArrayList;
import java.util.List;

public class Build {
	private List<Plugin> plugins;

	public List<Plugin> getPlugins() {
		return plugins;
	}

	public void setPlugins(List<Plugin> plugins) {
		this.plugins = plugins;
	}
	
	public static Build newInstance() {
		return new Build();
	}

	@Override
	public String toString() {
		return "Build [plugins=" + plugins + "]";
	}
	
	public void addPlugin(Plugin plugin) {
		if (plugins == null) {
			plugins = new ArrayList<>();
			plugins.add(plugin);
		} else {
			plugins.add(plugin);
		}
	}

}
