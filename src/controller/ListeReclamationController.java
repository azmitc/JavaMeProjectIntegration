/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;







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
import java.io.FileNotFoundException;








import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javax.swing.text.StyleConstants.FontFamily;
import org.controlsfx.control.Notifications;

import entities.Reclamation;
import services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author balha
 */
public class ListeReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> table_rec;
    @FXML
    private TableColumn<Reclamation,String> col_idr;
    @FXML
    private TableColumn<Reclamation, String> col_desc;
    @FXML
    private TableColumn<Reclamation, String> col_rec;
    @FXML
    private TableColumn<Reclamation, String> col_da;
    @FXML
    private TableColumn<Reclamation, String> col_de;
    @FXML
    private TableColumn<Reclamation, String> col_rm;
    @FXML
    private TableColumn<Reclamation, String> col_idc;
    private ArrayList<Reclamation> reclamation;
        public ProgressIndicator prog;
    @FXML
    private JFXButton rte_btn;
    @FXML
    private JFXButton supp_btn;
    @FXML
    private JFXButton rep_btn;
    @FXML
    private TextField txt_id;
    @FXML
    private JFXButton btn_pdf;
        public static Reclamation mat=new Reclamation();
    @FXML
    private JFXTextField mail_txt;
   // private TextField mail_txt;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setmouseclick();
        afficherRec();
        // TODO
    } 
      private void setmouseclick(){
    table_rec.setOnMouseClicked((MouseEvent event) -> {
    Reclamation tl=table_rec.getItems().get(table_rec.getSelectionModel().getSelectedIndex());
    mail_txt.setText(tl.getDescription());
    txt_id.setText(tl.getId());
    

     //combo_etat.getValue()
     
    
});


}
    
    
     public void afficherRec() {

        Task<ArrayList<Reclamation>> task = new Task() {
            @Override
            protected Object call() {
//                Platform.runLater(() -> prog.setVisible(true));
                
                reclamation =  new ServiceReclamation().SelectReclamation();
                return reclamation;
            }
        };
        task.setOnSucceeded(e -> {
     col_idr.setCellValueFactory(new PropertyValueFactory<>("id"));
         col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
     col_rec.setCellValueFactory(new PropertyValueFactory<>("typeReclamation"));
    col_da.setCellValueFactory(new PropertyValueFactory<>("dateAchat"));
    col_de.setCellValueFactory(new PropertyValueFactory<>("dateEnvoi"));
         col_rm.setCellValueFactory(new PropertyValueFactory<>("idMateriel"));
         col_idc.setCellValueFactory(new PropertyValueFactory<>("idUtilisateur"));
     table_rec.setItems(FXCollections.observableArrayList(task.getValue()));
        }
        );
        task.setOnFailed(e -> {
          //  Platform.runLater(() -> Platform.runLater(() -> AlertDialog.show("Erreur", "Erreur d'actualisation ", Alert.AlertType.ERROR)));
            afficherRec();
            prog.setVisible(false);

        });
        Thread th = new Thread(task);
        th.start();
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
    
      
    

    @FXML
    private void delete(ActionEvent event) {
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("voulez vous effacer cette reclamation ?");
        Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK) {
        String ide=txt_id.getText();
        
      ServiceReclamation s=new ServiceReclamation();
      s.deleteReclamation(ide);
        Notifications notification = Notifications.create()
                .title("delete avec  success")
                .text("delete success")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("delete succes");
            }
        });
        notification.showConfirm();
      
     afficherRec();
    
         }
    }

    @FXML
    private void gomail_btn(ActionEvent event) {
        mat=table_rec.getItems().get(table_rec.getSelectionModel().getSelectedIndex());
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/EnvoyerMail.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void pdf(ActionEvent event) throws DocumentException, FileNotFoundException {
        
        Font RED_NORMAL = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL, BaseColor.RED);
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream("Reclamation.pdf"));
        document.open();

        PdfPTable table = new PdfPTable(5);

        Chunk c = new Chunk("Mes reclamations", RED_NORMAL);
        Paragraph pa = new Paragraph(c);
        document.add(pa);

        PdfPCell cellId = new PdfPCell(new Phrase("id"));
        cellId.setBackgroundColor(BaseColor.CYAN);
        table.addCell(cellId);
        

        PdfPCell cellDesc = new PdfPCell(new Phrase("description"));
        cellDesc.setBackgroundColor(BaseColor.CYAN);
        table.addCell(cellDesc);

        PdfPCell cellType = new PdfPCell(new Phrase("type reclamation"));
        cellType.setBackgroundColor(BaseColor.CYAN);
        table.addCell(cellType);
        
        
        PdfPCell cellMat = new PdfPCell(new Phrase("reference materiel"));
        cellMat.setBackgroundColor(BaseColor.CYAN);
        table.addCell(cellMat);
        
        
         PdfPCell cellMail = new PdfPCell(new Phrase("Mail"));
        cellMail.setBackgroundColor(BaseColor.CYAN);
        table.addCell(cellMail);

        

        for (int i = 0; i < table_rec.getItems().size(); i++) {

           // table.addCell(String.valueOf(table_rec.getItems().get(i).getId()));
            table.addCell(table_rec.getItems().get(i).getId());
            table.addCell(table_rec.getItems().get(i).getDescription());
            table.addCell(table_rec.getItems().get(i).getTypeReclamation());
            table.addCell(table_rec.getItems().get(i).getIdMateriel());
           // table.addCell(table_rec.getItems().get(i).getIdUtilisateur());
           
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

    
    
}
