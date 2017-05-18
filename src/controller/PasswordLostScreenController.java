/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import entities.Randonneur;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeMath.random;
import services.ServiceRandonneur;
import utils.AlertDialog;

/**
 * FXML Controller class
 *
 * @author Nadim
 */
public class PasswordLostScreenController implements Initializable {

    @FXML
    ImageView img;
    private Randonneur user;
    @FXML
    JFXTextField mail, code;
    @FXML
    JFXPasswordField pass;
    @FXML
    JFXButton send;

    @FXML
    JFXSpinner prog;
    @FXML Label inf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
                        double aleacode =random(10);

        prog.setVisible(false);
        code.setVisible(false);
        mail.setStyle("-fx-label-float:true;");
        code.setStyle("-fx-label-float:true;");
 
        send.setOnAction((ActionEvent e) -> {

            prog.setVisible(true);

            if (mail.isVisible()) {

                Task<Randonneur> t = new Task() {

                    @Override
                    protected Object call() throws Exception {
                        user = new ServiceRandonneur().getUserByMail(mail.getText());
                        return user;
                    }
                };

                t.setOnSucceeded(ee -> {

                    prog.setVisible(false);

                    if (t.getValue() == null) {
                        AlertDialog.show("Erreur", "Aucun utilisateur n'existe avec ce mail", Alert.AlertType.ERROR);
                    } else {
                    String generatedcode=user.getCin()+aleacode;
                         System.out.println(generatedcode);
                         
                        Mailer.send("randonnitunisie@gmail.com","randonni123",user.getMail(),"Randonni : Mot de passe oublie"," <img src=\"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcThHUzeIdNQ9J7vuNM5Cyg5eOBs5i7gTTChI_8ElZxfNJn7Uk6h\"><br><h1>Veuillez saisir ce code</h1><br><h2>"+generatedcode+"</h2>");
                        AlertDialog.show("Ok", "Veuillez récupérer le code d'activation de votre email", Alert.AlertType.INFORMATION);
                        inf.setText("Veuillez saisir votre code");
                        code.setVisible(true);
                        mail.setVisible(false);
                    }

                });

                new Thread(t).start();

            } else if (code.isVisible()) {

                Task<Boolean> t = new Task() {

                    @Override
                    protected Object call() throws Exception {
                        //ServiceRandonneur s=new ServiceRandonneur();
                    String generatedcode=user.getCin()+aleacode;

                        return generatedcode.equals(code.getText());
                        //  return  s.verifCode(user.getUser_name(), code.getText());
//                        Connection con = MyConnection.getInstance();
//                        PreparedStatement ps = con.prepareStatement("SELECT code from remote_confirmations WHERE id_user = ?");
//                        System.out.println(user);
//                        ps.setInt(1, user.getId());
//                        ResultSet rs = ps.executeQuery();
//                        rs.first();
//                        System.out.println(rs.getString(1));
//
//                        if (rs.first()) {
//                            if (rs.getString(1).equals(code.getText())) {
//                                return true;
//                            } else {
//                                return false;
//                            }
//                        } else {
//                            return false;
//                        }
                    }
                };

                t.setOnSucceeded(ee -> {
                    System.out.println("succeded");
                    prog.setVisible(false);
                    System.out.println(t.getValue());
                    if (t.getValue()) {
                        code.setVisible(false);
                        pass.setVisible(true);
                        inf.setText("Veuillez saisir votre nouveau mot de passe");
                    } else {
                        AlertDialog.show("Erreur", "Le code saisi est incorrect", Alert.AlertType.ERROR);

                    }

                });
                t.setOnFailed(ee -> {
                    System.out.println("failed");
                    System.out.println(t.getValue());
                });

                new Thread(t).start();

            } else if (pass.isVisible()) {

                prog.setVisible(true);

                Task<Boolean> t = new Task() {

                    @Override
                    protected Object call() throws Exception {
                        user.setPwd(pass.getText());
                        return new ServiceRandonneur().update(user);
                    }
                };

                t.setOnSucceeded(ee -> {
                    prog.setVisible(false);

                    if (t.getValue()) {
                        Alert a = AlertDialog.showDialog("Succès", "Mot de passe changé avec succès", Alert.AlertType.INFORMATION);
                        Optional<ButtonType> result = a.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            try{
                                
                                
                                 Parent r = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                                   stage.setScene(new Scene(r));
                                
                            }catch(IOException ex){
                            }
                        }
                    } else {
                        System.out.println("error changing");
                    }

                });

                t.setOnFailed(ee -> System.out.println("error"));

                if (!pass.getText().isEmpty()) {
                    new Thread(t).start();
                }
            }

        });
    }

}
