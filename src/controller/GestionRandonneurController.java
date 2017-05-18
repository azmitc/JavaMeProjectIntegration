/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Randonneur;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import services.ServiceRandonneur;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class GestionRandonneurController implements Initializable {
private ArrayList<Randonneur> Randonneurs;
//public ProgressIndicator prog;
    private ListData listdata = new ListData();

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Randonneur> personsTable;

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
    private TableColumn<Randonneur, String> LoginColonne;
    @FXML
    private TableColumn<Randonneur, String> MailColonne;
    @FXML
    private JFXTextField txt_rechercher;
    @FXML
    private JFXButton pdf;
    @FXML
    private TableColumn<Randonneur, Integer> fideliteCol;
    @FXML
    private JFXTextField txt_mail;
    @FXML
    private JFXTextField txt_phone;
    @FXML
    private JFXButton btn_excel;
    @FXML
    private ImageView photo;
    @FXML
    private JFXTextField url_img;
    
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
                personsTable.setItems(listdata.getRandonneurs());
                
                LoginColonne.setCellValueFactory(cell -> cell.
                getValue().getUser_nameProperty());
        MailColonne.setCellValueFactory(cell -> cell.
                getValue().getMailProperty());
        fideliteCol.setCellValueFactory(new PropertyValueFactory<>("pt_fidel"));
    /***********recuper du tableview vers les inputs*********************************************************/
        
        personsTable.setOnMouseClicked(event->{
      
        txt_cin.setText(listdata.getRandonneurs().get(personsTable.getSelectionModel().getSelectedIndex()).getCin());
        txt_mail.setText(listdata.getRandonneurs().get(personsTable.getSelectionModel().getSelectedIndex()).getMail());
        txt_phone.setText(listdata.getRandonneurs().get(personsTable.getSelectionModel().getSelectedIndex()).getNum_tel());
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
                Logger.getLogger(GestionRandonneurController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
//afficher();
    }
public void afficher() {

        Task<ArrayList<Randonneur>> task = new Task() {
            protected Object call() throws SQLException {
               // Platform.runLater(() -> prog.setVisible(true));
                
                Randonneurs =  new ServiceRandonneur().DisplayAllList();
                return Randonneurs;
            }
        };
        task.setOnSucceeded(e -> {

     LoginColonne.setCellValueFactory(new PropertyValueFactory<>("username"));
    MailColonne.setCellValueFactory(new PropertyValueFactory<>("email"));
fideliteCol.setCellValueFactory(new PropertyValueFactory<>("pt_fidel"));
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
        alert.setHeaderText("voulez vous effacer ce randonneur ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        String cin=txt_cin.getText();
      ServiceRandonneur s=new ServiceRandonneur();
      Randonneur a=new Randonneur();
      a.setCin(cin);
        System.out.println(a.getCin());
            
            s.delete(cin);
            alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Suppression avec succes!");
        alert.show();
        
      //s.delete(cin);
                  afficher();
    }
    }
   

    @FXML
    private void modif_Action(ActionEvent event) {
         String mail=txt_mail.getText();
                  String phone=txt_phone.getText();
String cin=txt_cin.getText();
      ServiceRandonneur s=new ServiceRandonneur();
      Randonneur a=new Randonneur();
      a.setNum_tel(phone);
      a.setMail(mail);
      a.setCin(cin);
        System.out.println(a.getNum_tel());
                System.out.println(a.getMail());

            s.updateMailPhone(a);
            afficher();
    }
public void rechercher() {

        Task<ArrayList<Randonneur>> task = new Task() {
            @Override
            protected Object call() throws SQLException {
                //Platform.runLater(() -> prog.setVisible(true));
                String login=txt_rechercher.getText();
                Randonneurs =  new ServiceRandonneur().RechercherList(login);
                return Randonneurs;
            }
        };
        task.setOnSucceeded(e -> {

     MailColonne.setCellValueFactory(new PropertyValueFactory<>("email"));
    LoginColonne.setCellValueFactory(new PropertyValueFactory<>("username"));
        fideliteCol.setCellValueFactory(new PropertyValueFactory<>("pt_fidel"));

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

    @FXML
    private void pdf(ActionEvent event) throws DocumentException, FileNotFoundException {
        Font RED_NORMAL = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL, BaseColor.RED);
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream("Randonneurs.pdf"));
        document.open();

        PdfPTable table = new PdfPTable(5);

        Chunk c = new Chunk("Mes randonneurs", RED_NORMAL);
        Paragraph pa = new Paragraph(c);
        document.add(pa);

        PdfPCell cellLOGIN = new PdfPCell(new Phrase("LOGIN"));
        cellLOGIN.setBackgroundColor(BaseColor.GREEN);
        table.addCell(cellLOGIN);
        

        PdfPCell cellMail = new PdfPCell(new Phrase("Mail"));
        cellMail.setBackgroundColor(BaseColor.GREEN);
        table.addCell(cellMail);

        PdfPCell cellPhone = new PdfPCell(new Phrase("Phone"));
        cellPhone.setBackgroundColor(BaseColor.GREEN);
        table.addCell(cellPhone);
        
         PdfPCell cellPrenom = new PdfPCell(new Phrase("Prenom"));
        cellPrenom.setBackgroundColor(BaseColor.GREEN);
        table.addCell(cellPrenom);
        
         PdfPCell cellNom = new PdfPCell(new Phrase("Nom"));
        cellNom.setBackgroundColor(BaseColor.GREEN);
        table.addCell(cellNom);        
       

        

        for (int i = 0; i < personsTable.getItems().size(); i++) {

           // table.addCell(String.valueOf(table_rec.getItems().get(i).getId()));
            table.addCell(personsTable.getItems().get(i).getUser_name());
            table.addCell(personsTable.getItems().get(i).getMail());
            table.addCell(personsTable.getItems().get(i).getNum_tel());
            table.addCell(personsTable.getItems().get(i).getPrenom());
            table.addCell(personsTable.getItems().get(i).getNom());
           
        }
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(table);
        document.close();
           Notifications notification = Notifications.create()
                .title("export avec  success")
                .text("export success")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("export succes");
            }
        });
        notification.showConfirm();
        
    }

    @FXML
    private void export(ActionEvent event) throws SQLException, IOException {
       ServiceRandonneur s=new ServiceRandonneur();
       s.excel();
        System.out.println("exceldatabase.xlsx written successfully");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Excel");
        alert.setHeaderText(null);
        alert.setContentText("Fichier Excel généré avec succès !");
        alert.showAndWait();
    
    }
}
