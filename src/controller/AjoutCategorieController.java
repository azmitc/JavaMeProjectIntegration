/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.CategorieRandonnee;
import services.SerCategorieRandonnee;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import utils.ConnexionSingleton;
import utils.Upload;
import utils.Upload2;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class AjoutCategorieController implements Initializable {
    

    @FXML
    private Label nom_lbl;
    @FXML
    private Label desc_lbl;
    @FXML
    private TextField nom_text;
    @FXML
    private TextArea desc_text;
    @FXML
    private Button ajout_btn;
    private TextField image_text;
    @FXML
    private Label image_lbl;
    @FXML
    private Button affiche_btn;
    @FXML
    private JFXTextField ss;
    @FXML
    private ImageView imgshow;
    @FXML
    private JFXButton retour_btn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         retour_btn.setOnAction((ActionEvent event) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AccueilAdmin.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
        ajout_btn.setOnAction(event -> {
            boolean isnotAlphNom=validation.TextFieldValidation.textalphabet(nom_text, nom_lbl, "Il faut remplir avec des alphabets");
    //     boolean isnotAlphDesc=pi.validation.TextFiledvalidation.textalphabet(txt_desc, txt_errD, "Il faut remplir avec des alphabets");
      //   boolean isNumprix=pi.validation.TextFiledvalidation.texNum (txt_prix, txt_errP, "Il faut remplir avec des numero");
        //boolean isNumquant=pi.validation.TextFiledvalidation.texNum (txt_quant, txt_errT, "Il faut remplir avec des numero");
       // boolean isAlphaNumReft=pi.validation.TextFiledvalidation.texNum  (txt_ref, txt_errLab, "Il faut remplir avec des nums");

//texAlphNum
if (isnotAlphNom)
{
nom_lbl.setText("");
}/*
if (isnotAlphDesc)
{
txt_errD.setText("");
}
if (isNumprix)
{
txt_errP.setText("");
}
if (isNumquant)
{
txt_errT.setText("");
}
if (isAlphaNumReft)
{
txt_errLab.setText("");
}*/



               if ((isnotAlphNom))//&&(isnotAlphDesc)&&(isNumprix)&&(isNumquant)&&(isAlphaNumReft))
               {
            
            CategorieRandonnee r = new CategorieRandonnee();
            r.setNom(nom_text.getText());
            r.setImage(ss.getText());
            r.setDescription(desc_text.getText());
            
           SerCategorieRandonnee sercr = SerCategorieRandonnee.getInstance();
            sercr.insert(r);
               
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Catégorie insérée avec succés!");
        alert.show();
               }  });
        
                
                
        affiche_btn.setOnAction((ActionEvent event) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AfficheCategorie.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
           
    }    

    @FXML
    private void afficherimage(ActionEvent event) throws IOException {
           File selectedfile;
        String path_img;
        Upload2 up = new Upload2();

        FileChooser fc = new FileChooser();

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png")
        );

        selectedfile = fc.showOpenDialog(null);

        if (selectedfile != null) {

            System.out.println("aaaaaaaaaa");
            BufferedImage bufferedImage = ImageIO.read(selectedfile);
            path_img = selectedfile.getName();
            ss.setText(path_img);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgshow.setImage(image);
            System.out.println("sssssssssssssssss");
            up.upload(selectedfile);
        } else {
            System.out.println("FICHIER erroné");
        }
    }
    
}
