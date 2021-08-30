/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Randonnee;
import services.SerRandonnee;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controller.AccueilAdminController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import utils.Upload;
import utils.Upload1;
import validation.TextFieldValidation;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class AjoutRandonneeController implements Initializable {

    private FileInputStream f;
    private javafx.scene.image.Image image;
    
    @FXML
    private JFXDatePicker hd_txt;
    @FXML
    private JFXDatePicker hr_txt;
    @FXML
    private TextField prix_txt;
    @FXML
    private TextField lieu_txt;
    
    @FXML
    private TextField equip_txt;
    @FXML
    private TextField circuit_txt;
    @FXML
    private TextArea desc_txt;
    @FXML
    private ComboBox org_cb,guide_cb,cat_cb,niveau_cb;
    @FXML
    private Button ajout_btn;
    @FXML
    private DatePicker dated_pic;
    @FXML
    private DatePicker dater_pic;
    @FXML
    private Button browse_btn;
    public static File file;
    @FXML
    private ImageView imgshow;
    @FXML
    private TextField ss;
    @FXML
    private Button affiche_btn;
    @FXML
    private JFXTextField nbrplace_txt;
    @FXML
    private JFXButton browse1_btn;
    @FXML
    private JFXTextField ss1;
    @FXML
    private ImageView imgv;
    @FXML
    private JFXButton retour_btn;
    @FXML
    private Label prix_lbl;
    @FXML
    private Label nbpl_lbl;
    @FXML
    private Label cir_lbl;
    @FXML
    private Label lieu_lbl;
    @FXML
    private Label desc_lbl;
    @FXML
    private Label equip_lbl;
    
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SerRandonnee S = new SerRandonnee();
        ObservableList<String> lst = FXCollections.observableArrayList();
        ObservableList<String> lst1 = FXCollections.observableArrayList();
        ObservableList<String> lst2 = FXCollections.observableArrayList();
        lst=S.getNomCategorie();
        lst1=S.getNomGuide();
        lst2=S.getNomOrganisateur();
        cat_cb.getItems().setAll(lst);
        guide_cb.setItems(lst1);
        org_cb.setItems(lst2);
        niveau_cb.getItems().setAll("Facile","Moyen","Difficile");
          retour_btn.setOnAction((ActionEvent event) -> {
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
        
        
        affiche_btn.setOnAction((ActionEvent event) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AfficheRandonnee.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        ajout_btn.setOnAction((ActionEvent event) -> {
          boolean isnotNumnbrplace=TextFieldValidation.texNum(nbrplace_txt, nbpl_lbl, "Il faut remplir avec des chiffres");
       boolean isnotNumprix=TextFieldValidation.texNum(prix_txt, prix_lbl, "Il faut remplir avec des chiffres");
        boolean isnotremp=TextFieldValidation.textValidation(circuit_txt, cir_lbl, "Il faut remplir le champ");
           boolean isnotremp1=TextFieldValidation.textValidation(equip_txt, equip_lbl, "Il faut remplir le champ");
                 boolean isnotremp2=TextFieldValidation.textValidation(lieu_txt, lieu_lbl, "Il faut remplir le champ");
                 //boolean isnotremp3=utils.TextFiledvalidation.textValidation(desc_txt, desc_lbl, "Il faut remplir le champ");
              //   boolean isnotremp4=utils.TextFiledvalidation.textValidation(circuit_txt, cir_lbl, "Il faut remplir le champ");
                
       
       if (isnotNumnbrplace)
{
nbpl_lbl.setText("");
}  
                  if (isnotNumprix)
{
nbpl_lbl.setText("");
}  
                                    if (isnotremp)
{
cir_lbl.setText("");
} 
                                     if (isnotremp1)
{
equip_lbl.setText("");
}  
                                      if (isnotremp2)
{
lieu_lbl.setText("");
}  
            
                  if((isnotNumnbrplace)&&(isnotNumprix)&&(isnotremp)&&(isnotremp1)&&(isnotremp2)){
                  Randonnee r = new Randonnee();
                    Date d = java.sql.Date.valueOf(dated_pic.getValue());
                    Date d1 = java.sql.Date.valueOf(dater_pic.getValue());
                    Time t=java.sql.Time.valueOf(hd_txt.getTime());
                     Time t1=java.sql.Time.valueOf(hr_txt.getTime());
             SerRandonnee sercr = SerRandonnee.getInstance();
             System.out.print((String)cat_cb.getSelectionModel().getSelectedItem());
                     String a =sercr.getIdCategorie(cat_cb.getSelectionModel().getSelectedItem().toString());
             System.out.print("hnééééééééé");
                     System.out.print(a);
                     r.setImage(ss.getText());
                    r.setDate_debut(d);
                    r.setDate_retour(d1);
                    r.setHeure_depart(t);
                    r.setHeure_retour(t1);
                    r.setPrix(prix_txt.getText());
                    r.setNbrplace(nbrplace_txt.getText());
                    r.setLieu(lieu_txt.getText());
                    r.setCircuit(circuit_txt.getText());
                    r.setNiveau((String) niveau_cb.getSelectionModel().getSelectedItem());
                    r.setEquipement(equip_txt.getText());
                    r.setDescription(desc_txt.getText());
                    r.setCategorie(a);
                   r.setAutorisation(ss1.getText());
                    r.setId_guide((String) guide_cb.getSelectionModel().getSelectedItem());
                   // r.setId_organisateur((String) org_cb.getSelectionModel().getSelectedItem());
                    
                    
                    sercr.insert(r);
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Randonnée insérée avec succés!");
                    alert.show();
                
                  }
        });
        
        // TODO
    }  
    @FXML
    private void afficherimage(ActionEvent event) throws MalformedURLException, IOException {
  
        File selectedfile;
    String path_img;
  Upload up=new Upload();
 

 
        FileChooser fc = new FileChooser();
     
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg","*.png")
             
        );
        
         selectedfile = fc.showOpenDialog(null);
         
        if(selectedfile != null){
            
            System.out.println("aaaaaaaaaa");
           BufferedImage bufferedImage = ImageIO.read(selectedfile);
            path_img= selectedfile.getName();
            ss.setText(path_img);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
           imgshow.setImage(image);
            System.out.println("sssssssssssssssss");
            up.upload(selectedfile);
        }else{
            System.out.println("FICHIER erroné");
                    }
        
        
    }

    @FXML
    private void afficherimage1(ActionEvent event) throws IOException {
        
        File selectedfile;
        String path_img;
        Upload1 up = new Upload1();

        FileChooser fc = new FileChooser();

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png")
        );

        selectedfile = fc.showOpenDialog(null);

        if (selectedfile != null) {

            System.out.println("aaaaaaaaaa");
            BufferedImage bufferedImage = ImageIO.read(selectedfile);
            path_img = selectedfile.getName();
            ss1.setText(path_img);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgv.setImage(image);
            System.out.println("sssssssssssssssss");
            up.upload(selectedfile);
        } else {
            System.out.println("FICHIER erroné");
        }

    
    }
    
    
    
    
    
    
    }
    

