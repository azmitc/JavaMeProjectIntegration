/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import services.ServiceNote;
import entities.Note;


/**
 * FXML Controller class
 *
 * @author user
 */
public class NoteController implements Initializable {
 Image etoileremp = new Image("/pi/view/e2.png");
 Image etoilevide = new Image("/pi/view/e1.png");
  @FXML
    private ImageView Evide1;
    @FXML
    private ImageView Evide2;
    @FXML
    private ImageView Evide3;
private static String note;
   // private JFXButton Noterbtn;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
  Evide1.setOnMouseClicked((MouseEvent E1)->{
             Evide1.setImage(etoileremp);
              Evide2.setImage(etoilevide);
               Evide3.setImage(etoilevide);
               note="1";
                 System.out.println(note);
             Evide1.setAccessibleText("touche pas");
             });
         
          Evide1.setOnMouseExited((MouseEvent E2)->{
             if(Evide1.getAccessibleText() != "touche pas")
             {
               Evide1.setImage(etoilevide); 
             }
             });
             
             Evide1.setOnMouseEntered((MouseEvent )->{
             
                  Evide1.setImage(etoileremp); 
            });
             
             Evide2.setOnMouseClicked((MouseEvent E1)->{
             Evide1.setImage(etoileremp);
             Evide2.setImage(etoileremp);
             Evide3.setImage(etoilevide);
             Evide1.setAccessibleText("touche pas");
             Evide2.setAccessibleText("touche pas");
             note="2";
                 System.out.println(note);
             });
             
             Evide2.setOnMouseExited((MouseEvent E2)->{
             if(Evide2.getAccessibleText() != "touche pas")
             {
               Evide1.setImage(etoilevide);
               Evide2.setImage(etoilevide);
               Evide3.setImage(etoilevide);
             }
             });
             
             Evide2.setOnMouseEntered((MouseEvent )->{
             
                  Evide1.setImage(etoileremp); 
                  Evide2.setImage(etoileremp);
            });
             
            Evide3.setOnMouseClicked((MouseEvent E1)->{
             Evide1.setImage(etoileremp);
             Evide2.setImage(etoileremp);
             Evide3.setImage(etoileremp);
             Evide2.setAccessibleText("touche pas");
             Evide2.setAccessibleText("touche pas");
                          Evide3.setAccessibleText("touche pas");
                          note="3";
                          System.out.println(note);

             });
             
             Evide3.setOnMouseExited((MouseEvent E2)->{
             if(Evide3.getAccessibleText() != "touche pas")
             {
               Evide1.setImage(etoilevide);
               Evide2.setImage(etoilevide);
               Evide3.setImage(etoilevide);
               
             }
             });
             
             Evide3.setOnMouseEntered((MouseEvent )->{
             
                  Evide1.setImage(etoileremp); 
                  Evide2.setImage(etoileremp);
                  Evide3.setImage(etoileremp);
            });
       
        
      
        // TODO
    }   


    @FXML
    private void envoi(ActionEvent event) {
         ServiceNote s= new ServiceNote();
          Note a=new Note();
       
         a.setNote(note);
         s.ajoutNote(a);
    }
    
}
