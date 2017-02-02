/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdrental;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author tawashy
 */
@Named(value = "actorController")
@SessionScoped
public class ActorController implements Serializable {

    // these fields map directlu to components in the actor.xhtml
    String firstName;
    String lastName;
    String response;
    
    // this is our class that uses Hibernare tp quert the actor table
    ActorHelper helper;
    
    
    // this is our Actor POJO
    Actor actor;
    /**
     * Creates a new instance of ActorController
     */
    public ActorController() {
        helper = new ActorHelper();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getResponse() {
        
        if (firstName != null && lastName != null) {
            
            // getting the current date in sql format
            Date date = new Date();
            Timestamp timeStamp = new Timestamp(date.getTime());
            
            // initializing an actor
            actor = new Actor(firstName, lastName, timeStamp);
            
            //calling our helper that inserts a row into the actor table
            if (helper.InsertActor(actor) == 1 ){
                // insert was successful
                firstName = null;
                lastName = null;
                response = "Actor Added.";
                return response;
            } else {
                // inser failed
                firstName = null;
                lastName = null;
                response = "Actor Not Added.";
            }
        } else {
            // don't dis[lay a message when the user hasn't input 
            // a first and last name
            response = " ";
            return response;
        }
        
    }

    public void setResponse(String response) {
        this.response = response;
    }
    
}
