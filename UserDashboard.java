package com.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserDashboard extends JFrame {

    
	private static final long serialVersionUID = 1L;
	private JTextArea cartTextArea;
    private double totalAmount;
    private JLabel totalLabel;
    private List<Product> productList;

    public UserDashboard() {
        setTitle("Products Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        productList = new ArrayList<>();

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        add(mainPanel);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Products Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel);

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(contentPanel, BorderLayout.CENTER);

      
        JPanel categoryPanel = new JPanel();
        JLabel categoryLabel = new JLabel("Category:");
        JComboBox<String> categoryComboBox = new JComboBox<>();
        categoryComboBox.addItem("All");
        categoryComboBox.addItem("Electronics");
        categoryComboBox.addItem("Clothing");
        categoryComboBox.addItem("Books");
        categoryComboBox.addItem("Home Decor");
        categoryPanel.add(categoryLabel);
        categoryPanel.add(categoryComboBox);
        contentPanel.add(categoryPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel productsPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        productsPanel.setBorder(BorderFactory.createTitledBorder("Products"));
        scrollPane.setViewportView(productsPanel);

        productList.add(new Product("Laptop", "C:\\Users\\ABOLI\\Downloads\\laptop.jpg", "₹1,22,999", 122999, "Electronics"));
        productList.add(new Product("oneplus", "C:\\Users\\ABOLI\\Downloads\\OIP.jpg", "₹54,999", 54999, "Electronics"));
        productList.add(new Product("OPPO", "C:\\Users\\ABOLI\\Downloads\\Oppo.jpg", "₹23,999", 23999, "Electronics"));
        productList.add(new Product("realme", "C:\\Users\\ABOLI\\Downloads\\realme.jpg", "₹19,999", 19999, "Electronics"));
        productList.add(new Product("White shirt", "C:\\Users\\ABOLI\\Downloads\\R.jpg", "₹999", 999, "Clothing"));
        productList.add(new Product("US polo formal shirt", "C:\\Users\\ABOLI\\Downloads\\polo.jpg", "₹1999", 1999, "Clothing"));
        productList.add(new Product("linen trouser", "C:\\Users\\ABOLI\\Downloads\\trouser.jpg", "₹1499", 1499, "Clothing"));
        productList.add(new Product("Core java", "C:\\Users\\ABOLI\\Downloads\\corejava.jpg", "₹749", 749, "Books"));
        productList.add(new Product("operating Systems", "C:\\Users\\ABOLI\\Downloads\\operatingsys.jpg", "₹1249", 1249, "Books"));
        productList.add(new Product("python for machine Learning", "C:\\Users\\ABOLI\\Downloads\\python.jpg", "₹849", 849, "Books"));
        productList.add(new Product("Table Lamps", "C:\\Users\\ABOLI\\Downloads\\lamps.jpg", "₹350", 350, "Home Decor"));
        productList.add(new Product("Vases", "C:\\Users\\ABOLI\\Downloads\\vases.jpg", "₹150", 150, "Home Decor"));

        updateProductPanel(productsPanel, "All");

        JPanel leftPanel = new JPanel(new BorderLayout());
        contentPanel.add(leftPanel, BorderLayout.WEST);

        JPanel cartPanel = new JPanel(new BorderLayout());
        cartPanel.setPreferredSize(new Dimension(200, 400));
        cartPanel.setBorder(BorderFactory.createTitledBorder("Cart"));
        leftPanel.add(cartPanel, BorderLayout.CENTER);

        cartTextArea = new JTextArea();
        cartTextArea.setEditable(false);
        JScrollPane cartScrollPane = new JScrollPane(cartTextArea);
        cartPanel.add(cartScrollPane, BorderLayout.CENTER);

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalLabel = new JLabel("Total Amount: ₹0.00");
        totalPanel.add(totalLabel);
        cartPanel.add(totalPanel, BorderLayout.SOUTH);


        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton resetButton = new JButton("Reset");
        JPanel resetPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(resetPanel, BorderLayout.WEST);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetCart();
            }
        });
        resetPanel.add(resetButton);
        
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (totalAmount == 0.0) {
                    JOptionPane.showMessageDialog(UserDashboard.this, "Please select products before checkout.");
            	}else {
                JOptionPane.showMessageDialog(UserDashboard.this, "Checkout complete!");
                showBillingDashboard();
            }
            }
        });
        
        bottomPanel.add(checkoutButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryComboBox.getSelectedItem();
                updateProductPanel(productsPanel, selectedCategory);
            }
        });
    }

    private void updateProductPanel(JPanel productsPanel, String selectedCategory) {
        productsPanel.removeAll();

        
        for (Product product : productList) {
            if (selectedCategory.equals("All") || product.getCategory().equals(selectedCategory)) {
                addProductPanel(productsPanel, product);
            }
        }

       
        productsPanel.revalidate();
        productsPanel.repaint();
    }
    public void resetCart() {
        cartTextArea.setText("");
        totalAmount = 0.0;
        totalLabel.setText("Total Amount: ₹0.00");
    }    
    private void addProductPanel(JPanel productsPanel, Product product) {
        try {
            Image productImage = ImageIO.read(new File(product.getImagePath()));
            Image scaledProductImage = productImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);

            JPanel productPanel = new JPanel(new GridBagLayout());
            productPanel.setPreferredSize(new Dimension(200, 250));
            productPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel imageLabel = new JLabel(new ImageIcon(scaledProductImage));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(0, 0, 10, 0);
            productPanel.add(imageLabel, gbc);

            JLabel nameLabel = new JLabel(product.getProductName());
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(0, 0, 5, 0);
            productPanel.add(nameLabel, gbc);

            JLabel priceLabel = new JLabel(product.getPrice());
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(0, 0, 5, 0);
            productPanel.add(priceLabel, gbc);

            JButton addToCartButton = new JButton("Add to Cart");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.CENTER;
            productPanel.add(addToCartButton, gbc);

            addToCartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cartItem = product.getProductName() + " - " + product.getPrice();
                    cartTextArea.append(cartItem + "\n");
                    totalAmount += product.getAmount();
                    totalLabel.setText(String.format("Total Amount: ₹%.2f", totalAmount));
                }
            });

            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cartItem = product.getProductName() + " - " + product.getPrice();
                    String cartText = cartTextArea.getText();
                    int index = cartText.indexOf(cartItem);

                    if (index >= 0) {
                        cartText = cartText.substring(0, index) + cartText.substring(index + cartItem.length());
                        cartTextArea.setText(cartText);

                      
                        if (totalAmount > 0) {
                            totalAmount -= product.getAmount();
                            totalLabel.setText(String.format("Total Amount: ₹%.2f", totalAmount));
                        }
                    }
                }
            });
            JPanel buttonPanel = new JPanel(new BorderLayout());
            buttonPanel.add(removeButton, BorderLayout.WEST);
            buttonPanel.add(addToCartButton, BorderLayout.EAST);
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.CENTER;
            productPanel.add(buttonPanel, gbc);

            productsPanel.add(productPanel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showBillingDashboard() {
        BillingDashboard billingDashboard = new BillingDashboard(cartTextArea.getText(), totalAmount);
        billingDashboard.setVisible(true);
    }
    
    public class Product {
        private String productName;
        private String imagePath;
        private String price;
        private double amount;
        private String category;

        public Product(String productName, String imagePath, String price, double amount, String category) {
            this.productName = productName;
            this.imagePath = imagePath;
            this.price = price;
            this.amount = amount;
            this.category = category;
        }

        public String getProductName() {
            return productName;
        }

        public String getImagePath() {
            return imagePath;
        }

        public String getPrice() {
            return price;
        }

        public double getAmount() {
            return amount;
        }

        public String getCategory() {
            return category;
        }

		public void setDiscount_normal(double p3) {
		
		}

		public int getQuantity() {
		
			return 0;
		}
    }
    public static void main(String[] args) {
        UserDashboard userDashboard = new UserDashboard();
        userDashboard.setVisible(true);
    }


}



