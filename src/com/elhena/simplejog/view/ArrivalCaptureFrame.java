/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.elhena.simplejog.app.model.Application;
import com.elhena.simplejog.controller.ArrivalCaptureController;
import com.elhena.simplejog.controller.CompetitionController;
import com.elhena.simplejog.model.Race;
import com.elhena.simplejog.util.loader.ResourcesLoader;

public class ArrivalCaptureFrame extends JFrame {

	// Constants
	private static final long serialVersionUID = 1L;
	private static final String WINDOW_TITLE = "Capture à l'arrivée";
	private static final Dimension WINDOW_SIZE = new Dimension(200, 220);
	
	// Attributes
	private ArrivalCaptureController controller;
	
	// Swing components
	private JPanel contentPane;
	private JPanel pnlCapture;
	private JPanel pnlPreviousCapture;
	private JLabel lblInstruction;
	private JLabel lblPreviousCapture;
	private JLabel lblPreviousCaptureName;
	private JTextField tfdCapture;
	private JButton btnCapture;
	
	
	// Constructor
	public ArrivalCaptureFrame(ArrivalCaptureController controller) {
		this.controller = controller;
		
		// Window setup
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(ResourcesLoader.getImage("icon.png"));
		setTitle(WINDOW_TITLE);
		setSize(WINDOW_SIZE);
		setMinimumSize(WINDOW_SIZE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// Capture panel
		pnlCapture = new JPanel();
		pnlCapture.setBorder(new EmptyBorder(5, 55, 20, 55));
		pnlCapture.setLayout(new BoxLayout(pnlCapture, BoxLayout.Y_AXIS));
		contentPane.add(pnlCapture, BorderLayout.CENTER);
		
		lblInstruction = new JLabel("Numéro: ");
		lblInstruction.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlCapture.add(lblInstruction);
		
		tfdCapture = new JTextField();
		tfdCapture.setHorizontalAlignment(SwingConstants.CENTER);
		tfdCapture.setSize(new Dimension(50, 50));
		tfdCapture.setFont(new Font("Times New Roman", Font.BOLD, 60));
		tfdCapture.requestFocus();
		pnlCapture.add(tfdCapture);
		
		btnCapture = new JButton("Capturer");
		btnCapture.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlCapture.add(btnCapture);
		btnCapture.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				if (tfdCapture.getText().matches("[0-9]+"))
					arrival(Integer.parseInt(tfdCapture.getText()));
				else
					JOptionPane.showMessageDialog(ArrivalCaptureFrame.this, "Le champ ne tolère qu'un nombre.", Application.NAME + " - Erreur de saisie", JOptionPane.ERROR_MESSAGE);
;			}
		});
		
		// Last arrival panel
		pnlPreviousCapture = new JPanel();
		pnlPreviousCapture.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlPreviousCapture.setLayout(new BoxLayout(pnlPreviousCapture, BoxLayout.Y_AXIS));
		contentPane.add(pnlPreviousCapture, BorderLayout.SOUTH);
		
		lblPreviousCapture = new JLabel("Arrivée précédente: ");
		lblPreviousCapture.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPreviousCaptureName = new JLabel();
		lblPreviousCaptureName.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlPreviousCapture.add(lblPreviousCapture);
		pnlPreviousCapture.add(lblPreviousCaptureName);
	}
		
	// Method : arrival
	private void arrival(int number) {
		Race race = ((CompetitionController) controller.getParentController()).addArrival(number);
		
		tfdCapture.setText("");
		tfdCapture.requestFocus();
		
		if (race != null)
			lblPreviousCaptureName.setText(race.getJogger().getName());
	}
}
