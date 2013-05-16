/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import com.elhena.simplejog.app.model.Application;
import com.elhena.simplejog.controller.NewCompetitionController;
import com.elhena.simplejog.model.Competition;
import com.elhena.simplejog.util.loader.ResourcesLoader;
import com.elhena.simplejog.util.logger.Log;

public class NewCompetitionFrame extends JFrame {

	// Constants
	private static final long serialVersionUID = 1L;
	private static final String WINDOW_TITLE = "Nouvelle compétition";
	private static final Dimension WINDOW_SIZE = new Dimension(400, 160);
	
	// Attributes
	private NewCompetitionController controller;
	
	// Swing components
	private JPanel contentPane;
	private JPanel pnlForm;
	private JPanel pnlActions;
	private JLabel lblFormName;
	private JLabel lblFormLocation;
	private JTextField tfdFormName;
	private JTextField tfdFormLocation;
	private JButton btnCancel;
	private JButton btnCreate;

	
	// Constructor
	public NewCompetitionFrame(final NewCompetitionController controller) {
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
		
		// Form
		pnlForm = new JPanel();
		pnlForm.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlForm.setLayout(new MigLayout());
		contentPane.add(pnlForm, BorderLayout.CENTER);
		
		// Form : Name
		lblFormName = new JLabel("Nom: ");
		tfdFormName = new JTextField();
		pnlForm.add(lblFormName, "cell 0 0");
		pnlForm.add(tfdFormName, "cell 1 0, width :340:");
		
		// Form : Location
		lblFormLocation = new JLabel("Lieu: ");
		tfdFormLocation = new JTextField();
		pnlForm.add(lblFormLocation, "cell 0 1");
		pnlForm.add(tfdFormLocation, "cell 1 1, width :340:");
		
		// Actions
		pnlActions = new JPanel();
		pnlActions.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlActions.setLayout(new FlowLayout(FlowLayout.RIGHT));
		contentPane.add(pnlActions, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Annuler");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dispose();
				controller.getFrontController().openHomeFrame();
			}
		});
		
		btnCreate = new JButton("Créer");
		btnCreate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				checkForm();
			}
		});
		
		pnlActions.add(btnCancel);
		pnlActions.add(btnCreate);
	}
	
	// Method : Check form
	public void checkForm() {
		ArrayList<String> errors = new ArrayList<String>();
		
		if (tfdFormName.getText().length() < 2)
			errors.add("Le nom de l'évènement ne peut être vide et doit être composé d'au moins deux caractères");
		
		if (tfdFormLocation.getText().length() < 2)
			errors.add("Le lieu de l'évènement doit être obligatoirement spécifié et contenir au moins deux caractères");
		
		if (errors.size() == 0) {
			Competition competition = new Competition(tfdFormName.getText(), tfdFormLocation.getText());
			Log.i("The competition '" + competition.getName() + "' has been created");
			dispose();
			controller.getFrontController().openCompetitionFrame(competition);
		}
		
		else {
			String message = "";
			for (String error : errors)
				message += "- " + error + ".\n";
			
			JOptionPane.showMessageDialog(this, message, Application.NAME + " - Informations incomplètes", JOptionPane.ERROR_MESSAGE);
		}
	}
}
