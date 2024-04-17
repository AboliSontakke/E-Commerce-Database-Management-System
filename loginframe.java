package com.model;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class loginframe extends JFrame implements ActionListener {
   
	private static final long serialVersionUID = 1L;
	JTextField t_us;
    JTextField t_pas;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JRadioButton userRad;
    JRadioButton adminRad;
    JButton submit;
    JButton signup_but;
    JLabel message;
    public loginframe() {
        setTitle("E-commerce Login");
        JLabel headlineLabel = new JLabel("LOGIN DASHBOARD");
        headlineLabel.setBounds(110, 10, 200, 30);
        headlineLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(headlineLabel);
        
        
       
        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(100,50,100,50);
        t_us = new JTextField();
        t_us.setBounds(200,65,70,20);
        add(usernameLabel);
        add(t_us);

       
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100,90,100,50);
        t_pas = new JTextField();
        t_pas.setBounds(200,105,70,20);
        add(passwordLabel);
        add(t_pas);

       
        submit = new JButton("Submit");
        submit.setBounds(150,160,80,30);
        add(submit);
        submit.addActionListener(this);
        
        
        signup_but= new JButton("Sign Up");
        signup_but.setBounds(240,240,80,30);
        
        add(signup_but);
        signup_but.addActionListener(this) ;
        
        message=new JLabel("Dont have an account ?");
        message.setBounds(100,230,200,50);
        add(message);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(null);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

   
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==submit) {
			String username = t_us.getText();
			String password = t_pas.getText();
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");

				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
				//PreparedStatement ps = con.prepareStatement("insert into register values(?,?)");
				//ps.setInt(1, 0);
				//ps.setString(2, username);
				//ps.setString(3, password);

				//ps.executeUpdate();
				//System.out.println("Login Successful");
				JOptionPane.showMessageDialog(this, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
				UserDashboard userdashboard=new UserDashboard();
				userdashboard.setVisible(true);
				this.dispose();
			
			} 
			catch (Exception e1) {
				
				JOptionPane.showMessageDialog(this, "invalid login details!", "Success", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		else if (e.getSource()==signup_but) {
			
			new userRegistrationForm();
			System.out.println("signup completed");
			this.dispose();
		}
	}
	}

