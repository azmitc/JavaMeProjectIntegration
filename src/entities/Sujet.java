/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.File;
import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import services.ServiceSujet;

/**
 *
 * @author user
 */
public class Sujet {
  private  String identif;
  private  String titre;
  private  String theme;
 private String name_user;
  private  String text;
  private String role;
  private Date date1;
 File Image = null;
 private String pic;
 
    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

 
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public File getImage() {
        return Image;
    }
 public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }
    public void setImage(File Image) {
        this.Image = Image;
    }

    public Sujet(String identif, String titre, String theme, String name_user, String text, String pic) {
        this.identif = identif;
        this.titre = titre;
        this.theme = theme;
        this.name_user = name_user;
        this.text = text;
        this.pic = pic;
    }
     
 public Sujet( String titre, String theme,String text,String pic) {//ef
       
        this.titre = titre;
        this.theme =theme;    
        this.text=text;
        this.pic = pic;//ef
    }
 public Sujet( String titre, String theme,String text,String pic,File Image) {//ef
       
        this.titre = titre;
        this.theme =theme;    
        this.text=text;
        this.pic = pic;//ef
        this.Image=Image;
    }
    public Sujet( String titre, String theme,String text) {
       
        this.titre = titre;
        this.theme =theme;
        this.text=text;
    }

    public Sujet() {
    }


   
    @Override
    public String toString() {
        return "Sujets{" + "identif=" + identif + ", titre=" + titre + ", theme=" + theme+ ", text=" + text + ", pic=" + pic +'}';
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text =text;
    }

    public String getIdentif() {
        return identif;
    }

    public void setIdentif(String id) {
        this.identif = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme =theme;
    }

  
    
     public String getPic() { //ef
        return pic;
    }
       public void setPic(String pic) {//ef
        this.pic = pic;
      }
       
     
    
    
}
