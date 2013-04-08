/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.elhena.simplejog.app.model.Application;
import com.elhena.simplejog.controller.CompetitionController;
import com.elhena.simplejog.io.CompetitionPDFExporter;
import com.elhena.simplejog.model.Competition;
import com.elhena.simplejog.model.CompetitionStatus;
import com.elhena.simplejog.model.Race;
import com.elhena.simplejog.util.loader.ResourcesLoader;
import com.elhena.simplejog.view.model.jtable.RaceTableModel;

public class CompetitionFrame extends JFrame {

	// Constants
	private static final long serialVersionUID = 1L;
	private static final Dimension WINDOW_SIZE = new Dimension(800, 600);
	
	// Attributes
	private CompetitionController controller;
	private Timer timer;
	public static String savePath;
	
	// Swing components
	private JPanel contentPane;
	private JPanel pnlCompetition;
	private JPanel pnlCompetitionInfos;
	private JPanel pnlCompetitionControls;
	private JPanel pnlCompetitionTimer;
	private JPanel pnlJoggersControls;
	private JPanel pnlJoggersControlsAdd;
	private JPanel pnlJoggersControlsEdit;
	private JScrollPane pnlJoggers;
	private JTable tblJoggers;
	private JLabel lblInfos;
	private JLabel lblTime;
	private JButton btnStartandStop;
	private JButton btnAddJogger;
	private JButton btnViewJogger;
	private JButton btnEditJogger;
	private JButton btnDeleteJogger;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JMenuItem menuSave;
	private JMenuItem menuItemStartCompetition;
	private JMenuItem menuItemStopCompetition;
	
