package model;

import java.util.ArrayList;

import process.Actor;

public class Customer extends Actor {

	private Model model;

	public Customer(Model model) {
		this.model = model;
	}

	@Override
	protected void rule() {
		while (getDispatcher().getCurrentTime() < (model.getGui()
				.getChooseModelingTime().getInt())) {

			Project project = new Project();
			project.setTimeDevelop(20 + (Math.random())*10);

			model.getQueueProject().addLast(project);

			sortTeams();
			for (Team team : model.getAllTeams()) {
				if ((team.getProject() == null)
						& (model.getQueueProject().size() != 0)) {
					team.setProject((Project) model.getQueueProject()
							.removeFirst());
				}
			}

			holdForTime(model.getGui().getChooseRandomIntens().next());

		}
	}

	private void swap(ArrayList<Team> arr, int i, int j) {
		Team t = arr.get(i);
		arr.set(i, arr.get(j));
		arr.set(j, t);
	}

	private void sortTeams() {
		for (int i = model.getAllTeams().size() - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if (model.getAllTeams().get(j).getCountFails() > model
						.getAllTeams().get(j + 1).getCountFails())
					swap(model.getAllTeams(), j, j + 1);
			}
		}
	}

}
