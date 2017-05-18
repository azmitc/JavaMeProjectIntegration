/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Materiel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceMateriel;
import utils.MyImage;


/**
 * FXML Controller class
 *
 * @author balha
 */
public class ListeMaterielController implements Initializable {

    @FXML
    private TextField txt_type;
    @FXML
    private TextField txt_etat;
    @FXML
    private TableView<Materiel> table_mat;
    @FXML
    private TableColumn<Materiel,String> col_nom;
    @FXML
    private TableColumn<Materiel,String> col_ref;
    @FXML
    private TableColumn<Materiel,String> col_prix;
    @FXML
    private TableColumn<Materiel,String> col_desc;
    @FXML
    private TableColumn<Materiel,String> col_type;
    @FXML
    private TableColumn<Materiel,String> col_quant;
    @FXML
    private JFXTextField rech_txt;
    @FXML
    private ImageView imgToShow;
    private ArrayList<Materiel> materiel;
    @FXML
    private JFXButton resv_id;
        public static Materiel mat=new Materiel();
    @FXML
    private JFXButton btn_retour;
    @FXML
    private TableColumn<Materiel,String> col_pic;
    @FXML
    private JFXTextField ss;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setmouseclick();
        afficherMat();
        // TODO
         btn_retour.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AccueilFront.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
    
    
        public void afficherMat() {

        Task<ArrayList<Materiel>> task = new Task() {
            @Override
            protected Object call() {
//                Platform.runLater(() -> prog.setVisible(true));
                
                materiel =  new ServiceMateriel().SelectMaterielPourReserver();
                return materiel;
            }
        };
        task.setOnSucceeded(e -> {
     col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    
    col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
     col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
     col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
    col_ref.setCellValueFactory(new PropertyValueFactory<>("reference"));
         col_quant.setCellValueFactory(new PropertyValueFactory<>("quantite"));
         col_pic.setCellValueFactory(new PropertyValueFactory<>("pic"));
         
     table_mat.setItems(FXCollections.observableArrayList(task.getValue()));
        }
        );
        task.setOnFailed(e -> {
            afficherMat();
           // prog.setVisible(false);

        });
        Thread th = new Thread(task);
        th.start();
    }

   
        
        
        
        public void afficherMat1() {

        Task<ArrayList<Materiel>> task = new Task() {
            @Override
            protected Object call() {
//                Platform.runLater(() -> prog.setVisible(true));
                String ref=rech_txt.getText();
                materiel =  new ServiceMateriel().SelectMaterielByRefClient(ref);
                return materiel;
            }
        };
        task.setOnSucceeded(e -> {
     col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    col_ref.setCellValueFactory(new PropertyValueFactory<>("reference"));
    col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
     col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
     col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
    
                  col_quant.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                           col_pic.setCellValueFactory(new PropertyValueFactory<>("pic"));

                      


     table_mat.setItems(FXCollections.observableArrayList(task.getValue()));

            
//            prog.setVisible(false);

        }
        );
        task.setOnFailed(e -> {
          //  Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
            afficherMat();
           // prog.setVisible(false);

        });
        Thread th = new Thread(task);
        th.start();
    }
        
         private void setmouseclick(){
    table_mat.setOnMouseClicked((MouseEvent event) -> {
    Materiel tl=table_mat.getItems().get(table_mat.getSelectionModel().getSelectedIndex());
    
     
     ss.setText(tl.getPic());
     String ide=ss.getText();
    // imgToShow.setImage(MyImage.fromResources(ide));
     //combo_etat.getValue()
     
     Image img=new Image(tl.getPic());
        System.out.println("------------------------------");
        System.out.println(tl.getPic());
        
        imgToShow.setImage(img);
    
});


}
        
        
        
        
        
        
        
        
        
        
        
        
        

    @FXML
    private void rech(KeyEvent event) {
        table_mat.getItems().clear();
        afficherMat1();
    }

    @FXML
    private void res_handl(ActionEvent event) {
            mat=table_mat.getItems().get(table_mat.getSelectionModel().getSelectedIndex());

          try {
              
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/Reserver.fxml"));
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void retour(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    
    
}
