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
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author tawashy
 */
@Named(value = "filmActorController")
@SessionScoped
public class FilmActorController implements Serializable {

    // these fields map to the options in the select components of the film.xhtml
    DataModel actorValues;
    DataModel categoryValues;
    DataModel languagesValues;
    
    // this is our helper class that uses Hibernate to query the database
    FilmActorHelper helper;
    
    
    /**
     * Creates a new instance of FilmActorController
     */
    public FilmActorController() {
        helper = new FilmActorHelper();
    }

    public DataModel getActorValues() {
        if( actorValues == null){
            actorValues = new ListDataModel (helper.getActors());
        }
        return actorValues;
    }

    public void setActorValues(DataModel actorValues) {
        this.actorValues = actorValues;
    }

    public DataModel getCategoryValues() {
        if( categoryValues == null){
            categoryValues = new ListDataModel (helper.getCategories());
        }
        return categoryValues;
    }

    public void setCategoryValues(DataModel categoryValues) {
        this.categoryValues = categoryValues;
    }

    public DataModel getLanguagesValues() {
        if( languagesValues == null){
            languagesValues = new ListDataModel (helper.getLanguages());
        }
        return languagesValues;
    }

    public void setLanguagesValues(DataModel languagesValues) {
        this.languagesValues = languagesValues;
    }

    public FilmActorHelper getHelper() {
        return helper;
    }

    public void setHelper(FilmActorHelper helper) {
        this.helper = helper;
    }
    
}
