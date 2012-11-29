package model;

public class Task {
	private boolean success = false;
	private boolean tested = false;
	private Developer developer;

	public boolean isSuccess() {
		return success;
	}

	public boolean isTested() {
		return tested;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setTested(boolean tested) {
		this.tested = tested;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	
}
