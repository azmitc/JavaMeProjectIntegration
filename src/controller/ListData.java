/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Admin;
import entities.Randonneur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.ServiceAdmin;
import services.ServiceRandonneur;




public class ListData {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Admin> persons=FXCollections.observableArrayList();
    private ObservableList<Randonneur> randonneursList=FXCollections.observableArrayList();

    public ListData() {
        
        ServiceAdmin pdao=ServiceAdmin.getInstance();
                ServiceRandonneur s=ServiceRandonneur.getInstance();

        persons= pdao.displayAll();
        randonneursList=s.displayAll();
        System.out.println(persons);
                System.out.println(randonneursList);

    }
    
    public ObservableList<Admin> getPersons(){
        return persons;
    }
   public ObservableList<Randonneur> getRandonneurs(){
        return randonneursList;
    }
}
