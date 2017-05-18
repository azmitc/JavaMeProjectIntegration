/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import entities.Randonnee;
import entities.ReservationR;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.Header;
import services.ServiceReservation;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import services.ServiceReservationR;

/**
 * FXML Controller class
 *
 * @author azmi
 */
public class RecuController implements Initializable {

    @FXML
    private JFXButton btn_imprimer;
    @FXML
    private TableView<ReservationR> tabel_reserv;
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
    private TableColumn<ReservationR,String> colum_org;
   private ObservableList<ReservationR>data;
  private Connection conn;
    private Statement ste;
    public ProgressIndicator prog;
   private ArrayList<ReservationR>reservation;
   static Randonnee ob;
    @FXML
    private JFXButton btn_retour;
    
   /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
        
        afficheReservation();
        
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
            
            
         
tabel_reserv.setItems(FXCollections.observableArrayList(task.getValue()));
            
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
    public void print(final Node node) {
    Printer printer = Printer.getDefaultPrinter();

    PrinterJob job = PrinterJob.createPrinterJob();
    if (job != null) {
        boolean success = job.printPage(node);
        if (success) {
            job.endJob();
        }
    }
}

    @FXML
    private void imprimer(ActionEvent event) {
        print(tabel_reserv);
    
    
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
    
    
    
    

    

    

