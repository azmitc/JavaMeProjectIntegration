/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import entities.MyImage;
import entities.Organisateur;
import java.awt.image.BufferedImage;
import java.io.File;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;
import services.ServiceOrganisateur;
import static sun.security.jgss.GSSUtil.login;
import utils.ConnexionSingleton;


/**
 * FXML Controller class
 *
 * @author azmi
 */
public class FXMLController implements Initializable {

    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_numtel;
    @FXML
    private TextField txt_cin;
    @FXML
    private TextField txt_mail;
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_pswd;
    @FXML
    private Button btn_add;
    
    @FXML
    private TableView<Organisateur> table_user;
    @FXML
    private TableColumn<Organisateur, String> column_nom;
    @FXML
    private TableColumn<Organisateur, String> column_prenom;
    @FXML
    private TableColumn<Organisateur, String> column_numtel;
    @FXML
    private TableColumn<Organisateur, String> column_cin;
  
    @FXML
    private TableColumn<Organisateur, String> column_mail;

    @FXML
    private TableColumn<Organisateur, String> column_role;
   private ObservableList<Organisateur>data;
ConnexionSingleton conn;
    private Statement ste;
    public ProgressIndicator prog;
   private ArrayList<Organisateur>organisateur;
   
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
    private Label error_username;
    private Label error_pswd;
    @FXML
    private ComboBox  txt_role;
    @FXML
    private ImageView imgshow;
    @FXML
    private Button browse;
    public static File file;
    private static Image image;
    @FXML
    private TextField ss;
    private String pic;
    @FXML
    private TableColumn<Organisateur,String> column_typeorg;
    @FXML
    private ComboBox txt_typeorg;
    @FXML
    private JFXButton btn_send;
    @FXML
    private TextField txt_rechercher;
    @FXML
    private AnchorPane xx;
    @FXML
    private JFXButton btn_back;
    
    
    @FXML
    public void AfficherImage(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
     //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.jpg", "*.JPG", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilter);
      //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);

            image = SwingFXUtils.toFXImage(bufferedImage, null);
           imgshow.setImage(image);
            ss.setText(file.getName());
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
         txt_role.getItems().setAll("admin","organisateur","Randonneur","Guide");
       txt_typeorg.getItems().setAll("organipro","organiAgence","organinormal");
        conn=ConnexionSingleton.getInstance();
       data=FXCollections.observableArrayList();
         setcelltable();
    setmouseclick();
        afficherMat();
    
       
    }    
    
