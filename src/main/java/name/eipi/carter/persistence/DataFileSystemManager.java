package name.eipi.carter.persistence;

import name.eipi.carter.entity.Report;

import java.io.*;

/**
 * Created by Damien on 07/09/2016.
 */
public class DataFileSystemManager<T extends Report> implements IDataManager<T> {

    @Override
    public T read(Class<T> t) throws Exception {
        File file = new File("C:\\dev\\" + t.getSimpleName() + ".rtr");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        T objectIn = (T) ois.readObject();
        ois.close();
        fis.close();
        return objectIn;
    }

    @Override
    public void write(T data) throws Exception {
        File file = new File("C:\\dev\\" + data.getClass().getSimpleName() + ".rtr");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(data);
        oos.close();
        fos.close();
    }
}
