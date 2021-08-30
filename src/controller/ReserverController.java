/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Materiel;
import entities.Randonneur;
import entities.reservation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.ServiceMateriel;
import services.ServiceRandonneur;
import services.ServiceReservation;
import utils.MyImage;


/**
 * FXML Controller class
 *
 * @author balha
 */
public class ReserverController implements Initializable {

    @FXML
    private ImageView img_id;
    @FXML
    private JFXTextField txt_prix;
    @FXML
    private JFXTextField txt_quant;
    @FXML
    private DatePicker date_txt;
    @FXML
    private JFXButton vld_btn;
    @FXML
    private TextField txt_id;
    @FXML
    private JFXButton ret_btn;
    @FXML
    private JFXTextField ss;
    @FXML
    private TextField qte_txt;
    @FXML
    private JFXTextField reslt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                Materiel mat1= ListeMaterielController.mat;
txt_prix.setText(mat1.getPrix());
txt_id.setText(mat1.getId());
qte_txt.setText(mat1.getQuantite());
//ss.setText(mat1.get)
   ss.setText(mat1.getPic());
   
     String ide=ss.getText();
    // img_id.setImage(MyImage.fromResources(ide));


        
    }    
//
    @FXML
    private void add_Res(ActionEvent event) {
        
ServiceReservation ser=new ServiceReservation();
String  prix=txt_prix.getText();     
String quantite=txt_quant.getText();
//Date d =java.sql.Date.valueOf(date_txt.getValue());
String image = ss.getText();
String idM=txt_id.getText();
        Randonneur ran= AccueilFrontController.rand;

reservation R=new reservation (prix, quantite, idM,image,ran.getId());
        System.out.println("id "+ran.getId());
ServiceRandonneur s=new ServiceRandonneur();
ser.ajouterReservation(R);
s.updateptsfidelite(ran.getUser_name(),20);
ran.setPt_fidel(ran.getPt_fidel()+20);
/**************Notifications*********************************/
            Notifications notification = Notifications.create()
                .title("Merci pour votre fidelite !")
                .text("Vous venez de gagner 20 points de fidelite :) a bientot! ")
                .graphic(null)
                .hideAfter(Duration.seconds(7))
                .position(Pos.BOTTOM_RIGHT)
                .onAction((ActionEvent event1) -> {
         });
        notification.showConfirm();

/***********************************************************/
Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reservation avec succes!");
        alert.show();
        ServiceMateriel m = new ServiceMateriel();
        //Materiel m2=new Materiel(idM,quantite);
        
         
        int rest = Integer.parseInt(qte_txt.getText()) -Integer.parseInt(quantite); 
        //Integer.toString(rest);
       
        System.out.println(rest);
        m.updateQuant(Integer.toString(rest),idM);
        //System.out.println(idM);
        
        
       // System.out.println(m.ge);
    }

    @FXML
    private void retour(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/ListeMateriel.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
