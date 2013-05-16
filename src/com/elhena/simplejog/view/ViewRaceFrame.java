/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.elhena.simplejog.app.model.Application;
import com.elhena.simplejog.controller.CompetitionController;
import com.elhena.simplejog.controller.ViewRaceController;
import com.elhena.simplejog.util.loader.ResourcesLoader;

public class ViewRaceFrame extends JDialog {

	// Constants
	private static final long serialVersionUID = 1L;
	private static final String WINDOW_TITLE = Application.NAME + " - ";
	private static final Dimension WINDOW_SIZE = new Dimension(400, 140);
	
	// Attributes
	private ViewRaceController controller;
	
	// Swing components
	private JPanel contentPane;
	private JPanel pnlInfos;
	private JPanel pnlControls;
	private JLabel lblNumber;
	private JLabel lblName;
	private JLabel lblBirthday;
	private JLabel lblSex;
	private JButton btnClose;
	
	
	// Constructor
	public ViewRaceFrame(ViewRaceController controller) {
		this.controller = controller;
		
		// Window setup
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(ResourcesLoader.getImage("icon.png"));
		setTitle(WINDOW_TITLE + controller.getRace().getJogger().getName());
		setSize(WINDOW_SIZE);
		setMinimumSize(WINDOW_SIZE);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(((CompetitionController) controller.getParentController()).getFrame());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// Infos panel
		pnlInfos = new JPanel();
		pnlInfos.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlInfos.setLayout(new BoxLayout(pnlInfos, BoxLayout.Y_AXIS));
		contentPane.add(pnlInfos, BorderLayout.CENTER);
		
		lblNumber = new JLabel("Numéro: " + controller.getRace().getNumber());
		lblName = new JLabel("Nom: " + controller.getRace().getJogger().getName());
		lblBirthday = new JLabel("Date de naissance: " + new SimpleDateFormat("dd/MM/yyyy").format(controller.getRace().getJogger().getBirthday()));
		lblSex = new JLabel("Sexe: " + controller.getRace().getJogger().getSexAsString());
		pnlInfos.add(lblNumber);
		pnlInfos.add(lblName);
		pnlInfos.add(lblBirthday);
		pnlInfos.add(lblSex);
		
		// Controls panel
		pnlControls = new JPanel();
		pnlControls.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlControls.setLayout(new FlowLayout(FlowLayout.RIGHT));
		contentPane.add(pnlControls, BorderLayout.SOUTH);
		
		btnClose = new JButton("Fermer");
		pnlControls.add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				dispose();
			}
		});
	}
}
