/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.CategorieRandonnee;
import services.SerCategorieRandonnee;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class AfficheCategorieController implements Initializable {

    @FXML
    private TableColumn<CategorieRandonnee, String> nom_col;
    @FXML
    private TableColumn<CategorieRandonnee, String> img_col;
    @FXML
    private TableColumn<CategorieRandonnee, String> desc_col;
    public static CategorieRandonnee cr=new CategorieRandonnee();
    @FXML
    private TableView<CategorieRandonnee> categorie_table;
    public  ArrayList<CategorieRandonnee> sercr;
    public ProgressIndicator prog;
    @FXML
    private JFXButton supp_btn;
    @FXML
    private JFXButton modif_btn;
    @FXML
    private JFXTextField rech_txt;
    @FXML
    private TableColumn<CategorieRandonnee ,String> id_col;
    @FXML
    private JFXButton retour_btn;
    @FXML
    private JFXTextField id_text;
    @FXML
    private ImageView imgshow;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 //.setOnMouseClicked(event->
   // {idtxt.setText(listdat.getPers().get(tableCandidat.getSelectionModel().getSelectedIndex()).getCin());  });
         setmouseclick();
 afficherCategorie();
 
  retour_btn.setOnAction((ActionEvent event) -> {
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
            categorie_table.setOnMouseClicked((MouseEvent event) -> {
     CategorieRandonnee ran=categorie_table.getItems().get(categorie_table.getSelectionModel().getSelectedIndex());
  
     id_text.setText(ran.getId());
   
  Image a1=new Image("http://localhost/ImagePi/"+ran.getImage());
        System.out.println("------------------------------");
        System.out.println("http://localhost/ImagePi/"+ran.getImage());
        
        imgshow.setImage(a1);    
   
});}
    public void afficherCategorie() {

        Task<ArrayList<CategorieRandonnee>> task = new Task() {
            
            @Override
            protected Object call(){
   sercr =  new SerCategorieRandonnee().Affiches();
     return sercr;
            }};
        task.setOnSucceeded(e -> {
          id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
    nom_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
            img_col.setCellValueFactory(new PropertyValueFactory<>("image_name"));
            desc_col.setCellValueFactory(new PropertyValueFactory<>("Description"));
            categorie_table.setItems(FXCollections.observableArrayList(task.getValue()));     
           
            // prog.setVisible(false);
        });
        task.setOnFailed(e -> {
            //Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
           afficherCategorie();
           prog.setVisible(false);});
        Thread th = new Thread(task);
        th.start();
    }
    
      public void afficherRech() {

        Task<ArrayList<CategorieRandonnee>> task = new Task() {
            @Override
            protected Object call() {
//                Platform.runLater(() -> prog.setVisible(true));
                String lieu=rech_txt.getText();
                ArrayList<CategorieRandonnee> randonnee = new SerCategorieRandonnee().listerRech(lieu);
                return randonnee;
            }
        };
        task.setOnSucceeded(e -> {
 id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
    nom_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
            img_col.setCellValueFactory(new PropertyValueFactory<>("image_name"));
            desc_col.setCellValueFactory(new PropertyValueFactory<>("Description"));
     
            categorie_table.setItems(FXCollections.observableArrayList(task.getValue())); 
//            prog.setVisible(false);

        }
        );
        task.setOnFailed(e -> {
          //  Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
            afficherCategorie();
            prog.setVisible(false);

        });
        Thread th = new Thread(task);
        th.start();
    }
     
    @FXML
    private void Supprimer(ActionEvent event) {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("voulez vous effacer cette randonnee ?");
        Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK) {
        String ide=id_text.getText();
        
      SerCategorieRandonnee s=new SerCategorieRandonnee();
      s.delete(ide);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Delete succes!");
        alert.show();
      
      afficherCategorie();
         }
    }

    @FXML
    private void Modifier(ActionEvent event) {
        
        cr=categorie_table.getItems().get(categorie_table.getSelectionModel().getSelectedIndex());
  
        
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/ModifierCategorie.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void recherche(KeyEvent event) {
         
        categorie_table.getItems().clear();
        afficherRech();
    
    }
    
    
    
}
