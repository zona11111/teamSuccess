package model;

import java.util.ArrayList;

public class Project {
	
	private double timeDevelop;
	private int countTasks;
	private ArrayList<Task> allTasks = new ArrayList<Task>();

	public double getTimeDevelop() {
		return timeDevelop;
	}

	public void setTimeDevelop(double timeDevelop) {
		this.timeDevelop = timeDevelop;
	}

	public int getCountTasks() {
		return countTasks;
	}

	public void setCountTasks(int countTasks) {
		this.countTasks = countTasks;
		for (int i = 0; i < countTasks; i++) {
			allTasks.add(i, new Task());
		}
	}
	
	public Task getTask(int index){
		return allTasks.get(index);
	}
	
	public boolean isOk(){
		for (Task task : allTasks) {
			if ( (!task.isSuccess()) | (!task.isTested()) ) {
				return false; 
			}
		}
		return true;
	}

}
