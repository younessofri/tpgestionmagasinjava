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

	private JPanel contentPane;
	private JTextField textField;

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
		setTitle("Ajouter Cat\u00E9gorie");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		
		
		JLabel lblNewLabel = new JLabel("Libell\u00E9");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 28, 44, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String libelle = textField.getText();
				
				CategoriesController catControl = new CategoriesController();
				boolean result = catControl.ajouterCategorie(libelle);
				
				if(result)
				{
					
				}
				else
				{
					int comfirm = JOptionPane.showConfirmDialog(null, "Une erreur s'est produite. Voulez vous réessayer?", "Problème", JOptionPane.YES_NO_OPTION);
					
					if(comfirm == JOptionPane.NO_OPTION)
						dispose();
				}
				
			}
		});
		btnNewButton.setBounds(64, 77, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Annuler");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int comfirm = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter?", "Comfirmation", JOptionPane.YES_NO_OPTION);
				
				if(comfirm == JOptionPane.YES_OPTION)
					dispose();
				
			}
		});
		btnNewButton_1.setBounds(185, 77, 89, 23);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(64, 27, 210, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		
		
	}
}
