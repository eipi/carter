package name.eipi.carter.entity.arcar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * Created by Damien on 11/09/2016.
 */
@ManagedBean(name = "arcarSession")
@SessionScoped
public class ArcarSession {

    @ManagedProperty(value = "#{arcarReport}")
    private ArcarReport arcarReport;

    public ArcarReport getArcarReport() {
        return arcarReport;
    }

    public void setArcarReport(ArcarReport arcarReport) {
        this.arcarReport = arcarReport;
    }

    private ArcarLineItem activeLineItem = null;

    public ArcarLineItem getActiveLineItem() {
        return activeLineItem;
    }

    public void setActiveLineItem(ArcarLineItem arcarLineItem) {
        this.activeLineItem = arcarLineItem;
    }

}
