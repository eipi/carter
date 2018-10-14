package name.eipi.carter.entity.arcar;

import name.eipi.carter.entity.Report;
import name.eipi.carter.logic.NewInput;
import name.eipi.carter.persistence.DataFileSystemManager;
import name.eipi.carter.persistence.IDataManager;

import javax.faces.bean.*;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Damien on 06/09/2016.
 */
@ManagedBean(name="arcarReport")
@ApplicationScoped
public class ArcarReport implements Report<ArcarLineItem>, Serializable {

    public static final long serialVersionUID  = 1l;

    public static final String Error_Create_Date = "Error Create Date";
    public static final String QPS_Policy_Number = "QPS Policy Number";
    public static final String Error_Message = "Error Message";
    public static final String Communication_Status = "Communication Status";
    public static final String QPS_Renewal_Status = "QPS Renewal Status";
    public static final String QPS_Renewal_Case_ID = "QPS Renewal Case ID";
    public static final String QPS_Policy_Name = "QPS Policy Name";
    public static final String QPS_Benefit_Name = "QPS Benefit Name";
    public static final String QPS_Renewal_Date = "QPS Renewal Date";
    public static final String QPS_Rate_Group_Name = "QPS Rate Group Name";
    public static final String Rate_Group_ID = "Rate Group ID";
    private static final DateFormat dateFormatIn = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
    private static final DateFormat dateFormatShort = new SimpleDateFormat("M/d/yyyy");

    private String lastReportDate;

    public String getLastReportDate() {
        return lastReportDate;
    }

    public void setLastReportDate(String lastReportDate) {
        this.lastReportDate = lastReportDate;
    }

    Collection<ArcarLineItem> database;

    public ArcarReport() {
        this(null);
    }

    public ArcarReport(final Collection<ArcarLineItem> dataIn) {
        if (dataIn != null) {
            database = dataIn;
        } else {
            database = new ArrayList<>();
        }
    }

    public Collection<ArcarLineItem> getData() {
        return database;
    }

    public ArcarLineItem getLineItem(Integer policyNumber) {
        for (ArcarLineItem arcarLineItem : database) {
            if (arcarLineItem.getPolicyNumber().equals(policyNumber)) {
                return arcarLineItem;
            }
        }
        return null;
    }
//
//    private Part file; // +getter+setter
//
//    public void save() {
//        try (InputStream input = file.getInputStream()) {
//            Files.copy(input, new File(uploads, filename).toPath());
//        }
//        catch (IOException e) {
//            // Show faces message?
//        }
//    }

    @Override
    public void add(final Map<String, String> data) throws ParseException {
        ArcarLineItem arcarLineItem = new ArcarLineItem();

        Integer policyNumber = Double.valueOf((data.get(QPS_Policy_Number))).intValue();
        arcarLineItem.setPolicyNumber(policyNumber);

        arcarLineItem.setRenewalDate(dateFormatIn.parse(data.get(QPS_Renewal_Date)));

        arcarLineItem.addBenefit(data.get(QPS_Benefit_Name));

        arcarLineItem.addErrorMessage(data.get(Error_Message));

        arcarLineItem.setAdded(dateFormatIn.parse(data.get(Error_Create_Date)));

        // Assume new unless find match
        arcarLineItem.setNew(true);

        for (ArcarLineItem lineItem : database) {
            if (lineItem.equals(arcarLineItem)) {
                lineItem.setResolved(false);
                arcarLineItem.setNew(false);
                boolean hasChanges = merge(lineItem, arcarLineItem);
                lineItem.setUpdated(!lineItem.isNew() && hasChanges);
            }
        }

        if (arcarLineItem.isNew()) {
            database.add(arcarLineItem);
        }
    }

    private boolean merge(ArcarLineItem existingLineItem, ArcarLineItem newLineItem) {
        boolean hasChanges = false;

        ArcarBenefit benefit = newLineItem.getBenefits().iterator().next();
        if (!existingLineItem.getBenefits().contains(benefit)) {
            existingLineItem.addBenefit(benefit.getBenefitName());
            hasChanges = true;
        }

        ArcarError errorMessage = newLineItem.getErrorMessages().iterator().next();
        if (!existingLineItem.getErrorMessages().contains(errorMessage)) {
            existingLineItem.addErrorMessage(errorMessage.getErrorText());
            hasChanges = true;
        }
        return hasChanges;
    }

    @Override
    public void loadData() throws Exception {
        IDataManager<ArcarReport> dataManager = new DataFileSystemManager<>();
        try {
            ArcarReport arcarReport = dataManager.read(ArcarReport.class);
            this.database = arcarReport.getData();
            this.lastReportDate = arcarReport.getLastReportDate();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void saveData() throws Exception {
        IDataManager<ArcarReport> dataManager = new DataFileSystemManager<>();
        dataManager.write(this);

    }

    public void processUploadedFile(InputStream inputStream) throws Exception {
        NewInput<ArcarReport> newInput = new NewInput<>();
        newInput.processExcel(this, inputStream);
    }

    @Override
    public void rebaseline(String date) throws Exception {
        for (ArcarLineItem existingLineItem : database) {
            existingLineItem.setNew(false);
            existingLineItem.setUpdated(false);
            // assume resolved unless still exists in current report.
            existingLineItem.setResolved(true);
            existingLineItem.setResolvedDate(dateFormatShort.parse(date));
            //
            existingLineItem.getBenefits().forEach(benefit -> {
                benefit.setNew(false);
            });
            existingLineItem.getErrorMessages().forEach(error -> {
                error.setNew(false);
            });
            setLastReportDate(date);
        }
    }
}
