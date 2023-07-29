package com.project.ui;

import com.project.controller.PropertiesController;
import com.project.model.Property;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Updates details to the database.</p>
 * @author Ajay Shrestha, Gaurab Pokharel, Nirajan Karki, Sakar Thapa
 * @since 7/28/2023
 **/

public class UpdateUI extends JFrame {
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

        public UpdateUI(Property property, int propertyId,String userName) throws ParseException {
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

            agentNameField.setText(property.getAgentName());
            askingPriceField.setText(property.getAskingPrice());
            regionField.setText(property.getRegion());
            propertiesTypeField.setText(property.getPropertiesType());
            SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = sdfFormat.parse(property.getClosingDate());
            closingDateField.setDate(date);

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
                    Property property = new Property(agentName, askingPrice, region, propertiesType, closingDate);
                    propertiesController.updateDataFromPropertyId(property,propertyId);
                    JOptionPane.showMessageDialog(null,"Update is done successfully.");
                    try {
                        ViewUI viewUI = new ViewUI(userName);
                        viewUI.setVisible(true);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
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