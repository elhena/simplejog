/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.elhena.simplejog.app.model.Application;
import com.elhena.simplejog.controller.HomeController;
import com.elhena.simplejog.util.loader.ResourcesLoader;

public class HomeFrame extends JFrame {
	
	// Constants
	private static final long serialVersionUID = 1L;
	private static final String WINDOW_TITLE = "Accueil";
	private static final Dimension WINDOW_SIZE = new Dimension(360, 220);

	// Attributes
	private HomeController controller;
	
	// Swing components
	private JPanel contentPane;
	private JPanel pnlChoice;
	private JPanel pnlChoiceLoad;
	private JPanel pnlChoiceNew;
	private JLabel lblNotice;
	private JButton btnLoad;
	private JButton btnNew;


	// Constructor
	public HomeFrame(final HomeController controller) {
		this.controller = controller;
		
		// Window setup
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(ResourcesLoader.getImage("icon.png"));
		setTitle(Application.NAME + " - " + WINDOW_TITLE);
		setSize(WINDOW_SIZE);
		setMinimumSize(WINDOW_SIZE);
		setResizable(false);
		setLocationRelativeTo(null);
	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// Label of information
		lblNotice = new JLabel("Veuillez choisir l'une des deux options suivantes:");
		contentPane.add(lblNotice, BorderLayout.NORTH);
		
		// Choices
		pnlChoice = new JPanel();
		pnlChoice.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlChoice.setLayout(new GridLayout(1, 2));
		contentPane.add(pnlChoice, BorderLayout.CENTER);
		
		// Choice : Load
		pnlChoiceLoad = new JPanel();
		pnlChoiceLoad.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlChoiceLoad.setLayout(new BoxLayout(pnlChoiceLoad, BoxLayout.Y_AXIS));
		pnlChoice.add(pnlChoiceLoad);
		btnLoad = new JButton(new ImageIcon(ResourcesLoader.getImage("home_load.png")));
		btnLoad.setToolTipText("Charger une compétition existante");
		btnLoad.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlChoiceLoad.add(btnLoad);
		pnlChoiceLoad.add(new JLabel("Charger une compétition"));
		
		// Action : Load competition
		btnLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				FileDialog dialog = new FileDialog(HomeFrame.this, "Charger une compétition", FileDialog.LOAD);
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
					controller.getFrontController().openCompetition(dialog.getDirectory() + dialog.getFile());
				}
			}
		});
		
		// Choice : New
		pnlChoiceNew = new JPanel();
		pnlChoiceNew.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlChoiceNew.setLayout(new BoxLayout(pnlChoiceNew, BoxLayout.Y_AXIS));
		pnlChoice.add(pnlChoiceNew);
		btnNew = new JButton(new ImageIcon(ResourcesLoader.getImage("home_new.png")));
		btnNew.setToolTipText("Créer une nouvelle compétition");
		btnNew.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlChoiceNew.add(btnNew);
		pnlChoiceNew.add(new JLabel("Créer une compétition"));
		
		// Action : New competition
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dispose();
				controller.getFrontController().openNewCompetitionFrame();
			}	
		});
	}
}
