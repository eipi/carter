package name.eipi.carter.services;

import name.eipi.carter.entity.arcar.ArcarReport;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Created by Damien on 10/09/2016.
 */
@ManagedBean(name = "dataController", eager = true)
@ApplicationScoped
public class DataController {

    private UploadedFile uploadedFile;

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void loadData(ArcarReport arcarReport) throws Exception {
        arcarReport.loadData();
    }

    public void saveData(ArcarReport arcarReport) throws Exception {
        arcarReport.saveData();
    }

}
