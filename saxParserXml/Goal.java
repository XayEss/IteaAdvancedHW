package saxParserXml;

public class Goal {
	private String goal;

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}
	
	public static Goal newInstance() {
		return new Goal();
	}

	@Override
	public String toString() {
		return "" + goal;
	}
	
	

}
