package name.eipi.carter.entity;

import name.eipi.carter.util.DateUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Damien on 07/09/2016.
 */
@ManagedBean(name = "noteBean")
@ViewScoped
public class Note implements Serializable {

    public static final long serialVersionUID  = 1l;

    private String text;

    private String author;

    private Date added;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getAdded() {
        return added;
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public String toString() {
        return author + " @ " + DateUtils.format(added) + "\r\n" + text;
    }
}
