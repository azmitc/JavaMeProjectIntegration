/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static controller.CommentaireController.comment1;
import services.ServiceCommentaire;
import services.ServiceSujet;
import entities.Commentaire;
import entities.Sujet;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SujetAdminController implements Initializable {
    @FXML
    private TableColumn<Sujet, String> titreSujet;
    @FXML
    private TableColumn<Sujet, String> SujetId;
    @FXML
    private TableColumn<Commentaire, String> textComm;
    @FXML
    private TableColumn<Commentaire, String> userComm;
    @FXML
    private TableColumn<Commentaire,String> idCom;
    @FXML
    private TableView<Sujet> tabSuj;
    @FXML
    private TableView<Commentaire> tabComm;
 public  ArrayList<Commentaire> comm;
  public  ArrayList<Sujet> suj;
    @FXML
    private JFXTextField rechComTxt;
    private JFXTextField sujidchache;
    @FXML
    private JFXTextField comidcache;
    @FXML
    private TableColumn<Commentaire, String> SujIdCol;
    @FXML
    private TableColumn<Commentaire, String> MailCol;
    @FXML
    private JFXTextField MailCache;
    public static Commentaire comment2=new Commentaire();
    @FXML
    private JFXButton RetA;
    @FXML
    private TableColumn<Commentaire, String> dateCol;
    @FXML
    private TableColumn<Sujet,String> dateSuj;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherSuj();
       afficherCom();
       setmouseclick();
    }   
    
     private void setmouseclick(){
   tabSuj.setOnMouseClicked((MouseEvent event) -> {
    Sujet suj=tabSuj.getItems().get(tabSuj.getSelectionModel().getSelectedIndex());   
   rechComTxt.setText(suj.getIdentif());
});
      tabComm.setOnMouseClicked((MouseEvent event) -> {
    Commentaire com=tabComm.getItems().get(tabComm.getSelectionModel().getSelectedIndex());   
    comidcache.setText(com.getId_com());
    MailCache.setText(com.getMail());
});
     
     }
    public void afficherSuj() {

      Task<ArrayList<Sujet>> task = new Task() {
            @Override
            protected Object call() {
            
suj=new ServiceSujet().Affiches();
  
     return suj;
            }};
        task.setOnSucceeded(e -> {        
    titreSujet.setCellValueFactory(new PropertyValueFactory<>("titre"));
     SujetId.setCellValueFactory(new PropertyValueFactory<>("identif"));
   dateSuj.setCellValueFactory(new PropertyValueFactory<>("updated_at"));
    tabSuj.setItems(FXCollections.observableArrayList(task.getValue())); 
    
        });
        task.setOnFailed(e -> {
            afficherSuj();});
           // prog.setVisible(false); 
        Thread th = new Thread(task);
        th.start();
    }
    public void afficherSujNoDispo() {

      Task<ArrayList<Sujet>> task = new Task() {
            @Override
            protected Object call() {
            
suj=new ServiceSujet().AffichesnoDispo();
  
     return suj;
            }};
        task.setOnSucceeded(e -> {        
    titreSujet.setCellValueFactory(new PropertyValueFactory<>("titre"));
     SujetId.setCellValueFactory(new PropertyValueFactory<>("identif"));
   
    tabSuj.setItems(FXCollections.observableArrayList(task.getValue())); 
    
        });
        task.setOnFailed(e -> {
            afficherSujNoDispo();});
           // prog.setVisible(false); 
        Thread th = new Thread(task);
        th.start();
    }
    
       public void afficherCom() {

      Task<ArrayList<Commentaire>> task = new Task() {
            @Override
            protected Object call() {
            
comm=new ServiceCommentaire().Affiche();
  
     return comm;
            }};
        task.setOnSucceeded(e -> {        
    textComm.setCellValueFactory(new PropertyValueFactory<>("text"));
     userComm.setCellValueFactory(new PropertyValueFactory<>("user_name"));
      idCom.setCellValueFactory(new PropertyValueFactory<>("id_com"));
   SujIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
   MailCol.setCellValueFactory(new PropertyValueFactory<>("mail"));
   dateCol.setCellValueFactory(new PropertyValueFactory<>("date_env"));
    tabComm.setItems(FXCollections.observableArrayList(task.getValue())); 
    
        });
        task.setOnFailed(e -> {
            afficherCom();});
           // prog.setVisible(false); 
        Thread th = new Thread(task);
        th.start();
    }
        public void RechCom() {
String ide=rechComTxt.getText();
      Task<ArrayList<Commentaire>> task = new Task() {
            @Override
            protected Object call() {
            
comm=new ServiceCommentaire().RechZ(ide);
  
     return comm;
            }};
        task.setOnSucceeded(e -> {        
 textComm.setCellValueFactory(new PropertyValueFactory<>("text"));
     userComm.setCellValueFactory(new PropertyValueFactory<>("user_name"));
      idCom.setCellValueFactory(new PropertyValueFactory<>("id_com"));
   SujIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
   MailCol.setCellValueFactory(new PropertyValueFactory<>("mail"));
    tabComm.setItems(FXCollections.observableArrayList(task.getValue())); 
    
        });
        task.setOnFailed(e -> {
            RechCom();});
           // prog.setVisible(false); 
        Thread th = new Thread(task);
        th.start();
    }
    
    
    @FXML
    private void Retirer(ActionEvent event) {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("voulez vous retirer ce Commentaire ?");
        Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK) {
        String ide=comidcache.getText();
        ServiceCommentaire s=new ServiceCommentaire();
         s.suppCom(ide);   
         afficherCom();
  
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Suppprimé!");
        alert.show();
     } 
    }

    @FXML
    private void Signaler(ActionEvent event) {
        comment2=tabComm.getItems().get(tabComm.getSelectionModel().getSelectedIndex()); 
            try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/mailAhmed.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void Desactiver(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("voulez vous Desactiver ce Sujet ?");
        Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK) {
         ServiceSujet s=new ServiceSujet();
        String ide=rechComTxt.getText();
        s.DesactiverSujet(ide);
        afficherSuj();
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Suppprimé!");
        alert.show();
         }
    }

    @FXML
    private void RechComm(MouseEvent event) {
    tabComm.getItems().clear();
    RechCom();
    }

    @FXML
    private void AfficherSignal(ActionEvent event) {
       tabComm.getItems().clear();
       afficherSi();
       
    }

    @FXML
    private void AfficherTout(ActionEvent event) {
      tabComm.getItems().clear();
   afficherCom();  
    }

    public void afficherSi() {

      Task<ArrayList<Commentaire>> task = new Task() {
            @Override
            protected Object call() {
            
comm=new ServiceCommentaire().AfficheSig();
  
     return comm;
            }};
        task.setOnSucceeded(e -> {        
    textComm.setCellValueFactory(new PropertyValueFactory<>("text"));
     userComm.setCellValueFactory(new PropertyValueFactory<>("user_name"));
      idCom.setCellValueFactory(new PropertyValueFactory<>("id_com"));
   SujIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
   MailCol.setCellValueFactory(new PropertyValueFactory<>("mail"));
    tabComm.setItems(FXCollections.observableArrayList(task.getValue())); 
    
        });
        task.setOnFailed(e -> {
            afficherCom();});
           // prog.setVisible(false); 
        Thread th = new Thread(task);
        th.start();
    }

    @FXML
    private void RetA(ActionEvent event) {
          try {
                Parent page3 = FXMLLoader.load(getClass().getResource("/view/AccueilAhmed.fxml"));
                Scene scene = new Scene(page3);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

   @FXML
    private void statistique(ActionEvent event) {
         try {
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/PieChartView.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void sujpasDispo(ActionEvent event) {
         tabSuj.getItems().clear();
    afficherSujNoDispo();
    }

    @FXML
    private void toutSuj(ActionEvent event) {
           tabSuj.getItems().clear();
    afficherSuj();
    }

 
    
}
