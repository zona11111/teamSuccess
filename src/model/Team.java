package model;

import process.Dispatcher;
import qusystem.MultiActor;

public class Team {

	private int countFails;

	private Model model;
	private Manager manager;
	private MultiActor developers;
	private MultiActor testers;

	private Project project;

	private String name;

	public Team(Model model, int countDevelopers, int countTesters,
			Dispatcher dispatcher) {
		this.model = model;

		manager = new Manager(model, this);
		model.getDispatcher().addStartingActor(manager);

		Developer developer = new Developer(model, this);
		developer.setNameForProtocol("Developer ");

		Tester tester = new Tester(model, this);
		tester.setNameForProtocol("Tester ");

		developers = new MultiActor(developer, countDevelopers);

		testers = new MultiActor(tester, countTesters);

		model.getDispatcher().addStartingActor(developers);
		model.getDispatcher().addStartingActor(testers);

	}

	public int getCountFails() {
		return countFails;
	}

	public void setNewFail() {
		setProject(null);
		model.getDispatcher().printToProtocol(
				getName() + " провалила проект");
		this.countFails++;
	}

	public Manager getManager() {
		return manager;
	}

	public MultiActor getDevelopers() {
		return developers;
	}

	public MultiActor getTesters() {
		return testers;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
