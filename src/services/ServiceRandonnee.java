/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Randonnee;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Amine
 * @param <T>
 */
public interface ServiceRandonnee<T> {
    public void insert(T o);
    public void delete(String id);
   public ArrayList<Randonnee> Affiches1();
   public ArrayList<Randonnee> stat();
    public ArrayList<Randonnee> Affiches();
       public ObservableList<String> getNomCategorie() ;
                public ObservableList<String> getNomGuide() ;
                        public ObservableList<String> getNomOrganisateur() ;
    public void update(T o);
     public void Valider(String id);
     public ArrayList<Randonnee> listerRech(String lieu) ;
              public ArrayList<Randonnee> listerRech1(String lieu) ;
                       public ArrayList<Randonnee> listerRech2(String lieu) ;
                                public ArrayList<Randonnee> listerRech3(String lieu) ;
}
