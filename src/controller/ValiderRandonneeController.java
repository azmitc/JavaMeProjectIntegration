/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Randonnee;
import services.SerRandonnee;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class ValiderRandonneeController implements Initializable {

    @FXML
    private TableView<Randonnee> randonneetable;
    @FXML
    private TableColumn<Randonnee,Date> dd_col;
    @FXML
    private TableColumn<Randonnee,Date> dr_col;
    @FXML
    private TableColumn<Randonnee,String> hd_col;
    @FXML
    private TableColumn<Randonnee,String> hr_col;
    @FXML
    private TableColumn<Randonnee,String> desc_col;
    @FXML
    private TableColumn<Randonnee,String> prix_col;
    @FXML
    private TableColumn<Randonnee,String> lieu_col;
    @FXML
    private TableColumn<Randonnee,String> niveau_col;
    @FXML
    private TableColumn<Randonnee,String> cat_col;
    @FXML
    private TableColumn<Randonnee,String> id_col;
    @FXML
    private TableColumn<Randonnee,String> equip_col;
    @FXML
    private TableColumn<Randonnee,String> img_col;
    @FXML
    private JFXTextField id_txt;
    @FXML
    private ImageView img_view;
    @FXML
    private JFXButton valid_btn;
   public ArrayList<Randonnee> ran;
    @FXML
    private JFXButton retour_btns;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      setmouseclick();
        afficherRandonnee();
        
    
      retour_btns.setOnAction((ActionEvent event) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AccueilAdmin.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
       private void setmouseclick(){
            randonneetable.setOnMouseClicked((MouseEvent event) -> {
     Randonnee ran=randonneetable.getItems().get(randonneetable.getSelectionModel().getSelectedIndex());
  
     id_txt.setText(ran.getId());
 
           Image a1=new Image("http://localhost/Image/"+ran.getImage());
        System.out.println("------------------------------");
        System.out.println("http://localhost/Image/"+ran.getImage());
        
        img_view.setImage(a1); 
            
   
});}
        
        public void afficherRandonnee() {

        Task<ArrayList<Randonnee>> task = new Task() {
            
            @Override
            protected Object call(){
              ran = new SerRandonnee().Affiches();
     return ran;
            }};
        task.setOnSucceeded(e -> {
                     
            dd_col.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
           dr_col.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
            hd_col.setCellValueFactory(new PropertyValueFactory<>("heure_depart"));
             hr_col.setCellValueFactory(new PropertyValueFactory<>("heure_retour"));
            desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
            prix_col.setCellValueFactory(new PropertyValueFactory<>("prix"));
            lieu_col.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            niveau_col.setCellValueFactory(new PropertyValueFactory<>("niveau"));
            cat_col.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
            equip_col.setCellValueFactory(new PropertyValueFactory<>("equipments"));
            img_col.setCellValueFactory(new PropertyValueFactory<>("image"));
            
             
            randonneetable.setItems(FXCollections.observableArrayList(task.getValue()));     
         
       
        
         
        });
        task.setOnFailed(e -> {
            //Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
           afficherRandonnee();
          // prog.setVisible(false);
                });
        Thread th = new Thread(task);
        th.start();
    }
        // TODO


    
    @FXML
    private void Valider(ActionEvent event) {
        
        
       String ide=id_txt.getText();
        
      SerRandonnee s=new SerRandonnee();
      s.Valider(ide);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Modif succes!");
        alert.show();
        
 afficherRandonnee();
     
           
    }
    
}
