/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.CategorieRandonnee;
import services.SerCategorieRandonnee;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
 * @author Amine
 */
public class ModifierCategorieController implements Initializable {

    @FXML
    private JFXTextArea desc_txt;
    @FXML
    private JFXTextField nom_txt;
    @FXML
    private JFXButton modif_btn;
    @FXML
    private JFXButton retour_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        retour_btn.setOnAction((ActionEvent event) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AfficheCategorie.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         CategorieRandonnee ran= AfficheCategorieController.cr;
        
        
        nom_txt.setText(ran.getNom());
       // prix_txt.setText(ran.getPrix());
        desc_txt.setText(ran.getDescription());
        
        // TODO
    }    

    @FXML
    private void Modifier(ActionEvent event) {
           CategorieRandonnee r = new CategorieRandonnee();
        r.setNom(nom_txt.getText());
        r.setDescription(desc_txt.getText());
           
            
        SerCategorieRandonnee s=new SerCategorieRandonnee();
        
      s.update(r);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Modif succes!");
        alert.show();

      nom_txt.clear();
desc_txt.clear();

    }
    
}
