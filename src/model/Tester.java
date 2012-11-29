package model;

import process.Actor;
import process.DispatcherFinishException;
import process.IWaitCondition;

public class Tester extends Actor {

	private Model model;
	private Team team;

	public Tester(Model model, Team team) {
		this.model = model;
		this.team = team;
	}

	@Override
	protected void rule() {
		while (getDispatcher().getCurrentTime() < model.getFinishTime()) {
			try {
				waitForCondition(new IWaitCondition() {
					@Override
					public boolean testCondition() {
						return (!(team.getManager().getQueueTestingTasks().queue
								.isEmpty()));
					}
				});
			} catch (DispatcherFinishException e) {
				return;
			}

			getDispatcher().printToProtocol(
					"“естируем" + " -- " + team.getName() + ", "
							+ getNameForProtocol());

			Task task = (Task) team.getManager().getQueueTestingTasks()
					.removeFirst();
			model.getQueueTestingTasksAll().remove(task);

			holdForTime(model.getGui().getChooseRandomTestingTime().next());

			getDispatcher().printToProtocol(
					"протестили" + " -- " + team.getName() + ", "
							+ getNameForProtocol());
			if (Math.random() < model.getGui().getChooseBugChanse().getDouble()) {
				task.setTested(false);
				task.getDeveloper().setTask(task);
				team.getManager().getQueueDevelopTasks().addLast(task);
				model.getQueueDevelopTasksAll().add(task);
				getDispatcher().printToProtocol(
						"есть баги, отправл€ем на доработку" + " -- "
								+ team.getName() + ", " + getNameForProtocol());
				continue;
			}
			getDispatcher().printToProtocol(
					"задача сделана правильно" + " -- " + team.getName() + ", "
							+ getNameForProtocol());
			task.setTested(true);
		}
	}

}
