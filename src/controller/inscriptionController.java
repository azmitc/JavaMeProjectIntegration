/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;
import entities.Admin;
import entities.Randonneur;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import services.ServiceAdmin;
import java.net.URL;
import java.util.Date;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import services.ServiceRandonneur;
import utils.Upload;


/**
 * FXML Controller class
 *
 * @author Othman Ben Jemaa
 */
public class inscriptionController implements Initializable {


    @FXML
    private Button btn_ajouter;
    @FXML
    private Label label;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_numtel;
    @FXML
    private TextField txt_cin;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_mail;
    @FXML
    private TextField txt_username;
    @FXML
    private ComboBox<String> txt_role;
    private TableColumn<Admin, String> col_nom;
    private TableColumn<Admin, String> col_prenom;
    private TableColumn<Admin, String> col_phone;
    private TableColumn<Admin, String> col_cin;
    private TableColumn<Admin, String> col_mail;
    private TableColumn<Admin, String> col_login;
    private TableColumn<Admin, String> col_role;
    @FXML
    private PasswordField txt_psw;
    @FXML
    private Label error_nom;
    @FXML
    private Label error_prenom;
    @FXML
    private Label error_num_tel;
    @FXML
    private Label error_cin;
    @FXML
    private Label error_mail;
    @FXML
    private Label error_username;
    @FXML
    private Label error_role;
    @FXML
    private Label error_pswd;
    @FXML
    private DatePicker birthday;
    @FXML
    private ImageView imgshow;
    @FXML
    private JFXButton browse;
        public static File file;
                public static Image image;
    @FXML
    private JFXTextField url_image;
    @FXML
    private JFXButton btn_fb;

 public static Randonneur rand=new Randonneur();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ComboBOX Options:txt_role
       txt_role.getItems().removeAll(txt_role.getItems());
    txt_role.getItems().addAll("admin", "randonneur", "organisateur");
    //txt_role.getSelectionModel().select("admin");
        /*******************************************************/
         btn_ajouter.setOnAction(event -> {
             /***********Validation***************************/
       
        boolean nomValide=validation.TextFieldValidation.textalphabet(txt_nom, error_nom, "Nom invalide");
        boolean prenomValide=validation.TextFieldValidation.textalphabet(txt_prenom, error_prenom, "Prenom invalide");
        boolean phoneValide=validation.TextFieldValidation.tex8Num(txt_numtel, error_num_tel, "Il faut remplir 8chiffres");
        boolean CINValide=validation.TextFieldValidation.tex8Num(txt_cin, error_cin, "Il faut remplir 8chiffres");
        boolean MailValide=validation.TextFieldValidation.texMail(txt_mail, error_mail, "Mail invalide");
        boolean PasswordValide=validation.TextFieldValidation.texAlphNum(txt_psw, error_pswd, "Il faut remplir avec alphabet et ou numeros");
        boolean LoginValide=validation.TextFieldValidation.texAlphNum(txt_username, error_username, "Il faut remplir avec alphabet et ou numeros");
    
        if (PasswordValide)
                 {
                   error_pswd.setText("");  
                 }
        if (LoginValide)
                 {
                   error_username.setText("");  
                 }
    if (nomValide)
                 {
                   error_nom.setText("");  
                 }
      if (prenomValide)
                 {
                   error_prenom.setText("");  
                 }
            if (CINValide)
                 {
                   error_cin.setText("");  
                 }
 if (phoneValide)
                 {
                   error_num_tel.setText("");  
                 }
  if (MailValide)
                 {
                   error_mail.setText("");  
                 }
           if ((nomValide)&&(prenomValide)&&(phoneValide)&&(CINValide)&&(MailValide)&&(PasswordValide)&&(LoginValide))
                /**************************************/
      {  

          String nom=txt_nom.getText();
        String prenom=txt_prenom.getText();
        String phone=txt_numtel.getText();
        String cin=txt_cin.getText();
        String mail=txt_mail.getText();
        String login=txt_username.getText();
                String psw=txt_psw.getText();
              String role="";  
                
       // String role=(String) txt_role.getValue();
       if(txt_role.getValue().equals("randonneur")){role="a:1:{i:0;s:15:\"ROLE_RANDONNEUR\";}";}
         if(txt_role.getValue().equals("organisateur")){role="a:1:{i:0;s:11:\"ROLE_AGENCE\";}";}
          if(txt_role.getValue().equals("admin")){role="a:1:{i:0;s:10:\"ROLE_ADMIN\";}";}
          
        Date date_naissance=java.sql.Date.valueOf(birthday.getValue());
        String photo=url_image.getText();
          System.out.println(date_naissance);
        Admin p=new Admin(nom,prenom,phone,cin,mail,login,psw,role,date_naissance,photo);
        ServiceAdmin s = ServiceAdmin.getInstance();
    
if (s.verifLoginUnique(login))
{   if (s.verifMailUnique(mail))  {
    s.insert(p);
    
    Mailer.send("randonnitunisie@gmail.com","randonni123",mail,"Bienvenue a Randonni!","<h1>L'equipe Randonni vous felicite</h1><br> <img src=\"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSFPLj7Cf-UraM2mGI2S_FxE-lXRDgq8tTkLQJLkKe15plJdPleFA\"> ");  
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Compte crée avec succés!");
        alert.show();
        /*********************************************************************/
        try {//FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListData());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
}
else {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("warning Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Mail existe deja!");
        alert.show();
}
}
else {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("warning Dialog");
        alert.setHeaderText(null);
        alert.setContentText("LOGIN existe deja!");
        alert.show();
}

      }
    
        });
        
        
    }


