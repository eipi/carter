package name.eipi.carter.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Damien on 08/09/2016.
 */
public class TestReport implements Report<TestLineItem>, Serializable {

    Collection<TestLineItem> database;

    public TestReport() {
        this(null);
    }

    public TestReport(final Collection<TestLineItem> dataIn) {
        if (dataIn != null) {
            database = dataIn;
        } else {
            database = new ArrayList<>();
        }
    }

    @Override
    public void add(Map<String, String> data) throws Exception {
        TestLineItem testLineItem = new TestLineItem();
        database.add(testLineItem);
    }

    @Override
    public void loadData() {
        // TODO
    }

    @Override
    public void rebaseline(String s) {
        // TODO
    }

}
