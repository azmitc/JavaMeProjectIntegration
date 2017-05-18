/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controller.AccueilController;
import entities.Randonnee;
import entities.ReservationR;
import entities.ReservationR;
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
import services.ServiceReservation;
import services.ServiceReservationR;

/**
 * FXML Controller class
 *
 * @author azmi
 */
public class AnnulerreservationController implements Initializable {

    @FXML
    private TableColumn<?, ?> column_capacite;
    @FXML
    private TableColumn<?, ?> column_nbrejre;
    @FXML
    private TableColumn<?, ?> column_nbrepl;
    @FXML
    private TableColumn<?, ?> column_dateres;
    @FXML
    private TableColumn<?, ?> column_prix;
    @FXML
    private TableColumn<?, ?> column_remise;
    @FXML
    private TableColumn<?, ?> column_total;
    @FXML
    private JFXButton btn_annuler;
    
    private TableColumn<ReservationR,String> colum_org;
   private ObservableList<ReservationR>data;
  private Connection conn;
    private Statement ste;
    public ProgressIndicator prog;
   private ArrayList<ReservationR>reservation;
   static ReservationR ob;
    @FXML
    private TableView<ReservationR> table_reserv;
    @FXML
    private JFXTextField txt_capacite;
    
ReservationR e=new ReservationR();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
table_reserv.setOnMouseClicked(event->{
     txt_capacite.setText(e.getCapacite());  
       
    });
setonmouseclick();

        afficheReservation();
        // TODO
    }
    
    public void setonmouseclick(){
    column_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
           column_nbrejre.setCellValueFactory(new PropertyValueFactory<>("nbrejour"));
           column_nbrepl.setCellValueFactory(new PropertyValueFactory<>("nbreplace"));
            column_dateres.setCellValueFactory(new PropertyValueFactory<>("dateres"));
             column_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            column_remise.setCellValueFactory(new PropertyValueFactory<>("remise"));
            column_total.setCellValueFactory(new PropertyValueFactory<>("total"));
    
    
    }
public void afficheReservation() {

        Task<ArrayList<entities.ReservationR>> task = new Task() {
            @Override
            protected Object call() {
               // Platform.runLater(() -> prog.setVisible(true));
                
                
                    reservation =  new ServiceReservationR().afficher();
               
                return reservation;
            }
        };
        task.setOnSucceeded(e -> {
    

   column_capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
           column_nbrejre.setCellValueFactory(new PropertyValueFactory<>("nbrejour"));
           column_nbrepl.setCellValueFactory(new PropertyValueFactory<>("nbreplace"));
            column_dateres.setCellValueFactory(new PropertyValueFactory<>("dateres"));
             column_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            column_remise.setCellValueFactory(new PropertyValueFactory<>("remise"));
            column_total.setCellValueFactory(new PropertyValueFactory<>("total"));
            
            
         
table_reserv.setItems(FXCollections.observableArrayList(task.getValue()));
            
          //  prog.setVisible(false);

        }
        );
        task.setOnFailed(e -> {
            //Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
            afficheReservation();
//            prog.setVisible(false);

        });
        Thread th = new Thread(task);
        th.start();
    }


    @FXML
    private void annulerres(ActionEvent event) {
        
            
     
        services.ServiceReservationR s=new ServiceReservationR(); 
        
       // ReservationRondonnerController R=new ReservationRondonnerController();
       
       // ReservationR p=R.ob;
       // String  capacite=p.getCapacite();
      // s.deleteReservation(cin);
    
   String capacite= txt_capacite.getText();
       
s.deleteReservation(capacite);
Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Delete succes!");
        alert.show();
    afficheReservation();
    }
    
}
