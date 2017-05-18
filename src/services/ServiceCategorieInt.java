/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CategorieRandonnee;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amine
 * @param <T>
 */
public interface ServiceCategorieInt<T> {
    public void insert(T o);
    public ArrayList<CategorieRandonnee> Affiches();
     public ArrayList<CategorieRandonnee> listerRech(String nom);
    public void update(T os);
      public void delete(String id);
    
}
