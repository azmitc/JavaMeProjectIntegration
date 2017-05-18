/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Randonneur;
import entities.Admin;
import services.ServiceRandonneur;
import services.ServiceAdmin;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Othman Ben Jemaa
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        //Setting title to the Stage 
      stage.setTitle("Gestion Admin"); 
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
//         Randonneur p1=new Randonneur("Ben","Oth","50705027","12260978","medothman.benjemaa@esprit.tn","obj95","123","Randonneur",10);
//ServiceRandonneur ser=new ServiceRandonneur();
//try{
//  ser.ajouterRandonneur(p1);
//        ser.updateRandonneur("obj95", "1234");
//} catch(SQLException ex){
//System.out.println(ex);
//}
//
//        try {
//            List<Randonneur> listper=ser.afficher();
//            System.out.println(listper);
//        } catch (SQLException ex) {
//            java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//         Randonneur r = ser.findRandonneurByLogin("obj95");
//        System.out.println(r);                
//
// // ser.deleteRandonneur("12560978");
//
//        System.out.println("*******ADMIN*********");
//         Admin p=new Admin("Ben","Oth","50705027","12560948","medothman.benjemaa@esprit.tn","obj95","123","admin");
//ServiceAdmin serAdmin=new ServiceAdmin();
//serAdmin.insert(p);
//List<Admin>  listper=serAdmin.displayAll();
//System.err.println(listper);
//         Admin a = serAdmin.displayByCIN("obj95");
//        System.out.println(a);                
//
// // serAdmin.deleteRandonneur("12560978");
        
    }
    
    
}
