/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Admin;
import java.sql.Date;
import java.sql.PreparedStatement;
import utils.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Othman Ben Jemaa
 */
public class ServiceAdmin implements Idao<Admin>{
        
    private static ServiceAdmin instance;
    private Statement st;
    private ResultSet rs;
    ConnexionSingleton cs;
        public ServiceAdmin(){//on peut aussi appliquer Singleton sur ServicePersonne
                cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
     public static ServiceAdmin getInstance(){
        if(instance==null) 
            instance=new ServiceAdmin();
        return instance;
    }
    


    @Override
    public void insert(Admin p) 
    {
    String requete="INSERT INTO `utilisateur` (`nom`, `prenom`, `num_tel`,`cin`,`email`,`username`,`password`,`roles`,`birthday`,`image_name`) values (?,?,?,?,?,?,?,?,?,?)";
try {
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
                 ps.setString(3, p.getNum_tel());
          
            ps.setString(4, p.getCin());
            ps.setString(5, p.getMail());
            ps.setString(6, p.getUser_name());
            ps.setString(7, p.getPwd());
            ps.setString(8, p.getRole());
            ps.setString(8, p.getRole());
            ps.setDate(9, (Date) p.getBirthday());
            ps.setString(10, p.getPhoto());

            //ps.executeUpdate();
                      System.out.println(requete);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
// public List<Admin> afficher() throws SQLException{
//        ArrayList<Admin> list=new ArrayList<>();
//        String req="select * from utilisateur where role='admin'";
//     ResultSet rs= st.executeQuery(req);
//     Admin per=null;
//     while (rs.next()){
//         per=new Admin(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
//         list.add(per);
//     }
//     return list;
//    }
     public void delete(String cin) {
String requete = "delete from utilisateur where cin=?";
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ps.setString(1, cin);
            ps.executeUpdate();
            System.out.println("client supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression Admin" + ex.getMessage());
        }
    }
    @Override
    public void delete(Admin o) {
String req="delete from utilisateur where cin = ?";

        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(req);
            ps.setString(1, o.getCin());
            ps.executeUpdate(req);
            System.out.println("client supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression Admin" + ex.getMessage());
        }
    }
     @Override
    public boolean update(Admin p) {
        String qry = "UPDATE utilisateur SET password=?,username=? WHERE cin = ?";
        System.out.println(qry);
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(qry);
            ps.setString(1,p.getPwd()); 
             ps.setString(2, p.getUser_name());       
             ps.setString(3, p.getCin());           
             
            if (ps.executeUpdate() > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;   
    }
            public boolean updateMailPhone(Admin p) {
String qry = "UPDATE utilisateur SET email=? ,num_tel=? WHERE CIN = ?";
        System.out.println(qry);
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(qry);
            ps.setString(1,p.getMail()); 
             ps.setString(2, p.getNum_tel()); 
             ps.setString(3, p.getCin());           
             
            if (ps.executeUpdate() > 0) {                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;   
    }
                         public void updateImage(Admin p) {
        String requete = "UPDATE utilisateur SET image_name = ?  WHERE username=?";
         System.out.println(requete);
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ps.setString(1,p.getPhoto()); 
             ps.setString(2, p.getUser_name());           
            ps.executeUpdate();
            System.out.println("photo update");
        } catch (SQLException ex) {
            System.out.println("erreur lors de modification  photo" + ex.getMessage());
        }
    }
    public ArrayList<Admin> DisplayAllAllList() throws SQLException{
        ArrayList<Admin> list=new ArrayList<>();
        String req="select * from utilisateur";
      rs= st.executeQuery(req);
     while (rs.next()){
         Admin per=new Admin(rs.getString("nom"), rs.getString("prenom"), rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getDate("birthday"),rs.getString("image_name"));
         list.add(per);
     }
     return list;
    }
 public ArrayList<Admin> DisplayAllList() throws SQLException{
        ArrayList<Admin> list=new ArrayList<>();
        String req="select * from utilisateur where roles='a:1:{i:0;s:10:\"ROLE_ADMIN\";}'";
      rs= st.executeQuery(req);
     while (rs.next()){
         
         Admin per=new Admin(rs.getString("nom"), rs.getString("prenom"), rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getDate("birthday"),rs.getString("image_name"));
         list.add(per);
     }
     return list;
    }
 public ArrayList<Admin> RechercherList(String Recherche) throws SQLException{
        ArrayList<Admin> list=new ArrayList<>();
        String req="select * from utilisateur  where roles='a:1:{i:0;s:10:\"ROLE_ADMIN\";}' and concat (`nom`, `prenom`, `num_tel`,`cin`,`email`,`username`,`roles`,`birthday`) like '%"+Recherche+"%' ";
     //System.out.println(req);
        rs= st.executeQuery(req);
     while (rs.next()){
         Admin per=new Admin(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getDate(10),rs.getString(11));
         list.add(per);
     }
     return list;
    }
    @Override
   public ObservableList<Admin> displayAll() {
 String req="select * from utilisateur where roles='a:1:{i:0;s:10:\"ROLE_ADMIN\";}'";
        ObservableList<Admin> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
//                p.setId(rs.getInt(1));
//                p.setNom(rs.getString("nom"));
//                p.setPrenom(rs.getString(3));
         Admin per=new Admin(rs.getString("nom"), rs.getString("prenom"), rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getDate("birthday"),rs.getString("image_name"));
         list.add(per);            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
//     public int nbLignes(String login){
//         int i=0;
//        String req="SELECT * FROM utilisateur WHERE DATE_FORMAT(birthday,'%m%d') LIKE DATE_FORMAT(CURDATE(),'%m%d') and user_name='"+login+"'";
//				try {
//            rs=st.executeQuery(req);
//            while(rs.next()){
//         Admin per=new Admin(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getDate(10),rs.getString(11));
//         i++;
//            }
//                                    System.out.println(i);
//            return i;
//        } catch (SQLException ex) {
//           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("erreur lors du chargement des Admins "+ex.getMessage());
//        }
//       
//
//        return i;
//     }
    public boolean login(String login,String pwd) {
        
String req="select * from utilisateur where username=? and password=?";
         //Admin p=new Admin(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(req);
            ps.setString(1,login); 
             ps.setString(2, pwd);           
           rs =  ps.executeQuery();
            while(rs.next()){

         Admin per=new Admin(rs.getString("nom"), rs.getString("prenom"), rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getDate("birthday"),rs.getString("image_name"));
         return true;
            }
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Admins "+ex.getMessage());
        }
     

        return false;
    }
public boolean verifAdmin(String login) {
    
String req="select * from utilisateur where username=? and roles='a:1:{i:0;s:10:\"ROLE_ADMIN\";}' ";
         //Admin p=new Admin(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
        try {
PreparedStatement ps = cs.getCnx().prepareStatement(req);
            ps.setString(1,login); 
            rs = ps.executeQuery();         
            while(rs.next()){
         Admin p=new Admin(rs.getString("nom"), rs.getString("prenom"), rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getDate("birthday"),rs.getString("image_name"));
         return true;
            }
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Admins "+ex.getMessage());
        }
        

        return false;
    }
public boolean verifLoginUnique(String login ) {

    String req="select * from utilisateur where username=? ";
         //Admin p=new Admin(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
        try {
PreparedStatement ps = cs.getCnx().prepareStatement(req);
            ps.setString(1,login); 
             rs =ps.executeQuery();         
            while(rs.next()){
         Admin p=new Admin(rs.getString("nom"), rs.getString("prenom"), rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getDate("birthday"),rs.getString("image_name"));
         return false;
            }
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Admins "+ex.getMessage());
        }
       

        return true;
    }
public boolean verifMailUnique(String mail) {

    String req="select * from utilisateur where email=? ";
         //Admin p=new Admin(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
        try {
PreparedStatement ps = cs.getCnx().prepareStatement(req);
            ps.setString(1,mail); 
             rs =ps.executeQuery();
            while(rs.next()){
         Admin p=new Admin(rs.getString("nom"), rs.getString("prenom"), rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getDate("birthday"),rs.getString("image_name"));
         return false;
            }
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des Admins "+ex.getMessage());
        }
       

        return true;
    }
       public Admin displayByLogin(String login){
        String req="select * from utilisateur where username =?";
        System.out.println(req);
           Admin p=new Admin();
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(req);
            ps.setString(1,login); 
           rs =  ps.executeQuery();
           while(rs.next()){
           // rs.next();
         p=new Admin(rs.getString("nom"), rs.getString("prenom"), rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getDate("birthday"),rs.getString("image_name"));

//                p.setUser_name(rs.getString("user_name"));
//                p.setBirthday(rs.getDate("birthday"));
//                p.setPt_fidel(rs.getInt("pt_fidel"));
            }  
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public Admin displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
