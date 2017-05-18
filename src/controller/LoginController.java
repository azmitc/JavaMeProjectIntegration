/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
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
import services.ServiceAdmin;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.Admin;

import entities.Randonneur;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import services.ServiceRandonneur;
/**
 * FXML Controller class
 *
 * @author Othman Ben Jemaa
 */
public class LoginController implements Initializable {

   

    @FXML
    private JFXButton btn_connect;

    @FXML
    private JFXPasswordField txt_pwd;


    @FXML
    private JFXButton btn_inscription;

    @FXML
    private JFXTextField txt_username;
   
 

 public static Randonneur rand=new Randonneur();
 public static Admin adm=new Admin();


    @FXML
    private Hyperlink forget_psw;
    private Label message;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
            btn_connect.setOnAction(event -> {
            
        String login=txt_username.getText();
        rand.setUser_name(login);
                adm.setUser_name(login);

        ServiceRandonneur r=new ServiceRandonneur();
        rand=r.displayByLogin(login);

                String pwd=txt_pwd.getText();    
                ServiceAdmin s = ServiceAdmin.getInstance();
                adm=s.displayByLogin(login);

if ( s.login(login,pwd)){
    if(s.verifAdmin(login)){
        
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Randonni");
        alert.setHeaderText(null);
        alert.setContentText("Bienvenue!");
        alert.show();
        /*********************************************************************/
        try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListData());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    else{
        rand.setConnected(1);

              Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Randonni");
        alert.setHeaderText(null);
        alert.setContentText("Ca nous fait plaisir de te revoir :) ");
        alert.show();
        /***************************************************/
           try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/AccueilFront.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListData());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
}
           }        
else{
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Verifier vos donnees!");
        alert.show();
}
       
    
        });
            
            
            /****************************************************/
             btn_inscription.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/inscription.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
/*********************************************************/
        forget_psw.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/passwordLostScreen.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    

}
