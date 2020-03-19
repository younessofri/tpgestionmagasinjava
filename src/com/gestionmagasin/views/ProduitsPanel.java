package com.gestionmagasin.views;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.gestionmagasin.beans.Categories;
import com.gestionmagasin.beans.Produits;
import com.gestionmagasin.buisness.controller.CategoriesController;
import com.gestionmagasin.buisness.controller.ProduitsController;

public class ProduitsPanel extends JPanel {
	
	/*VARIABLES*/
	private DefaultTableModel produitsTableModel;
	private String[] columns = {"Libellé","Quantité","Catégorie"};
	private Object[][] rows;
	private ArrayList<Produits> listProd;
	private int idProdMod;
	private int idProdDel;
	private int idCatMod;
	private String libelleProd;
	private int quantiteProd;
	private String[] comboList;
	private ArrayList<Categories> listCat;
	private ProduitsController prodControl;
	private CategoriesController catControl;
	private boolean supprimer = false;
	private boolean modifier = false;
	private boolean result;
	/*COMPONENTS*/
	private JTable produitsTable;
	private JScrollPane scroll;
	private JButton ajouterButton;
	private JButton enregistrerButton;
	private JButton modifierButton;
	private JButton supprimerButton;
	private JButton actualiserButton;
	private JButton annulerButton;
	private JComboBox categorieComboBox;
	
	/*LOAD JTABLE*/
	private void refresh() {
		
		//REMPLISSAGE DU TABLE MODEL
		prodControl = new ProduitsController();
		catControl = new CategoriesController();
		listProd = prodControl.getProduits();
		int k = listProd.size();
		rows = new Object[k][5];

		for(int i=0;i<k;i++) {
			
			int idCat = listProd.get(i).getIdCategorie();
			
			rows[i][0] = listProd.get(i).getLibelleProduit();	
			rows[i][1] = listProd.get(i).getQuantiteProduit();
			rows[i][2] = catControl.getCategorie(idCat).getLibelleCategorie();
			rows[i][3] = listProd.get(i).getIdCategorie();
			rows[i][4] = listProd.get(i).getId();
		}
		
		produitsTableModel = new DefaultTableModel(rows,columns);
		produitsTable.setModel(produitsTableModel);
	}
	
	
	/**
	 * Create the panel.
	 */
	public ProduitsPanel() {
		
		/*INITIATION DU CONTAINER*/
		setBounds(new Rectangle(0, 0, 684, 340));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Liste des produits");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 144, 21);
		add(lblNewLabel);
		
		/*JTABLE*/
		//REMPLISSAGE DU TABLE MODEL
		prodControl = new ProduitsController();
		catControl = new CategoriesController();
		listProd = prodControl.getProduits();
		int k = listProd.size();
		rows = new Object[k][5];

		for(int i=0;i<k;i++) {
			
			int idCat = listProd.get(i).getIdCategorie();
			
			rows[i][0] = listProd.get(i).getLibelleProduit();	
			rows[i][1] = listProd.get(i).getQuantiteProduit();
			rows[i][2] = catControl.getCategorie(idCat).getLibelleCategorie();
			rows[i][3] = listProd.get(i).getIdCategorie();
			rows[i][4] = listProd.get(i).getId();
		}
		
		produitsTableModel = new DefaultTableModel(rows,columns);

		//Création du JTable
		produitsTable = new JTable(produitsTableModel);
		produitsTable.setEnabled(false);
		scroll = new JScrollPane(produitsTable);
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

		
		/*AJOUT DES COMPOSANTS*/
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
				
				AjouterProduitsFrame ajouterProd = new AjouterProduitsFrame();
				ajouterProd.setVisible(true);
			}
		});
		
		
		//Modifier
		modifierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
				//ComboBox de modification
				catControl = new CategoriesController();
				listCat = catControl.getCategorie();
				
				comboList = new String[listCat.size()] ;
				
				for(int i=0;i<listCat.size();i++)
				{
					comboList[i] = listCat.get(i).getLibelleCategorie();
				}
				
				categorieComboBox = new JComboBox(comboList);
				categorieComboBox.setBounds(88, 90, 186, 22);
				categorieComboBox.setEditable(false);
				TableColumn catColumn = produitsTable.getColumnModel().getColumn(2);
				catColumn.setCellEditor(new DefaultCellEditor(categorieComboBox));
						
				//Visibilité des boutons
				enregistrerButton.setVisible(true);
				modifierButton.setVisible(false);
				supprimerButton.setVisible(true);		
				annulerButton.setVisible(true);
				
				produitsTable.setEnabled(true);
			}
		});
		
		//Supprimer
		supprimerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int rowIndex = produitsTable.getSelectedRow();

				if(rowIndex != -1)
				{					
					idProdDel = (int) rows[rowIndex][4];
					produitsTableModel.removeRow(rowIndex);
				}
			}
		});
		
		//Enregistrer
		enregistrerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(modifier)
				{
					result = false;
					prodControl = new ProduitsController();
					
					result = prodControl.modifierProduits(idProdMod, libelleProd, quantiteProd, idCatMod);
					
					if(!result)
						JOptionPane.showMessageDialog(null,"Erreur lors de l'enregistrement de la modification!");  
					
				}
				
				if(supprimer)
				{
					result = false;
					prodControl = new ProduitsController();
					
					result = prodControl.supprimerProduits(idProdDel);

					System.out.println(result);

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
				
				produitsTable.setEnabled(false);
				
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
				
				produitsTable.setEnabled(false);
			}
		});
		
		//Actualiser
		actualiserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		
		/*TABLE CHNAGED*/
		produitsTableModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {

				if(e.getType() == TableModelEvent.UPDATE)
				{
					modifier = true;
					
					int rowIndex = e.getFirstRow();
					int columnIndex = e.getColumn();
					
					idProdMod = (int) rows[rowIndex][4];
					libelleProd = produitsTableModel.getValueAt(rowIndex, 0).toString();
					quantiteProd = Integer.valueOf(produitsTableModel.getValueAt(rowIndex, 1).toString());
					idCatMod = listCat.get(categorieComboBox.getSelectedIndex()).getId();
				}
				else if(e.getType() == TableModelEvent.DELETE)
				{
					supprimer = true;
				}
			}
		});
		
	}
}
