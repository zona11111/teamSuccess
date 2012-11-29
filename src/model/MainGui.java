package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import process.Dispatcher;
import process.DispatcherFinishListener;
import rnd.Negexp;
import widgets.ChooseData;
import widgets.ChooseRandom;
import widgets.Diagram;
import widgets.experiments.ExperimentControl;
import widgets.regres.RegresAnaliser;
import widgets.trans.TransMonitorView;

public class MainGui {
	private JFrame jFrame = null; // @jve:decl-index=0:visual-constraint="10,10"
	private JPanel jContentPane = null;
	private ChooseData chooseCountDevelopers = null;
	private ChooseData chooseCountTesters = null;
	private ChooseData chooseCountTeams = null;
	private ChooseData chooseCountCustomers = null;
	private ChooseData chooseBugChanse = null;
	private ChooseData chooseModelingTime = null;
	private ChooseRandom chooseRandom = null;
	private ChooseRandom chooseRandomDevelopTime = null;
	private ChooseRandom chooseRandomTestingTime = null;
	private ChooseRandom chooseRandom3 = null;
	private JTabbedPane panelRight = null;
	private JPanel jPanelTestTab = null;
	private JPanel jPanelStatTab = null;
	private Diagram diagramQueueProjects = null;
	private Diagram diagramQueueDevelopmentTasks = null;
	private Diagram diagramQueueTestingTasks = null;
	private Diagram diagramCountCompletedProjects = null;
	private Diagram diagram4 = null;
	private Diagram diagram5 = null;
	private JButton jButtonTest = null;
	private JButton jButtonStat = null;
	private JScrollPane jScrollPane = null;
	private JTextArea jTextArea = null;
	private JScrollPane jScrollPane1 = null;
	private JTextArea jTextArea1 = null;
	private Dispatcher dispatcher;
	private JPanel jPanelRegressTab;
	private JPanel jPanelTransTab;
	private JCheckBox chckbxUseDebugConsole;
	private JSplitPane splitPane;
	private JPanel panelFeft;
	private JPanel panel;
	private JPanel panel_1;
	private Diagram diagramRegress;
	private ExperimentControl experimentControl;
	private RegresAnaliser regresAnaliser;
	private Diagram diagramTransient;
	private TransMonitorView transMonitorView;
	public static MainGui application;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				application = new MainGui();
				application.getJFrame().setVisible(true);
			}
		});
	}

	/**
	 * This method initializes chooseCountDevelopers
	 * 
	 * @return widgets.ChooseData
	 */
	public ChooseData getChooseCountDevelopers() {
		if (chooseCountDevelopers == null) {
			chooseCountDevelopers = new ChooseData();
			chooseCountDevelopers.setText("4");
			chooseCountDevelopers.setTitle("Количество разработчиков");
		}
		return chooseCountDevelopers;
	}

	/**
	 * This method initializes chooseCountTesters
	 * 
	 * @return widgets.ChooseData
	 */
	public ChooseData getChooseCountTesters() {
		if (chooseCountTesters == null) {
			chooseCountTesters = new ChooseData();
			chooseCountTesters.setText("5");
			chooseCountTesters.setTitle("Количество тестеров");
		}
		return chooseCountTesters;
	}

	/**
	 * This method initializes chooseCountTeams
	 * 
	 * @return widgets.ChooseData
	 */
	public ChooseData getChooseCountTeams() {
		if (chooseCountTeams == null) {
			chooseCountTeams = new ChooseData();
			chooseCountTeams.setText("5");
			chooseCountTeams.setTitle("Количество команд");
		}
		return chooseCountTeams;
	}

	/**
	 * This method initializes chooseCountCustomers
	 * 
	 * @return widgets.ChooseData
	 */
	public ChooseData getChooseCountCustomers() {
		if (chooseCountCustomers == null) {
			chooseCountCustomers = new ChooseData();
			chooseCountCustomers.setText("4");
			chooseCountCustomers.setTitle("Количество заказчиков");
		}
		return chooseCountCustomers;
	}

	/**
	 * This method initializes chooseBugChanse
	 * 
	 * @return widgets.ChooseData
	 */
	public ChooseData getChooseBugChanse() {
		if (chooseBugChanse == null) {
			chooseBugChanse = new ChooseData();
			chooseBugChanse.setText("0.2");
			chooseBugChanse.setTitle("Вероят. ошибки ");
		}
		return chooseBugChanse;
	}

	/**
	 * This method initializes chooseModelingTime
	 * 
	 * @return widgets.ChooseData
	 */
	public ChooseData getChooseModelingTime() {
		if (chooseModelingTime == null) {
			chooseModelingTime = new ChooseData();
			chooseModelingTime.setText("200");
			chooseModelingTime.setTitle("Время моделирования");
		}
		return chooseModelingTime;
	}

	/**
	 * This method initializes chooseRandom
	 * 
	 * @return widgets.ChooseRandom
	 */
	public ChooseRandom getChooseRandom() {
		if (chooseRandom == null) {
			Negexp negexp1 = new Negexp();
			negexp1.setM(50.0D);
			chooseRandom = new ChooseRandom();
			chooseRandom
					.setTitle("\u0418\u043D\u0442\u0435\u043D\u0441\u0438\u0432. \u043F\u043E\u044F\u0432\u044B \u043D\u0430\u0432\u043E\u0433\u043E \u043F\u0440.");
			chooseRandom.setRandom(negexp1);
		}
		return chooseRandom;
	}

	/**
	 * This method initializes chooseRandomDevelopTime
	 * 
	 * @return widgets.ChooseRandom
	 */
	public ChooseRandom getChooseRandomDevelopTime() {
		if (chooseRandomDevelopTime == null) {
			Negexp negexp2 = new Negexp();
			negexp2.setM(6.0D);
			chooseRandomDevelopTime = new ChooseRandom();
			chooseRandomDevelopTime
					.setTitle("\u0412\u0440\u0435\u043C\u044F \u0440\u0430\u0441\u0440\u0430\u0431\u043E\u0442\u043A\u0438");
			chooseRandomDevelopTime.setRandom(negexp2);
		}
		return chooseRandomDevelopTime;
	}

	/**
	 * This method initializes chooseRandomTestingTime
	 * 
	 * @return widgets.ChooseRandom
	 */
	public ChooseRandom getChooseRandomTestingTime() {
		if (chooseRandomTestingTime == null) {
			Negexp negexp3 = new Negexp();
			negexp3.setM(5.0D);
			chooseRandomTestingTime = new ChooseRandom();
			chooseRandomTestingTime
					.setTitle("\u0412\u0440\u0435\u043C\u044F \u0442\u0435\u0441\u0442\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u044F");
			chooseRandomTestingTime.setRandom(negexp3);
		}
		return chooseRandomTestingTime;
	}

	/**
	 * This method initializes chooseRandom3
	 * 
	 * @return widgets.ChooseRandom
	 */
	public ChooseRandom getChooseRandom3() {
		if (chooseRandom3 == null) {
			Negexp negexp4 = new Negexp();
			negexp4.setM(2.0D);
			chooseRandom3 = new ChooseRandom();
			chooseRandom3
					.setTitle("\u0412\u0440\u0435\u043C\u044F \u0440\u0430\u0441\u0431\u0438\u0435\u043D\u0438\u044F \u043D\u0430 \u0437\u0430\u0434\u0430\u0447\u0438");
			chooseRandom3.setRandom(negexp4);
		}
		return chooseRandom3;
	}

	/**
	 * This method initializes jTabbedPane
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getPanelRight() {
		if (panelRight == null) {
			panelRight = new JTabbedPane();
			panelRight.setToolTipText("");
			panelRight.addTab("Тест модели", null, getJPanelTestTab(), null);
			panelRight.addTab("Статистика", null, getJPanelStatTab(), null);
			panelRight
					.addTab("\u0420\u0435\u0433\u0440\u0435\u0441\u0441\u0438\u043E\u043D\u043D\u044B\u0439 \u0430\u043D\u0430\u043B\u0438\u0437",
							null, getJPanelRegressTab(), null);
			panelRight
					.addTab("\u041F\u0435\u0440\u0435\u0445\u043E\u0434\u043D\u044B\u0435 \u043F\u0440\u043E\u0446\u0435\u0441\u0441\u044B",
							null, getJPanelTransTab(), null);
			panelRight
					.addChangeListener(new javax.swing.event.ChangeListener() {
						public void stateChanged(javax.swing.event.ChangeEvent e) {
							// System.out.println("stateChanged()");
							// if (getJPanel().isShowing()) {
							// getDiagramQueueProjects().setBounds(
							// new Rectangle(2, -4, 527, 127));
							// getDiagramQueueDevelopmentTasks().setBounds(
							// new Rectangle(3, 123, 525, 128));
							// getDiagramQueueTestingTasks().setBounds(
							// new Rectangle(4, 251, 524, 125));
							// getDiagramCountCompletedProjects().setBounds(
							// new Rectangle(4, 376, 523, 120));
							// }
						}

					});
			panelRight
					.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
						public void propertyChange(
								java.beans.PropertyChangeEvent e) {
							if ((e.getPropertyName().equals("enabled"))) {

							}
						}
					});

		}
		return panelRight;
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelTestTab() {
		if (jPanelTestTab == null) {
			jPanelTestTab = new JPanel();
			jPanelTestTab.setName("");
			jPanelTestTab.setToolTipText("");
			jPanelTestTab.setLayout(new GridLayout(0, 1, 0, 0));
			jPanelTestTab.add(getDiagramQueueProjects());
			jPanelTestTab.add(getDiagramQueueDevelopmentTasks());
			jPanelTestTab.add(getDiagramQueueTestingTasks());
			jPanelTestTab.add(getDiagramCountCompletedProjects());
			jPanelTestTab.add(getJButtonTest());
		}
		return jPanelTestTab;
	}

	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanelStatTab() {
		if (jPanelStatTab == null) {
			jPanelStatTab = new JPanel();
			jPanelStatTab.setLayout(new GridLayout(5, 1, 0, 0));
			jPanelStatTab.add(getDiagram4());
			jPanelStatTab.add(getJScrollPane());
			jPanelStatTab.add(getDiagram5());
			jPanelStatTab.add(getJScrollPane1());
			jPanelStatTab.add(getJButtonStat());
		}
		return jPanelStatTab;
	}

	/**
	 * This method initializes diagramQueueProjects
	 * 
	 * @return widgets.Diagram
	 */
	public Diagram getDiagramQueueProjects() {
		if (diagramQueueProjects == null) {
			diagramQueueProjects = new Diagram();
			diagramQueueProjects.setTitleText("Очередь проектов");
			diagramQueueProjects.setPainterColor(Color.red);

		}
		return diagramQueueProjects;
	}

	/**
	 * This method initializes diagramQueueDevelopmentTasks
	 * 
	 * @return widgets.Diagram
	 */
	public Diagram getDiagramQueueDevelopmentTasks() {
		if (diagramQueueDevelopmentTasks == null) {
			diagramQueueDevelopmentTasks = new Diagram();
			diagramQueueDevelopmentTasks
					.setTitleText("Очередь задач на разработку");
			diagramQueueDevelopmentTasks.setPainterColor(Color.red);
		}
		return diagramQueueDevelopmentTasks;
	}

	/**
	 * This method initializes diagramQueueTestingTasks
	 * 
	 * @return widgets.Diagram
	 */
	public Diagram getDiagramQueueTestingTasks() {
		if (diagramQueueTestingTasks == null) {
			diagramQueueTestingTasks = new Diagram();
			diagramQueueTestingTasks.setTitleText("Очередь на тестирование");
			diagramQueueTestingTasks.setPainterColor(Color.red);
		}
		return diagramQueueTestingTasks;
	}

	/**
	 * This method initializes diagramCountCompletedProjects
	 * 
	 * @return widgets.Diagram
	 */
	public Diagram getDiagramCountCompletedProjects() {
		if (diagramCountCompletedProjects == null) {
			diagramCountCompletedProjects = new Diagram();
			diagramCountCompletedProjects
					.setTitleText("Количество выполненых проектов");
			diagramCountCompletedProjects.setPainterColor(Color.red);
		}
		return diagramCountCompletedProjects;
	}

	/**
	 * This method initializes diagram4
	 * 
	 * @return widgets.Diagram
	 */
	private Diagram getDiagram4() {
		if (diagram4 == null) {
			diagram4 = new Diagram();
			diagram4.setTitleText("Время потраченное на разработку проекта");
		}
		return diagram4;
	}

	/**
	 * This method initializes diagram5
	 * 
	 * @return widgets.Diagram
	 */
	private Diagram getDiagram5() {
		if (diagram5 == null) {
			diagram5 = new Diagram();
			diagram5.setTitleText("Время потраченное на исправление ошибок в задачах");
		}
		return diagram5;
	}
	
	/**
	 * This method initializes diagramRegress
	 * 
	 * @return widgets.Diagram
	 */
	private Diagram getDiagramRegress() {
		if (diagramRegress == null) {
			diagramRegress = new Diagram();
		}
		return diagramRegress;
	}
	
	/**
	 * This method initializes diagramTransient
	 * 
	 * @return widgets.Diagram
	 */
	private Diagram getDiagramTransient() {
		if (diagramTransient == null) {
			diagramTransient = new Diagram();
		}
		return diagramTransient;
	}

	/**
	 * This method initializes jButtonTest
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonTest() {
		if (jButtonTest == null) {
			jButtonTest = new JButton();
			jButtonTest.setText("Старт");
			jButtonTest.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					startTest();
				}
			});
		}
		return jButtonTest;
	}

	/**
	 * This method initializes jButtonStat
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonStat() {
		if (jButtonStat == null) {
			jButtonStat = new JButton();
			jButtonStat.setText("Начать експеримент");
			jButtonStat.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					startStat();
				}
			});
		}
		return jButtonStat;
	}

	private void startTest() {
		getDiagramQueueProjects().clear();
		getDiagramQueueDevelopmentTasks().clear();
		getDiagramQueueTestingTasks().clear();
		getDiagramCountCompletedProjects().clear();

		getDiagramQueueProjects().getPainter().placeToXY(0, 0);
		getDiagramQueueDevelopmentTasks().getPainter().placeToXY(0, 0);
		getDiagramQueueTestingTasks().getPainter().placeToXY(0, 0);
		getDiagramCountCompletedProjects().getPainter().placeToXY(0, 0);

		getDiagramQueueProjects().setHorizontalMaxText(
				"" + chooseModelingTime.getInt());
		getDiagramQueueDevelopmentTasks().setHorizontalMaxText(
				"" + chooseModelingTime.getInt());
		getDiagramQueueTestingTasks().setHorizontalMaxText(
				"" + chooseModelingTime.getInt());
		getDiagramCountCompletedProjects().setVerticalMaxText(
				"" + chooseModelingTime.getInt() / 10);

		getDiagramQueueDevelopmentTasks().setVerticalMaxText(
				"" + Math.floor(chooseCountDevelopers.getInt() * (20.3)));

		if ((chooseCountTeams.getInt() + 4) >= chooseCountCustomers.getInt())
			getDiagramQueueProjects().setVerticalMaxText(
					"" + (chooseCountCustomers.getInt()) * 10);
		else
			getDiagramQueueProjects()
					.setVerticalMaxText(
							""
									+ Math.floor((chooseCountTeams.getInt() + chooseCountCustomers
											.getInt()) * (2.5)));
		if ((chooseCountTeams.getInt() + 2) <= chooseCountCustomers.getInt())
			getDiagramCountCompletedProjects().setHorizontalMaxText(
					"" + Math.floor((chooseModelingTime.getInt()) * (0.4)));
		else
			getDiagramCountCompletedProjects().setHorizontalMaxText(
					"" + Math.floor((chooseModelingTime.getInt()) * (0.3)));
		if ((chooseCountDevelopers.getInt() + 2) >= chooseCountTesters.getInt())
			getDiagramQueueTestingTasks().setVerticalMaxText(
					"" + Math.floor((chooseCountTesters.getInt()) * (2.5)));
		else
			getDiagramQueueTestingTasks().setVerticalMaxText(
					"" + Math.floor((chooseModelingTime.getInt()) * (0.1)));

		getDiagramQueueDevelopmentTasks().setVerticalMaxText(
				"" + chooseCountDevelopers.getInt() * chooseCountTeams.getInt()
						* 2);
		getDiagramQueueTestingTasks().setVerticalMaxText(
				"" + chooseCountTesters.getInt() * chooseCountTeams.getInt()
						/ 2);
		getDiagramCountCompletedProjects().setHorizontalMaxText(
				"" + chooseModelingTime.getInt());

		getJButtonTest().setEnabled(false);

		final Model model = (Model) ModelFactory.getInstance().createModel(
				getDispatcher());
		model.initForTest();
		getDispatcher().addDispatcherFinishListener(
				new DispatcherFinishListener() {

					@Override
					public void onDispatcherFinish() {
						getJButtonTest().setEnabled(true);
					}
				});
		getDispatcher().start();

	}

	private void startStat() {
		getDiagram4().clear();
		getDiagram5().clear();
		getDiagram4().getPainter().placeToXY(0, 0);
		getDiagram5().getPainter().placeToXY(0, 0);
		getJTextArea().setText("");
		getJTextArea1().setText("");
		getJButtonStat().setEnabled(false);

		final Model model = (Model) ModelFactory.getInstance().createModel(
				getDispatcher());
		model.initForStat();

		getDispatcher().addDispatcherFinishListener(
				new DispatcherFinishListener() {

					@Override
					public void onDispatcherFinish() {
						if (getJPanelStatTab().isShowing()) {
							model.getTimeWorkPr().showRelFrec(getDiagram4());
							getJTextArea().setText(
									model.getTimeWorkPr().toString());
							model.getTimeCorrectionTask().showRelFrec(
									getDiagram5());
							getJTextArea1().setText(
									model.getTimeCorrectionTask().toString());
						}
						getJButtonStat().setEnabled(true);

					}
				});
		getDispatcher().start();
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTextArea());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTextArea
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
		}
		return jTextArea;
	}

	/**
	 * This method initializes jScrollPane1
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTextArea1());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jTextArea1
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea1() {
		if (jTextArea1 == null) {
			jTextArea1 = new JTextArea();
		}
		return jTextArea1;
	}

	/**
	 * This method initializes jFrame
	 * 
	 * @return javax.swing.JFrame
	 */
	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setSize(800, 600);
			jFrame.setContentPane(getJContentPane());
			jFrame.setTitle("Team Success");
		}
		return jFrame;
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridLayout(0, 1, 0, 0));
			jContentPane.add(getSplitPane());
		}
		return jContentPane;
	}

	public Dispatcher getDispatcher() {
		if (dispatcher == null) {
			dispatcher = new Dispatcher();
			dispatcher.setProtocolFileName("");
		}
		return dispatcher;
	}

	private JPanel getJPanelRegressTab() {
		if (jPanelRegressTab == null) {
			jPanelRegressTab = new JPanel();
			jPanelRegressTab.setLayout(new GridLayout(2, 0, 0, 0));
			jPanelRegressTab.add(getPanel());
			jPanelRegressTab.add(getPanel_1());
		}
		return jPanelRegressTab;
	}

	private JPanel getJPanelTransTab() {
		if (jPanelTransTab == null) {
			jPanelTransTab = new JPanel();
			jPanelTransTab.setLayout(new GridLayout(2, 0, 0, 0));
			jPanelTransTab.add(getDiagramTransient());
			jPanelTransTab.add(getTransMonitorView());
		}
		return jPanelTransTab;
	}

	private JSplitPane getSplitPane() {
		if (splitPane == null) {
			splitPane = new JSplitPane();
			splitPane.setLeftComponent(getPanelFeft());
			splitPane.setRightComponent(getPanelRight());
		}
		return splitPane;
	}

	private JPanel getPanelFeft() {
		if (panelFeft == null) {
			panelFeft = new JPanel();
			panelFeft.setLayout(new GridLayout(11, 0, 0, 0));
			panelFeft.add(getChooseCountDevelopers());
			panelFeft.add(getChooseCountTesters());
			panelFeft.add(getChooseCountCustomers());
			panelFeft.add(getChooseCountTeams());
			panelFeft.add(getChooseBugChanse());
			panelFeft.add(getChooseModelingTime());
			panelFeft.add(getChooseRandom());
			panelFeft.add(getChooseRandomDevelopTime());
			panelFeft.add(getChooseRandomTestingTime());
			panelFeft.add(getChooseRandom3());

			chckbxUseDebugConsole = new JCheckBox(
					"\u0412\u044B\u0432\u043E\u0434 \u043D\u0430 \u043A\u043E\u043D\u0441\u043E\u043B\u044C");
			chckbxUseDebugConsole.setFont(new Font("Dialog", Font.PLAIN, 12));
			panelFeft.add(chckbxUseDebugConsole);
			chckbxUseDebugConsole.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					if (chckbxUseDebugConsole.isSelected()) {
						getDispatcher().setProtocolFileName("Console");
					} else {
						getDispatcher().setProtocolFileName("");
					}
				}
			});
		}
		return panelFeft;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(1, 0, 0, 0));
			panel.add(getDiagramRegress());
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new GridLayout(0, 2, 0, 0));
			panel_1.add(getExperimentControl());
			panel_1.add(getRegresAnaliser());
		}
		return panel_1;
	}
	private ExperimentControl getExperimentControl() {
		if (experimentControl == null) {
			experimentControl = new ExperimentControl();
			experimentControl.setFactory(ModelFactory.getInstance());
			experimentControl.setDiagram(getDiagramRegress());
		}
		return experimentControl;
	}
	private RegresAnaliser getRegresAnaliser() {
		if (regresAnaliser == null) {
			regresAnaliser = new RegresAnaliser();
			regresAnaliser.setDiagram(getDiagramRegress());
			regresAnaliser.setIRegresable(getExperimentControl());
		}
		return regresAnaliser;
	}

	private TransMonitorView getTransMonitorView() {
		if (transMonitorView == null) {
			transMonitorView = new TransMonitorView();
			transMonitorView.setFactory(ModelFactory.getInstance());
			transMonitorView.setDiagram(getDiagramTransient());
		}
		return transMonitorView;
	}
}
