/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utils.Client;
import utils.NetWorkConnection;
import utils.Server;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ChatInsController implements Initializable {
    @FXML
    private JFXTextField ecrirTxt;
    @FXML
    private JFXTextArea ResultTxt;
    private boolean isServer =false;
private NetWorkConnection connection =isServer ? creatServer():creatClient();


    /**
     * Initializes the controller class.
     */
private Server creatServer(){
    return new Server(55555,data->{Platform.runLater(()->{
    ResultTxt.appendText(data.toString()+"\n");
    }
    );});
}
private Client creatClient(){
return new Client("127.0.0.1",55555,data->{
Platform.runLater(()->{
    ResultTxt.appendText(data.toString()+"\n");
    }
    );
});
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            connection.startConnection();
        } catch (Exception ex) {
            Logger.getLogger(ChatInsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void helloReso(ActionEvent event) {
        String msg=isServer?"mnayek: ":"mr: ";
        msg+=ecrirTxt.getText();
        ecrirTxt.clear();
        ResultTxt.appendText(msg +"\n");
        try {
            connection.send(msg);
        } catch (Exception ex) {
                    ResultTxt.appendText("failed to send" +"\n");

        }
    }
    
}
