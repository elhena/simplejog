/**
 * @author Fabien Vanden Bulck <fabien@elhena.com>
 */

package com.elhena.simplejog.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.elhena.simplejog.app.model.Application;
import com.elhena.simplejog.controller.AboutController;
import com.elhena.simplejog.util.loader.ResourcesLoader;
import java.awt.Component;

public class AboutFrame extends JDialog {

	// Constants
	private static final long serialVersionUID = 1L;
	private static final String WINDOW_TITLE = Application.NAME + " " + Application.VERSION + " - " + Application.LAST_UPDATE;
	private static final Dimension WINDOW_SIZE = new Dimension(400, 390);
	
	// Attributes
	private AboutController controller;
	
	// Swing components
	private JPanel contentPane;
	private JPanel pnlContent;
	private JLabel lblLogo;
	
	
	// Constructor
	public AboutFrame(AboutController controller) {
		this.controller = controller;
		
		// Window setup
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(ResourcesLoader.getImage("icon.png"));
		setTitle(WINDOW_TITLE);
		setSize(WINDOW_SIZE);
		setMinimumSize(WINDOW_SIZE);
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(5, 5));
		setContentPane(contentPane);
		
		// Panel content
		pnlContent = new JPanel();
		pnlContent.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.Y_AXIS));
		contentPane.add(pnlContent, BorderLayout.CENTER);
		
		// Logo
		lblLogo = new JLabel(new ImageIcon(ResourcesLoader.getImage("logo.png")));
		lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
		contentPane.add(lblLogo, BorderLayout.NORTH);
		
		// Informations
		pnlContent.add(new JLabel("Version: " + Application.VERSION));
		pnlContent.add(new JLabel("Date de mise à jour: " + Application.LAST_UPDATE));
		pnlContent.add(new JLabel("Auteur: Fabien Vanden Bulck <fabien@elhena.com>"));
		pnlContent.add(new JLabel("Sur demande de: Nicolas Lambert (HF011 Pionniers)"));
		pnlContent.add(new JLabel("Dédiée à: scouts et autres mouvements de jeunesse"));
		pnlContent.add(new JLabel("Page: " + Application.WEBPAGE));
		pnlContent.add(new JLabel("Site Internet: " + Application.WEBSITE));
		pnlContent.add(new JLabel("Copyright: " + Application.COPYRIGHT));
		pnlContent.add(new JLabel("License: Licence publique générale GNU"));
	}
}
