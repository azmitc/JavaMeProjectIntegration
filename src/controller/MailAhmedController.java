/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import entities.Commentaire;

import controller.Mailer;

/**
 * FXML Controller class
 *
 * @author user
 */
public class MailAhmedController implements Initializable {
    @FXML
    private JFXTextField fromTxt;
    @FXML
    private JFXTextField pswTxt;
    @FXML
    private JFXTextField toTxt;
    @FXML
    private JFXTextField subTxt;
    @FXML
    private JFXTextArea msgTxt;

 Commentaire co=SujetAdminController.comment2;
  //Sujet suj1=FXMLDocumentController.suj;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        toTxt.setText(co.getMail());
    }    

    @FXML
    private void EnvoyerMail(ActionEvent event) {
        Mailer m= new Mailer();
        String  from=fromTxt.getText();
         String  pwd=pswTxt.getText();
         String  to=toTxt.getText();
         String sub=subTxt.getText();
         String msg=msgTxt.getText();
        m.send(from, pwd, to, sub, msg);
    }
    
}
