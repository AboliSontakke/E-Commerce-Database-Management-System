package com.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class userRegistrationForm extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JTextField UsernameField;
    private JTextField pincodeField;
    private JTextField mobileNoField;
    private JTextField addressField;
    private JTextField dateField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public userRegistrationForm() {
        setTitle("User Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 2));

        JLabel UserNameLabel = new JLabel("Username:");
        UsernameField = new JTextField();

        JLabel lastNameLabel = new JLabel("Pincode:");
        pincodeField = new JTextField();

        JLabel mobileNoLabel = new JLabel("Mobile number:");
        mobileNoField = new JTextField();

        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField();

        JLabel dateLabel = new JLabel("DOB:");
        dateField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        registerButton = new JButton("Register");
        registerButton.addActionListener(this);

        add(UserNameLabel);
        add(UsernameField);
        add(passwordLabel);
        add(passwordField);
        add(emailLabel);
        add(emailField);
        add(mobileNoLabel);
        add(mobileNoField);
        add(dateLabel);
        add(dateField);
        add(addressLabel);
        add(addressField);
        add(lastNameLabel);
        add(pincodeField);

        add(registerButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new userRegistrationForm());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(registerButton)) {
            String Username = UsernameField.getText();
            String pin = pincodeField.getText();
            long pincode = Long.parseLong(pin); 
            String mobile = mobileNoField.getText();
            long mobileNo = Long.parseLong(mobile); 
            String address = addressField.getText();
            String dob = dateField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
                PreparedStatement ps = con.prepareStatement("insert into register values(?,?,?,?,?,?,?)");
                //ps.setInt(1, 0);
                ps.setString(1, Username);
                ps.setString(2, password);
                ps.setString(3, email);
                ps.setLong(4, mobileNo); 
                ps.setString(5, dob);
                ps.setString(6, address);
                ps.setLong(7, pincode); // Set as long in the prepared statement
                ps.executeUpdate();
                showSignupSuccessMessage();
            } catch (Exception ex) {
                ex.printStackTrace();
                showErrorMessage("Registration failed!");
            }

            clearFormFields();
            this.dispose();
            new loginframe();
        }
    }

    private void showSignupSuccessMessage() {
        JOptionPane.showMessageDialog(this, "Signup successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void clearFormFields() {
        UsernameField.setText("");
        pincodeField.setText("");
        mobileNoField.setText("");
        addressField.setText("");
        dateField.setText("");
        emailField.setText("");
        passwordField.setText("");
    }
}