    @FXML
    public void AfficherImage(ActionEvent e) throws IOException {

       File selectedfile;
        String path_img;
        Upload up = new Upload();

        FileChooser fc = new FileChooser();

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png")
        );

        selectedfile = fc.showOpenDialog(null);

        if (selectedfile != null) {

            BufferedImage bufferedImage = ImageIO.read(selectedfile);
            path_img = selectedfile.getName();
            url_image.setText(path_img);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgshow.setImage(image);
            System.out.println("image");
            up.upload(selectedfile);
        } else {
            System.out.println("FICHIER erroné");
        }
 }

    @FXML
    private void authUser(ActionEvent event) {
                String domain = "https://www.google.fr/?gws_rd=ssl";
        String appId="1264247583623638";  
        //donner permission access au utilisateur
        String authUrl = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id="+appId+"&redirect_uri="+domain+"&scope=user_about_me,"
                + "user_actions.books,user_actions.fitness,user_actions.music,user_actions.news,user_actions.video,user_birthday,user_education_history,"
                + "user_events,user_photos,user_friends,user_games_activity,user_hometown,user_likes,user_location,user_photos,user_relationship_details,"
                + "user_relationships,user_religion_politics,user_status,user_tagged_places,user_videos,user_website,user_work_history,ads_management,ads_read,email,"
                + "manage_pages,publish_actions,read_insights,read_page_mailboxes,rsvp_event";
       System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
       
       WebDriver driver = new ChromeDriver();
        driver.get(authUrl);
           String accessToken ;

           while(true){
       
            if(!driver.getCurrentUrl().contains("facebook.com")){
            String url = driver.getCurrentUrl();
            accessToken = url.replaceAll(".*#access_token=(.+)&.*", "$1");
           
           
                FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                User user = fbClient.fetchObject("me",User.class);
                 // we have to tell faceboook fields that we want with extra parameter "fields" like this
        
        user = fbClient.fetchObject("me", User.class,Parameter.with("fields","id,name,email,cover,picture"));
        // now we can use these fields
        
                           driver.quit();
                System.out.println(user.getName()+" is connected ");
                System.out.println(user.getLastName()+"-"+user.getFirstName()+"-"+user.getEmail()+"--"+user.getBirthday());
//                txt_nom.setText(user.getName());
//                txt_mail.setText(user.getEmail());
//                txt_prenom.setText(user.getLastName());
//                txt_username.setText(user.getFirstName());
                ServiceRandonneur s = ServiceRandonneur.getInstance();
                if (s.verifLoginUnique(user.getName())){
                 rand=new Randonneur(user.getName(),user.getFirstName(),null,null,user.getEmail(),user.getName(),"123","randonneur",user.getBirthdayAsDate(),0,"noimage.jpg");
                            s.insert(rand);
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Randonni");
                            alert.setHeaderText(null);
                            alert.setContentText("Compte cree! ton login est "+user.getName()+" Mot de passe est 123");
                             alert.show();
                }
                rand=s.getUserByLogin(user.getName());
                rand.setConnected(1);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Randonni");
        alert.setHeaderText(null);
        alert.setContentText("Bienvenue "+user.getName()+" Ravi de vous revoir :) ");
        alert.show();
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AccueilFront.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(inscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            }

       
           
    }
    }
    
}
