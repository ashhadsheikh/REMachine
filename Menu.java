import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Menu {

	private JFrame frmRecm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frmRecm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	JButton btnConversions,btnTextFiles, btnExit;
	private void initialize() {
		frmRecm = new JFrame();
		frmRecm.getContentPane().setBackground(SystemColor.desktop);
		frmRecm.getContentPane().setForeground(Color.RED);
		frmRecm.setTitle("RecM++");
		frmRecm.setBounds(100, 100, 450, 300);
		frmRecm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRecm.getContentPane().setLayout(null);
		
		JLabel lblWelcomeToRegular = new JLabel("Welcome To Regular Expression Combo Machine");
		lblWelcomeToRegular.setForeground(SystemColor.window);
		lblWelcomeToRegular.setFont(new Font("AR CENA", Font.PLAIN, 21));
		lblWelcomeToRegular.setBounds(41, 25, 383, 36);
		frmRecm.getContentPane().add(lblWelcomeToRegular);
		
		 btnConversions = new JButton("Conversions ");
		btnConversions.setBackground(SystemColor.window);
		btnConversions.setBounds(154, 103, 127, 23);
		frmRecm.getContentPane().add(btnConversions);
		btnConversions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conversions window = new Conversions();
				window.frmConvertRegularExpression.setVisible(true);
				frmRecm.setVisible(false);
			}
		});
		 btnTextFiles = new JButton("Text Files");
		btnTextFiles.setBackground(SystemColor.window);
		btnTextFiles.setBounds(154, 153, 127, 23);
		frmRecm.getContentPane().add(btnTextFiles);
		btnTextFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CheckFileInterface window = new CheckFileInterface();
				window.frmRecm.setVisible(true);
				frmRecm.setVisible(false);
			}
		});
		 btnExit = new JButton("Exit");
		btnExit.setBackground(SystemColor.window);
		btnExit.setBounds(154, 203, 127, 23);
		frmRecm.getContentPane().add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String message = " Are you sure you want to Quit ? ";
	             String title = "Quit";
	             int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
	             if (reply == JOptionPane.YES_OPTION){
	            	 frmRecm.setVisible(false);
	             }
			}
		});
	}

}
