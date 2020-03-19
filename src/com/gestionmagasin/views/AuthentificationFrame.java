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

	/*VARIABLE*/
	private AuthentificationController auth;
	private Utilisateurs user;
	private String login;
	private String pw;
	/*COMPOSANTS*/
	private JFrame frmAuthentification;
	private JLabel logo;
	private JLabel loginLabel;
	private JLabel pwLabel;
	private JLabel titre;
	private JLabel message;
	private JTextField loginTextField;
	private JPasswordField pwdYounes;
	private JButton connectButton;
	private JSeparator separator;
	
	
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
		
		/*INITIATION DU CONTAINER*/
		frmAuthentification = new JFrame();
		frmAuthentification.setTitle("Authentification");
		frmAuthentification.setBounds(100, 100, 700, 400);
		frmAuthentification.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAuthentification.setLocationRelativeTo(null);
		frmAuthentification.getContentPane().setLayout(null);

		/*AJOUTER UNE IMAGE*/
		ImageIcon image = new ImageIcon(getClass().getResource("/images/auth.png"));
		
		/*CREATION DES COMPOSANTS*/
		logo = new JLabel(image);
		logo.setBounds(20, 34, 233, 207);
		
		titre = new JLabel("Authentification");
		titre.setFont(new Font("Tahoma", Font.BOLD, 30));
		titre.setBounds(20, 260, 247, 48);
		
		loginLabel = new JLabel("Login");
		loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		loginLabel.setBounds(360, 101, 59, 20);
		
		pwLabel = new JLabel("Mot de passe");
		pwLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pwLabel.setBounds(308, 150, 111, 20);
		
		message = new JLabel("");
		message.setFont(new Font("Tahoma", Font.PLAIN, 15));
		message.setForeground(Color.BLACK);
		message.setBounds(421, 248, 247, 28);
		
		loginTextField = new JTextField();
		loginTextField.setText("younes97");
		loginTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		loginTextField.setColumns(10);
		loginTextField.setBounds(421, 101, 233, 20);
		
		pwdYounes = new JPasswordField();
		pwdYounes.setText("younes@97");
		pwdYounes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pwdYounes.setBounds(421, 150, 233, 20);
		
		connectButton = new JButton("Se connecter");
		connectButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		connectButton.setBounds(421, 200, 131, 28);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(283, 34, 28, 274);
		
		
		/*AJOUT DES COMPOSANTS*/
		frmAuthentification.getContentPane().add(logo);
		frmAuthentification.getContentPane().add(loginLabel);
		frmAuthentification.getContentPane().add(pwLabel);
		frmAuthentification.getContentPane().add(loginTextField);
		frmAuthentification.getContentPane().add(pwdYounes);
		frmAuthentification.getContentPane().add(message);
		frmAuthentification.getContentPane().add(connectButton);
		frmAuthentification.getContentPane().add(titre);
		frmAuthentification.getContentPane().add(separator);
		
		
		//BOUTON seConnecter
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				login = loginTextField.getText();
				pw = new String(pwdYounes.getPassword());
				
				auth = new AuthentificationController(); 
				user = auth.seConnecter(login, pw);
				
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
	}
}
