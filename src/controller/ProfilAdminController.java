/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static controller.inscriptionController.file;
import static controller.inscriptionController.image;
import entities.Admin;
import entities.Randonneur;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import services.ServiceAdmin;
import services.ServiceRandonneur;
import utils.MyImage;

/**
 * FXML Controller class
 *
 * @author Othman Ben Jemaa
 */
public class ProfilAdminController implements Initializable {

    @FXML
    private Label error_num_tel;
    @FXML
    private Label error_mail;
    @FXML
    private Label error_pswd;
    @FXML
    private JFXTextField txt_nom;
    @FXML
    private JFXTextField txt_prenom;
    @FXML
    private JFXTextField txt_numtel;
    @FXML
    private JFXTextField txt_mail;
    @FXML
    private JFXTextField txt_username;
    @FXML
    private JFXPasswordField txt_psw;
    @FXML
    private ImageView imgshow;
    @FXML
    private JFXTextField url_image;
    @FXML
    private JFXButton btn_retour;
    @FXML
    private JFXPasswordField txt_verifpsw;
    @FXML
    private JFXButton btn_ajouter;
    @FXML
    private JFXButton browse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       btn_retour.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       /*****************************************/
              Admin ad= LoginController.adm;

        txt_mail.setText(ad.getMail());
         txt_nom.setText(ad.getNom());
         txt_numtel.setText(ad.getNum_tel());
         txt_prenom.setText(ad.getPrenom());
         txt_psw.setText(ad.getPwd());
         txt_verifpsw.setText(ad.getPwd());         
         txt_username.setText(ad.getUser_name());
         //url_image.setText(ad.getPhoto());
                 Image a1=new Image("http://localhost/ImagePi/"+ad.getPhoto());
 imgshow.setImage(a1);
     


    }    

    @FXML
    private void modif_Action(ActionEvent event) {
         Admin ad= LoginController.adm;
/***************validation*************/
        boolean phoneValide=validation.TextFieldValidation.tex8Num(txt_numtel, error_num_tel, "Il faut remplir 8chiffres");
boolean MailValide=validation.TextFieldValidation.texMail(txt_mail, error_mail, "Mail invalide");
        boolean PasswordValide=validation.TextFieldValidation.texAlphNum(txt_psw, error_pswd, "Il faut remplir avec alphabet et ou numeros");
        if (PasswordValide)
                 {
                   error_pswd.setText("");  
                 }
        if (phoneValide)
                 {
                   error_num_tel.setText("");  
                 }
  if (MailValide)
                 {
                   error_mail.setText("");  
                 }
  if ((MailValide)&&(phoneValide) && (PasswordValide)){
        String psw=txt_psw.getText();
      String phone=txt_numtel.getText();
      String Mail=txt_mail.getText();
      String cin=ad.getCin();
      Admin a=new Admin();
      a.setPwd(psw);
      a.setMail(Mail);
      a.setNum_tel(phone);
      a.setCin(cin);
        ServiceAdmin s=new ServiceAdmin();
            s.updateMailPhone(a);
            if (psw.equals(txt_verifpsw.getText())){
                a.setUser_name(txt_username.getText());
            s.update(a);
            }
            else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur dans le mot de passe");
        alert.setHeaderText(null);
        alert.setContentText("Retapez le meme mot de passe!");
        alert.show();
            }
            /**********motifications***********************/
            Notifications notification = Notifications.create()
                .title("Modification avec  success")
                .text("Modification success")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction((ActionEvent event1) -> {
                    System.out.println("Modification succes");
         });
        notification.showConfirm();
  }
    }

    @FXML
    private void ModifierImage(ActionEvent event) {
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
            url_image.setText(file.getName());
            Admin ad= LoginController.adm;
            ad.setPhoto(url_image.getText());
            System.out.println(ad.getPhoto());
      ServiceAdmin s=new ServiceAdmin();
      s.updateImage(ad);
        } catch (IOException ex) {
            Logger.getLogger(ProfilAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
