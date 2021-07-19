package saxParserXml;

import java.util.ArrayList;
import java.util.List;

public class Execution {
	private String id;
	private String phase;
	List<Goal> goals;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public List<Goal> getGoals() {
		return goals;
	}
	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}

	public static Execution newInstance() {
		return new Execution();
	}
	
	public void addGoal(Goal goal) {
		if (goals == null) {
			goals = new ArrayList<>();
			goals.add(goal);
		} else {
			goals.add(goal);
		}

	}
	@Override
	public String toString() {
		return "" + id + "," + phase + "," + goals;
	}
}
