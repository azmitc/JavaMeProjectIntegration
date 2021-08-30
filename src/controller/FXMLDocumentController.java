/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import entities.Sujet;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import services.ServiceCommentaire;
import services.ServiceSujet;
import entities.Commentaire;
import utils.ConnexionSingleton;
import utils.MyImage;
import utils.Upload;


/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private Button btnAjout;
    @FXML
    private TextField titretxt;

    private TextField themetxt;
    @FXML
    private TextArea texttxt;
   //private ObservableList<Sujet>data;
    //private Sujet listdata = new Sujet();
   
      private Connection conn;
    private Statement ste;
    @FXML
    private TableView<Sujet> tb_sujet;
    @FXML
    private Button SuppButton;
    @FXML
    private Button ModButton;
    @FXML
    private TextField idtxt;
      @FXML
    public TextField ss;
    
public ProgressIndicator prog;
  
    @FXML
    private TableColumn<Sujet, String> titry;
    @FXML
    private TableColumn<Sujet, String> texty;
     @FXML
    private TableColumn<Sujet, String> idcol;
    public  ArrayList<Sujet> sujet;

    @FXML
    private TextField txtrech;
    @FXML
    private Label errtitre;
    @FXML
    private Label errtheme;
    @FXML
    private Label errtext;
    
    public static File file; //eff
    @FXML
    private ImageView imgshow;
    //public static Image image;
    @FXML
    private Button browse;
   @FXML
    private TableColumn<Sujet,File> ColImg;
      public List<Sujet>listee ; 
    @FXML
    private Button stat;
  
    @FXML
    private ComboBox combotheme;
    @FXML
    private TableColumn<Sujet,String> themecol;
    @FXML
    private JFXButton parti;
  public static Sujet suj =new Sujet();
    public static Commentaire com =new Commentaire();
    @FXML
    private TableColumn<Sujet, String> user;
    @FXML
    private JFXComboBox<String> comboTxt;
    @FXML
    private JFXTextField userCacher;
    @FXML
    private TableColumn<Sujet,String> roleCol;
    @FXML
    private JFXTextField roleCacher;
    @FXML
    private Label errTitre;

    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        //conn=MyConnexion.getInstance();
       ServiceSujet s=new ServiceSujet();
ObservableList<String> lst = FXCollections.observableArrayList();
lst=s.getIdUser();
comboTxt.getItems().setAll(lst);

