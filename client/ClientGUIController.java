/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import networking.LoginHandler;
import networking.User;
import logging.Error;

/**
 * FXML Controller class
 *
 * @author austen
 */
public class ClientGUIController implements Initializable {
    //FXML Injections
    @FXML private TextField user;
    @FXML private TextField pass;
    @FXML private TextArea textArea;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void login(ActionEvent e)
    {
        String username = user.getText();
        String password = pass.getText();
        User user = LoginHandler.login(username, password, true);
        if (user == null)
        {
            this.user.setText("");
            this.pass.setText("");
            this.textArea.setText("Unable to verify account");
        }
        else
        {
            CurrentUser.setUser(user);
            Parent root;
            try
            {
                root = FXMLLoader.load(getClass().getResource("Main.fxml"));
                Stage stage = new Stage();
                stage.setTitle("AKNR Desktop App");
                stage.setScene(new Scene(root));
                stage.show();
                ((Node) (e.getSource())).getScene().getWindow().hide();
            }
            catch(Exception ex)
            {
                new Error("GUI Error").log();
            }
        }
    }
    
}
