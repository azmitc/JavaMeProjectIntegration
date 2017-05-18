/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.ServiceAdmin;


/**
 *
 * @author wiemhjiri
 */
public class ConnexionBDJavafx extends Application {

    private Stage primaryStage;
    private Parent parentPage;
   
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Randonni");
        
        parentPage = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(parentPage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
         //from,password,to,subject,message  
    // Mailer.send("randonnitunisie@gmail.com","randonni123","medothman.benjemaa@esprit.tn","Randonni vous felicite !","<h1>L'equipe Randonni vous felicite</h1>");  

    }

}
