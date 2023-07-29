package com.project.ui;

import com.project.controller.PropertiesController;
import com.project.model.Property;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>View details from the database.</p>
 * @author Ajay Shrestha, Gaurab Pokharel, Nirajan Karki, Sakar Thapa
 * @since 7/28/2023
 **/
//View ui to display all the result selected from the database with delete button
//search with agent name and back button
public class ViewUI extends JFrame {
	private JTable userTable;
	private JButton backButton;
	private JButton deleteButton;
	private List<Integer> selectedRows;
	private JTextField searchField;
	private JButton searchButton;

	private JButton updateButton;

	//constructor
	public ViewUI(String username) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Details Display");
		searchField = new JTextField(20);
		searchButton = new JButton("Search");

		// Initialize the selectedRows list
		selectedRows = new ArrayList<>();
		// Create the table model
		DefaultTableModel tableModel = new DefaultTableModel() {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				if (columnIndex == 0) {
					return Boolean.class; // Checkbox column
				}
				return super.getColumnClass(columnIndex);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0; // Allow editing only for the checkbox column
			}
		};

		// Add column headers
		tableModel.addColumn("Select");
		tableModel.addColumn("Property Id");
		tableModel.addColumn("Agent Name");
		tableModel.addColumn("Asking Price");
		tableModel.addColumn("Region");
		tableModel.addColumn("Properties Type");
		tableModel.addColumn("Closing Date");

		PropertiesController propertiesController = new PropertiesController();
		List<Property> propertiesArrayList = propertiesController.getAllPropertiesDetail();
		int rowCount = 0;
		// Add rows to the table model
		for (Property property : propertiesArrayList) {
			int propertyId = property.getPropertyId();
			String agentName = property.getAgentName();
			String askingPrice = property.getAskingPrice();
			String region = property.getRegion();
			String propertiesType = property.getPropertiesType();
			String closingDate = property.getClosingDate();

			// Create an array to hold the user data for each row
			Object[] rowData = { false, propertyId, agentName, askingPrice, region, propertiesType, closingDate };

			// Add the row to the table model
			tableModel.addRow(rowData);

			// Add the row index to the selectedRows list
			selectedRows.add(rowCount);
			rowCount++;
		}

		// Create the JTable with the table model
		userTable = new JTable(tableModel);

		// Add the table to a scroll pane
		JScrollPane scrollPane = new JScrollPane(userTable);

//        // Add the scroll pane to the content pane
		getContentPane().add(scrollPane);
		pack();

		// Create the "Back" button
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create a new instance of IndexPage
				IndexUIPage indexPage = new IndexUIPage(username);

				// Set the IndexPage frame as visible
				indexPage.setVisible(true);

				// Dispose the current ViewUser frame
				dispose();
			}
		});

		// Create the "Delete" button
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the selected row(s)?",
						"Confirmation", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					deleteSelectedRows();
					ViewUI v = null;
					
					try {
						v = new ViewUI(username);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					v.setVisible(true);
				}
			}
		});

		//Create the "Update" button
		updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) userTable.getModel();
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to update the selected row(s)?",
						"Confirmation", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					if (selectedRows.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No rows selected.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						Property property = new Property();
						for (int i = selectedRows.size() - 1; i >= 0; i--) {
							int rowIndex = selectedRows.get(i);
							String flag = model.getValueAt(rowIndex, 0).toString();
							if (flag.equalsIgnoreCase("true")) {
								int propertyId = Integer.parseInt(model.getValueAt(rowIndex, 1).toString());
								property = propertiesController.getPropertiesDetailByPropertyId(propertyId);
								UpdateUI updateUI = null;
								try {
									updateUI = new UpdateUI(property, propertyId, username);
									updateUI.setVisible(true);
									dispose();
								} catch (ParseException ex) {
									throw new RuntimeException(ex);
								}
							}
						}
					}
				}
			}
		});

		//Search button
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchAgentName = searchField.getText();
				searchByAgentName(searchAgentName);
			}
		});

		JPanel searchPanel = new JPanel();
		searchPanel.add(new JLabel("Search by Agent Name:"));
		searchPanel.add(searchField);
		searchPanel.add(searchButton);

		// Create a panel to hold the buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(backButton);

		// Create a container to hold the scroll pane and the button panel
		Container container = getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.add(searchPanel);
		container.add(scrollPane);
		container.add(buttonPanel);

		// Pack the components
		pack();

		// Center the frame on the screen
		setLocationRelativeTo(null);
	}

	//method to delete the select rows
	private void deleteSelectedRows() {
		if (selectedRows.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No rows selected.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		DefaultTableModel model = (DefaultTableModel) userTable.getModel();
		PropertiesController propertiesController = new PropertiesController();
		// Delete the selected rows from the database
		for (int i = selectedRows.size() - 1; i >= 0; i--) {
			int rowIndex = selectedRows.get(i);
			String flag = model.getValueAt(rowIndex, 0).toString();
			if (flag.equalsIgnoreCase("true")) {
				int propertyId = Integer.parseInt(model.getValueAt(rowIndex, 1).toString());
				propertiesController.deleteDataFromProperties(propertyId);
				model.removeRow(rowIndex);
				dispose();
			}
		}
	}

	//method to search agent name
	private void searchByAgentName(String agentName) {
		DefaultTableModel model = (DefaultTableModel) userTable.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		userTable.setRowSorter(sorter);

		if (agentName.trim().isEmpty()) {
			sorter.setRowFilter(null); // Clear the filter if the search box is empty
		} else {
			sorter.setRowFilter(RowFilter.regexFilter("(?i)" + agentName, 2)); // Search by agent name (column index 2)
		}
	}
}
