package xmlParser;

import java.util.ArrayList;
import java.util.List;

public class Configuration {
	private List<DescriptionRef> descriptionRefs;
	private Archive archive;

	public List<DescriptionRef> getDescriptionRefs() {
		return descriptionRefs;
	}

	public void setDescriptionRefs(List<DescriptionRef> descriptionRefs) {
		this.descriptionRefs = descriptionRefs;
	}

	public Archive getArchive() {
		return archive;
	}

	public void setArchive(Archive archive) {
		this.archive = archive;
	}

	public void addDescritionRef(DescriptionRef descriptionRef) {
		if (descriptionRefs == null) {
			descriptionRefs = new ArrayList<>();
			descriptionRefs.add(descriptionRef);
		} else {
			descriptionRefs.add(descriptionRef);
		}

	}

	public static Configuration newInstance() {
		return new Configuration();
	}

	@Override
	public String toString() {
		return "Configuration [descriptionRefs=" + descriptionRefs + ", archive=" + archive + "]";
	}

}
