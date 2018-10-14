package name.eipi.carter.persistence;

import name.eipi.carter.entity.Report;

/**
 * Created by Damien on 06/09/2016.
 */
public interface IDataManager<T extends Report> {

    T read(Class<T> t) throws Exception;

    void write(T data) throws Exception;

}
