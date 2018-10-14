package name.eipi.carter.entity.arcar;

import java.io.Serializable;

/**
 * Created by Damien on 11/09/2016.
 */
public class ArcarBenefit implements Serializable {

    public static final long serialVersionUID  = 1l;

    private String benefitName;

    private boolean isNew;

    public String getBenefitName() {
        return benefitName;
    }

    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArcarBenefit that = (ArcarBenefit) o;
        return benefitName.equals(that.benefitName);
    }

    @Override
    public int hashCode() {
        return benefitName.hashCode();
    }
}
