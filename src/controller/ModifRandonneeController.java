/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Randonnee;
import services.SerRandonnee;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeJava.type;
import static jdk.nashorn.internal.objects.NativeJava.type;
import static jdk.nashorn.internal.objects.NativeJava.type;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class ModifRandonneeController implements Initializable {

    @FXML
    JFXTextField prix_txt;
    @FXML
     JFXTextField equip_txt;
    @FXML
  JFXTextArea desc_txt;
    @FXML
     JFXComboBox niveau_cb,cat_cb;
    @FXML
    private JFXButton affiche_btn;
    @FXML
    private JFXTextField id_txt;
    @FXML
    private JFXDatePicker dd_pic;
    @FXML
    private JFXDatePicker dr_pic;
    @FXML
    private JFXDatePicker hd_pic;
    @FXML
    private JFXDatePicker hr_pic;
    @FXML
    private JFXTextField lieu_txt;
    @FXML
    private JFXTextField cir_txt;
    @FXML
    private JFXTextField nbrplace_txt;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        SerRandonnee S = new SerRandonnee();
        ObservableList<String> lst = FXCollections.observableArrayList();
        lst=S.getNomCategorie();
        cat_cb.getItems().setAll(lst);
        niveau_cb.getItems().setAll("Facile","Moyen","Difficile");
        Randonnee ran= AfficheRandonneeController.rand;
       
        
        Date date =ran.getDate_debut();
Instant instant = Instant.ofEpochMilli(date.getTime());
LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();


 Date date1 =ran.getDate_retour();
Instant instant1 = Instant.ofEpochMilli(date1.getTime());
LocalDate res1 = LocalDateTime.ofInstant(instant1, ZoneId.systemDefault()).toLocalDate();



Time time=ran.getHeure_depart();
Instant instant2 = Instant.ofEpochMilli(time.getTime());
LocalDate res2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault()).toLocalDate();
Time time1=ran.getHeure_retour();
Instant instant3 = Instant.ofEpochMilli(time1.getTime());
LocalDate res3 = LocalDateTime.ofInstant(instant3, ZoneId.systemDefault()).toLocalDate();

        dd_pic.setValue(res);
        dr_pic.setValue(res1);
        
        hd_pic.setValue(res2);
        hr_pic.setValue(res3);
        
        lieu_txt.setText(ran.getLieu());
        cir_txt.setText(ran.getCircuit());
        equip_txt.setText(ran.getEquipement());
        prix_txt.setText(ran.getPrix());
        desc_txt.setText(ran.getDescription());
        id_txt.setText(ran.getId());
        cat_cb.setValue(ran.getCategorie());
        niveau_cb.setValue(ran.getNiveau());
        nbrplace_txt.setText(ran.getNbrplace());
        
         affiche_btn.setOnAction((ActionEvent event) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AfficheRandonnee.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        
        
        // TODO
    }    

    @FXML
    private void Modifier(ActionEvent event) {
          Randonnee r = new Randonnee();
          
             SerRandonnee S = SerRandonnee.getInstance();
       r.setId(id_txt.getText());
       r.setPrix(prix_txt.getText());
            Date d = java.sql.Date.valueOf(dd_pic.getValue());
                    Date d1 = java.sql.Date.valueOf(dr_pic.getValue());
                    Time t=java.sql.Time.valueOf(hd_pic.getTime());
                     Time t1=java.sql.Time.valueOf(hr_pic.getTime());
                    String a =S.getIdCategorie(cat_cb.getSelectionModel().getSelectedItem().toString());
             
                        r.setNbrplace(equip_txt.getText());
                    r.setDate_debut(d);
                    r.setDate_retour(d1);
                    r.setHeure_depart(t);
                    r.setHeure_retour(t1);
                    r.setNbrplace(nbrplace_txt.getText());
                    r.setLieu(lieu_txt.getText());
                    r.setCircuit(cir_txt.getText());
            r.setNiveau((String) niveau_cb.getSelectionModel().getSelectedItem());
           r.setEquipement(equip_txt.getText());
           
 r.setCategorie(a);
          r.setDescription(desc_txt.getText());


      SerRandonnee s=new SerRandonnee();
     
      s.update(r);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Modif succes!");
        alert.show();

      prix_txt.clear();
equip_txt.clear();
desc_txt.clear();


niveau_cb.getItems().clear();
cat_cb.getItems().clear();
       lieu_txt.clear();
cir_txt.clear();
nbrplace_txt.clear();

    }
    
    
}
