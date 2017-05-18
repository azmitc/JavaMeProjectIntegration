
package controller;

import controller.ReservationController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import controller.AccueilController;
import entities.Guide;
import entities.Randonnee;
import entities.ReservationR;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.ServiceReservation;
import services.ServiceReservationR;

/**
 * FXML Controller class
 *
 * @author azmi
 */
public class ReservationRondonnerController implements Initializable {

    @FXML
    private JFXButton AnnulerReser;
    @FXML
    private JFXButton btn_add;
    @FXML
    private JFXDatePicker txt_date;
    @FXML
    private JFXTextField txt_nombreplace;
    @FXML
    private JFXTextField txt_prix;
    @FXML
    private JFXTextField txt_remise;
    @FXML
    private JFXTextField txt_total;
    @FXML
    private JFXTextField txt_nbrejre;
    @FXML
    private JFXTextField txt_capacite;
    @FXML
    private JFXButton btn_recu;
   static ReservationR ob;
   
    @FXML
    private JFXButton btn_back;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReservationController R=new ReservationController();
        GuideController V=new GuideController();
        Randonnee p=R.ob;
        Guide gui=V.bn;
        
        
       String  prixx=p.getPrix();
        String pn=p.getNbrplace();
        String x=p.getCategorie();
       int v=p.getNote();
        String image=p.getImage();
        Date u=(Date) p.getDate_debut();
        String v1=p.getId_guide();
       txt_capacite.setText(v1);
        txt_prix.setText(prixx);
        txt_nombreplace.setText(pn);
        txt_remise.setText(x);
        txt_nbrejre.setText(String.valueOf(v));
        Integer a = Integer.valueOf(txt_prix.getText());
        Integer b = Integer.valueOf(txt_nombreplace.getText());
        
        Integer c = a * b ;
        txt_total.setText(String.valueOf(c));
       // System.out.println(a);
       // txt_total.setText(String.valueOf(txt_prix.setText(prixx)* txt_nombreplace.setText(pn)));
        
        btn_recu.setOnAction(event -> {
            
           

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Recu.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });   
        
 AnnulerReser.setOnAction(event -> {
             try {
                System.out.println("testttttttttttttt");
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/view/Annulerreservation.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(controller.AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }

    @FXML
    private void Validerreservation(ActionEvent event) {
        ServiceReservationR s=new ServiceReservationR();
    entities.ReservationR g=new ReservationR();
 
 Date d =java.sql.Date.valueOf(txt_date.getValue());
 g.setDateres(d);
    String capacite=txt_capacite.getText();
    g.setCapacite(capacite);
    String nombrejr=txt_nombreplace.getText();
    g.setNbreplace(nombrejr);
        String total=txt_total.getText();
        g.setTotal(total);
        String remise=txt_remise.getText();
        g.setRemise(remise);
        
        String prix=txt_prix.getText();
        g.setPrix(prix);
        String nbrejre=txt_nbrejre.getText();
        g.setNbrejour(nbrejre);
     
       
       
       
        try {
            //String dispo=  (String) txt_dispo.getSelectionModel().getSelectedItem();
            
            
            
            
            s.ajouterReservation(g);
               Notifications notification = Notifications.create()
                .title("Ajout avec  success")
                .text("Ajout success")
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
            
            /*
            g.setCapacite((integer) txt_capacite.getSelectionModel().getSelectedItem());
            String prenom=txt_prenom.getText();
            String num_tel=txt_numtel.getText();
            String cin=txt_cin.getText();
            String mail=txt_mail.getText();
            String username=txt_username.getText();
            String pswd=txt_pswd.getText();*/
        } catch (SQLException ex) {
            Logger.getLogger(ReservationRondonnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void recu(ActionEvent event) {
         
            
           

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Recu.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
    
    }

    @FXML
    private void annulerreservation(ActionEvent event) {
        
           
    }

    @FXML
    private void back(ActionEvent event) {
          try {
                System.out.println("testttttttttttttt");
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/view/reservation.fxml"));
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
