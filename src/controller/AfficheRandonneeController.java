/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Randonnee;
import services.SerRandonnee;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class AfficheRandonneeController implements Initializable {
    public ArrayList<Randonnee> ran;
    private Image image;

    @FXML
    private TableColumn<Randonnee, Date> dd_col;
    @FXML
    private TableColumn<Randonnee, Date> dr_col;
    @FXML
    private TableColumn<Randonnee, String> hd_col;
    @FXML
    private TableColumn<Randonnee, String> hr_col;
    @FXML
    private TableColumn<Randonnee, String> desc_col;
    @FXML
    private TableColumn<Randonnee, String> prix_col;
    @FXML
    private TableColumn<Randonnee, String> lieu_col;
    @FXML
    private TableColumn<Randonnee, String> niveau_col;
    @FXML
    private TableColumn<Randonnee, String> cat_col;
    @FXML
    private TableView<Randonnee> randonneetable;
    public ProgressIndicator prog;
    @FXML
    private TextField id_txt;
    @FXML
    private TableColumn<Randonnee,String> id_col;
    @FXML
    private TableColumn<Randonnee,String> equip_col;
     
                    public static Randonnee rand=new Randonnee();
    @FXML
    private JFXTextField lieu_txt;
    @FXML
    private TableColumn<Randonnee,String> img_col;
    
    @FXML
    private JFXTextField image_txt;
    @FXML
    private ImageView img_view;
    @FXML
    private JFXButton modif_btn;
    @FXML
    private JFXButton supp_btn;
    @FXML
    private JFXButton maps_btn;
    @FXML
    private JFXComboBox rech_cb;
    @FXML
    private JFXButton retour_btn;
    @FXML
    private TableColumn<?, ?> cir_col;
    @FXML
    private TableColumn<?, ?> nbpl_col;
    @FXML
    private JFXButton pdf_but;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setmouseclick();
        afficherRandonnee();
        rech_cb.getItems().setAll("Lieu","Niveau","Prix","Catégorie");
        
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
    
    }
        private void setmouseclick(){
            randonneetable.setOnMouseClicked((MouseEvent event) -> {
     Randonnee ran=randonneetable.getItems().get(randonneetable.getSelectionModel().getSelectedIndex());
  
     id_txt.setText(ran.getId());
     Image a1=new Image("http://localhost/Image/"+ran.getImage());
        System.out.println("------------------------------");
        System.out.println("http://localhost/Image/"+ran.getImage());
        
        img_view.setImage(a1);    // String localPath="localhost:80/";
         
  /* 
   image_txt.setText(ran.getImage());
    
   String ide=image_txt.getText();
    img_view.setImage(MyImage.fromResources(ide));
    */
  //   image=new Image(ran.getImage());
     
    //  img_view.setImage(image);
           /* Image image = new Image(ran.getImage()) ; 
            img_view.setImage(image);
            img_view.setFitHeight(50);
            img_view.setFitWidth(50);
            img_view.setStyle("-fx-border-width: 10;-fx-border-color: red;");*/
           
            
   
});}
        
        public void afficherRandonnee() {

        Task<ArrayList<Randonnee>> task = new Task() {
            
            @Override
            protected Object call(){
              ran = new SerRandonnee().Affiches1();
     return ran;
            }};
        task.setOnSucceeded(e -> {
                     
            dd_col.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
           dr_col.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
            hd_col.setCellValueFactory(new PropertyValueFactory<>("heure_depart"));
             hr_col.setCellValueFactory(new PropertyValueFactory<>("heure_retour"));
            desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
            prix_col.setCellValueFactory(new PropertyValueFactory<>("prix"));
            lieu_col.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            niveau_col.setCellValueFactory(new PropertyValueFactory<>("niveau"));
            cat_col.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
            equip_col.setCellValueFactory(new PropertyValueFactory<>("equipement"));
            img_col.setCellValueFactory(new PropertyValueFactory<>("image"));
            cir_col.setCellValueFactory(new PropertyValueFactory<>("circuit"));
            nbpl_col.setCellValueFactory(new PropertyValueFactory<>("nbrplace"));
            
             
            randonneetable.setItems(FXCollections.observableArrayList(task.getValue()));     
         //   randonneetable.setItems(listdata.getRandonnee());
       
        
           // prog.setVisible(false);
        });
        task.setOnFailed(e -> {
            //Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
           afficherRandonnee();
          // prog.setVisible(false);
                });
        Thread th = new Thread(task);
        th.start();
    }
        // TODO

    @FXML
    private void Supprimer(ActionEvent event) {
        
                Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("voulez vous effacer cette randonnee ?");
        Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK) {
        String ide=id_txt.getText();
        
      SerRandonnee s=new SerRandonnee();
      s.delete(ide);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Delete succes!");
        alert.show();
      
      afficherRandonnee();
         }

    }

    @FXML
    private void Modifier(ActionEvent event) {
         rand=randonneetable.getItems().get(randonneetable.getSelectionModel().getSelectedIndex());
  
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/ModifRandonnee.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
               
            } catch (IOException ex) {
                Logger.getLogger(AfficheRandonneeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          
    
    }   
    public void afficherRech() {

        Task<ArrayList<Randonnee>> task = new Task() {
            @Override
            protected Object call() {
//                Platform.runLater(() -> prog.setVisible(true));
                String lieu=lieu_txt.getText();
                ArrayList<Randonnee> randonnee = new SerRandonnee().listerRech(lieu);
                return randonnee;
            }
        };
        task.setOnSucceeded(e -> {

    dd_col.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
            dr_col.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
            hd_col.setCellValueFactory(new PropertyValueFactory<>("heure_depart"));
             hr_col.setCellValueFactory(new PropertyValueFactory<>("heure_retour"));
            desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
            prix_col.setCellValueFactory(new PropertyValueFactory<>("prix"));
            lieu_col.setCellValueFactory(new PropertyValueFactory<>("lieu"));
             niveau_col.setCellValueFactory(new PropertyValueFactory<>("niveau"));
            cat_col.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
            equip_col.setCellValueFactory(new PropertyValueFactory<>("equipement"));
            img_col.setCellValueFactory(new PropertyValueFactory<>("image"));
     
            randonneetable.setItems(FXCollections.observableArrayList(task.getValue())); 
//            prog.setVisible(false);

        }
        );
        task.setOnFailed(e -> {
          //  Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
            afficherRandonnee();
            prog.setVisible(false);

        });
        Thread th = new Thread(task);
        th.start();
    }
     public void afficherRech1() {

        Task<ArrayList<Randonnee>> task = new Task() {
            @Override
            protected Object call() {
//                Platform.runLater(() -> prog.setVisible(true));
                String niveau=lieu_txt.getText();
                ArrayList<Randonnee> randonnee = new SerRandonnee().listerRech1(niveau);
                return randonnee;
            }
        };
        task.setOnSucceeded(e -> {

    dd_col.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
            dr_col.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
            hd_col.setCellValueFactory(new PropertyValueFactory<>("heure_depart"));
             hr_col.setCellValueFactory(new PropertyValueFactory<>("heure_retour"));
            desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
            prix_col.setCellValueFactory(new PropertyValueFactory<>("prix"));
            lieu_col.setCellValueFactory(new PropertyValueFactory<>("lieu"));
             niveau_col.setCellValueFactory(new PropertyValueFactory<>("niveau"));
            cat_col.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
            equip_col.setCellValueFactory(new PropertyValueFactory<>("equipement"));
            img_col.setCellValueFactory(new PropertyValueFactory<>("image"));
     
            randonneetable.setItems(FXCollections.observableArrayList(task.getValue())); 
//            prog.setVisible(false);

        }
        );
        task.setOnFailed(e -> {
          //  Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
            afficherRandonnee();
            prog.setVisible(false);

        });
        Thread th = new Thread(task);
        th.start();
    }

      public void afficherRech2() {

        Task<ArrayList<Randonnee>> task = new Task() {
            @Override
            protected Object call() {
//                Platform.runLater(() -> prog.setVisible(true));
                String prix=lieu_txt.getText();
                ArrayList<Randonnee> randonnee = new SerRandonnee().listerRech2(prix);
                return randonnee;
            }
        };
        task.setOnSucceeded(e -> {

    dd_col.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
            dr_col.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
            hd_col.setCellValueFactory(new PropertyValueFactory<>("heure_depart"));
             hr_col.setCellValueFactory(new PropertyValueFactory<>("heure_retour"));
            desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
            prix_col.setCellValueFactory(new PropertyValueFactory<>("prix"));
            lieu_col.setCellValueFactory(new PropertyValueFactory<>("lieu"));
             niveau_col.setCellValueFactory(new PropertyValueFactory<>("niveau"));
            cat_col.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
            equip_col.setCellValueFactory(new PropertyValueFactory<>("equipement"));
            img_col.setCellValueFactory(new PropertyValueFactory<>("image"));
     
            randonneetable.setItems(FXCollections.observableArrayList(task.getValue())); 
//            prog.setVisible(false);

        }
        );
        task.setOnFailed(e -> {
          //  Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
            afficherRandonnee();
            prog.setVisible(false);

        });
        Thread th = new Thread(task);
        th.start();
    }

       public void afficherRech3() {

        Task<ArrayList<Randonnee>> task = new Task() {
            @Override
            protected Object call() {
//                Platform.runLater(() -> prog.setVisible(true));
                String categorie=lieu_txt.getText();
                ArrayList<Randonnee> randonnee = new SerRandonnee().listerRech3(categorie);
                return randonnee;
            }
        };
        task.setOnSucceeded(e -> {

    dd_col.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
            dr_col.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
            hd_col.setCellValueFactory(new PropertyValueFactory<>("heure_depart"));
             hr_col.setCellValueFactory(new PropertyValueFactory<>("heure_retour"));
            desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
            prix_col.setCellValueFactory(new PropertyValueFactory<>("prix"));
            lieu_col.setCellValueFactory(new PropertyValueFactory<>("lieu"));
             niveau_col.setCellValueFactory(new PropertyValueFactory<>("niveau"));
            cat_col.setCellValueFactory(new PropertyValueFactory<>("categorie"));
            id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
            equip_col.setCellValueFactory(new PropertyValueFactory<>("equipement"));
            img_col.setCellValueFactory(new PropertyValueFactory<>("image"));
     
            randonneetable.setItems(FXCollections.observableArrayList(task.getValue())); 
//            prog.setVisible(false);

        }
        );
        task.setOnFailed(e -> {
          //  Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
            afficherRandonnee();
            prog.setVisible(false);

        });
        Thread th = new Thread(task);
        th.start();
    }

    


    @FXML
    private void recherche(KeyEvent event) {
        if(rech_cb.getSelectionModel().isSelected(0))
        {
        randonneetable.getItems().clear();
        afficherRech();
        }
         if(rech_cb.getSelectionModel().isSelected(1))
        {
        randonneetable.getItems().clear();
        afficherRech1();
        }
          if(rech_cb.getSelectionModel().isSelected(2))
        {
        randonneetable.getItems().clear();
        afficherRech2();
        }
           if(rech_cb.getSelectionModel().isSelected(3))
        {
        randonneetable.getItems().clear();
        afficherRech3();
        }
       
        
    }

    @FXML
    private void MapsG(ActionEvent event) {
        rand=randonneetable.getItems().get(randonneetable.getSelectionModel().getSelectedIndex());
  
        
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/MapApi.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    



    




}
    

