/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Materiel;
import javafx.scene.image.Image ;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.ServiceMateriel;
import utils.MyImage;



/**
 * FXML Controller class
 *
 * @author balha
 */
public class AjoutMaterielController implements Initializable {

    @FXML
    private TextField txt_nom;
    @FXML
    private Button btn_add;
    @FXML
    private TextField txt_ref;
    @FXML
    private TextField txt_prix;
    @FXML
    private TextField txt_desc;
    @FXML
    private TextField txt_type;
    @FXML
    private TextField txt_etat;
    @FXML
    private TableColumn<Materiel,String> col_nom;
    @FXML
    private TableColumn<Materiel,String> col_ref;
    @FXML
    private TableColumn<Materiel,Float> col_prix;
    @FXML
    private TableColumn<Materiel,String> col_desc;
    @FXML
    private TableColumn<Materiel,String> col_type;
    
  private Connection conn;
    private Statement ste;
       private ObservableList<Materiel> data;
    @FXML
    private TableColumn<Materiel,String> col_etat;
    @FXML
    private TableView<Materiel> table_mat;
    public ProgressIndicator prog;
    private ArrayList<Materiel> materiel;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_modif;
    @FXML
    private TableColumn<Materiel,Integer> col_id;
    @FXML
    private TextField txt_id;
    @FXML
    private TextField rech_txt;
    @FXML
    private Label txt_errNom;
    @FXML
    private Label txt_errLab;
    @FXML
    private Label txt_errP;
    @FXML
    private Label txt_errD;
    @FXML
    private Label txt_errT;
    @FXML
    private Label txt_errE;
    @FXML
    private ComboBox combo_id;
    @FXML
    private Button btn_stat;
    @FXML
    private ComboBox combo_type;
    @FXML
    private ComboBox combo_etat;
//private ImageView imageView;
    public static Image image;
    @FXML
    private TextField txt_quant;
    @FXML
    private TableColumn<?, ?> col_quant;
    @FXML
    private JFXButton afficherImageButton;
    @FXML
    private ImageView imgToShow;
    public static File file;
    @FXML
    private TableColumn<Materiel, String> ColPic;
    public static Materiel mat=new Materiel();
    @FXML
    private JFXButton rt_button;
    @FXML
    private JFXTextField ss;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        btn_stat.setOnAction(event->{
            try {
                System.out.println("testttttttttttttt");
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/pi/view/PieChartView.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjoutMaterielController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        combo_etat.getItems().addAll("dispo","non dispo");
        combo_type.getItems().addAll("couchage","sac a dos","tentes","habillements");
        
               // conn=Myconnexion.getInstance();
       
        data=FXCollections.observableArrayList();
        
      
               // setcelltable();
               // setmouseclick();
                afficherMat();

    }  
     
    public void afficherMat() {

        Task<ArrayList<Materiel>> task = new Task() {
            @Override
            protected Object call() {
//                Platform.runLater(() -> prog.setVisible(true));
                
                materiel =  new ServiceMateriel().SelectMateriel();
                return materiel;
            }
        };
        task.setOnSucceeded(e -> {
     col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    
    col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
     col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
     col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
    col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    col_ref.setCellValueFactory(new PropertyValueFactory<>("reference"));
         col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
         col_quant.setCellValueFactory(new PropertyValueFactory<>("quantite"));
          ColPic.setCellValueFactory(new PropertyValueFactory<>("pic"));

     table_mat.setItems(FXCollections.observableArrayList(task.getValue()));
        }
        );
        task.setOnFailed(e -> {
            afficherMat();
            prog.setVisible(false);

        });
        Thread th = new Thread(task);
        th.start();
    }
   /*private void setcelltable(){
    col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    col_ref.setCellValueFactory(new PropertyValueFactory<>("reference"));
    col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
     col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
     col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
    col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    col_quant.setCellValueFactory(new PropertyValueFactory<>("quantite"));

     
    }*/
    private void setmouseclick(){
    table_mat.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Materiel tl=table_mat.getItems().get(table_mat.getSelectionModel().getSelectedIndex());
            txt_nom.setText(tl.getNom());
            txt_ref.setText(tl.getReference());
            txt_prix.setText(tl.getPrix());
            txt_desc.setText(tl.getDescription());
            txt_type.setText(tl.getType());
            txt_etat.setText(tl.getEtat());
            txt_id.setText(tl.getId());
            txt_quant.setText(tl.getQuantite());
            
            ss.setText(tl.getPic());
            String ide=ss.getText();
            imgToShow.setImage(MyImage.fromResources(ide));
            //combo_etat.getValue()
        }
    });


}
    
     
   

    @FXML
    private void handleadd(ActionEvent event) {
           // services.ServiceOrganisateur s=new ServiceOrganisateur();
            ServiceMateriel ser=new ServiceMateriel();
    String  nom=txt_nom.getText();
    String reference=txt_ref.getText();
    String  prix=txt_prix.getText();
        String description=txt_desc.getText();
        String quantite=txt_quant.getText();
        String type=(String) combo_type.getSelectionModel().getSelectedItem();
        //String etat=txt_etat.getText();
      String etat=  (String) combo_etat.getSelectionModel().getSelectedItem();
       String image = ss.getText();
               
        
//Materiel m=new Materiel(nom,reference,prix,description,type,etat);
Materiel m=new Materiel(nom, reference, description,prix,type, etat,quantite,image);



ser.ajouterMateriel(m);
Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Ajout succes!");
        alert.show();
        afficherMat();
imgToShow.setImage(null);
txt_ref.clear();
txt_nom.clear();
txt_prix.clear();
txt_desc.clear();
txt_quant.clear();




    
   
    
    
    }
    

    @FXML
    private void DeleteMat(ActionEvent event) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("voulez vous effacer ce Materiel ?");
        Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK) {
        String ide=txt_id.getText();
        
      ServiceMateriel s=new ServiceMateriel();
      s.deleteMateriel(ide);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Delete succes!");
        alert.show();
      
      afficherMat();
          txt_ref.clear();
txt_nom.clear();
txt_prix.clear();
txt_desc.clear();
txt_quant.clear();
         }
    }

    @FXML
    private void ModifMat(ActionEvent event) {
          String  nom=txt_nom.getText();
          String  prix=txt_prix.getText();
          String description=txt_desc.getText();
         // String type=txt_type.getText();
            String type=  (String) combo_type.getSelectionModel().getSelectedItem();
         
           String reference=txt_ref.getText();
           String quantite=txt_quant.getText();
          String id=txt_id.getText();
            String etat=  (String) combo_etat.getSelectionModel().getSelectedItem();


      ServiceMateriel s=new ServiceMateriel();
      s.updateMateriel(nom,reference,prix,description,type, etat,quantite,id);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Modif succes!");
        alert.show();
      
      afficherMat();
      txt_ref.clear();
txt_nom.clear();
txt_prix.clear();
txt_desc.clear();
txt_quant.clear();
//combo_type.getItems().clear();
//combo_etat.getItems().clear();
      
           
    }
       public void afficherMat1() {

        Task<ArrayList<Materiel>> task = new Task() {
            @Override
            protected Object call() {
//                Platform.runLater(() -> prog.setVisible(true));
                String ref=rech_txt.getText();
                materiel =  new ServiceMateriel().SelectMaterielByRef(ref);
                return materiel;
            }
        };
        task.setOnSucceeded(e -> {
     col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    col_ref.setCellValueFactory(new PropertyValueFactory<>("reference"));
    col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
     col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
     col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
    col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                  col_quant.setCellValueFactory(new PropertyValueFactory<>("quantite"));
                      


     table_mat.setItems(FXCollections.observableArrayList(task.getValue()));

            
//            prog.setVisible(false);

        }
        );
        task.setOnFailed(e -> {
          //  Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
            afficherMat();
            prog.setVisible(false);

        });
        Thread th = new Thread(task);
        th.start();
    }

    @FXML
    private void rech(KeyEvent event) {
        table_mat.getItems().clear();
        afficherMat1();
    }

    @FXML
    private void AfficherImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
     //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.jpg", "*.JPG", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilter);
      //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);

            image = SwingFXUtils.toFXImage(bufferedImage, null);
           imgToShow.setImage(image);
            ss.setText(file.toURL().toString());
        } catch (IOException ex) {
            Logger.getLogger(AjoutMaterielController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
            mat =table_mat.getItems().get(table_mat.getSelectionModel().getSelectedIndex());

        try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/ModifMat.fxml"));
               
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
