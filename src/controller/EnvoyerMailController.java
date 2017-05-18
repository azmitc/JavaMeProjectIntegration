/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import controller.Mailer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Othman Ben Jemaa
 */
public class EnvoyerMailController implements Initializable {

    @FXML
    private JFXTextArea txt_msg;
    @FXML
    private JFXTextField txt_mail;
    @FXML
    private JFXPasswordField txt_psw;
    @FXML
    private JFXButton btn_envoyer;
    @FXML
    private JFXTextField txt_sujet;
    @FXML
    private JFXButton btn_retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_envoyer.setOnAction(event -> {
            
        String mail=txt_mail.getText();
                String pwd=txt_psw.getText();
                 String sujet=txt_sujet.getText();
                 String msg=txt_msg.getText();
                Mailer.send(mail,pwd,"randonnitunisie@gmail.com",sujet,msg);  
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Mail envoyé avec succés!");
        alert.show();
       }  
        ); 
             btn_retour.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AccueilFront.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    
}
}