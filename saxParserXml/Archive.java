package saxParserXml;

public class Archive {
	private Manifest manifest;

	public Manifest getManifest() {
		return manifest;
	}

	public void setManifest(Manifest manifest) {
		this.manifest = manifest;
	}
	
	public static Archive newInstance() {
		return new Archive();
	}

	@Override
	public String toString() {
		return "" + manifest;
	}
	
}
