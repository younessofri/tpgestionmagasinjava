package com.gestionmagasin.views;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.Font;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.gestionmagasin.beans.Categories;
import com.gestionmagasin.buisness.controller.CategoriesController;
import com.gestionmagasin.buisness.controller.ProduitsController;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CategoriesPanel extends JPanel {
	/*VARIABLES*/
	private DefaultTableModel categoriesTableModel;
	private String[] columns = {"Libellé"};
	private Object[][] rows;
	private ArrayList<Categories> listCat;
	private String libelle;
	private int idCatDel;
	private int idCatMod;
	private boolean modifier = false;
	private boolean supprimer = false;
	private boolean result;
	private CategoriesController catControl;
	/*COMPONENTS*/
	private JTable categoriesTable;
	private JScrollPane scroll;
	private JButton ajouterButton;
	private JButton enregistrerButton;
	private JButton modifierButton;
	private JButton supprimerButton;
	private JButton actualiserButton;
	private JButton annulerButton;
	
	/*LOAD JTABLE*/
	private void refresh() {
		
		//REMPLISSAGE DU JTABLE
		catControl = new CategoriesController();
		listCat = catControl.getCategorie();
		int k = listCat.size();
		rows = new Object[k][2];

		for(int i=0;i<k;i++) {
			rows[i][0] = listCat.get(i).getLibelleCategorie();	
			rows[i][1] = listCat.get(i).getId();
		}
		
		//Création du JTable
		DefaultTableModel categoriesTableModel = new DefaultTableModel(rows,columns);
		categoriesTable.setModel(categoriesTableModel);
	}
	
	/**
	 * Create the panel.
	 */
	public CategoriesPanel() {
		
		/*INITIATION DU CONTAINER*/
		setBounds(new Rectangle(0, 0, 684, 340));
		setLayout(null);
		catControl = new CategoriesController();
		listCat = catControl.getCategorie();
		JLabel lblNewLabel = new JLabel("Liste des cat\u00E9gories");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 144, 21);
		add(lblNewLabel);
		
		/*JTABLE*/
		
		//REMPLISSAGE DU JTABLE
		catControl = new CategoriesController();
		listCat = catControl.getCategorie();
		int k = listCat.size();
		rows = new Object[k][2];

		for(int i=0;i<k;i++) {
			rows[i][0] = listCat.get(i).getLibelleCategorie();	
			rows[i][1] = listCat.get(i).getId();
		}
		
		//Création du JTable
		categoriesTableModel = new DefaultTableModel(rows,columns);
		categoriesTable = new JTable(categoriesTableModel);
		categoriesTable.setEnabled(false);
		scroll = new JScrollPane(categoriesTable);
		scroll.setBounds(10, 37, 664,258);
				
		
		/*CREATION DES BOUTONS*/
		ajouterButton = new JButton("Ajouter");
		ajouterButton.setBounds(10, 306, 98, 23);

		enregistrerButton = new JButton("Enregistrer");
		enregistrerButton.setBounds(118, 306, 98, 23);
		enregistrerButton.setVisible(false);
		
		modifierButton = new JButton("Modifier");
		modifierButton.setBounds(118, 306, 98, 23);
		
		supprimerButton = new JButton("Supprimer");
		supprimerButton.setBounds(226, 306, 98, 23);
		supprimerButton.setVisible(false);		

		annulerButton = new JButton("Annuler");
		annulerButton.setVisible(false);
		annulerButton.setBounds(334, 306, 98, 23);
		
		actualiserButton = new JButton("Actualiser");
		actualiserButton.setBounds(576, 306, 98, 23);

		
		/*AJOUTER DES COMPOSANTS*/
		add(scroll);
		add(ajouterButton);
		add(modifierButton);
		add(enregistrerButton);
		add(supprimerButton);
		add(actualiserButton);
		add(annulerButton);
		
		
		/*ACTION LISTENERS*/
		
		//Ajouter
		ajouterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AjouterCategorieFrame ajouterCat = new AjouterCategorieFrame();
				ajouterCat.setVisible(true);
			}
		});
		
		//Modifier
		modifierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Visibilité des boutons
				categoriesTable.setEnabled(true);
				enregistrerButton.setVisible(true);
				modifierButton.setVisible(false);
				supprimerButton.setVisible(true);		
				annulerButton.setVisible(true);
			}
		});
		
		//Supprimer
		supprimerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int rowIndex = categoriesTable.getSelectedRow();

				if(rowIndex != -1)
				{
					idCatDel = (int) rows[rowIndex][1];
					categoriesTableModel.removeRow(rowIndex);
				}
			}
		});
		
		//Enregistrer
		enregistrerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(modifier)
				{
					result = false;
					CategoriesController catControl = new CategoriesController();
					
					result = catControl.modifierCategorie(idCatMod, libelle);	
					
					if(!result)
						JOptionPane.showMessageDialog(null,"Erreur lors de l'enregistrement de la modification!");  
				}
				
				if(supprimer)
				{
					result = false;
					catControl = new CategoriesController();
					
					result = catControl.supprimerCategorie(idCatDel);

					if(!result)	
						JOptionPane.showMessageDialog(null,"Erreur lors de l'enregistrement de la suppression!");
				}
				
				revalidate();
				repaint();
				
				//Visibilité des boutons
				enregistrerButton.setVisible(false);
				modifierButton.setVisible(true);
				supprimerButton.setVisible(false);		
				annulerButton.setVisible(false);
				
				categoriesTable.setEnabled(false);
				
				modifier = false;
				supprimer = false;
			}
		});
		
		//Annuler
		annulerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Visibilité des boutons
				
				enregistrerButton.setVisible(false);
				modifierButton.setVisible(true);
				supprimerButton.setVisible(false);
				annulerButton.setVisible(false);
				
				categoriesTable.setEnabled(false);
			}
		});
		
		//Actualiser
		actualiserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				
				refresh();
				
			}
		});
		
		
		//TABLE CHNAGED
		categoriesTableModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				
				if(e.getType() == TableModelEvent.UPDATE)
				{
					modifier = true;
					
					int rowIndex = e.getFirstRow();
					int columnIndex = e.getColumn();
					
					libelle = categoriesTableModel.getValueAt(rowIndex, columnIndex).toString();
					idCatMod =(int) rows[rowIndex][1];
				}
				else if(e.getType() == TableModelEvent.DELETE)
				{
					supprimer = true;
				}
			}
		});

		
	}
}

