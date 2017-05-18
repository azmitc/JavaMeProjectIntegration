/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.directions.DirectionsWaypoint;
import com.lynden.gmapsfx.service.elevation.ElevationService;
import com.lynden.gmapsfx.service.elevation.ElevationServiceCallback;
import com.lynden.gmapsfx.service.elevation.LocationElevationRequest;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MapApiController implements Initializable, MapComponentInitializedListener {
       
    @FXML
    private GoogleMapView mapView;

    @FXML
    private TextField addressTextField;

    private GoogleMap map;

    private GeocodingService geocodingService;
  
    private StringProperty address = new SimpleStringProperty();
    @FXML
    private Button retour_btn;
    private MarkerOptions markerOptions2;
    private Marker myMarker2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        addressTextField.setText(AfficheRandonneeController.rand.getLieu());
        
        mapView.addMapInializedListener(this);
        address.bind(addressTextField.textProperty());
       
         
         retour_btn.setOnAction((ActionEvent event) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AfficheRandonnee.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
     
    
   
    @Override
    public void mapInitialized() {
                geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(36.803660, 10.171084))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);
         
        
        
        markerOptions2 = new MarkerOptions();
        LatLong markerLatLong2 = new LatLong(36.803660, 10.171084);
        markerOptions2.position(markerLatLong2)
                .title("My new Marker")
                .visible(true);

        myMarker2 = new Marker(markerOptions2);

        map.addMarker(myMarker2);
  
    }
    
  
    

     @FXML
    public void addressTextFieldAction(ActionEvent event) {
    
    
    address.bind(addressTextField.textProperty());
        geocodingService.geocode(address.get(), (GeocodingResult[] results, GeocoderStatus status) -> {
            
            LatLong latLong = null;
            
            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
            }
            
            map.setCenter(latLong);
            

        });
    }
}
