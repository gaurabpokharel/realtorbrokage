package com.brokage.ui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * <p>Index page if the login success</p>
 * @author Ajay Shrestha, Gaurab Pokharel, Nirajan Karki, Sakar Thapa
 * @since 7/28/2023
 **/
//Index Page if login is success
public class IndexUIPage extends JFrame {
	private JButton addButton;
	private JButton viewButton;

	public IndexUIPage(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Welcome");

		// Create a label to display the welcome message
//		JLabel welcomeLabel = new JLabel("Welcome: " + username);
//		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
//		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// Create buttons for different actions
		addButton = createButton("Add Detail");
		viewButton = createButton("View Details");

		// Add action listeners to the buttons
		addButton.addActionListener(e -> openAddForm(username));
		viewButton.addActionListener(e -> {
			try {
				openViewForm(username);
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
		});

		// Create a panel to hold the buttons
		JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		buttonPanel.add(addButton);
		buttonPanel.add(viewButton);

		// Create a main panel and add the components
		JPanel mainPanel = new JPanel(new BorderLayout());
//		mainPanel.add(welcomeLabel, BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.CENTER);

		// Add the main panel to the content pane
		getContentPane().add(mainPanel);

		pack();
		setLocationRelativeTo(null);
	}

	// Utility method to create a styled button
	private JButton createButton(String text) {
		JButton button = new JButton(text);

		button.setFont(new Font("Arial", Font.PLAIN, 14));
		button.setPreferredSize(new Dimension(180, 60));
		return button;
	}

	// Methods to open different forms
	private void openAddForm(String username) {
		AddForm addForm = new AddForm(username);
		addForm.setVisible(true);
	}

	private void openViewForm(String username) throws SQLException {
		ViewUI view = new ViewUI(username);
		view.setVisible(true);
		dispose();
	}

	// Utility method to show an error dialog
	private void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}