    public void afficherMat1() {

        Task<ArrayList<Organisateur>> task = new Task() {
            @Override
            protected Object call() {
               // Platform.runLater(() -> prog.setVisible(true));
                
                String cin=txt_rechercher.getText();
                    organisateur =  new ServiceOrganisateur().Rechercher(cin);
               
                return organisateur;
            }
        };
        task.setOnSucceeded(e -> {
    column_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    column_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    column_numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
     column_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
     column_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
 
     column_role.setCellValueFactory(new PropertyValueFactory<>("role"));
     column_typeorg.setCellValueFactory(new PropertyValueFactory<>("typeOrganisateur"));
table_user.setItems(FXCollections.observableArrayList(task.getValue()));
            
            prog.setVisible(false);

        }
        );
        task.setOnFailed(e -> {
            //Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
            afficherMat();
            prog.setVisible(false);

        });
        Thread th = new Thread(task);
        th.start();
    }
    public void afficherMat() {

        Task<ArrayList<Organisateur>> task = new Task() {
            @Override
            protected Object call() {
               // Platform.runLater(() -> prog.setVisible(true));
                
                
                    organisateur =  new ServiceOrganisateur().afficher();
               
                return organisateur;
            }
        };
        task.setOnSucceeded(e -> {

    column_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    column_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    column_numtel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
     column_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
     column_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
 
     column_role.setCellValueFactory(new PropertyValueFactory<>("role"));
     column_typeorg.setCellValueFactory(new PropertyValueFactory<>("typeOrganisateur"));
table_user.setItems(FXCollections.observableArrayList(task.getValue()));
            
          //  prog.setVisible(false);

        }
        );
        task.setOnFailed(e -> {
            //Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
            afficherMat();
//            prog.setVisible(false);

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

     column_role.setCellValueFactory(new PropertyValueFactory<>("roles"));
      column_typeorg.setCellValueFactory(new PropertyValueFactory<>("typeOrganisateur"));
      
     
    }
   
    private void setmouseclick(){
table_user.setOnMouseClicked((MouseEvent event) -> {
    Organisateur tl=table_user.getItems().get(table_user.getSelectionModel().getSelectedIndex());
    txt_nom.setText(tl.getNom());
    txt_prenom.setText(tl.getPrenom());
    txt_numtel.setText(tl.getNum_tel());
    txt_cin.setText(tl.getCin());
    txt_mail.setText(tl.getMail());
    txt_username.setText(tl.getUser_name());
    txt_pswd.setText(tl.getPwd());
    ss.setText(tl.getImage());
    String id=ss.getText();
    
    imgshow.setImage(MyImage.fromResources(id));
    
    
});


}
   
/*private void loaddatafromdatabase(){
ServiceOrganisateur s=new ServiceOrganisateur();
        try {
            s.afficher();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }        
table_user.setItems(data);
}*/
    
    
    
    
    
    @FXML
    private void handleaddbutton(ActionEvent event) {
        
        
       
    services.ServiceOrganisateur s=new ServiceOrganisateur();
    String  nom=txt_nom.getText();
    String prenom=txt_prenom.getText();
    String num_tel=txt_numtel.getText();
        String cin=txt_cin.getText();
        String mail=txt_mail.getText();
        String username=txt_username.getText();
        String pswd=txt_pswd.getText();
                String photo=ss.getText();
                

       String role=  (String) txt_role.getSelectionModel().getSelectedItem();
       String typeorg=  (String) txt_typeorg.getSelectionModel().getSelectedItem();
Organisateur p=new Organisateur(nom,prenom,num_tel,cin,mail,username,pswd,role,typeorg,photo);
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
         
        
          
            
           
          
         
        /* boolean isemptym=validation.TextFiledvalidation.texAlphNum(txt_mail, error_mail, "verifier votre mail");
          boolean isemptyuse=validation.TextFiledvalidation.texAlphNum(txt_username, error_username, "verifier votre cin");
           boolean isemptyp=validation.TextFiledvalidation.texAlphNum(txt_pswd, error_pswd, "verifier votre pssword");
    if (isemptynu1)
{
txt_numtel.setText("");
}
if (isemptym)
{
txt_mail.setText("");
}
if (isemptyp)
{
txt_pswd.setText("");
}
if (isemnom)
{
txt_nom.setText("");
}
if (iseprenom)
{
txt_prenom.setText("");
}


             /*  if ((isemptydisc)&&(isemptyprix)&&(isemptynom)&&(isemptyref)&&(isemptyquant)&&(isnotAlphNom)&&(isnotAlphDesc)&&(isNumprix)&&(isNumquant)&&(isAlphaNumReft))
               {    
               
               
               }*/   
        if(isemnom&&isemptyprenom&&isemptynumtel&&isemptycin&&isemptymail&&isemptyusername&&isemptypswd){
s.ajouterOrganisateur(p);
Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("insertion avec  succes!");
        alert.show();
afficherMat();
        }
    }

    @FXML
    private void handlemodifier(ActionEvent event) {
            services.ServiceOrganisateur s=new ServiceOrganisateur();
        String username=txt_username.getText();
        String pswd=txt_pswd.getText();
s.updateOrganisateur(username,pswd);
Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Delete succes!");
        alert.show();
    afficherMat();
    
    }

    @FXML
    private void handledelete(ActionEvent event) {
   String  cin=txt_cin.getText();
       services.ServiceOrganisateur s=new ServiceOrganisateur();
s.deleteOrganisateur(cin);
Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Delete succes!");
        alert.show();
    afficherMat();
    
    }

    
     
    
    
   

    @FXML
    private void mail(ActionEvent event) {
  
             try {
                System.out.println("testttttttttttttt");
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/view/MAIL.fxml"));
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

    @FXML
    private void onreleased(KeyEvent event) {
    table_user.getItems().clear();
    afficherMat1();
    
    
    
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
