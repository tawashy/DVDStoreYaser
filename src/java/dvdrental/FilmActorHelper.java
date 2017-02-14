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
        
        // this method is going to insert a film into the film table
        private int insertFilm(String title, String description, int langauge,
                String rating, Timestamp timeStamp) {
            
            int result = 0;
            
            String sql = "inser into film"
                    + "(title, description, langaude_id, rental_duration, rental_rate,"
                    + "replacement_cost, rating, last_update)"
                    + "value (:title, :description, :languageId, :rentalDuration,"
                    + ":rentalRate, :replacement_cost, :rating, :update)";
            
            try {
                // if the current transaction isn't active, begin one
                if (!this.session.getTransaction().isActive()){
                    session.beginTransaction();
                }
                
                // Create actual query that will be executed against the database
                SQLQuery q = session.createSQLQuery(sql);
                
                q.addEntity(Film.class);
                
                q.setParameter("title", title);
                q.setParameter("description", description);
                q.setParameter("langaugeId", langauge);
                q.setParameter("rentalDuration", 3);
                q.setParameter("rentalRate", 4.99);
                q.setParameter("replacementCost", 19.99);
                q.setParameter("rating", rating);
                q.setParameter("update", timeStamp);
                
                result = q.executeUpdate();
                
                session.getTransaction().commit();
                
            } catch (Exception e){
                e.printStackTrace();
            }
            
            return result;
            
        }
        
        // this method is going to return the film id of the last film insearted
        // into the film table
        private int getFilmId(){
            List<Film> filmList = null;
            
            String sql = "SELECT * FROM FILM ORDER BY LAST_UPDATE DESC LIMIT 1";
            
            try {
                // if the current transaction isn't active, begin one
                if (!this.session.getTransaction().isActive()){
                    session.beginTransaction();
                }
                
                // Create actual query that will be executed against the database
                SQLQuery q = session.createSQLQuery(sql);
                
                q.addEntity(Film.class);
                

                
                filmList = (List<Film>) q.list();
                
                session.getTransaction().commit();
                
            } catch (Exception e){
                e.printStackTrace();
            }
            
            return filmList.get(0).getFilmId();
            
        }
        
        // this method is going to insert a film actor into the film actor table
        private int insertFilmActor(int actor, int film, Timestamp timeStamp){
            
            int result = 0;
            
            String sql = "INSERT INTO FILM_ACTOR VALUES (:actorId, :filmId, :update)";
            
            try {
                // if the current transaction isn't active, begin one
                if (!this.session.getTransaction().isActive()){
                    session.beginTransaction();
                }
                
                // Create actual query that will be executed against the database
                SQLQuery q = session.createSQLQuery(sql);
                
                q.addEntity(FilmActor.class);
                
                q.setParameter("actorId", actor);
                q.setParameter("filmId", film);
                q.setParameter("update", timeStamp);
                
                result = q.executeUpdate();
                
                session.getTransaction().commit();
                
            } catch (Exception e){
                e.printStackTrace();
            }
            
            return result;
        }
        
        // this method is going to insert a film category into the film category table
        
        private int insertFilmCategory (int film, int category, Timestamp timeStamp) {
            
            int result = 0;
            
            String sql = "INSERT INTO FILM_CATEGORY VALUES (:filmId, :categoryId, :update)";
            
            try {
                // if the current transaction isn't active, begin one
                if (!this.session.getTransaction().isActive()){
                    session.beginTransaction();
                }
                
                // Create actual query that will be executed against the database
                SQLQuery q = session.createSQLQuery(sql);
                
                q.addEntity(FilmActor.class);
                
                q.setParameter("categoryId", category);
                q.setParameter("filmId", film);
                q.setParameter("update", timeStamp);
                
                result = q.executeUpdate();
                
                session.getTransaction().commit();
                
            } catch (Exception e){
                e.printStackTrace();
            }
            
            return result;
        }
        
        public int insert (String title, String description, int actor, int category,
                int language, String rating, Timestamp timeStamp){
            
            int result = 0;
            
            int filmResult = insertFilm(title, description, language, rating, timeStamp);
            int filmId = getFilmId();
            int actorResult = insertFilmActor(actor, filmId, timeStamp);
            int categoryResult = insertFilmCategory(filmId, category, timeStamp);
            
            if (filmResult == 1 && actorResult == 1 && categoryResult == 1){
                result = 1;
            }
            
            return result;
        }
}
