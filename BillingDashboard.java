package com.model;


	import javax.swing.*;
	import java.awt.*;

	public class BillingDashboard extends JFrame {
	   
		private static final long serialVersionUID = 1L;
		private JTextArea cartTextArea;
	    private JLabel totalLabel;

	    public BillingDashboard(String products, double totalAmount) {
	        setTitle("Billing Dashboard");
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setSize(400, 400);
	        setLocationRelativeTo(null);

	        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
	        add(mainPanel);

	        JPanel headerPanel = new JPanel();
	        headerPanel.setBackground(Color.LIGHT_GRAY);
	        mainPanel.add(headerPanel, BorderLayout.NORTH);

	        JLabel titleLabel = new JLabel("Billing Dashboard");
	        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
	        headerPanel.add(titleLabel);

	        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
	        mainPanel.add(contentPanel, BorderLayout.CENTER);

	        JScrollPane scrollPane = new JScrollPane();
	        contentPanel.add(scrollPane, BorderLayout.CENTER);

	        JPanel cartPanel = new JPanel(new BorderLayout());
	        cartPanel.setBorder(BorderFactory.createTitledBorder("Cart Items"));
	        scrollPane.setViewportView(cartPanel);

	        cartTextArea = new JTextArea();
	        cartTextArea.setEditable(false);
	        cartTextArea.setText(products);
	        cartPanel.add(cartTextArea, BorderLayout.CENTER);

	        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        totalLabel = new JLabel(String.format("Total Amount: ₹%.2f", totalAmount));
	        totalPanel.add(totalLabel);
	        cartPanel.add(totalPanel, BorderLayout.SOUTH);

	        JButton proceedToPaymentButton = new JButton("Proceed to Payment");
	        proceedToPaymentButton.addActionListener(e -> {

	            JOptionPane.showMessageDialog(this, "Select Payment Option");

	            PaymentOptionsFrame paymentOptionsFrame = new PaymentOptionsFrame(products, totalAmount);
	            paymentOptionsFrame.setVisible(true);

	  
	            dispose();
	        });
	        contentPanel.add(proceedToPaymentButton, BorderLayout.SOUTH);
	    }

	    public static void main(String[] args) {
	        BillingDashboard billingDashboard = new BillingDashboard("Product 1\nProduct 2\nProduct 3", 100.0);
	        billingDashboard.setVisible(true);
	    }
	}



