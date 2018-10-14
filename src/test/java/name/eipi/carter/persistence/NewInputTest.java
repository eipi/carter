package name.eipi.carter.persistence;

import name.eipi.carter.entity.Report;
import name.eipi.carter.entity.TestLineItem;
import name.eipi.carter.entity.arcar.ArcarReport;
import name.eipi.carter.logic.NewInput;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Damien on 07/09/2016.
 */
public class NewInputTest {

    @Test
    public void processExcel() throws Exception {

        String testFilePath = "C:\\dev\\sample.xls";

        NewInput newInput = new NewInput();

        Report report = new ArcarReport();
        newInput.processExcel(report, testFilePath);
        newInput.processExcel(report, testFilePath);

    }

}
