package name.eipi.carter.services;

import name.eipi.carter.entity.Note;
import name.eipi.carter.entity.arcar.ArcarLineItem;
import name.eipi.carter.entity.arcar.ArcarReport;
import name.eipi.carter.entity.arcar.ArcarSession;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 * Created by Damien on 08/09/2016.
 */
@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {

    public static final long serialVersionUID = 1L;

    public String moveToHomePage(){
            return "home";
        }

    public String openPolicy(final ArcarSession arcarSession, final ArcarLineItem arcarLineItem){
            arcarSession.setActiveLineItem(arcarLineItem);
            return "policy";
        }

    public String attachNote(final ArcarReport arcarReport, final ArcarLineItem activeLineItem, final Note note) throws Exception {
        note.setAdded(new Date());
        activeLineItem.addNote(note);
        arcarReport.saveData();
        return "policy";
    }

    public String processUploadedFile(final ArcarReport arcarReport, DataController dataController) throws Exception {
        InputStream is = dataController.getUploadedFile().getInputStream();
        arcarReport.processUploadedFile(is);
        is.close();
        arcarReport.saveData();
        return "home";
    }


    public String save(final ArcarReport arcarReport) throws Exception {
        arcarReport.saveData();
        return "home";
    }

}
