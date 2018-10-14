package name.eipi.carter.entity;

import java.util.Map;

/**
 * Created by Damien on 06/09/2016.
 */
public interface Report<V extends LineItem> {

    void add(Map<String, String> data) throws Exception;

    void loadData() throws Exception;

    void rebaseline(String date) throws Exception;
}
