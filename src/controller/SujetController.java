/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceCommentaire;
import services.ServiceGalerie;
import services.ServiceSujet;
import entities.Commentaire;
import entities.Galerie;
import entities.Sujet;
import utils.MyImage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SujetController implements Initializable {
    @FXML
    private ImageView imgGalerie;
    @FXML
    private JFXListView<String> listGalerie;
    @FXML
    private JFXTextField titreTxt;
       Sujet suj1=FXMLDocumentController.suj;
      

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       titreTxt.setText(suj1.getTitre());
  //imgGalerie.setText(suj1.getPic());
     Image a1=new Image("http://localhost/ImagePi/"+suj1.getPic());
 imgGalerie.setImage(a1);
    }    
  
@FXML
    private void BoutonPhoto(ActionEvent event) {
       FileChooser fileChooser = new FileChooser();
    
        //fileChooser.setInitialDirectory(new File ("C:\\Users\\user\\Documents\\NetBeansProjects\\PiJava\\src\\pics"));
       // FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.jpg", "*.JPG", "*.png");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*.jpg", "*.JPG", "*.png"));
        List<File> selectedFiles =fileChooser.showOpenMultipleDialog(null);
       // file = fileChooser.showOpenDialog(null);
     if(selectedFiles !=null){
     for(int i=0;i<selectedFiles.size();i++){
     listGalerie.getItems().add(selectedFiles.get(i).getName());
 /*String id=listGalerie.getSelectionModel().
 Galerie gal=new Galerie(); 
ServiceGalerie g=new ServiceGalerie();
 //com.setId(idTXT.getText()); 
 gal.setPhoto(id);
 g.ajoutGalerie(gal);*/
    }
     }
    }

    @FXML
    private void choixImage(MouseEvent event) {
  String id=listGalerie.getSelectionModel().getSelectedItem();

imgGalerie.setImage(MyImage.fromResources(id));

    }

    @FXML
    private void Choiximg(KeyEvent event) {
         String id=listGalerie.getSelectionModel().getSelectedItem();
        
        imgGalerie.setImage(MyImage.fromResources(id));
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

}