/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Guide;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.management.Notification;
import org.controlsfx.control.Notifications;
import services.ServiceGuide;
import services.ServiceOrganisateur;
import sun.security.x509.X500Name;
import utils.ConnexionSingleton;

/**
 * FXML Controller class
 *
 * @author azmi
 */
public class GuideController implements Initializable {

    @FXML
    private JFXTextField txt_nom;
    @FXML
    private JFXTextField txt_prenom;
    @FXML
    private JFXTextField txt_numtel;
    @FXML
    private JFXTextField txt_cin;
    @FXML
    private JFXTextField txt_mail;
    @FXML
    private JFXTextField txt_username;
    @FXML
    private PasswordField txt_pswd;
    @FXML
    private ComboBox txt_role;
    
    @FXML
    private ComboBox txt_competence;
    @FXML
    private ComboBox txt_dispo;
    //private ObservableList<Guide>data;
  ConnexionSingleton conn;
    private Statement ste;
    public ProgressIndicator prog;
  
   private ArrayList<Guide>materiel;
    @FXML
    private TableColumn<Guide, String> column_nom;
    @FXML
    private TableColumn<Guide, String> column_prenom;
    @FXML
    private TableColumn<Guide, String> column_numtel;
    @FXML
    private TableColumn<Guide, String> column_cin;
    @FXML
    private TableColumn<Guide, String> column_mail;
    @FXML
    private TableColumn<Guide, String> column_username;
    @FXML
    private TableColumn<Guide, String> column_pswd;
    @FXML
    private TableColumn<Guide, String> column_role;
    @FXML
    private TableColumn<Guide, String> column_dispo;
    @FXML
    private TableColumn<Guide, String> column_competence;
    
    @FXML
    private Button btn_add;
    @FXML
    private JFXTextField txt_rechercher;
    @FXML
    private TableView<Guide> table_user;
    @FXML
    private Label error_nom;
    @FXML
    private Label error_prenom;
    @FXML
    private Label error_num_tel;
    @FXML
    private Label error_cin;
    @FXML
    private Label error_mail;
    @FXML
    private Label error_username;
    @FXML
    private Label error_pswd;
    @FXML
    private JFXButton btn_back;
     static Guide bn;

    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
txt_role.getItems().setAll("admin","organisateur","guide","rondonneur");
txt_dispo.getItems().setAll("disponible","non disponible");
txt_competence.getItems().setAll("faible","moyenne","excellente");

        conn=ConnexionSingleton.getInstance();
      // data=FXCollections.observableArrayList();
    setcelltable();
    setmouseclick();
        afficherMat();        // TODO
    }    
