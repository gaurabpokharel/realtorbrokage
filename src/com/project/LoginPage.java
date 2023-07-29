package com.project;

/**
 * <p>Default class to display when program runs</p>
 * @author Ajay Shrestha, Gaurab Pokharel, Nirajan Karki, Sakar Thapa
 * @since 7/28/2023
 **/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import com.project.dao.DatabaseConnectivityDao;
import com.project.ui.IndexUIPage;
import org.apache.ibatis.jdbc.ScriptRunner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class LoginPageForm extends JFrame implements ActionListener {

	//defining label and button for ui
    private JTextField textField1;
    private JPasswordField passwordField;
    private JButton submitButton;


    //LoginForm ui
    LoginPageForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Realtor Brokerage");

        // Create the main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        // Create the username label and text field
        JLabel userLabel = new JLabel("Username");
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(userLabel, constraints);

        textField1 = new JTextField(20); // Increased width to 20
        constraints.gridx = 1;
        constraints.gridy = 0;
        mainPanel.add(textField1, constraints);

        // Create the password label and password field
        JLabel passLabel = new JLabel("Password");
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(passLabel, constraints);

        passwordField = new JPasswordField(20); // Increased width to 20
        constraints.gridx = 1;
        constraints.gridy = 1;
        mainPanel.add(passwordField, constraints);

        // Create the submit button
        submitButton = new JButton("Submit");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(submitButton, constraints);

        // Add an action listener to the submit button
        submitButton.addActionListener(this);

        // Add the main panel to the content pane
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    //actionPerformed to validate whether user input match with username and password
    @Override
    public void actionPerformed(ActionEvent e) {
        String userValue = textField1.getText();
        String passValue = new String(passwordField.getPassword());

        if (userValue.equals("test@gmail.com") && passValue.equals("test")) {
        	//If success redirect to the IndexUIPage
            IndexUIPage page = new IndexUIPage(userValue);
            page.setVisible(true);

            dispose();
        } else {
        	//If fail show the message as invalid username or password as login error
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class LoginPage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set the look and feel to the system's default
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //Open LoginForm
            LoginPageForm form = new LoginPageForm();
            form.setVisible(true);
        });
        try {
        	//Connect with the database
            DatabaseConnectivityDao jdbConnection = new DatabaseConnectivityDao();
            Connection con = jdbConnection.setUpConnection();

            // Initialize the script runner to create the database,table and insert the data 
            ScriptRunner sr = new ScriptRunner(con);

            // Creating a reader object
            FileReader fileReader = new FileReader("ProjectSQL.sql");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Running the script
            sr.runScript(bufferedReader);

            // Closing the reader and connection
            bufferedReader.close();
            con.close();
        } catch (SQLException | IOException e) {
        	//Catching the exception if occured
            e.printStackTrace();
        }
    }
}
