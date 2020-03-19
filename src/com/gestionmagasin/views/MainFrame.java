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
	
	/*VARIABLE*/
	private Utilisateurs user;

	/*COMPONENTS*/
	private JFrame frmGestionMagasin;
	private JLayeredPane layeredPane; 
	private JMenuBar menuBar;
	private JMenu produitsMenu;
	private JMenuItem listProduitsMenuItem;
	private JMenuItem ajouterProduitsMenuItem;
	private JMenu categoriesMenu;
	private JMenuItem listCategoriesMenuItem;
	private JMenuItem ajouterCategoriesMenuItem;
	private JMenu userMenu;
	private JMenuItem logoutMenuItem;
	private JMenuItem quitterMenuItem;
	private JSeparator mainSeparator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_4;
	
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
		
		/*INITIATION DU CONTAINER*/
		frmGestionMagasin = new JFrame();
		frmGestionMagasin.setTitle("Gestion Magasin");
		frmGestionMagasin.setBounds(100, 100, 700, 400);
		frmGestionMagasin.getContentPane().setLayout(null);
		frmGestionMagasin.setLocationRelativeTo(null) ;
		frmGestionMagasin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmGestionMagasin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int comfirm = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?", "Comfirmation", JOptionPane.YES_NO_OPTION);
				
				if(comfirm == JOptionPane.YES_OPTION)
					frmGestionMagasin.dispose();
			}
		});
		
		
		
		/*MENU BAR*/
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 684, 20);
		
		//MENU PRODUITS
		produitsMenu = new JMenu("Produits");
		listProduitsMenuItem = new JMenuItem("Liste");
		separator_1 = new JSeparator();
		ajouterProduitsMenuItem = new JMenuItem("Ajouter");

		//MENU CATEGORIES
		categoriesMenu = new JMenu("Cat\u00E9gories");
		listCategoriesMenuItem = new JMenuItem("Liste");
		separator_2 = new JSeparator();
		ajouterCategoriesMenuItem = new JMenuItem("Ajouter");
		
		//MENU UTILISATEURS
		userMenu = new JMenu();
		logoutMenuItem = new JMenuItem("Se d\u00E9connecter");
		separator_4 = new JSeparator();
		quitterMenuItem = new JMenuItem("Quitter");
		
		
		//AFFECTATION
		produitsMenu.add(listProduitsMenuItem);
		produitsMenu.add(separator_1);
		produitsMenu.add(ajouterProduitsMenuItem);
		
		categoriesMenu.add(listCategoriesMenuItem);
		categoriesMenu.add(separator_2);
		categoriesMenu.add(ajouterCategoriesMenuItem);
		
		userMenu.add(logoutMenuItem);
		userMenu.add(separator_4);
		userMenu.add(quitterMenuItem);
		
		menuBar.add(produitsMenu);
		menuBar.add(categoriesMenu);
		
		mainSeparator = new JSeparator();
		menuBar.add(mainSeparator);
		menuBar.add(userMenu);
		
		
		/*ACTION LISTENERS*/
		
		//ProduitsMenu
		listProduitsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProduitsPanel prodPanel = new ProduitsPanel();
				switchPanels(prodPanel);	
			}
		});
		ajouterProduitsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjouterProduitsFrame ajouterProduit = new AjouterProduitsFrame();
				ajouterProduit.setVisible(true);
			}
		});
		
		//CategoriesMenu
		listCategoriesMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoriesPanel catPanel = new CategoriesPanel();
				switchPanels(catPanel);			
				}
		});
		ajouterCategoriesMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AjouterCategorieFrame ajouterCat = new AjouterCategorieFrame();		
				ajouterCat.setVisible(true);
			}
		});
		
		//UserMenu
		logoutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AuthentificationFrame authFrame = new AuthentificationFrame();
				authFrame.getFrame().setVisible(true);
				frmGestionMagasin.dispose();
			}
		});
		quitterMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int comfirm = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?", "Comfirmation", JOptionPane.YES_NO_OPTION);
				
				if(comfirm == JOptionPane.YES_OPTION)
					frmGestionMagasin.dispose();
			}
		});
		
		
		
		//NOM UTILISATEUR
		if(user == null) {
			userMenu.setText("Authentifiez-vous d'abord!");
			logoutMenuItem.setText("Se connecter");
		}
		else {
			userMenu.setText(user.getNomComplet());
			logoutMenuItem.setText("Se déconnecter");
		}
		
		
		
		
		//LAYERED PANE
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 22, 684, 339);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		
		frmGestionMagasin.getContentPane().add(menuBar);
		frmGestionMagasin.getContentPane().add(layeredPane);

		
	}

}