public void afficherMat1() {

        Task<ArrayList<Guide>> task = new Task() {
            @Override
            protected Object call() {                
                String cin=txt_rechercher.getText();
                    materiel =  new ServiceGuide().Rechercher(cin);
               
                return materiel;
            }
        };
        task.setOnSucceeded(e -> {

   column_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    column_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    column_numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
     column_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
     column_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
    column_username.setCellValueFactory(new PropertyValueFactory<>("username"));
   // column_pswd.setCellValueFactory(new PropertyValueFactory<>("password"));
     column_role.setCellValueFactory(new PropertyValueFactory<>("role"));
      column_dispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
     column_competence.setCellValueFactory(new PropertyValueFactory<>("competence"));
     
table_user.setItems(FXCollections.observableArrayList(task.getValue()));
        }
        );
        task.setOnFailed(e -> {
            afficherMat();
        });
        Thread th = new Thread(task);
        th.start();
    }
    public void afficherMat() { 
        Task<ArrayList<Guide>> task = new Task() {
            @Override
            protected Object call() {
               // Platform.runLater(() -> prog.setVisible(true));
                
                String cin=txt_rechercher.getText();
                    materiel =  new ServiceGuide().afficher();
               
                return materiel;
            }
        };
        task.setOnSucceeded(e -> {

    column_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    column_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    column_numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
     column_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
     column_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
    column_username.setCellValueFactory(new PropertyValueFactory<>("username"));
   // column_pswd.setCellValueFactory(new PropertyValueFactory<>("password"));
     column_role.setCellValueFactory(new PropertyValueFactory<>("role"));
      column_dispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
     column_competence.setCellValueFactory(new PropertyValueFactory<>("competence"));
     
table_user.setItems(FXCollections.observableArrayList(task.getValue()));
            
            //prog.setVisible(false);

        }
        );
        task.setOnFailed(e -> {
            //Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
            afficherMat();
            //prog.setVisible(false);

        });
        Thread th = new Thread(task);
        th.start();
    }
    private void setcelltable(){
    column_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    column_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    column_numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
     column_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
     column_mail.setCellValueFactory(new PropertyValueFactory<>("email"));
    column_username.setCellValueFactory(new PropertyValueFactory<>("username"));
    //column_pswd.setCellValueFactory(new PropertyValueFactory<>("password"));
     column_role.setCellValueFactory(new PropertyValueFactory<>("roles"));
    // column_dispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
    // column_competence.setCellValueFactory(new PropertyValueFactory<>("competence"));
      
    }
   
    private void setmouseclick(){
table_user.setOnMouseClicked((MouseEvent event) -> {
    Guide tl=table_user.getItems().get(table_user.getSelectionModel().getSelectedIndex());
    txt_nom.setText(tl.getNom());
    txt_prenom.setText(tl.getPrenom());
    txt_numtel.setText(tl.getNum_tel());
    txt_cin.setText(tl.getCin());
    txt_mail.setText(tl.getMail());
    txt_username.setText(tl.getUser_name());
    txt_pswd.setText(tl.getPwd());
    
    
    
});


}
    @FXML
    private void handleaddbutton(ActionEvent event) {
 ServiceGuide s=new ServiceGuide();
    String  nom=txt_nom.getText();
    String prenom=txt_prenom.getText();
    String num_tel=txt_numtel.getText();
        String cin=txt_cin.getText();
        String mail=txt_mail.getText();
        String username=txt_username.getText();
        String pswd=txt_pswd.getText();
      /* String role=  (String) txt_role.getSelectionModel().getSelectedItem();
       String dispo=  (String) txt_dispo.getSelectionModel().getSelectedItem();
       String competence=  (String) txt_competence.getSelectionModel().getSelectedItem();*/
       
       
       
//String dispo=  (String) txt_dispo.getSelectionModel().getSelectedItem();

 Guide g=new Guide();
 g.setNom(txt_nom.getText());
 g.setPrenom(txt_prenom.getText());
 g.setNum_tel(txt_numtel.getText());
 g.setCin(txt_cin.getText());
 g.setMail(txt_mail.getText());
 g.setUser_name(txt_username.getText());
 g.setPwd(txt_pswd.getText());
 
 
 
 
 g.setRole((String) txt_role.getSelectionModel().getSelectedItem());
 g.setDisponibilite((String) txt_dispo.getSelectionModel().getSelectedItem());
 g.setCompetence((String) txt_competence.getSelectionModel().getSelectedItem());
 boolean isemptynom=validation.TextFieldValidation.textValidation(txt_nom, error_nom,"verifier votre nom");
        boolean isemptyprenom=validation.TextFieldValidation.textValidation(txt_prenom, error_prenom, "verifier votre prenom");
         boolean isemptynumtel=validation.TextFieldValidation.textValidation(txt_numtel, error_num_tel, "verifier votre telephone");
         boolean isemptycin=validation.TextFieldValidation.textValidation(txt_cin, error_cin, "verifier votre cin");
         boolean isemptymail=validation.TextFieldValidation.textValidation(txt_mail, error_mail, "verifier votre mail");
          boolean isemptyusername=validation.TextFieldValidation.textValidation(txt_username, error_username, "verifier votre username");
           boolean isemptypswd=validation.TextFieldValidation.textValidation(txt_pswd, error_pswd, "verifier votre pssword");
         
         
         boolean isemptynu1=validation.TextFieldValidation.texNum(txt_numtel, error_num_tel, "verifier votre telephone");
         boolean isemptycn=validation.TextFieldValidation.texNum(txt_cin, error_cin, "verifier votre cin");
        
          
           
            boolean isemnom=validation.TextFieldValidation.textalphabet(txt_nom, error_nom,"verifier votre nom");
      
            
            if(isemnom&&isemptyprenom&&isemptynumtel&&isemptycin&&isemptymail&&isemptyusername&&isemptypswd&&isemptynu1&&isemptycn){
            
            txt_nom.setText("");
            txt_prenom.setText("");
            txt_numtel.setText("");
            txt_cin.setText("");
            txt_username.setText("");
            txt_pswd.setText("");
            }
            if (isemnom)
{
error_nom.setText("");
}
if (isemptyprenom)
{
error_prenom.setText("");
}
if (isemptynumtel)
{
error_num_tel.setText("");
}
if (isemptycin)
{
error_cin.setText("");
}
if (isemptymail)
{
error_mail.setText("");
}
 if (isemptyusername)
{
error_username.setText("");
}           
if (isemptypswd)
{
error_pswd.setText("");
}           
 
 
 if(isemnom&&isemptyprenom&&isemptynumtel&&isemptycin&&isemptymail&&isemptyusername&&isemptypswd){ 
      
            s.ajouterGuide(g);
            Image img=new Image("/pics/validation.png");
            Notifications notification = Notifications.create()
                .title("Ajout avec  success")
                .text("Ajout success")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Ajout succes");
            }
        });
            notification.darkStyle();
        notification.showConfirm();
                
      
         

            
        
            afficherMat();
    

    }
    }

    @FXML
    private void handlemodifier(ActionEvent event) {
             services.ServiceGuide s=new ServiceGuide();
        String username=txt_username.getText();
        String nom=txt_nom.getText();
s.updateGuide(username,nom);
    afficherMat();
    }

    @FXML
    private void handledelete(ActionEvent event) {
        String  cin=txt_cin.getText();
       services.ServiceGuide s=new ServiceGuide();
s.deleteGuide(cin);

       Notifications notification = Notifications.create()
                .title("delete success")
                .text("delete success")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("delete succes");
            }
        });
        notification.darkStyle();
        notification.showConfirm();
                
      
         

    
    afficherMat();
    }

    @FXML
    private void rechercherRealesed(KeyEvent event) {
    table_user.getItems().clear();
    afficherMat1();
    }

    private void notify(ActionEvent event) {
        
        Image img=new Image("pics/validation.png");
        Notifications notification = Notifications.create()
                .title("notifictaion")
                .text("lllllll")
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("hello notif");
            }
        });
       
                
      
         

    }

    @FXML
    private void back(ActionEvent event) {
     try {
                System.out.println("testttttttttttttt");
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
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
 
    

