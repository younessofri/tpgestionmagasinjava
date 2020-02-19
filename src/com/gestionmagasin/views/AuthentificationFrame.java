package com.gestionmagasin.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.gestionmagasin.beans.Utilisateurs;
import com.gestionmagasin.buisness.controller.AuthentificationController;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AuthentificationFrame {

	private JFrame frmAuthentification;

	public JFrame getFrame() {
		return frmAuthentification;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthentificationFrame window = new AuthentificationFrame();
					window.frmAuthentification.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AuthentificationFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAuthentification = new JFrame();
		frmAuthentification.setTitle("Authentification");
		frmAuthentification.setBounds(100, 100, 700, 400);
		frmAuthentification.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAuthentification.setLocationRelativeTo(null);
		
		//AJOUTER UNE IMAGE
		ImageIcon image = new ImageIcon(getClass().getResource("/images/auth.png"));
		frmAuthentification.getContentPane().setLayout(null);
		
		JLabel titre = new JLabel(image);
		titre.setBounds(20, 34, 233, 207);
		frmAuthentification.getContentPane().add(titre);
		
		JLabel loginLabel = new JLabel("Login");
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		loginLabel.setBounds(360, 101, 59, 20);
		frmAuthentification.getContentPane().add(loginLabel);
		
		JLabel pwLabel = new JLabel("Mot de passe");
		pwLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pwLabel.setBounds(308, 150, 111, 20);
		frmAuthentification.getContentPane().add(pwLabel);
		
		JTextField loginTextField = new JTextField();
		loginTextField.setText("younes97");
		loginTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		loginTextField.setColumns(10);
		loginTextField.setBounds(421, 101, 233, 20);
		frmAuthentification.getContentPane().add(loginTextField);
		
		JPasswordField pwdYounes = new JPasswordField();
		pwdYounes.setText("younes@97");
		pwdYounes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pwdYounes.setBounds(421, 150, 233, 20);
		frmAuthentification.getContentPane().add(pwdYounes);
		
		JLabel message = new JLabel("");
		message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		message.setForeground(Color.BLACK);
		message.setBounds(421, 248, 247, 28);
		frmAuthentification.getContentPane().add(message);
		
		//BOUTON seConnecter
		JButton connectButton = new JButton("Se connecter");
		connectButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String login = loginTextField.getText();
				String pw = new String(pwdYounes.getPassword());
				
				AuthentificationController auth = new AuthentificationController(); 
				Utilisateurs user = auth.seConnecter(login, pw);
				
				
				if(login.equals("") || pw.equals(""))
				{
					message.setText("Tous les champs sont obligatoires!");
					message.setForeground(Color.orange);
				}
				else
				{
					if(user.getId()>0)
					{
						MainFrame main = new MainFrame(user);
						
						main.getFrame().setVisible(true);
						frmAuthentification.dispose();
					}
					else
					{
						message.setText("Access refusé!");
						message.setForeground(Color.red);
					}	
				}
	
			}
		});
		connectButton.setBounds(421, 200, 131, 28);
		frmAuthentification.getContentPane().add(connectButton);
		
		JLabel lblNewLabel = new JLabel("Authentification");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(20, 260, 247, 48);
		frmAuthentification.getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(283, 34, 28, 274);
		frmAuthentification.getContentPane().add(separator);
		
		
		
	}
}
