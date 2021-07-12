package xmlParser;

public class Properties {
	
	private String sourceEncoding;
	private String compilerSource;
	private String target;
	
	
	
	public Properties(String sourceEncoding, String compilerSource, String target) {
		super();
		this.sourceEncoding = sourceEncoding;
		this.compilerSource = compilerSource;
		this.target = target;
	}

	public static Properties createProperties(String sourceEncoding, String compilerSource, String target) {
		return new Properties(sourceEncoding, compilerSource, target);
	}

	@Override
	public String toString() {
		return "Properties [sourceEncoding=" + sourceEncoding + ", compilerSource=" + compilerSource + ", target="
				+ target + "]";
	}

}
