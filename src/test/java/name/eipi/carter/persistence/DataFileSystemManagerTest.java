package name.eipi.carter.persistence;

import name.eipi.carter.entity.TestLineItem;
import name.eipi.carter.entity.TestReport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Damien on 08/09/2016.
 */
public class DataFileSystemManagerTest {

    @Test
    public void writeSomeData() throws Exception {
        Collection<TestLineItem> database = new ArrayList<>();
        TestLineItem testLineItem = new TestLineItem();
        database.add(testLineItem);
        TestReport testReport = new TestReport();
        testReport.add(null);
        IDataManager<TestReport> testReportDataManager = new DataFileSystemManager<>();
        testReportDataManager.write(testReport);
    }

    @Test
    public void readSomeData() throws Exception {
        IDataManager<TestReport> testReportDataManager = new DataFileSystemManager<>();
        TestReport testReport = testReportDataManager.read(TestReport.class);
    }


}
