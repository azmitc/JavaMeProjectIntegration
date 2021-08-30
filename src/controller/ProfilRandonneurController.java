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

import services.ServiceRandonneur;
import utils.MyImage;

/**
 * FXML Controller class
 *
 * @author Othman Ben Jemaa
 */
public class ProfilRandonneurController implements Initializable {

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
    private JFXButton btn_ajouter;
    @FXML
    private ImageView imgshow;
    @FXML
    private JFXButton browse;
    @FXML
    private JFXTextField url_image;
    @FXML
    private JFXButton btn_retour;
    @FXML
    private JFXPasswordField txt_verifpsw;
    @FXML
    private JFXTextField txt_pt_fidel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        btn_retour.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AccueilFront.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Randonneur ran= AccueilFrontController.rand;
    System.out.println("ran AccueilFrontController initialize "+ran.getConnected());


        if (ran.getConnected() != 0){

            txt_mail.setText(ran.getMail());
         txt_nom.setText(ran.getNom());
         txt_numtel.setText(ran.getNum_tel());
         txt_prenom.setText(ran.getPrenom());
         txt_psw.setText(ran.getPwd());
         txt_verifpsw.setText(ran.getPwd());         
         txt_username.setText(ran.getUser_name());
         txt_pt_fidel.setText("" + ran.getPt_fidel());
         url_image.setText(ran.getPhoto());
          Image a1=new Image("http://localhost/ImagePi/"+ran.getPhoto());
 imgshow.setImage(a1);
        }
        

        
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDate d;
//        d = LocalDate.parse((CharSequence) ran.getBirthday(), formatter);
//         birthday.setValue(d);
    //Image img=new Image(ran.getPhoto());
        System.out.println("------------------------------");
        //System.out.println(ran.getPhoto()); 
            // imgshow.setImage(img);
         //imgshow.setImage(MyImage.fromResources(url_image.getText()));
  
    }    


    @FXML
    private void modif_Action(ActionEvent event) {
        Randonneur ran= AccueilFrontController.rand;
                /************validation***************************/

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
      String cin=ran.getCin();
      //Randonneur a=new Randonneur();
      ran.setPwd(psw);
      ran.setMail(Mail);
      ran.setNum_tel(phone);
      ran.setCin(cin);
      ServiceRandonneur s=new ServiceRandonneur();
            s.updateMailPhone(ran);
            if (psw.equals(txt_verifpsw.getText())){
                ran.setUser_name(txt_username.getText());
            s.update(ran);
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
/*   BufferedImage bufferedImage = ImageIO.read(selectedfile);
            path_img = selectedfile.getName();
            ss.setText(path_img);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgshow.setImage(image);*/
            image = SwingFXUtils.toFXImage(bufferedImage, null);
           imgshow.setImage(image);
            url_image.setText(file.getName());
        Randonneur ran= AccueilFrontController.rand;
            ran.setPhoto(url_image.getText());
            System.out.println(ran.getPhoto());
      ServiceRandonneur s=new ServiceRandonneur();
      s.updateImage(ran);
        } catch (IOException ex) {
            Logger.getLogger(ProfilRandonneurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    }
    