combotheme.getItems().setAll("Partage Souvenir","Discussion");
        setmouseclick();
        afficherSuj();
         /*Commentaire com=
        userTXT.setText(suj.getId_user());*/

    }
    
    public void afficherSuj() {

        Task<ArrayList<Sujet>> task = new Task() {
            @Override
            protected Object call() {
   sujet =  new ServiceSujet().Affiches();
     return sujet;
            }};
        task.setOnSucceeded(e -> {

    titry.setCellValueFactory(new PropertyValueFactory<>("titre"));
    texty.setCellValueFactory(new PropertyValueFactory<>("text"));
   idcol.setCellValueFactory(new PropertyValueFactory<>("identif"));
   themecol.setCellValueFactory(new PropertyValueFactory<>("theme"));
   user.setCellValueFactory(new PropertyValueFactory<>("name_user"));
     roleCol.setCellValueFactory(new PropertyValueFactory<>("roles"));
    //ColImg.setCellValueFactory(new PropertyValueFactory<>("pic"));
  
       tb_sujet.setItems(FXCollections.observableArrayList(task.getValue()));            
        });
        task.setOnFailed(e -> {
            afficherSuj();
            prog.setVisible(false); });
        Thread th = new Thread(task);
        th.start();
    }
    
     public void afficherRech() {
 String ide=txtrech.getText();
        Task<ArrayList<Sujet>> task = new Task() {
            @Override
            protected Object call() {
                Sujet ss=new Sujet();
   sujet =  new ServiceSujet().RechZ(ide);
     return sujet;
            }};
        task.setOnSucceeded(e -> {
     
            
    titry.setCellValueFactory(new PropertyValueFactory<>("titre"));
    texty.setCellValueFactory(new PropertyValueFactory<>("text"));
    idcol.setCellValueFactory(new PropertyValueFactory<>("identif"));
    themecol.setCellValueFactory(new PropertyValueFactory<>("theme"));
    tb_sujet.setItems(FXCollections.observableArrayList(task.getValue()));   
    
        });
        task.setOnFailed(e -> {
        afficherSuj();
        prog.setVisible(false); });
        Thread th = new Thread(task);
        th.start();
    }
 
    private void setmouseclick(){
   tb_sujet.setOnMouseClicked((MouseEvent event) -> {
    Sujet suj=tb_sujet.getItems().get(tb_sujet.getSelectionModel().getSelectedIndex());   
    texttxt.setText(suj.getText());
    titretxt.setText(suj.getTitre());
    combotheme.setValue(suj.getTheme());
    idtxt.setText(suj.getIdentif());
    userCacher.setText(suj.getName_user());
 ss.setText(suj.getPic());
Image img=new Image("http://localhost/ImagePi/"+suj.getPic());
        System.out.println("------------------------------");
        System.out.println("http://localhost/ImagePi/"+suj.getPic());
        
        imgshow.setImage(img);
roleCacher.setText(suj.getRole());
    //imgshow.setImage(new javafx.scene.image.Image("localhost/sujet/pic/etude-marché.jpg"));
    
    
   
});}
  @FXML
    private void handleBoutonAjout(ActionEvent event) {

        /***********Validation***************************/
       boolean titreString=validation.TextFieldValidation.textalphabet(titretxt, errtitre, "titre n'accepte que des lettres");
       boolean imge=validation.TextFieldValidation.textValidation(ss, errtheme,"image vide");
       boolean text=validation.TextFieldValidation.AreaValidation(texttxt, errtext,"text vide");
        // boolean themevide=validation.TextFieldValidation.textValidation(themetxt, errtheme, "theme vide");
         if ((titreString)&&(imge)&&(text))
         {
    services.ServiceSujet s=new ServiceSujet();
       Sujet suj=new Sujet();  
       suj.setTitre(titretxt.getText());  
      
       suj.setTheme((String) combotheme.getSelectionModel().getSelectedItem());
       // suj.setName_user((String) comboTxt.getSelectionModel().getSelectedItem()); 
       suj.setText(texttxt.getText());
           suj.setPic(ss.getText());  
               //suj.setPic((String) listImg.getSelectionModel().getSelectedItem()); 
          
       Notifications notification = Notifications.create()
                .title("Ajout avec  succes")
                .text("Ajout succes")
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
          s.ajoutSujet(suj);
            titretxt.clear();    
            texttxt.clear();
            ss.clear();
            imgshow.setImage(null);
            afficherSuj();
         }
         if(titreString){errtitre.setText("");}
           if(imge){errtheme.setText("");}
           if(text){errtext.setText("");}
}
    
    @FXML
    private void handleBoutonSupp(ActionEvent event){
     Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("voulez vous effacer ce Sujet ?");
        Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK) {
        String ide=idtxt.getText();
        services.ServiceSujet s=new ServiceSujet();
        ServiceCommentaire s2=new ServiceCommentaire();
        s.suppSujet(ide); 
        s2.suppCom2(ide);
      
        
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Suppprimé!");
        alert.show();
        afficherSuj();
       titretxt.clear();    
       texttxt.clear();
       imgshow.setImage(null);
 
 }
    }

    @FXML
    private void handleBoutonMod(ActionEvent event) {
         String ide=idtxt.getText();
          String titre=titretxt.getText();
          String text=texttxt.getText();
          String pic=ss.getText();
              String theme=(String) combotheme.getSelectionModel().getSelectedItem(); 
     services.ServiceSujet s=new ServiceSujet();
      s.modifSujet(ide,titre,theme,text,pic); 
      afficherSuj();
}


    @FXML
    private void rechmnt(KeyEvent event) {
     tb_sujet.getItems().clear();
     afficherRech();
    }

    @FXML
    public void AfficherImage(ActionEvent e) throws IOException {
    
              File selectedfile;
        String path_img;
        Upload up = new Upload();

        FileChooser fc = new FileChooser();
        //fc.setInitialDirectory(new File ("C:\\Users\\user\\Desktop\\img"));

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png", "*.PNG", "*.JPG")
        );

        selectedfile = fc.showOpenDialog(null);

        if (selectedfile != null) {

          
            BufferedImage bufferedImage = ImageIO.read(selectedfile);
            path_img = selectedfile.getName();
            ss.setText(path_img);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgshow.setImage(image);
          
            up.upload(selectedfile);
        } else {
            System.out.println("FICHIER erroné");
        }
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

    @FXML
    private void switchImg(MouseEvent event) {
       imgshow.setImage(null); 
                   ss.clear();    

    }

    @FXML
    private void participer(ActionEvent event) {
        boolean titrevid=validation.TextFieldValidation.textValidation(titretxt, errTitre,"Il faut choisir un Sujet pour participer");
          if ((titrevid)){
         suj=tb_sujet.getItems().get(tb_sujet.getSelectionModel().getSelectedIndex()); 
   
         
         if(roleCacher.getText().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")&&(combotheme.getSelectionModel().getSelectedItem()=="Discussion")){
          try {
                Parent page4 = FXMLLoader.load(getClass().getResource("/view/SujetAdmin.fxml"));
               
                Scene scene = new Scene(page4);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if((!roleCacher.getText().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}"))&&(combotheme.getSelectionModel().getSelectedItem()=="Discussion")){
             try {
                Parent page4 = FXMLLoader.load(getClass().getResource("/view/Commentaire.fxml"));
               
                Scene scene = new Scene(page4);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
     if((combotheme.getSelectionModel().getSelectedItem()=="Partage Souvenir")){
             try {
                Parent page4 = FXMLLoader.load(getClass().getResource("/view/Sujet.fxml"));           
                Scene scene = new Scene(page4);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            } } } } 

    @FXML
    private void statistique(ActionEvent event) {
    }
}
