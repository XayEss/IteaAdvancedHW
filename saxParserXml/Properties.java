package saxParserXml;

public class Properties {
	private String sourceEncoding;
	private String compilerSource;
	private String target;
	
	public Properties() {

	}

	public Properties(String sourceEncoding, String compilerSource, String target) {
		super();
		this.sourceEncoding = sourceEncoding;
		this.compilerSource = compilerSource;
		this.target = target;
	}

	public static Properties createProperties(String sourceEncoding, String compilerSource, String target) {
		return new Properties(sourceEncoding, compilerSource, target);
	}
	
	public static Properties newInstance() {
		return new Properties();
	}
	
	public String getSourceEncoding() {
		return sourceEncoding;
	}

	public void setSourceEncoding(String sourceEncoding) {
		this.sourceEncoding = sourceEncoding;
	}

	public String getCompilerSource() {
		return compilerSource;
	}

	public void setCompilerSource(String compilerSource) {
		this.compilerSource = compilerSource;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return  sourceEncoding + "," + compilerSource + ","
				+ target;
	}

}
