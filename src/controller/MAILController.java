package controller;




import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import controller.FXMLController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import entities.Mail;

/**
 * FXML Controller class
 *
 * @author balha
 */
public class MAILController implements Initializable {

    @FXML
    private JFXComboBox from_combo;
    @FXML
    private JFXTextField from_pwd;
    @FXML
    private JFXTextField to_pwd;
    @FXML
    private JFXComboBox subj_combo;
    @FXML
    private JFXButton send_btn;
    @FXML
    private JFXTextArea msg_fild;
    @FXML
    private JFXButton retour_btn;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

                from_combo.getItems().addAll("randonnitunisie@gmail.com");
                subj_combo.getItems().addAll("s'excuser","expliquer","remerciment");
                //        Materiel mat1= AjoutMaterielController.mat;
                 //Materiel mat1= AjoutMaterielController.mat;
                  //txt_nom.setText(mat1.getNom());

                  



        // TODO
    }    

    @FXML
    private void send(ActionEvent event) {
        Mail m= new Mail();
        String  pwd=from_pwd.getText();
         String  to=to_pwd.getText();
         String  msg=msg_fild.getText();
         
                 String from=(String) from_combo.getSelectionModel().getSelectedItem();
                String sujet =(String) subj_combo.getSelectionModel().getSelectedItem();
                m.send(from, pwd, to, sujet, msg);
                


        
        
    }

    
    
}
