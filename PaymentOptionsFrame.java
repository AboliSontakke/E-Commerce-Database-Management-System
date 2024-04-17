package com.model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PaymentOptionsFrame extends JFrame {
  
	private static final long serialVersionUID = 1L;
	private JTextField addressTextField;
    private JLabel addressLabel;

    public PaymentOptionsFrame(String products, double totalAmount) {
        setTitle("Payment Options");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        add(mainPanel);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Payment Options");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(titleLabel);

        JPanel optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.setBorder(BorderFactory.createTitledBorder("Payment Options"));
        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        JRadioButton option1RadioButton = new JRadioButton("UPI");
        JRadioButton option2RadioButton = new JRadioButton("Card");
        JRadioButton option3RadioButton = new JRadioButton("Net Banking");

        ButtonGroup paymentOptionsGroup = new ButtonGroup();
        paymentOptionsGroup.add(option1RadioButton);
        paymentOptionsGroup.add(option2RadioButton);
        paymentOptionsGroup.add(option3RadioButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        optionsPanel.add(option1RadioButton, gbc);

        gbc.gridy++;
        optionsPanel.add(option2RadioButton, gbc);

        gbc.gridy++;
        optionsPanel.add(option3RadioButton, gbc);

        addressTextField = new JTextField();
        addressTextField.setPreferredSize(new Dimension(300, 50)); // Set preferred size
        addressLabel = new JLabel("Address");
        gbc.gridy++;
        optionsPanel.add(addressLabel, gbc);

        gbc.gridy++;
        optionsPanel.add(addressTextField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = "";
                if (option1RadioButton.isSelected()) {
                    selectedOption = "UPI";
                } else if (option2RadioButton.isSelected()) {
                    selectedOption = "Card";
                } else if (option3RadioButton.isSelected()) {
                    selectedOption = "Net Banking";
                } else {
                    JOptionPane.showMessageDialog(PaymentOptionsFrame.this, "Please select a payment option.");
                    return;
                }

                String address = addressTextField.getText();
                if (address.isEmpty()) {
                    JOptionPane.showMessageDialog(PaymentOptionsFrame.this, "Please enter an address.");
                    return;
                }

                JOptionPane.showMessageDialog(PaymentOptionsFrame.this, "Selected Option: " + selectedOption);
                JOptionPane.showMessageDialog(PaymentOptionsFrame.this, "Payment Successful\nSave the Receipt");
                generateReceipt(products, totalAmount, address);
                JOptionPane.showMessageDialog(PaymentOptionsFrame.this, "Receipt Downloaded\nVisit again.....");
                dispose();
                UserDashboard userDashboard = new UserDashboard();
                userDashboard.setVisible(true);  
            }
        });
        buttonPanel.add(proceedButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void generateReceipt(String products, double totalAmount, String address) {
        JFileChooser fileChooser = new JFileChooser();
        int userChoice = fileChooser.showSaveDialog(this);
        if (userChoice == JFileChooser.APPROVE_OPTION) {
           String filePath = fileChooser.getSelectedFile().getPath();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                writer.write("Receipt\n\n");
                writer.write("Products: \n");
                writer.write(products);
                writer.write("\n\n");
                writer.write("Total Amount: ₹" + totalAmount);
                writer.write("\n\n");
                writer.write("Address: " + address);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