	// Constructor
	public CompetitionFrame(final CompetitionController controller) {
		this.controller = controller;
		
		// Window setup
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(ResourcesLoader.getImage("icon.png"));
		setTitle(Application.NAME + " - " + controller.getCompetition().getName() + " @ " + controller.getCompetition().getLocation());
		setSize(WINDOW_SIZE);
		setMinimumSize(WINDOW_SIZE);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// Menu
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
				
		// Menu : File
		menu = new JMenu("Fichier");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Nouveau");
		menuItem.setMnemonic(KeyEvent.VK_N);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (!Competition.dataIsUpdated()) {
					int choice = JOptionPane.showConfirmDialog(CompetitionFrame.this, "Êtes-vous sûr de vouloir fermer cette compétition non sauvegardée?", "Fermer sans sauvegarder?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					
					if (choice == JOptionPane.YES_OPTION) {
						dispose();
						controller.closeArrivalCaptureFrame();
						controller.getFrontController().openNewCompetitionFrame();
					}
				}
				
				else {
					dispose();
					controller.closeArrivalCaptureFrame();
					controller.getFrontController().openNewCompetitionFrame();
				}
				
			}
		});
		
		menuItem = new JMenuItem("Ouvrir");
		menuItem.setMnemonic(KeyEvent.VK_O);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (!Competition.dataIsUpdated()) {
					int choice = JOptionPane.showConfirmDialog(CompetitionFrame.this, "Êtes-vous sûr de vouloir fermer cette compétition non sauvegardée", "Fermer sans sauvegarder?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					
					if (choice == JOptionPane.YES_OPTION) {
						FileDialog dialog = new FileDialog(CompetitionFrame.this, "Charger une compétition...", FileDialog.LOAD);
						dialog.setFilenameFilter(new FilenameFilter() {
							@Override
							public boolean accept(File directory, String fileName) {
								if (fileName.matches(".+[.]jog"))
									return true;
								else
									return false;
							}
						});
						
						dialog.setVisible(true);
						
						if (!(dialog.getDirectory() + dialog.getFile()).equals("nullnull")) {
							dispose();
							controller.closeArrivalCaptureFrame();
							controller.getFrontController().openCompetition(dialog.getDirectory() + dialog.getFile());
						}
					}
				}
				
				else {
					FileDialog dialog = new FileDialog(CompetitionFrame.this, "Charger une compétition...", FileDialog.LOAD);
					dialog.setFilenameFilter(new FilenameFilter() {
						@Override
						public boolean accept(File directory, String fileName) {
							if (fileName.matches(".+[.]jog"))
								return true;
							else
								return false;
						}
					});
					
					dialog.setVisible(true);
					
					if (!(dialog.getDirectory() + dialog.getFile()).equals("nullnull")) {
						dispose();
						controller.closeArrivalCaptureFrame();
						controller.getFrontController().openCompetition(dialog.getDirectory() + dialog.getFile());
					}
				}
			}
		});
		
		menuSave = new JMenuItem("Sauvegarder");
		menuSave.setMnemonic(KeyEvent.VK_S);
		menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
		menu.add(menuSave);
		menuSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (savePath == null) {
				
					FileDialog dialog = new FileDialog(CompetitionFrame.this, "Enregistrer la compétition sous...", FileDialog.SAVE);
					dialog.setVisible(true);
					
					savePath = dialog.getDirectory() + dialog.getFile() + ".jog";
					
					if (savePath.equals("nullnull.jog"))
						savePath = dialog.getDirectory() + "competition.jog";
				}
				
				controller.saveCompetition(savePath);
				JOptionPane.showMessageDialog(CompetitionFrame.this, "La compétition a bien été sauvegardée!", "Compétition sauvegardée!", JOptionPane.INFORMATION_MESSAGE);
				Competition.notifyDataUpdated();
				disableSaveFunction();
			}
		});
		
		menuItem = new JMenuItem("Sauvegarder sous...");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				FileDialog dialog = new FileDialog(CompetitionFrame.this, "Enregistrer la compétition sous...", FileDialog.SAVE);
				dialog.setVisible(true);
				
				String path = dialog.getDirectory() + dialog.getFile() + ".jog";
				
				if (!path.equals("nullnull.jog")) {
					controller.saveCompetition(dialog.getDirectory() + dialog.getFile() + ".jog");
					JOptionPane.showMessageDialog(CompetitionFrame.this, "La compétition a bien été sauvegardée!", "Compétition sauvegardée", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Fermer");
		menuItem.setMnemonic(KeyEvent.VK_F);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (!Competition.dataIsUpdated()) {
					int choice = JOptionPane.showConfirmDialog(CompetitionFrame.this, "Êtes-vous sûr de vouloir fermer cette compétition non sauvegardée?", "Fermer sans sauvegarder?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					
					if (choice == JOptionPane.YES_OPTION) {
						dispose();
						controller.getFrontController().openHomeFrame();
						controller.closeArrivalCaptureFrame();
					}
				}
				
				else {
					dispose();
					controller.getFrontController().openHomeFrame();
				}
			}
		});
		
		menuItem = new JMenuItem("Quitter");
		menuItem.setMnemonic(KeyEvent.VK_Q);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (!Competition.dataIsUpdated()) {
					int choice = JOptionPane.showConfirmDialog(CompetitionFrame.this, "Êtes-vous sûr de vouloir quitter l'application alors que la compétition n'est pas sauvegardée?", "Quitter sans sauvegarder?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					
					if (choice == JOptionPane.YES_OPTION) {
						dispose();
						System.exit(0);
					}
				}
				
				else {
					dispose();
					System.exit(0);
				}
			}
		});
		
		// Menu : Competition
		menu = new JMenu("Compétition");
		menu.setMnemonic(KeyEvent.VK_C);
		menuBar.add(menu);
		
		menuItemStartCompetition = new JMenuItem("Démarrer");
		menuItemStartCompetition.setMnemonic(KeyEvent.VK_D);
		menuItemStartCompetition.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		menu.add(menuItemStartCompetition);
		menuItemStartCompetition.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				controller.startCompetition();
			}
		});
		
		menuItemStopCompetition = new JMenuItem("Arrêter");
		menuItemStopCompetition.setEnabled(false);
		menuItemStopCompetition.setMnemonic(KeyEvent.VK_A);
		menuItemStopCompetition.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		menu.add(menuItemStopCompetition);
		menuItemStopCompetition.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (JOptionPane.showConfirmDialog(CompetitionFrame.this, "Êtes-vous sûr de vouloir mettre fin à la compétition? Cela sera irreversible.", Application.NAME + " - Arrêter la compétition?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) controller.stopCompetition();
			}
		});
		
		// Menu : Help
		menu = new JMenu("?");
		menu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("À propos...");
		menuItem.setMnemonic(KeyEvent.VK_A);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				controller.openAboutFrame();
			}
		});
		
		
		// Competition panel
		pnlCompetition = new JPanel();
		pnlCompetition.setBorder(new EmptyBorder(0, 0, 10, 0));
		pnlCompetition.setLayout(new BoxLayout(pnlCompetition, BoxLayout.Y_AXIS));
		contentPane.add(pnlCompetition, BorderLayout.NORTH);
		
		// Competition infos
		pnlCompetitionInfos = new JPanel();
		pnlCompetitionInfos.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlCompetitionInfos.setLayout(new BoxLayout(pnlCompetitionInfos, BoxLayout.X_AXIS));
		pnlCompetition.add(pnlCompetitionInfos);
		
		// Competition details
		lblInfos = new JLabel("Compétition: " + controller.getCompetition().getName() + " à " + controller.getCompetition().getLocation());
		pnlCompetitionInfos.add(lblInfos);
		
		// Competition controls
		pnlCompetitionControls = new JPanel();
		pnlCompetitionControls.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlCompetitionControls.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlCompetitionInfos.add(pnlCompetitionControls);
		
		btnStartandStop = new JButton("Démarrer");
		btnStartandStop.setToolTipText("Commencer la course");
		pnlCompetitionControls.add(btnStartandStop);
		btnStartandStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				switch(controller.getCompetition().getStatus()) {
					case STANDBY: controller.startCompetition(); break;
					case RUNNING: if (JOptionPane.showConfirmDialog(CompetitionFrame.this, "Êtes-vous sûr de vouloir mettre fin à la compétition? Cela sera irreversible.", Application.NAME + " - Arrêter la compétition?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) controller.stopCompetition(); break;
					case FINISHED: generatePDF(); break;
					default: break;
				}	
			}
		});
		
		// Competition timer
		pnlCompetitionTimer = new JPanel();
		pnlCompetitionTimer.setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Chronomètre", TitledBorder.CENTER, TitledBorder.TOP));
		pnlCompetitionTimer.setLayout(new BorderLayout(0, 0));
		pnlCompetition.add(pnlCompetitionTimer);
		
		lblTime = new JLabel("00:00:00");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Times New Roman", Font.BOLD, 40));
		pnlCompetitionTimer.add(lblTime, BorderLayout.CENTER);
		
		// Joggers
		tblJoggers = new JTable(new RaceTableModel(controller.getCompetition()));
		tblJoggers.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tblJoggers.setAutoCreateRowSorter(true);
		tblJoggers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pnlJoggers = new JScrollPane(tblJoggers);
		pnlJoggers.setBorder(new LineBorder(Color.GRAY));
		contentPane.add(pnlJoggers, BorderLayout.CENTER);
		
		// Joggers controls panel
		pnlJoggersControls = new JPanel();
		pnlJoggersControls.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlJoggersControls.setLayout(new BoxLayout(pnlJoggersControls, BoxLayout.X_AXIS));
		contentPane.add(pnlJoggersControls, BorderLayout.SOUTH);
		
		// Joggers controls panel add
		pnlJoggersControlsAdd = new JPanel();
		pnlJoggersControlsAdd.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlJoggersControlsAdd.setLayout(new FlowLayout(FlowLayout.LEFT));
		pnlJoggersControls.add(pnlJoggersControlsAdd);
		
		btnAddJogger = new JButton("Ajouter");
		btnAddJogger.setToolTipText("Ajouter un nouveau participant");
		pnlJoggersControlsAdd.add(btnAddJogger);
		btnAddJogger.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				controller.openSetRaceFrame(null);
			}
		});
		
		// Joggers controls panel edit & delete
		pnlJoggersControlsEdit = new JPanel();
		pnlJoggersControlsEdit.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlJoggersControlsEdit.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnlJoggersControls.add(pnlJoggersControlsEdit);
		
		btnViewJogger = new JButton("Consulter");
		btnViewJogger.setToolTipText("Afficher les informations du participant sélectionné");
		pnlJoggersControlsEdit.add(btnViewJogger);
		btnViewJogger.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (tblJoggers.getSelectedRow() != -1)
					controller.openViewRaceFrame(controller.getCompetition().getRaces().get(tblJoggers.convertRowIndexToModel(tblJoggers.getSelectedRow())));
				else
					JOptionPane.showMessageDialog(CompetitionFrame.this, "Aucun participant n'a été sélectionné pour être consulté.", Application.NAME + " - Consultation impossible", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnEditJogger = new JButton("Modifier");
		btnEditJogger.setToolTipText("Modifier le participant sélectionné");
		pnlJoggersControlsEdit.add(btnEditJogger);
		btnEditJogger.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (tblJoggers.getSelectedRow() != -1)
					controller.openSetRaceFrame(controller.getCompetition().getRaces().get(tblJoggers.convertRowIndexToModel(tblJoggers.getSelectedRow())));
				else
					JOptionPane.showMessageDialog(CompetitionFrame.this, "Aucun participant n'a été sélectionné pour être modifié.", Application.NAME + " - Modification impossible", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnDeleteJogger = new JButton("Supprimer");
		btnDeleteJogger.setToolTipText("Supprimer le participant sélectionné");
		pnlJoggersControlsEdit.add(btnDeleteJogger);
		btnDeleteJogger.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				if (tblJoggers.getSelectedRow() != -1) {
					Race raceToDelete = controller.getCompetition().getRaces().get(tblJoggers.convertRowIndexToModel(tblJoggers.getSelectedRow()));
					
					int choice =  JOptionPane.showConfirmDialog(CompetitionFrame.this, "Êtes-vous sûr de vouloir supprimer le participant '" + raceToDelete.getJogger().getName() + "'?", Application.NAME + " - Suppression de la sélection", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					
					if (choice == JOptionPane.YES_OPTION) {
						controller.removeRace(raceToDelete);
						Competition.notifyDataChanged();
						enableSaveFunction();
					}
				}
				
				else
					JOptionPane.showMessageDialog(CompetitionFrame.this, "Aucun participant n'a été sélectionné pour être supprimé.", Application.NAME + " - Suppression impossible", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		// Fermeture
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				if (!Competition.dataIsUpdated()) {
					int choice = JOptionPane.showConfirmDialog(CompetitionFrame.this, "Êtes-vous sûr de vouloir quitter l'application alors que la compétition n'est pas sauvegardée? Dans ce cas, les modifications ne seront pas prises en compte.", "Quitter sans sauvegarder?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					
					if (choice == JOptionPane.YES_OPTION) {
						dispose();
						System.exit(0);
					}
				}
				
				else {
					dispose();
					System.exit(0);
				}
			}
		});
		
		// Restore competition
		if (controller.getCompetition().getStatus() == CompetitionStatus.RUNNING) {
			startCompetition();
			controller.openArrivalCaptureFrame();
			disableSaveFunction();
		}
		
		if (controller.getCompetition().getStatus() == CompetitionStatus.FINISHED) {
			btnAddJogger.setEnabled(false);
			btnEditJogger.setEnabled(false);
			btnDeleteJogger.setEnabled(false);
			btnStartandStop.setText("Générer le PDF");
			btnStartandStop.setToolTipText("Obtenir le classement au format PDF");
			btnEditJogger.setEnabled(true);
			menuItemStartCompetition.setEnabled(false);
			menuItemStopCompetition.setEnabled(false);
			disableSaveFunction();
		}
	}
	
	// Method : Update table
	public void updateTable() {
		((RaceTableModel) tblJoggers.getModel()).fireTableDataChanged();
	}
	
	// Method : Start competition
	public void startCompetition() {
		// Disable buttons
		btnStartandStop.setText("Arrêter");
		btnStartandStop.setToolTipText("Mettre fin à la course");
		btnAddJogger.setEnabled(false);
		btnEditJogger.setEnabled(false);
		btnDeleteJogger.setEnabled(false);
		menuItemStartCompetition.setEnabled(false);
		menuItemStopCompetition.setEnabled(true);
		
		// Update timer
		timer = new Timer();;
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				lblTime.setText(new SimpleDateFormat("HH:mm:ss").format(new Date(new Date().getTime() - controller.getCompetition().getStartTime().getTime() - (1000 * 60 * 60))));
			}
		}, new Date(), 1000);
		
		updateTable();
		Competition.notifyDataChanged();
		enableSaveFunction();
	}
	
	// Method : Stop competition
	public void stopCompetition() {
		// Kill timer
		timer.cancel();
		
		// Enable buttons
		btnStartandStop.setText("Générer le classement");
		btnStartandStop.setToolTipText("Obtenir le classement au format PDF");
		btnEditJogger.setEnabled(true);
		menuItemStopCompetition.setEnabled(false);
		
		updateTable();
		Competition.notifyDataChanged();
		enableSaveFunction();
	}
	
	// Method : Enable save function
	public void enableSaveFunction() {
		menuSave.setEnabled(true);
	}
	
	// Method : Disable save function
	public void disableSaveFunction() {
		menuSave.setEnabled(false);
	}
	
	// Method : Generate PDF file
	public void generatePDF() {
		FileDialog dialog = new FileDialog(this, "Exporter la compétition en PDF...", FileDialog.SAVE);
		dialog.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String fileName) {
				if (fileName.matches(".+[.]pdf"))
					return true;
				else
					return false;
			}
		});
		
		dialog.setVisible(true);
		
		if (dialog.getDirectory() != null && dialog.getFile() != null) {
			CompetitionPDFExporter exporter = new CompetitionPDFExporter(dialog.getDirectory() + dialog.getFile() + ".pdf");
			exporter.generate(controller.getCompetition());
			JOptionPane.showMessageDialog(this, "Le classement a bien été exporté!", "Classement exporté en PDF", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}