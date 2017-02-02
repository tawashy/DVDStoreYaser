/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dvdrental;

import org.hibernate.SQLQuery;
import org.hibernate.Session;


/**
 *
 * @author tawashy
 */
public class ActorHelper {
    
    Session session = null;
    
    public ActorHelper(){
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public int InsertActor(Actor a){
        int result = 0;
        
        String sql = "insert into actor(first_name, last_name, last_update) " 
                + "values (:fName, :lName, :update)";
        
        try {
            // starting a transaction if on wisn't active
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            
            // creating an actul query that can be executed
            SQLQuery q = session.createSQLQuery(sql);
            //associating our Avtor POJO and table with the query 
            q.addEntity(Actor.class);
            
            // binding values to the placeholders in the query
            q.setParameter("fName", a.getFirstName);
            q.setParameter("lName", a.getLastName);
            q.setParameter("update", a.getLastUpdate);
            
            // executing the query 
            result = q.executeUpdate();
            
            // commiting the query to the database
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
}
