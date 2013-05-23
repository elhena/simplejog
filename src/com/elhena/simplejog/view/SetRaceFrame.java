/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import com.elhena.simplejog.app.model.Application;
import com.elhena.simplejog.controller.CompetitionController;
import com.elhena.simplejog.controller.SetRaceController;
import com.elhena.simplejog.model.Competition;
import com.elhena.simplejog.model.Jogger;
import com.elhena.simplejog.model.JoggerSex;
import com.elhena.simplejog.model.Race;
import com.elhena.simplejog.util.loader.ResourcesLoader;
import com.elhena.simplejog.util.logger.Log;

public class SetRaceFrame extends JDialog {

	// Constants
	private static final long serialVersionUID = 1L;
	private static final Dimension WINDOW_SIZE = new Dimension(400, 200);
	private static final ResourceBundle RESOURCES = ResourceBundle.getBundle(SetRaceFrame.class.getName());
	
	// Attributes
	private SetRaceController controller;
	private boolean newRace;
	
	// Swing components
	private JPanel contentPane;
	private JPanel pnlForm;
	private JPanel pnlControls;
	private JLabel lblFormNumber;
	private JLabel lblFormName;
	private JLabel lblFormSex;
	private JLabel lblFormBirthday;
	private JSpinner spnFormNumber;
	private JTextField tfdFormName;
	private JTextField tfdFormBirthday;
	private JRadioButton rbtnSexMale;
	private JRadioButton rbtnSexFemale;
	private JButton btnCancel;
	private JButton btnEdit;
	private ButtonGroup rbtnSex;
	
	
	// Constructor
	public SetRaceFrame(SetRaceController controller) {
		this.controller = controller;
		
		if (controller.getRace() == null)
			newRace = true;
		
		// Window setup
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(ResourcesLoader.getImage("icon.ong"));
		if (newRace)
			setTitle(Application.NAME + " - " + RESOURCES.getString("title.add"));
		else
			setTitle(Application.NAME + " - " + RESOURCES.getString("title.edit"));
		setSize(WINDOW_SIZE);
		setMinimumSize(WINDOW_SIZE);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(((CompetitionController) controller.getParentController()).getFrame());
		setAlwaysOnTop(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// Form
		pnlForm = new JPanel();
		pnlForm.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlForm.setLayout(new MigLayout());
		contentPane.add(pnlForm, BorderLayout.CENTER);
		
		// Form : Number
		lblFormNumber = new JLabel(RESOURCES.getString("label.lblFormNumber") + ": ");
		spnFormNumber = new JSpinner();
		spnFormNumber.setValue(((CompetitionController) controller.getParentController()).getNextNumberAvailable());
		pnlForm.add(lblFormNumber, "cell 0 0");
		pnlForm.add(spnFormNumber, "cell 1 0, width :50:");
		
		// Form : Name
		lblFormName = new JLabel(RESOURCES.getString("label.lblFormName") + ": ");
		tfdFormName = new JTextField();
		tfdFormName.requestFocus();
		pnlForm.add(lblFormName, "cell 0 1");
		pnlForm.add(tfdFormName, "cell 1 1, width :340:");
		
		// Form : Birthday
		lblFormBirthday = new JLabel(RESOURCES.getString("label.lblFormBirthday") + ": ");
		tfdFormBirthday = new JTextField(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		pnlForm.add(lblFormBirthday, "cell 0 2");
		pnlForm.add(tfdFormBirthday, "cell 1 2, width :95:");
		
		// Form : Sex
		lblFormSex = new JLabel(RESOURCES.getString("label.lblFormSex") + ": ");
		rbtnSex = new ButtonGroup();
		rbtnSexMale = new JRadioButton(RESOURCES.getString("radio.rbtnSexMale"));
		rbtnSexMale.setSelected(true);
		rbtnSexFemale = new JRadioButton(RESOURCES.getString("radio.rbtnSexFemale"));
		rbtnSex.add(rbtnSexMale);
		rbtnSex.add(rbtnSexFemale);
		pnlForm.add(lblFormSex, "cell 0 3");
		pnlForm.add(rbtnSexMale, "cell 1 3");
		pnlForm.add(rbtnSexFemale, "cell 1 3");
		
		// Controls panel
		pnlControls = new JPanel();
		pnlControls.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlControls.setLayout(new FlowLayout(FlowLayout.RIGHT));
		contentPane.add(pnlControls, BorderLayout.SOUTH);
		
		btnCancel = new JButton(RESOURCES.getString("button.btnCancel"));
		btnCancel.setToolTipText(RESOURCES.getString("button.btnCancel.tip"));
		pnlControls.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
		
		btnEdit = new JButton();
		if (newRace)
			btnEdit.setText(RESOURCES.getString("button.btnEdit.add"));
		else
			btnEdit.setText(RESOURCES.getString("button.btnEdit.edit"));
		pnlControls.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				checkForm();
			}
		});
		
