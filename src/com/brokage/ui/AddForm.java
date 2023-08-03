package com.brokage.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.brokage.controller.PropertiesController;
import com.brokage.model.Property;
import com.toedter.calendar.JDateChooser;

/**
 * <p>Add details to the database.</p>
 * @author Ajay Shrestha, Gaurab Pokharel, Nirajan Karki, Sakar Thapa
 * @since 7/28/2023
 **/
//UI to add data into the database
public class AddForm extends JFrame {

        private JLabel agentNameLabel;
        private JTextField agentNameField;
        private JLabel askingPriceLabel;
        private JTextField askingPriceField;
        private JLabel regionLabel;
        private JTextField regionField;
        private JLabel propertiesTypeLabel;
        private JTextField propertiesTypeField;
        private JLabel closingDateLabel;
        private JDateChooser closingDateField;

        //Constructor
        public AddForm(String username) {
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setTitle("Add New Details");

            // Create form components
            agentNameLabel = new JLabel("Agent Name:");
            agentNameField = new JTextField(20);
            askingPriceLabel = new JLabel("Asking Price:");
            askingPriceField = new JTextField(20);
            regionLabel = new JLabel("Region:");
            regionField = new JTextField(20);
            propertiesTypeLabel = new JLabel("Properties Type:");
            propertiesTypeField = new JTextField(20);
            closingDateLabel = new JLabel("Closing Date:");
            closingDateField = new JDateChooser();


            // Create a panel for the form
            int vgap = 10;
            int hgap = 10;
            JPanel formPanel = new JPanel(new GridLayout(7, 2, vgap, hgap));
            formPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            formPanel.add(agentNameLabel);
            formPanel.add(agentNameField);
            formPanel.add(askingPriceLabel);
            formPanel.add(askingPriceField);
            formPanel.add(regionLabel);
            formPanel.add(regionField);
            formPanel.add(propertiesTypeLabel);
            formPanel.add(propertiesTypeField);
            formPanel.add(closingDateLabel);
            formPanel.add(closingDateField);

            // Create a button for submitting the form
            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String agentName = agentNameField.getText();
                    String askingPrice = askingPriceField.getText();
                    String region = regionField.getText();
                    String propertiesType = propertiesTypeField.getText();
                    String closingDate = (closingDateField.getDate()).toString();
                    if (agentName.isEmpty() || askingPrice.isEmpty() || region.isEmpty() || propertiesType.isEmpty() || closingDate.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    PropertiesController propertiesController = new PropertiesController();
                    Property property = new Property(agentName,askingPrice,region,propertiesType,closingDate);
                    try {
						propertiesController.insertData(property);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    dispose();
                }
            });

            // Create a panel for the button
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(submitButton);

            // Create a main panel and add the form panel and button panel
            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.add(formPanel, BorderLayout.CENTER);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);

            // Add the main panel to the content pane
            getContentPane().add(mainPanel);

            pack();
            setLocationRelativeTo(null);
        }
    }
