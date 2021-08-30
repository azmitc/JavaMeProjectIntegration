/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import controller.AccueilController;
import entities.Guide;

import entities.Randonnee;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ServiceOrganisateur;
import services.ServiceRandonnee;

/**
 * FXML Controller class
 *
 * @author azmi
 */
public class ReservationController implements Initializable {

    @FXML
      private TableColumn<Randonnee,String> column_gui;
    private TableColumn<Randonnee,String> colum_org;
   private ObservableList<Randonnee>data;
  private Connection conn;
    private Statement ste;
    public ProgressIndicator prog;
   private ArrayList<Randonnee>randonnee;
   static Randonnee ob;
  
    
    @FXML
    private TableColumn<Randonnee,String> column_img;
    @FXML
    private TableColumn<Randonnee,String> column_datedebt;
    @FXML
    private TableColumn<Randonnee,String> column_date_fin;
    @FXML
    private TableColumn<Randonnee,String> column_hredep;
    @FXML
    private TableColumn<Randonnee,String> column_hre_ret;
    @FXML
    private TableColumn<Randonnee,String> column_prix;
    @FXML
    private TableColumn<Randonnee,String> column_nbrpl;
    @FXML
    private TableColumn<Randonnee,String> column_lieu;
    @FXML
    private TableColumn<Randonnee,String> column_circuit;
    @FXML
    private TableColumn<Randonnee,String> column_niveau;
    @FXML
    private TableColumn<Randonnee,String> column_descri;
    @FXML
    private TableColumn<Randonnee,String> column_cat;
    @FXML
    private TableColumn<Randonnee,String> column_note;
    @FXML
    private TableColumn<Randonnee,String> column_auto;
   @FXML
    private TableColumn<Randonnee,String> column_equip;

    @FXML
    private TableView<Randonnee> table_reserv;
    @FXML
    private JFXButton btn_reserv;
    @FXML
    private TableColumn<?, ?> column_org;
    @FXML
    private JFXButton back;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        btn_reserv.setOnAction(event -> {
            
           

            try {
                
         ob=table_reserv.getSelectionModel().getSelectedItem();
         
         System.out.println(ob);
         if(ob==(null)){
         
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("error");
        alert.setHeaderText(null);
        alert.setContentText("selectionné un Randonné ");
        alert.show();
         
         }
                
                
         else{       
                
                
                
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/ReservationRondonner.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
         }
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });   // TODO
afficherRandonnee();

// TODO
    }    
    
    
    
    public void afficherRandonnee() {

        Task<ArrayList<Randonnee>> task = new Task() {
            @Override
            protected Object call() {
               // Platform.runLater(() -> prog.setVisible(true));
                
                
                    randonnee =  new services.SerRandonnee().Affiches();
               
                return randonnee;
            }
        };
        task.setOnSucceeded(e -> {
    

   column_datedebt.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
           column_date_fin.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
           column_nbrpl.setCellValueFactory(new PropertyValueFactory<>("nbrplace"));
            column_hredep.setCellValueFactory(new PropertyValueFactory<>("heure_depart"));
             column_hre_ret.setCellValueFactory(new PropertyValueFactory<>("heure_retour"));
            column_descri.setCellValueFactory(new PropertyValueFactory<>("description"));
            column_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            column_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
             column_circuit.setCellValueFactory(new PropertyValueFactory<>("circuit"));
            column_cat.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            column_niveau.setCellValueFactory(new PropertyValueFactory<>("niveau"));
         column_equip.setCellValueFactory(new PropertyValueFactory<>("equipement"));    
           column_gui.setCellValueFactory(new PropertyValueFactory<>("id_guide"));  
           column_org.setCellValueFactory(new PropertyValueFactory<>("id_organisateur"));  
            column_note.setCellValueFactory(new PropertyValueFactory<>("note"));
                     column_auto.setCellValueFactory(new PropertyValueFactory<>("equipement"));    

           column_auto.setCellValueFactory(new PropertyValueFactory<>("autorisation"));    
            
      
            column_img.setCellValueFactory(new PropertyValueFactory<>("image"));
            
         
table_reserv.setItems(FXCollections.observableArrayList(task.getValue()));
            
          //  prog.setVisible(false);

        }
        );
        task.setOnFailed(e -> {
            //Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
            afficherRandonnee();
//            prog.setVisible(false);

        });
        Thread th = new Thread(task);
        th.start();
    }

    @FXML
    private void back(ActionEvent event) {
         try {
                System.out.println("testttttttttttttt");
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/view/AccueilFront.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(controller.AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
        
    

    
}
