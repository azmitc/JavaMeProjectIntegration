/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import static controller.AjoutMaterielController.file;
import entities.Materiel;
import entities.Randonneur;
import entities.Reclamation;
import services.ServiceMateriel;
import services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author balha
 */
public class AjoutRController implements Initializable {

    @FXML
    private JFXTextField client_txt;
    @FXML
    private JFXTextField desc_txt;
    @FXML
    //private JFXTextField id_txt;
    //@FXML
    private DatePicker achat_date;
    @FXML
    private DatePicker achat_txt;
    @FXML
    private ComboBox rec_txt;
    @FXML
    private JFXButton envoi_id;
    @FXML
    private JFXButton ret_btn;
    @FXML
    private Label txt_err;
    @FXML
    private Label desc_err;
    @FXML
    private Label err_id;
    @FXML
    private JFXComboBox id_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                rec_txt.getItems().addAll("Erreur","Défaut","Deception");
                
                   ObservableList<String> lst1 = FXCollections.observableArrayList();
                   ServiceMateriel s = new ServiceMateriel();
        lst1=s.getRefProd();
        id_txt.setItems(lst1);
       

        // TODO
        
    }    

    @FXML
    private void AddRec(ActionEvent event) {
        
     boolean isnotMail=validation. TextFieldValidation.texMail(client_txt, txt_err, "Il faut remplir avec un mail valide");
     boolean isnotAlphDesc=validation. TextFieldValidation.textalphabet(desc_txt, desc_err, "Il faut remplir avec des alphabets seulments");

if(isnotMail)
{
    txt_err.setText("");

}
if(isnotAlphDesc)
{
    desc_err.setText("");

}
              if ((isnotMail)&&(isnotAlphDesc)){
        
        
        
        Date d =java.sql.Date.valueOf(achat_date.getValue());
                Date d1 =java.sql.Date.valueOf(achat_date.getValue());

            ServiceReclamation ser=new ServiceReclamation();
    String  idC =client_txt.getText();
    String desc=desc_txt.getText();                 // services.ServiceOrganisateur s=new ServiceOrganisateur();

    String  reclamation=(String) rec_txt.getSelectionModel().getSelectedItem();
     
        String idM=(String) id_txt.getSelectionModel().getSelectedItem();
     
               
                Randonneur ran= AccueilFrontController.rand;

Reclamation r= new Reclamation(desc,reclamation,d,ran.getId(),idM) ;


        ser.ajouterReclamation(r);
   Notifications notification = Notifications.create()
                .title("ajout avec  success")
                .text("ajout success")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("ajout succes");
            }
        });
        notification.showConfirm();
}
    
    
}

    @FXML
    private void retour(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AccueilFront.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}