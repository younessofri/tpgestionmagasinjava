package com.gestionmagasin.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gestionmagasin.beans.Categories;
import com.gestionmagasin.buisness.controller.CategoriesController;
import com.gestionmagasin.buisness.controller.ProduitsController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AjouterProduitsFrame extends JFrame {

	/*VARIABLES*/
	private ProduitsController prdControl;
	private CategoriesController catControl;
	private ArrayList<Categories> list;
	private String[] comboList;
	/*COMPONENTS*/
	private JPanel contentPane;
	private JTextField libelleTextField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JComboBox categorieComboBox;
	private JSpinner quantiteSpinner;
	private JButton ajouterButton;
	private JButton annulerButton;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterProduitsFrame frame = new AjouterProduitsFrame();
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
	public AjouterProduitsFrame() {
		
		/*INITIATION DU CONTAINER*/
		setTitle("Ajouter Produit");
		setBounds(100, 100, 300, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null) ;

		/*REPLISSAGE DU COMBOBOX*/
		catControl = new CategoriesController();
		list = catControl.getCategorie();
		
		comboList = new String[list.size()] ;
		
		for(int i=0;i<list.size();i++)
			comboList[i] = list.get(i).getLibelleCategorie();
		
		
		
		/*CREATION DES COMPOSANTS*/
		lblNewLabel = new JLabel("Libell\u00E9 ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(36, 26, 42, 14);
		
		libelleTextField = new JTextField();
		libelleTextField.setBounds(88, 25, 186, 20);
		libelleTextField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Quantit\u00E9");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(23, 56, 55, 20);
		
		lblNewLabel_2 = new JLabel("Cat\u00E9gorie");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(17, 89, 61, 20);
		
		categorieComboBox = new JComboBox(comboList);
		categorieComboBox.setBounds(88, 90, 186, 22);
		categorieComboBox.setEditable(false);
		
		quantiteSpinner = new JSpinner();
		quantiteSpinner.setBounds(88, 58, 186, 20);
		
		ajouterButton = new JButton("Ajouter");
		ajouterButton.setBounds(88, 135, 89, 23);
		
		annulerButton = new JButton("Annuler");
		annulerButton.setBounds(185, 135, 89, 23);
		
		/*AJOUT DES COMPOSANTS*/
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel_2);
		contentPane.add(libelleTextField);
		contentPane.add(ajouterButton);
		contentPane.add(annulerButton);
		contentPane.add(categorieComboBox);
		contentPane.add(quantiteSpinner);
		
		
		/*ACTION LISTENERS*/
		
		//Ajouter
		ajouterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String libelle = libelleTextField.getText();
				int quantite = Integer.valueOf(quantiteSpinner.getValue().toString());
				int id = list.get(categorieComboBox.getSelectedIndex()).getId();
				
				prdControl = new ProduitsController();
				boolean result = prdControl.ajouterProduits(libelle, quantite, id);
				
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
