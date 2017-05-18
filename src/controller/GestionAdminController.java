/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextField;
import entities.Admin;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.ServiceAdmin;
import utils.MyImage;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class GestionAdminController implements Initializable {
private ArrayList<Admin> admins;
//public ProgressIndicator prog;
    private ListData listdata = new ListData();

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Admin> personsTable;

    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_pie;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modif;
    @FXML
    private TextField txt_cin;
    @FXML
    private TextField txt_login;
    @FXML
    private PasswordField txt_psw;
    @FXML
    private TableColumn<Admin, String> LoginColonne;
    @FXML
    private TableColumn<Admin, String> MailColonne;
    @FXML
    private JFXTextField txt_rechercher;
    @FXML
    private TableColumn<Admin, String> Colimg;
    @FXML
    private ImageView photo;
    @FXML
    private TextField url_img;
    @FXML
    private TableColumn<Admin, String> PhoneColonne;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //bouton retour 
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
        // afficher dans le tableau
                personsTable.setItems(listdata.getPersons());
    /***********recuper du tableview vers les inputs*********************************************************/

        LoginColonne.setCellValueFactory(cell -> cell.
                getValue().getUser_nameProperty());
        MailColonne.setCellValueFactory(cell -> cell.
                getValue().getMailProperty());
        PhoneColonne.setCellValueFactory(cell -> cell.
                getValue().getPhoneProperty());
        personsTable.setOnMouseClicked(event->{
      
        txt_cin.setText(listdata.getPersons().get(personsTable.getSelectionModel().getSelectedIndex()).getCin());
        txt_login.setText(listdata.getPersons().get(personsTable.getSelectionModel().getSelectedIndex()).getUser_name());
        txt_psw.setText(listdata.getPersons().get(personsTable.getSelectionModel().getSelectedIndex()).getPwd());
        url_img.setText(listdata.getPersons().get(personsTable.getSelectionModel().getSelectedIndex()).getPhoto());
      Image a1=new Image("http://localhost/Image/"+listdata.getPersons().get(personsTable.getSelectionModel().getSelectedIndex()).getPhoto());
        
        
        photo.setImage(a1);
        });
        /********************************************************************/

        //Redirection vers l'interface PieChart
        btn_pie.setOnAction(event->{
            try {
                System.out.println("testttttttttttttt");
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/view/PieChartView.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(GestionAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
//afficher();
    }
public void afficher() {

        Task<ArrayList<Admin>> task = new Task() {
            protected Object call() throws SQLException {
               // Platform.runLater(() -> prog.setVisible(true));
                
                admins =  new ServiceAdmin().DisplayAllList();
                return admins;
            }
        };
        task.setOnSucceeded(e -> {

     LoginColonne.setCellValueFactory(new PropertyValueFactory<>("username"));
    MailColonne.setCellValueFactory(new PropertyValueFactory<>("email"));
    
     personsTable.setItems(FXCollections.observableArrayList(task.getValue()));

            
          //  prog.setVisible(false);

        }
        );
        task.setOnFailed(e -> {
          //  Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
            afficher();
         //   prog.setVisible(false);

        });
        Thread th = new Thread(task);
        th.start();
        
                
    }

    @FXML
    private void Supprimer_Action(ActionEvent event) {
         Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("voulez vous effacer cet admin ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        String cin=txt_cin.getText();
      ServiceAdmin s=new ServiceAdmin();
      Admin a=new Admin();
      a.setCin(cin);
            System.out.println(a.getCin());
            
            s.delete(cin);
            alert.setTitle("Suppression");
        alert.setHeaderText(null);
        alert.setContentText("Suppression avec succes!");
        alert.show();
//        Notifications notif=Notifications.create()
//                .title("Suppression avec succes")
//                .text("Suppression succes")
//                .graphic(null)
//                .hideAfter(Duration.seconds(5))
//                .position(Pos.BOTTOM_RIGHT)
                
        
      //s.delete(cin);
                  afficher();
                  txt_cin.clear();
                 txt_login.clear();
                 txt_psw.clear();
    }
    }
   

    @FXML
    private void modif_Action(ActionEvent event) {
         String psw=txt_psw.getText();
                  String login=txt_login.getText();
String cin=txt_cin.getText();
      ServiceAdmin s=new ServiceAdmin();
      Admin a=new Admin();
      a.setPwd(psw);
      a.setUser_name(login);
      a.setCin(cin);
        System.out.println(a.getUser_name());
                System.out.println(a.getPwd());

            s.update(a);
            afficher();
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
public void rechercher() {

        Task<ArrayList<Admin>> task = new Task() {
            @Override
            protected Object call() throws SQLException {
                //Platform.runLater(() -> prog.setVisible(true));
                String login=txt_rechercher.getText();
                admins =  new ServiceAdmin().RechercherList(login);
                return admins;
            }
        };
        task.setOnSucceeded(e -> {

     MailColonne.setCellValueFactory(new PropertyValueFactory<>("email"));
    LoginColonne.setCellValueFactory(new PropertyValueFactory<>("username"));
    
     personsTable.setItems(FXCollections.observableArrayList(task.getValue()));

            
           // prog.setVisible(false);

        }
        );
        task.setOnFailed(e -> {
          //  Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
            afficher();
            //prog.setVisible(false);

        });
        Thread th = new Thread(task);
        th.start();
        
                //Redirection vers l'interface PieChart
    
    }

    @FXML
    private void RechercherAction(KeyEvent event) {
       personsTable.getItems().clear();
        rechercher();
    }
}
