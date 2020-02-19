package com.gestionmagasin.views;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import com.gestionmagasin.beans.Utilisateurs;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame {


	private JFrame frmGestionMagasin;
	private Utilisateurs user;
	//private JFrame frmGestionMagasin;
	private JLayeredPane layeredPane; 
	
	public JFrame getFrame()
	{
		return frmGestionMagasin;
	}
	
	public void switchPanels (JPanel panel)
	{
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame(null);
					window.frmGestionMagasin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame(Utilisateurs user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmGestionMagasin = new JFrame();
		frmGestionMagasin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
int comfirm = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?", "Comfirmation", JOptionPane.YES_NO_OPTION);
				
				if(comfirm == JOptionPane.YES_OPTION)
					frmGestionMagasin.dispose();
			}
		});
		frmGestionMagasin.setTitle("Gestion Magasin");
		frmGestionMagasin.setBounds(100, 100, 700, 400);
		frmGestionMagasin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmGestionMagasin.getContentPane().setLayout(null);
		frmGestionMagasin.setLocationRelativeTo(null) ;
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 684, 20);
		frmGestionMagasin.getContentPane().add(menuBar);
		
		JMenu produitsMenu = new JMenu("Produits");
		menuBar.add(produitsMenu);
		
		JMenuItem listProduitsMenuItem = new JMenuItem("Liste");
		produitsMenu.add(listProduitsMenuItem);
		
		JSeparator separator_2 = new JSeparator();
		produitsMenu.add(separator_2);
		
		JMenuItem ajouterProduitsMenuItem_1 = new JMenuItem("Ajouter");
		produitsMenu.add(ajouterProduitsMenuItem_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setMaximumSize(new Dimension(20, 20));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		menuBar.add(separator_1);
		
		JMenu categoriesMenu = new JMenu("Cat\u00E9gories");
		menuBar.add(categoriesMenu);
		
		JMenuItem listCategoriesMenuItem = new JMenuItem("Liste");
		listCategoriesMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CategoriesPanel catPanel = new CategoriesPanel();
				switchPanels(catPanel);			
				}
		});
		categoriesMenu.add(listCategoriesMenuItem);
		
		
		
		

		JSeparator separator_3 = new JSeparator();
		categoriesMenu.add(separator_3);
		
		JMenuItem ajouterCategoriesMenuItem = new JMenuItem("Ajouter");
		categoriesMenu.add(ajouterCategoriesMenuItem);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		menuBar.add(separator);
		
		

		JMenu userMenu = new JMenu();
		menuBar.add(userMenu);
		
		
		JMenuItem logoutMenuItem = new JMenuItem("Se d\u00E9connecter");
		logoutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AuthentificationFrame authFrame = new AuthentificationFrame();
				authFrame.getFrame().setVisible(true);
				frmGestionMagasin.dispose();
			}
		});
		userMenu.add(logoutMenuItem);
		
		JMenuItem quitterMenuItem = new JMenuItem("Quitter");
		quitterMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int comfirm = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?", "Comfirmation", JOptionPane.YES_NO_OPTION);
				
				if(comfirm == JOptionPane.YES_OPTION)
					frmGestionMagasin.dispose();
			}
		});
		userMenu.add(quitterMenuItem);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 22, 684, 339);
		frmGestionMagasin.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		
		
		if(user == null) {
			userMenu.setText("Authentifiez-vous d'abord!");
			logoutMenuItem.setText("Se connecter");
		}
		else {
			userMenu.setText(user.getNomComplet());
			logoutMenuItem.setText("Se déconnecter");
		}
	}

}
