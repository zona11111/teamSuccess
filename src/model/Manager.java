package model;

import java.util.ArrayList;
import java.util.Iterator;

import process.Actor;
import process.DispatcherFinishException;
import process.IWaitCondition;
import queues.QueueForTransactions;

public class Manager extends Actor {

	private QueueForTransactions queueTestingTasks;
	private QueueForTransactions queueDevelopTasks;

	private Model model;
	private Team team;

	public Manager(Model model, Team team) {
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
						return ((team.getProject() != null) && (getQueueDevelopTasks().queue
								.isEmpty()));
					}
				});
			} catch (DispatcherFinishException e) {
				return;
			}

			getDispatcher().printToProtocol("Project accepted");
			double startTime = getDispatcher().getCurrentTime();

			int countDevelopers = model.getGui().getChooseCountDevelopers()
					.getInt();
			int countTasks = ((int) team.getProject().getTimeDevelop() / countDevelopers / 20)
					+ countDevelopers;
			int iterCount = (countTasks / countDevelopers) + 1;
			
			team.getProject().setCountTasks(countTasks);
 
			for (int i = 0; i < countTasks; i++) {
				Task task = team.getProject().getTask(i);
				if (!task.isSuccess() | !task.isTested()) {
					queueDevelopTasks.addLast(task);
					model.getQueueDevelopTasksAll().add(task);
					holdForTime(model.getGui().getChooseRandom3().next());
				}
			}

			for (int iter = 0; iter < iterCount; iter++) {

				if (team.getProject().getTimeDevelop() < (getDispatcher()
						.getCurrentTime() - startTime)) {
					team.setNewFail();;
					break;
				}
				
				final ArrayList<Task> iterTasks = new ArrayList<Task>();
				iterTasks.clear();
				
				for (Iterator<Actor> iterator = team.getDevelopers()
						.getClonesArray().iterator(); iterator.hasNext();) {
					Developer developer = (Developer) iterator.next();
					if (queueDevelopTasks.size() != 0) {
						Task task = (Task) queueDevelopTasks.removeFirst();
						model.getQueueDevelopTasksAll().remove(task);
						holdForTime(model.getGui().getChooseRandom3().next());
						iterTasks.add(task);
						task.setDeveloper(developer);
						developer.setTask(task);
					}
				}

				try {
					waitForCondition(new IWaitCondition() {

						@Override
						public boolean testCondition() {
							for (Iterator<Task> iterator = iterTasks.iterator(); iterator
									.hasNext();) {
								Task task = (Task) iterator.next();
								if (task.isTested() == false) {
									return false;
								}
							}
							return true;
						}
					});
				} catch (DispatcherFinishException e) {
					return;
				}

				if (team.getProject().isOk()) {
					model.getQueueCompletedProjects()
							.addLast(team.getProject());
					team.setProject(null);
					getDispatcher().printToProtocol(
							team.getName() + " завершила проект");
					model.getTimeWorkPr().add(getDispatcher().getCurrentTime() - startTime);
					break;
				}

				if (iter == iterCount) {
					team.setNewFail();
				}


			}

		}
	}

	public QueueForTransactions getQueueDevelopTasks() {
		if (queueDevelopTasks == null) {
			queueDevelopTasks = new QueueForTransactions();
			queueDevelopTasks.init();
			queueDevelopTasks.setDispatcher(getDispatcher());
			queueDevelopTasks.setNameForProtocol(getNameForProtocol()
					+ " queueDevelopTasks");
		}
		return queueDevelopTasks;
	}

	public QueueForTransactions getQueueTestingTasks() {
		if (queueTestingTasks == null) {
			queueTestingTasks = new QueueForTransactions();
			queueTestingTasks.init();
			queueTestingTasks.setDispatcher(getDispatcher());
			queueTestingTasks.setNameForProtocol(getNameForProtocol()
					+ " queueTestingTasks");
		}
		return queueTestingTasks;
	}

}
