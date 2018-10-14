package name.eipi.carter.entity.arcar;

import name.eipi.carter.entity.LineItem;
import name.eipi.carter.entity.Note;
import name.eipi.carter.util.DateUtils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Damien on 07/09/2016.
 */
public class ArcarLineItem implements LineItem, Serializable {

    public static final long serialVersionUID  = 1l;

    private String assignee;

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    private Integer policyNumber;

    private Date renewalDate;

    private List<ArcarBenefit> benefits;

    private List<ArcarError> errorMessages;

    private List<Note> notes;

    private Date added;

    private Date resolvedDate;

    private boolean isNew;

    private boolean isUpdated;

    private boolean isResolved;

    public boolean isResolved() {
        return isResolved;
    }

    public void setResolved(boolean isResolved) {
        this.isResolved = isResolved;
    }

    public ArcarLineItem() {
        benefits = new ArrayList<>();
        errorMessages = new ArrayList<>();
        notes = new ArrayList<>();
    }

    public boolean isNew() {

        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    public Integer getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(Integer policyNumber) {
        this.policyNumber = policyNumber;
    }

    public List<ArcarBenefit> getBenefits() {
        return benefits;
    }

    public void addBenefit(String benefit) {
        ArcarBenefit arcarBenefit = new ArcarBenefit();
        arcarBenefit.setBenefitName(benefit);
        arcarBenefit.setNew(true);
        benefits.add(arcarBenefit);
    }

    public List<ArcarError> getErrorMessages() {
        return errorMessages;
    }

    public void addErrorMessage(String errorMessage) {
        ArcarError arcarError = new ArcarError();
        arcarError.setErrorText(errorMessage);
        arcarError.setNew(true);
        errorMessages.add(arcarError);
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public Date getAdded() {
        return added;
    }

    public String getAddedString() {
        return DateUtils.format(added);
    }

    public void setAdded(Date added) {
        this.added = added;
    }

    public Date getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(Date resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public int hashCode() {
        return policyNumber.hashCode() + renewalDate.hashCode();
    }

    public boolean equals(Object o) {
        if (o instanceof ArcarLineItem) {
            ArcarLineItem other = (ArcarLineItem) o;
            return ((other.getPolicyNumber().equals(this.policyNumber))
                    && (other.getRenewalDate().equals(this.renewalDate)));
        }
        return false;
    }

    public Integer daysOnReport() {
        return getDayDiff(this.added, isResolved ? resolvedDate : new Date());
    }

    public String status() {
        return isResolved ? ("Resolved on " + DateUtils.format(resolvedDate)) : "Unresolved " + daysOnReport() + " days";
    }

    /**
     * Get a diff between two dates
     * @param date1 the oldest date
     * @param date2 the newest date
     * @return the diff value, in the provided unit
     */
    private Integer getDayDiff(Date date1, Date date2) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return Long.valueOf(TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS)).intValue();
    }

}
