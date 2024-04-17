package com.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class WelcomePage extends JFrame {
    private JButton loginButton;
    private JButton registerButton;

    public WelcomePage() {
        super("Ecommerce Database Management System");
        initializeComponents();
        setupLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
    	
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        loginButton.setFocusable(false);
        registerButton.setFocusable(false);
        loginButton.setBackground(Color.GREEN);
        registerButton.setBackground(Color.green);
        loginButton.setBounds(50, 200, 100, 30);
        registerButton.setBounds(50, 240, 100, 30);

        

        loginButton.setFont(new Font("Arial", Font.BOLD, 12)); 
        registerButton.setFont(new Font("Arial", Font.BOLD, 12)); 
       

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
                loginframe loginFrame = new loginframe();
                dispose();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                userRegistrationForm userRegistrationForm = new userRegistrationForm();
                dispose(); 
            }
        });
    }

    private void setupLayout() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(new File("C:\\Users\\ABOLI\\Downloads\\eCommerce-Website.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        panel.setLayout(null); 

        loginButton.setBounds(100, 200, 100, 30); 
        registerButton.setBounds(100, 240, 100, 30);

        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomePage());
    }
}

