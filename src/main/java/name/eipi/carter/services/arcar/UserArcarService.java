package name.eipi.carter.services.arcar;

import name.eipi.carter.entity.arcar.ArcarReport;
import name.eipi.carter.persistence.DataFileSystemManager;
import name.eipi.carter.persistence.IDataManager;
import name.eipi.carter.services.UserService;


/**
 * Created by Damien on 06/09/2016.
 */
public class UserArcarService implements UserService<ArcarReport> {

    private static final IDataManager<ArcarReport> dataManager = new DataFileSystemManager<>();

    public ArcarReport getLiveReportData() throws Exception {
        return getAllData();
    }

    public ArcarReport getHistoricReportData() throws Exception {
        return getAllData();
    }

    private ArcarReport getAllData() throws Exception {
        ArcarReport allData = dataManager.read(ArcarReport.class);
        return allData;
    }

}

