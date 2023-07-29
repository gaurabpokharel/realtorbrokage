package com.project.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.project.controller.PropertiesController;
import com.project.model.Properties;
import com.toedter.calendar.JDateChooser;

/**
 * @author
 * @project RealtorBrokage
 * @since 7/28/2023
 **/
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
                    Properties properties = new Properties(agentName,askingPrice,region,propertiesType,closingDate);
                    propertiesController.insertData(properties);
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
