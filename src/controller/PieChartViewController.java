/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Admin;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import services.ServiceAdmin;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class PieChartViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PieChart pieChart;
    ObservableList<Data> list=FXCollections.
            observableArrayList();
    @FXML
    private Button btn_retour;
    
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
        // TODO
        ServiceAdmin pdao=ServiceAdmin.getInstance();
        List<Admin> pers = null;
        try {
            pers = pdao.DisplayAllAllList();
        } catch (SQLException ex) {
            Logger.getLogger(PieChartViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        for(Admin p:pers) {
//            list.addAll(
//                new Data(p.getRole(), 12.0)             
//        );
//        }
//        pieChart.setAnimated(true);
//        pieChart.setData(list);
//        pieChart.toString();
  ObservableList<PieChart.Data> lis =FXCollections.observableArrayList();
     
        try {
            pdao.DisplayAllAllList().stream().collect(Collectors.groupingBy(f->f.getRole(), Collectors.counting())).forEach((t,count)->lis.add(new PieChart.Data(t, count)));
        } catch (SQLException ex) {
            Logger.getLogger(PieChartViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        pieChart.setData(lis);      
        pieChart.setAnimated(true);
        
    }

}
