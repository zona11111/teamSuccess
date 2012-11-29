package model;

import java.util.ArrayList;

import process.Dispatcher;
import queues.QueueForTransactions;
import stat.Histo;
import widgets.experiments.IExperimentable;
import widgets.trans.ITransProcesable;

public class Model implements IModel, IExperimentable, ITransProcesable {

	private QueueForTransactions queueProject;
	private QueueForTransactions queueCompletedProjects;
	private QueueForTransactions queueDevelopTasksAll;
	private QueueForTransactions queueTestingTasksAll;

	private Dispatcher dispatcher;

	private final Histo timeWorkPr = new Histo();
	private final Histo timeCorrectionTask = new Histo();

	private MainGui gui = MainGui.application;

	private ArrayList<Team> allTeams = new ArrayList<Team>();

	private int countTesters = 0;
	private int countDevelopers = 0;
	private int countCustomers = 0;
	private int countTeams = 0;

	private double finishTime;

	public Model(Dispatcher arg0) {
		dispatcher = arg0;
	}

	@Override
	public void initForTest() {
		countTesters = gui.getChooseCountTesters().getInt();
		finishTime = getGui().getChooseModelingTime().getDouble();
		getQueueProject()
				.setPainter(gui.getDiagramQueueProjects().getPainter());
		getQueueCompletedProjects().setPainter(
				gui.getDiagramCountCompletedProjects().getPainter());
		getQueueDevelopTasksAll().setPainter(
				gui.getDiagramQueueDevelopmentTasks().getPainter());
		getQueueTestingTasksAll().setPainter(
				gui.getDiagramQueueTestingTasks().getPainter());
		init();
	}

	@Override
	public void initForStat() {
		countTesters = gui.getChooseCountTesters().getInt();
		finishTime = getGui().getChooseModelingTime().getDouble();
		getTimeWorkPr().init();
		getTimeCorrectionTask().init();
		init();
	}

	@Override
	public double getResultOfExperiment() {
		// TODO Auto-generated method stub
		return getQueueCompletedProjects().size();
	}

	@Override
	public void initForExperiment(double arg0) {
		// TODO Auto-generated method stub
		countTesters = (int) arg0;
		init();
	}

	@Override
	public double getTransResult() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public void initForTrans(double arg0) {
		// TODO Auto-generated method stub
		finishTime = arg0;
		countTesters = gui.getChooseCountTesters().getInt();
		init();
	}

	@Override
	public void resetTransAccum() {
		// TODO Auto-generated method stub

	}

	public Histo getTimeWorkPr() {
		return timeWorkPr;
	}

	public Histo getTimeCorrectionTask() {
		return timeCorrectionTask;
	}

	public MainGui getGui() {
		return gui;
	}

	private void init() {

		countDevelopers = gui.getChooseCountDevelopers().getInt();
		countTeams = gui.getChooseCountTeams().getInt();
		countCustomers = gui.getChooseCountCustomers().getInt();

		for (int i = 0; i < countTeams; i++) {
			Team team = new Team(this, countDevelopers, countTesters,
					dispatcher);
			team.setName("Team " + i);
			allTeams.add(team);
		}

		for (int i = 0; i < countCustomers; i++) {
			Customer m = new Customer(this);
			m.setNameForProtocol("Customer " + i + " ");
			dispatcher.addStartingActor(m);
		}
	}

	public QueueForTransactions getQueueProject() {
		if (queueProject == null) {
			queueProject = new QueueForTransactions();
			queueProject.setDispatcher(dispatcher);
			queueProject.setNameForProtocol("Queue Projects");
			queueProject.init();
		}
		return queueProject;
	}

	public QueueForTransactions getQueueDevelopTasksAll() {
		if (queueDevelopTasksAll == null) {
			queueDevelopTasksAll = new QueueForTransactions();
			queueDevelopTasksAll.setDispatcher(dispatcher);
			queueDevelopTasksAll.setNameForProtocol("QueueDevelopTasksAll");
			queueDevelopTasksAll.init();
		}
		return queueDevelopTasksAll;
	}

	public QueueForTransactions getQueueTestingTasksAll() {
		if (queueTestingTasksAll == null) {
			queueTestingTasksAll = new QueueForTransactions();
			queueTestingTasksAll.setDispatcher(dispatcher);
			queueTestingTasksAll.setNameForProtocol("QueueTestingTasksAll");
			queueTestingTasksAll.init();
		}
		return queueTestingTasksAll;
	}

	public QueueForTransactions getQueueCompletedProjects() {
		if (queueCompletedProjects == null) {
			queueCompletedProjects = new QueueForTransactions();
			queueCompletedProjects.setDispatcher(dispatcher);
			queueCompletedProjects.setNameForProtocol("QueueCompletedProjects");
			queueCompletedProjects.init();
		}
		return queueCompletedProjects;
	}

	public ArrayList<Team> getAllTeams() {
		return allTeams;
	}

	public Dispatcher getDispatcher() {
		return dispatcher;
	}

	public double getFinishTime() {
		return finishTime;
	}

}
