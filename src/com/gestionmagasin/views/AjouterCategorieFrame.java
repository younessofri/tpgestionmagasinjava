package com.gestionmagasin.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gestionmagasin.buisness.controller.CategoriesController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AjouterCategorieFrame extends JFrame {

	private CategoriesController catControl;
	
	private JPanel mainPanel;
	private JTextField libelleTextField;
	private JLabel libelleLabel;
	private JButton ajouterButton;
	private JButton annulerButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterCategorieFrame frame = new AjouterCategorieFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AjouterCategorieFrame() {
		
		/*INITIATION DU CONTAINER*/
		setTitle("Ajouter Cat\u00E9gorie");
		setBounds(100, 100, 300, 150);
		setLocationRelativeTo(null);

		
		/*CREATION DES COMPOSANTS*/
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(null);

		libelleLabel = new JLabel("Libell\u00E9");
		libelleLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		libelleLabel.setBounds(10, 28, 44, 14);
		
		libelleTextField = new JTextField();
		libelleTextField.setBounds(64, 27, 210, 20);
		libelleTextField.setColumns(10);
		
		ajouterButton = new JButton("Ajouter");
		ajouterButton.setBounds(64, 77, 89, 23);
		
		annulerButton = new JButton("Annuler");
		annulerButton.setBounds(185, 77, 89, 23);
		

		/*AJOUT DES COMPOSANTS*/
		setContentPane(mainPanel);
		mainPanel.add(ajouterButton);
		mainPanel.add(annulerButton);
		mainPanel.add(libelleTextField);
		mainPanel.add(libelleLabel);
		
		/*ACTION LISTENERS*/
		
		//Ajouter
		ajouterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String libelle = libelleTextField.getText();
				
				catControl = new CategoriesController();
				boolean result = catControl.ajouterCategorie(libelle);
				
				if(result)
				{
					JOptionPane.showMessageDialog(null,"Opération effectuée!");  
				}
				else
				{
					int comfirm = JOptionPane.showConfirmDialog(null, "Une erreur s'est produite. Voulez vous réessayer?", "Problème", JOptionPane.YES_NO_OPTION);
					
					if(comfirm == JOptionPane.NO_OPTION)
						dispose();
				}
				
			}
		});
		
		//Annuler
		annulerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});	
	}
}
