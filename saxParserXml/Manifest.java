package saxParserXml;

public class Manifest {
	private boolean addClasspath;
	private String mainClass;
	
	public boolean isAddClasspath() {
		return addClasspath;
	}
	public void setAddClasspath(boolean addClasspath) {
		this.addClasspath = addClasspath;
	}
	public String getMainClass() {
		return mainClass;
	}
	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}
	
	public static Manifest newInstance() {
		return new Manifest();
	}
	@Override
	public String toString() {
		return "" + addClasspath + "," + mainClass + ",";
	}
	
	

}
