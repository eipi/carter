package name.eipi.carter.services;

import name.eipi.carter.entity.Report;


/**
 * Created by Damien on 06/09/2016.
 */
public interface UserService<T extends Report> {

    T getLiveReportData() throws Exception;

    T getHistoricReportData() throws Exception;

}
