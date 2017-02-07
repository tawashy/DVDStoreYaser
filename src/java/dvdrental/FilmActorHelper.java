/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdrental;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author tawashy
 */

public class FilmActorHelper {
    Session session = null;
    
    public FilmActorHelper(){
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public List getActors(){
        List<Actor> actorList = null;
        
        String sql = "SELECT * FROM ACTOR";
        
        try {
            
            if(!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            SQLQuery q = session.createSQLQuery(sql);
            
            q.addEntity(Actor.class);
            
            actorList = (List<Actor>) q.list();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actorList;
    }
    
    public List getCategories(){
        
        //setting up local variable that will be used to return 
        // a list of languages
        List<Category> categoryList = null;
        
        String sql = "SELECT * FROM CATEGORY";
        
        try {
            // if the current transaction isn't active, begin one
            if(!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            // creating actual query that will be executed against the database
            SQLQuery q = session.createSQLQuery(sql);
            
            // associating the category POJO and table with the query 
            q.addEntity(Category.class);
            
            // executing the query 
            categoryList = (List<Category>) q.list();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryList;
    }
    
    
        public List getLanguages(){
        List<Language> languageList = null;
        
        String sql = "SELECT * FROM LANGUAGE";
        
        try {
            
            if(!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            SQLQuery q = session.createSQLQuery(sql);
            
            q.addEntity(Language.class);
            
            languageList = (List<Language>) q.list();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return languageList;
    }
}
