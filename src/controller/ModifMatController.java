/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import entities.Materiel;
import services.ServiceMateriel;

/**
 * FXML Controller class
 *
 * @author balha
 */
public class ModifMatController implements Initializable {

    @FXML
    private JFXTextField txt_nom;
    @FXML
    private JFXTextField txt_ref;
    @FXML
    private JFXTextField txt_prix;
    @FXML
    private JFXTextField txt_desc;
    @FXML
    private JFXTextField txt_quant;
    @FXML
    private JFXComboBox  combo_type;
    @FXML
    private JFXComboBox combo_etat;
    @FXML
    private JFXButton btn_modif;
    @FXML
    private JFXTextField txt_id;
    @FXML
    private JFXButton id_btn;
    @FXML
    private Label nom_err;
    @FXML
    private Label ref_err;
    @FXML
    private Label prix_err;
    @FXML
    private Label desc_err;
    @FXML
    private Label quant_err;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Materiel mat1= AjoutMaterielController.mat;
        txt_nom.setText(mat1.getNom());
        txt_id.setText(mat1.getId());
        txt_ref.setText(mat1.getReference());
        txt_prix.setText(mat1.getPrix());
        txt_desc.setText(mat1.getDescription());
        txt_quant.setText(mat1.getQuantite());
        combo_type.setValue(mat1.getType());
        combo_etat.setValue(mat1.getEtat());
        combo_etat.getItems().addAll("1","0");
        combo_type.getItems().addAll("couchage","sac a dos","tentes","habillements");
         

                
        
    }    

    @FXML
    private void ModifMat(ActionEvent event) {
/*      boolean isnotAlphNom=validation. TextFieldValidation.textalphabet(txt_nom, nom_err, "Il faut remplir avec des alphabets");       boolean isnotAlphDesc=validation. TextFieldValidation.textalphabet(txt_desc, desc_err, "Il faut remplir avec des alphabets");
       boolean isNumprix=validation. TextFieldValidation.texNum (txt_prix, prix_err, "Il faut remplir avec des numero");
       boolean isNumquant=validation. TextFieldValidation.texNum (txt_quant, quant_err, "Il faut remplir avec des numero");
       boolean isAlphaNumReft=validation. TextFieldValidation.texNum  (txt_ref, ref_err, "Il faut remplir avec des nums");


if (isnotAlphDesc)
{
desc_err.setText("");
}
if (isNumprix)
{
prix_err.setText("");
}
if (isNumquant)
{
quant_err.setText("");
}
if (isAlphaNumReft)
{
ref_err.setText("");
}
    */    
                     //  if ((isnotAlphDesc)&&(isNumprix)&&(isNumquant)&&(isAlphaNumReft)){
                           

        
        
         String  nom=txt_nom.getText();
          String  prix=txt_prix.getText();
          String description=txt_desc.getText();
         // String type=txt_type.getText();
         
            String type=  (String) combo_type.getSelectionModel().getSelectedItem();
         
           String reference=txt_ref.getText();
           String quantite=txt_quant.getText();
          String id=txt_id.getText();
         //   String etat=  (String) combo_etat.getSelectionModel().getSelectedItem();
               String etat=   (String) combo_etat.getSelectionModel().getSelectedItem();



      ServiceMateriel s=new ServiceMateriel();
      s.updateMateriel(nom,reference,prix,description,type, etat,quantite,id);
     /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Modif succes!");
        alert.show();*/
        Notifications notification = Notifications.create()
                .title("modif avec  success")
                .text("modif success")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("modif succes");
            }
        });
        notification.showConfirm();
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Ajout.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
                       
    //  afficherMat();
   /*   txt_ref.clear();
txt_nom.clear();
txt_prix.clear();
txt_desc.clear();
txt_quant.clear();*/
        
    }

    @FXML
    private void retour(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Ajout.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
