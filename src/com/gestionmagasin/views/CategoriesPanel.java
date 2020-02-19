package com.gestionmagasin.views;

import javax.swing.*;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.gestionmagasin.beans.Categories;
import com.gestionmagasin.buisness.controller.CategoriesController;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CategoriesPanel extends JPanel {
	private JTable categoriesTable;
	private JScrollPane scroll;
	private DefaultTableModel model;
	/**
	 * Create the panel.
	 */
	public CategoriesPanel() {
		setBounds(new Rectangle(0, 0, 684, 340));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Liste des cat\u00E9gories");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 144, 21);
		add(lblNewLabel);
		
		categoriesTable = new JTable();
		String[] columns = {"ID","Libellé"};
		model.setColumnIdentifiers(columns);

		CategoriesController cat = new CategoriesController();
		
		ArrayList<Categories> listCat = cat.getCategorie();
		Object[] row = new Object[2];
		
		for(int i=0;i>listCat.size();i++) {
			row[0] = listCat.get(i).getId();
			row[1] = listCat.get(i).getLibelleCategorie();
			
			model.addRow(row);
		}
		
		
		
		
		categoriesTable.setModel(model);
		scroll = new JScrollPane(categoriesTable);
		add(scroll);
		
	
		categoriesTable.setBounds(10, 36, 664, 293);
		//add(categoriesTable);
		
		
		
		
		
		
		
		
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AjouterCategorieFrame ajouterCat = new AjouterCategorieFrame();
				
				ajouterCat.setVisible(true);
			}
		});
		btnNewButton.setBounds(585, 9, 89, 23);
		add(btnNewButton);

		
		
		/*
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 22);
		frmGestion.getContentPane().add(menuBar);
		
		JMenu produitsMenu = new JMenu("Produits");
		menuBar.add(produitsMenu);
		
		JMenuItem listProduitsMenuItem = new JMenuItem("Liste");
		produitsMenu.add(listProduitsMenuItem);
		
		JSeparator separator_2 = new JSeparator();
		produitsMenu.add(separator_2);
		
		JMenuItem ajouterProduitsMenuItem_1 = new JMenuItem("Ajouter");
		produitsMenu.add(ajouterProduitsMenuItem_1);
		
		JMenu categoriesMenu = new JMenu("Cat\u00E9gories");
		menuBar.add(categoriesMenu);
		
		JMenuItem listCategoriesMenuItem = new JMenuItem("Liste");
		categoriesMenu.add(listCategoriesMenuItem);
		
		JSeparator separator_3 = new JSeparator();
		categoriesMenu.add(separator_3);
		
		JMenuItem ajouterCategoriesMenuItem = new JMenuItem("Ajouter");
		categoriesMenu.add(ajouterCategoriesMenuItem);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		menuBar.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		menuBar.add(separator_1);
		
		JMenu userMenu = new JMenu();
		menuBar.add(userMenu);
		
		
		
		
		
		JPanel produitsPanel = new JPanel();
		produitsPanel.setVisible(false);
		produitsPanel.setBounds(10, 33, 414, 217);
		frmGestion.getContentPane().add(produitsPanel);
		produitsPanel.setLayout(null);
		
		JLabel titre = new JLabel("Liste des produits");
		titre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titre.setBounds(10, 11, 151, 21);
		produitsPanel.add(titre);
		
		table = new JTable();
		table.setBounds(10, 36, 394, 170);
		produitsPanel.add(table);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBounds(315, 9, 89, 23);
		produitsPanel.add(btnNewButton);
		
		*/
		
		
	}
}
