/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Randonneur;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import utils.ConnexionSingleton;
import java.sql.*;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Othman Ben Jemaa
 */
public class ServiceRandonneur implements Idao<Randonneur> {
      private static ServiceRandonneur instance;
    private Statement st;
    private ResultSet rs;
    ConnexionSingleton cs;
        
        public ServiceRandonneur(){
            cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
         public static ServiceRandonneur getInstance(){
        if(instance==null) 
            instance=new ServiceRandonneur();
        return instance;
    }
    
    @Override
    public void insert(Randonneur p) {
   String requete="INSERT INTO `utilisateur` (`nom`, `prenom`, `num_tel`,`cin`,`email`,`email_canonical`,`username`,`username_canonical`,`password`,`roles`,`birthday`,`image_name`,`pt_fidel`,`enabled`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,1)";
try {
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getPrenom());
                 ps.setString(3, p.getNum_tel());
          
            ps.setString(4, p.getCin());
            ps.setString(5, p.getMail());
              ps.setString(6, p.getMail());
            ps.setString(7, p.getUser_name());
            ps.setString(9, p.getPwd());
            ps.setString(10, p.getRole());
            ps.setString(8, p.getUser_name());
            ps.setDate(11, (Date) p.getBirthday());
            ps.setString(12, p.getPhoto());
            ps.setInt(13,p.getPt_fidel());
            //ps.executeUpdate();
                      System.out.println(requete);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRandonneur.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public void delete(Randonneur o) {
String req="delete from utilisateur where cin=?";

        try {
PreparedStatement ps = cs.getCnx().prepareStatement(req);
            ps.setString(1,o.getCin()); 
            ps.executeUpdate();         
            System.out.println("randonneur supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression randonneur" + ex.getMessage());
        }
    }
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
    public ObservableList<Randonneur> displayAll() {
String req="select * from utilisateur where roles='a:1:{i:0;s:15:\"ROLE_RANDONNEUR\";}'";
        ObservableList<Randonneur> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
//                p.setId(rs.getInt(1));
//                p.setNom(rs.getString("nom"));
//                p.setPrenom(rs.getString(3));
        Randonneur per=new Randonneur(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getDate(10),rs.getInt(16),rs.getString(11));
         list.add(per);         
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Randonneur displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Randonneur p) {
String qry = "UPDATE utilisateur SET password=?,username=? WHERE username = ?";
        System.out.println(qry);
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(qry);
            ps.setString(1,p.getPwd()); 
             ps.setString(2, p.getUser_name()); 
             ps.setString(3, p.getUser_name());           
             
            if (ps.executeUpdate() > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRandonneur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;   
    }
        public boolean updateMailPhone(Randonneur p) {
String qry = "UPDATE utilisateur SET email=? ,num_tel=? WHERE username = ?";
        System.out.println(qry);
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(qry);
            ps.setString(1,p.getMail()); 
             ps.setString(2, p.getNum_tel()); 
             ps.setString(3, p.getUser_name());           
             
            if (ps.executeUpdate() > 0) {  
                            System.out.println("succes update");
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRandonneur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;   
    }
             public void updateImage(Randonneur p) {
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
     public void updateptsfidelite(String login,int pts) {
        String requete = "UPDATE utilisateur SET pt_fidel =pt_fidel + ?  WHERE username=?";
         System.out.println(requete);
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(requete);
            ps.setInt(1,pts); 
            ps.setString(2, login);
            
            ps.executeUpdate();
            System.out.println("points fidelite update");
        } catch (SQLException ex) {
            System.out.println("erreur lors de modification  ptsfidelite" + ex.getMessage());
        }
    }
     public int nbLignes(String table,String login){
         int count=0;
        String req="SELECT COUNT(*) AS rowcount  FROM "+table+" WHERE DATE_FORMAT(birthday,'%m%d') LIKE DATE_FORMAT(CURDATE(),'%m%d') and username='"+login+"'";
			      System.out.println(req);
        try {
            rs=st.executeQuery(req);
         rs.next();
        count = rs.getInt("rowcount");
       rs.close();
                                    System.out.println("count= "+count);
            return count;
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur "+ex.getMessage());
        }
       

        return count;
     }
//     public Date recupDate(String login){
//         
//         Date birthday = null;
//          try {
//              String req="select dateannivpts from historiqueanniv where user_name='"+login+"'";
//              rs=st.executeQuery(req);
//              rs.next();
//               birthday = rs.getDate("dateannivpts");
//              rs.close();
//              System.out.println("birthday " + birthday);
//          } catch (SQLException ex) {
//              Logger.getLogger(ServiceRandonneur.class.getName()).log(Level.SEVERE, null, ex);
//          }
//                        return birthday;
//
//     }
     public boolean anniv(String login){
	//Récupération de date actuel
        long millis=System.currentTimeMillis();  
java.sql.Date maintenant=new java.sql.Date(millis); 
         System.out.println("maintenat" +maintenant);
         /***********************/
       


//				$req1=$conn->prepare("SELECT * FROM client WHERE DATE_FORMAT(Date_naissance,'%m%d') LIKE DATE_FORMAT(CURDATE(),'%m%d') and CIN=".$CIN);
//				$req1->execute();
//				$nb = $req1->rowCount();   
                        int nb=nbLignes("utilisateur",login);
				if (nb !=0 )
				{
                                int n=nbLignes("historiqueanniv",login);

                                     if (n==0 )             
					{
						updateptsfidelite(login,20);
						String req="INSERT INTO historiqueanniv (username,birthday) VALUES ('"+login+"','"+maintenant+"')";
						try {
                                                    st.executeUpdate(req);
                                                     } catch (SQLException ex) {
                                                      Logger.getLogger(ServiceRandonneur.class.getName()).log(Level.SEVERE, null, ex);
                                                      }   
						return true;
					}
					else return false;
				}
				else return false;

			}
     
 
    public Randonneur displayByLogin(String login){
        String req="select * from utilisateur where username =?";
        System.out.println(req);
           Randonneur p=new Randonneur();
        try {
            PreparedStatement ps = cs.getCnx().prepareStatement(req);
            ps.setString(1,login); 
           rs =  ps.executeQuery();
           while(rs.next()){
           // rs.next();
                     p =new Randonneur(rs.getInt("id"),rs.getString("nom"), rs.getString("prenom"), rs.getString("num_tel"),rs.getString("cin"),rs.getString("email"),rs.getString("username"),rs.getString("password"),rs.getString("roles"),rs.getDate("birthday"),rs.getInt("pt_fidel"),rs.getString("image_name"));

//                p.setUser_name(rs.getString("user_name"));
//                p.setBirthday(rs.getDate("birthday"));
//                p.setPt_fidel(rs.getInt("pt_fidel"));
            }  
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }
    public boolean verifLoginUnique(String mail) {
         String req="select * from utilisateur where username=? ";
         //Admin p=new Admin(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
        try {
PreparedStatement ps = cs.getCnx().prepareStatement(req);
            ps.setString(1,mail); 
             rs =ps.executeQuery();
            while(rs.next()){
            Randonneur p =new Randonneur(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getDate(10),rs.getInt(16),rs.getString(11));
             return false;
            }
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(" verifLoginUnique Randonneur "+ex.getMessage());
        }
       

        return true;
    }
    
    public Randonneur getUserByMail(String mail) {
         String query = "SELECT * FROM utilisateur WHERE email = ?";
         System.out.println(query);
         Randonneur p=new Randonneur();
       try{
                   PreparedStatement ps = cs.getCnx().prepareStatement(query);
                    ps.setString(1, mail);
              ResultSet rs = ps.executeQuery();
              
            rs.next();
                     p =new Randonneur(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getDate(10),rs.getInt(16),rs.getString(11));
              
       }catch(SQLException e){
           System.err.println(e.getMessage());
           return null;
       }
              return p;
    }
    public Randonneur getUserByLogin(String login) {
         String query = "SELECT * FROM utilisateur WHERE username = ?";
         System.out.println(query);
         Randonneur p=new Randonneur();
       try{
                   PreparedStatement ps = cs.getCnx().prepareStatement(query);
                    ps.setString(1, login);
              ResultSet rs = ps.executeQuery();
              
            rs.next();
                     p =new Randonneur(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getDate(10),rs.getInt(16),rs.getString(11));
              
       }catch(SQLException e){
           System.err.println(e.getMessage());
           return null;
       }
              return p;
    }
    public ArrayList<Randonneur> DisplayAllList() throws SQLException{
        ArrayList<Randonneur> list=new ArrayList<>();
        String req="select * from utilisateur where roles='a:1:{i:0;s:10:\"ROLE_ADMIN\";}'";
      rs= st.executeQuery(req);
     while (rs.next()){
        Randonneur per=new Randonneur(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getDate(10),rs.getInt(16),rs.getString(11));
         list.add(per);
     }
     return list;
    }
//    public List<Randonneur> afficher() throws SQLException{
//        ArrayList<Randonneur> list=new ArrayList<>();
//        String req="select * from utilisateur where role='randonneur'";
//      rs= st.executeQuery(req);
//     Randonneur per=null;
//     while (rs.next()){
//         per=new Randonneur(rs.getString("nom"), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(15));
//         list.add(per);
//     }
//     return list;
//    }
    public ArrayList<Randonneur> RechercherList(String Recherche) throws SQLException{
        ArrayList<Randonneur> list=new ArrayList<>();
        String req="select * from utilisateur  where roles='a:1:{i:0;s:15:\"ROLE_RANDONNEUR\";}'and concat (`nom`, `prenom`, `num_tel`,`cin`,`email`,`username`,`roles`,`birthday`) like '%"+Recherche+"%' ";
     //System.out.println(req);
        rs= st.executeQuery(req);
     while (rs.next()){
        Randonneur per=new Randonneur(rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getDate(10),rs.getInt(16),rs.getString(11));
         list.add(per);
     }
     return list;
    }

    
//    public Randonneur findClientByLogin (String login){
//      Randonneur randonneur =null;
//        String requete = "select nom,prenom,mail from utilisateur where user_name='"+login+"'";
//        try {
//           Statement statement =  conn.createStatement();
//            ResultSet resultat = statement.executeQuery(requete);
//            while(resultat.next()){
//              randonneur  = new  Randonneur();
//               randonneur.setNom(resultat.getString(1));
//               randonneur.setPrenom(resultat.getString(2));
//               randonneur.setMail(resultat.getString(3));
//
//            }
//        } catch (SQLException ex) {
//           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("erreur lors du chargement des client "+ex.getMessage());
//        }
//        return randonneur;
//        
//    }

   public void excel() throws SQLException, IOException{
       
      String req="select * from utilisateur  where roles='a:1:{i:0;s:15:\"ROLE_RANDONNEUR\";}'";
     //System.out.println(req);
        rs= st.executeQuery(req);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook
                .createSheet("utilisateur");
        XSSFRow row = spreadsheet.createRow(1);
        XSSFCell cell;
        cell = row.createCell(1);
        cell.setCellValue("nom");
        cell = row.createCell(2);
        cell.setCellValue("prenom");
        cell = row.createCell(3);
        cell.setCellValue("num_tel");
        cell = row.createCell(4);
        cell.setCellValue("CIN");
        cell = row.createCell(5);
        cell.setCellValue("email");
        cell = row.createCell(6);
        cell.setCellValue("username");
        cell = row.createCell(7);
        cell.setCellValue("pt_fidel");
        cell = row.createCell(8);
        int i = 2;
        while (rs.next()) {
            row = spreadsheet.createRow(i);
            cell = row.createCell(1);
            cell.setCellValue(rs.getString("nom"));
            cell = row.createCell(2);
            cell.setCellValue(rs.getString("prenom"));
            cell = row.createCell(3);
            cell.setCellValue(rs.getString("num_tel"));
            cell = row.createCell(4);
            cell.setCellValue(rs.getString("CIN"));
            cell = row.createCell(5);
            cell.setCellValue(rs.getString("email"));
             cell = row.createCell(6);
            cell.setCellValue(rs.getString("username"));
              cell = row.createCell(7);
            cell.setCellValue(rs.getString("pt_fidel"));
            i++;
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(
                    new File("exceldatabase.xlsx"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServiceRandonneur.class.getName()).log(Level.SEVERE, null, ex);
        }
        workbook.write(out);
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ServiceRandonneur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   } 
   
 
}


