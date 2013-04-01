/**
 * @author Fabien Vanden Bulck
 */

package com.elhena.simplejog.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.elhena.simplejog.app.model.Application;
import com.elhena.simplejog.controller.CompetitionController;
import com.elhena.simplejog.model.Race;
import com.elhena.simplejog.util.loader.ResourcesLoader;
import com.elhena.simplejog.view.jtable.model.RaceTableModel;

public class CompetitionFrame extends JFrame {

	// Constants
	private static final long serialVersionUID = 1L;
	private static final Dimension WINDOW_SIZE = new Dimension(800, 600);
	
	// Attributes
	private CompetitionController controller;
	
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
	
	// Constructor
	public CompetitionFrame(final CompetitionController controller) {
		this.controller = controller;
		
		// Window Setup
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(ResourcesLoader.getImage("icon.png"));
		setTitle(Application.NAME + " - " + controller.getCompetition().getName() + " @ " + controller.getCompetition().getLocation());
		setSize(WINDOW_SIZE);
		setMinimumSize(WINDOW_SIZE);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
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
					controller.openViewRaceFrame(controller.getCompetition().getRaces().get(tblJoggers.convertColumnIndexToView(tblJoggers.getSelectedRow())));
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
					controller.openSetRaceFrame(controller.getCompetition().getRaces().get(tblJoggers.convertColumnIndexToView(tblJoggers.getSelectedRow())));
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
					
					if (choice == JOptionPane.YES_OPTION)
						controller.removeRace(raceToDelete);
				}
				
				else
					JOptionPane.showMessageDialog(CompetitionFrame.this, "Aucun participant n'a été sélectionné pour être supprimé.", Application.NAME + " - Suppression impossible", JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	
	// Method : Update table
	public void updateTable() {
		((RaceTableModel) tblJoggers.getModel()).fireTableDataChanged();
	}
}
