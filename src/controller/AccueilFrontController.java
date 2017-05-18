/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import entities.Randonneur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.ServiceRandonneur;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class AccueilFrontController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private AnchorPane rootLayout;
    private Stage primaryStage;
    

    @FXML
    private JFXButton btn_Profil;
    @FXML
    private JFXButton btn_materiel;
    @FXML
    private JFXButton btn_voirFB;
    @FXML
    private JFXButton btn_deconnect;
    @FXML
    private Hyperlink contact_link;
    
 public static Randonneur rand=new Randonneur();
    @FXML
    private JFXButton btn_rec;
    @FXML
    private JFXButton Reserv;
    @FXML
    private JFXButton OrjBtn;
    @FXML
    private JFXButton SujBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
Randonneur ran= LoginController.rand;
  Randonneur ranfb=inscriptionController.rand;
                                   System.out.println("ran initialize "+ran.getConnected());
                System.out.println("ranfb initialize "+ranfb.getConnected());
                if (ran.getConnected()==1){
                    rand=ran;
                    System.out.println("AccueilFrontController.rand"+rand);
                    rand.setConnected(1);
             System.out.println("rand initialize "+rand.getConnected());

                }
                if (ranfb.getConnected()==1){
                    rand=ranfb;
                   System.out.println("AccueilFrontController.rand"+rand);
                rand.setConnected(1);
             System.out.println("rand initialize facebook"+rand.getConnected());

                }
        btn_Profil.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/ProfilRandonneur.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

               SujBtn.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AccueilAhmed.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
   OrjBtn.setOnAction((ActionEvent event) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AccueilOrganisateur.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn_materiel.setOnAction(event -> {
            try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/ListeMateriel.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListData());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        //bouton retour 
        btn_deconnect.setOnAction(event -> {
                ran.setConnected(0);
                System.out.println("ran deconnexion "+ran.getConnected());
                ranfb.setConnected(0);
                System.out.println("ranfb deconnexion "+ranfb.getConnected());

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         btn_voirFB.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/webView.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         
     contact_link.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/EnvoyerMail.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
     
              Reserv.setOnAction(event -> {

                try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/reservation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }); 
     
        btn_rec.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AjoutR.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
                 /***********************Anniversaire******************************/
                         

              ServiceRandonneur s=new ServiceRandonneur();
 if (s.anniv(rand.getUser_name()))
 {
         Mailer.send("randonnitunisie@gmail.com","randonni123",ran.getMail(),"Joyeux anniversaire !","<h1>L'equipe Randonni vous souhaite un merveilleux anniversaire </h1><br><img id=\"outside-pic\" src=\"http://www.gifsanimes.fr/glitter-gifs/j/joyeux-anniversaire/joyeux-anniversaire-gifs-animes-1262753.gif\"><br>"
                 + "<h2>Comme cadeau, vous aurez 20 points de fidelite :) Randonni bien avec nous !</h2>");  
      
         Notifications notification = Notifications.create()
                .title("Joyeux anniversaire")
                .text("L'equipe vous souhaite ses meilleurs voeux :) check your mail")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction((ActionEvent event1) -> {
                    System.out.println("Modification succes");
         });
        notification.showConfirm();
 }
    }



      


}
