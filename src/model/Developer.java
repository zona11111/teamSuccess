package model;

import process.Actor;
import process.DispatcherFinishException;
import process.IWaitCondition;

public class Developer extends Actor {

	private Model model;
	private Team team;
	private Task task;

	public Developer(Model model, Team team) {
		this.model = model;
		this.team = team;
	}

	@Override
	protected void rule() {
		while (getDispatcher().getCurrentTime() < (model.getFinishTime())) {
			try {
				waitForCondition(new IWaitCondition() {
					@Override
					public boolean testCondition() {
						return (task != null);
					}
				});
			} catch (DispatcherFinishException e) {
				return;
			}

			if (!task.isSuccess()) {
				getDispatcher().printToProtocol(
						"есть задача на разработку" + " -- " + team.getName()
								+ ", " + getNameForProtocol());
				holdForTime(model.getGui().getChooseRandomDevelopTime().next());
				task.setSuccess(true);
				team.getManager().getQueueTestingTasks().addLast(task);
				model.getQueueTestingTasksAll().add(task);
				setTask(null);
				continue;
			}

			if (!task.isTested()) {
				Double d, d1;
				d = getDispatcher().getCurrentTime();
				holdForTime(model.getGui().getChooseRandomDevelopTime().next());
				d1 = getDispatcher().getCurrentTime();
				model.getTimeCorrectionTask().add(d1 - d);
				team.getManager().getQueueTestingTasks().addLast(task);
				model.getQueueTestingTasksAll().add(task);
				setTask(null);
				continue;
			}
		}
	}

	public void setTask(Task task) {
		this.task = task;
	}

}
