/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import static controller.FXMLDocumentController.suj;
import services.ServiceCommentaire;
import services.ServiceSujet;
import entities.Commentaire;
import entities.Sujet;
import utils.MyImage;



/**
 * FXML Controller class
 *
 * @author user
 */
public class CommentaireController implements Initializable {
      private Connection conn;
    private Statement ste;
  
   public  ArrayList<Commentaire> comm;
    public ProgressIndicator prog;
  
    @FXML
    private JFXTextField txtTitre;
    @FXML
    private JFXTextArea TextText;
    @FXML
    private JFXButton Com;
    @FXML
    private Label errTEXT;
 
    @FXML
    private JFXTextField idTXT;
    @FXML
    private ImageView imgShoww;
    @FXML
    private JFXTextField imgTESTER;
    @FXML
    private TableView<Commentaire> tab1;
    @FXML
    private TableColumn<Commentaire, String> Forumtxt;
    
  
public static Commentaire comment1=new Commentaire();

    /**
     * Initializes the controller class.
     */
    Sujet suj1=FXMLDocumentController.suj;
    
    @FXML
    private JFXButton Xefface;
    @FXML
    private JFXTextField idcomm;
    @FXML
    private TableColumn<Commentaire, String> idCol;
    @FXML
    private TableColumn<Commentaire, String> userCol;
    @FXML
    private TableColumn<Commentaire, String> MailCol;
    @FXML
    private JFXTextField userCacher2;
    @FXML
    private JFXTextField mailCacher;
    @FXML
    private Label errTitre;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
userCacher2.setText(suj1.getName_user());
 //imgTESTER.setText(suj1.getPic());
 idTXT.setText(suj1.getIdentif());
 String ide=imgTESTER.getText();
 txtTitre.setText(suj1.getTitre());
 //imgShoww.setImage(MyImage.fromResources(ide));
imgTESTER.setText(suj1.getPic());
 Image a1=new Image("http://localhost/ImagePi/"+suj1.getPic());
 imgShoww.setImage(a1);
 afficher();
 setmouseclick();
 
    }
     private void setmouseclick(){
   tab1.setOnMouseClicked((MouseEvent event) -> {
    Commentaire com=tab1.getItems().get(tab1.getSelectionModel().getSelectedIndex());   
    idcomm.setText(com.getId_com());
    mailCacher.setText(com.getMail());
   
});}
    
     public void afficher() {

      Task<ArrayList<Commentaire>> task = new Task() {
            @Override
            protected Object call() {
            
comm=new ServiceCommentaire().RechZ(idTXT.getText());
  
     return comm;
            }};
        task.setOnSucceeded(e -> {        
    Forumtxt.setCellValueFactory(new PropertyValueFactory<>("text"));
     idCol.setCellValueFactory(new PropertyValueFactory<>("id_com"));
   userCol.setCellValueFactory(new PropertyValueFactory<>("username"));
    MailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    tab1.setItems(FXCollections.observableArrayList(task.getValue()));    
        });
        task.setOnFailed(e -> {
            afficher();});
           // prog.setVisible(false); 
        Thread th = new Thread(task);
        th.start();
    }
    
    @FXML
    private void AjoutCom(ActionEvent event) {
   ServiceCommentaire s=new ServiceCommentaire();
 ServiceSujet ss=new ServiceSujet();
       Commentaire com=new Commentaire(); 
        com.setId(idTXT.getText()); 
       com.setText(TextText.getText());
       com.setUser_id(userCacher2.getText());
       
       
    boolean Textvide=validation.TextFieldValidation.AreaValidation(TextText, errTEXT,"commentaire vide");

       boolean titrevide=validation.TextFieldValidation.textValidation(txtTitre, errTitre,"Il faut choisir un Sujet pour commenter");
         if ((titrevide)&&(Textvide))
         {
              Notifications notification = Notifications.create()
                .title("Ajout avec  succes")
                .text("Ajout succes")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Ajout succes");
            }
        });
        notification.showConfirm();
         s.ajoutCom(com);
         ss.AugmenterNbParticipant(idTXT.getText());
         afficher();       
         }
    }

    @FXML
    private void RetAvant(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AccueilAhmed.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void RetirerCom(ActionEvent event) {
           String ids=idcomm.getText();
        ServiceCommentaire sc=new ServiceCommentaire();
        sc.suppCom(ids);
               afficher(); 
    }

    @FXML
    private void Signal(ActionEvent event) {
        String ids=idcomm.getText();
        ServiceCommentaire sc=new ServiceCommentaire();
        sc.SignalCom(ids);
       
    }


   
    
  
    




}