		// Hydrate form
		if (!newRace)
			hydrate(controller.getRace());
	}
	
	// Method : Hydrate form
	private void hydrate(Race race) {
		spnFormNumber.setValue(race.getNumber());
		tfdFormName.setText(race.getJogger().getName());
		tfdFormBirthday.setText(new SimpleDateFormat("dd/MM/yyyy").format(race.getJogger().getBirthday()));
		
		switch(race.getJogger().getSex()) {
			case MAN: rbtnSexMale.setSelected(true); break;
			case WOMAN: rbtnSexFemale.setSelected(true); break;
			default: rbtnSexMale.setSelected(true); break;
		}
	}
	
	// Method : Check form
	private void checkForm() {
		ArrayList<String> errors = new ArrayList<String>();
		
		// Number check
		if (!((CompetitionController) controller.getParentController()).numberIsAvailable((Integer) spnFormNumber.getValue()) || ((Integer) spnFormNumber.getValue()) < 1) {
			
			if (newRace)
				errors.add(RESOURCES.getString("formError.number.noavailable"));
			
			if (!(newRace || ((Integer) spnFormNumber.getValue()) == controller.getRace().getNumber()))
				errors.add(RESOURCES.getString("formError.number.noavailable"));
		}
		
		// Name check
		if (tfdFormName.getText().length() < 4)
			errors.add(RESOURCES.getString("formError.name.length"));
		
		// Birthday check
		if (!tfdFormBirthday.getText().matches("[0-9]{2}/[0-9]{2}/[0-9]{4}"))
			errors.add(RESOURCES.getString("formError.birthday.format") + " 'dd/mm/yyyy'");
		
		// Validating chek
		if (errors.size() == 0) {
			Jogger newJogger = null;
			try {
				newJogger = new Jogger(tfdFormName.getText(), JoggerSex.WOMAN, new SimpleDateFormat("dd/MM/yyyy").parse(tfdFormBirthday.getText()));
			} 
			
			catch (ParseException e) {
				Log.x(e.getMessage());
				if (Log.debug)
					if (Log.debug)
						e.printStackTrace();
			}
			
			// New race
			if (newRace) {
				Race race = new Race((Integer) spnFormNumber.getValue(), ((CompetitionController) controller.getParentController()).getCompetition(), newJogger);
				if (rbtnSexMale.isSelected())
					race.getJogger().setSex(JoggerSex.MAN);
				else
					race.getJogger().setSex(JoggerSex.WOMAN);
				
				((CompetitionController) controller.getParentController()).addRace(race);		
				dispose();
			}
			
			// Edit race
			else {
				controller.getRace().setNumber((Integer) spnFormNumber.getValue());
				controller.getRace().getJogger().setName(tfdFormName.getText());
				try {
					controller.getRace().getJogger().setBirthday(new SimpleDateFormat("dd/MM/yyyy").parse(tfdFormBirthday.getText()));
				}
				
				catch (ParseException e) {
					Log.x(e.getMessage());
					if (Log.debug)
						e.printStackTrace();
				}
				
				if (rbtnSexMale.isSelected())
					controller.getRace().getJogger().setSex(JoggerSex.MAN);
				else
					controller.getRace().getJogger().setSex(JoggerSex.WOMAN);
				
				dispose();
				((CompetitionController) controller.getParentController()).refreshRaces();
				Log.i("Jogger '" + controller.getRace().getJogger().getName() + "' has been modified");
			}
			
			Competition.notifyDataChanged();
			((CompetitionController) controller.getParentController()).getFrame().enableSaveFunction();
		}
		
		// Show errors
		else {
			String message = "";
			for (String error: errors)
				message += "- " + error + ".\n";
			
			JOptionPane.showMessageDialog(this, message, Application.NAME + " - " + RESOURCES.getString("dialog.formError.title"), JOptionPane.ERROR_MESSAGE);
		}
	}
}
