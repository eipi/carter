package name.eipi.carter.entity.arcar;

import java.io.Serializable;

/**
 * Created by Damien on 11/09/2016.
 */
public class ArcarError implements Serializable {

    public static final long serialVersionUID  = 1l;
    
    private String errorText;

    private boolean isNew;

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
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

        ArcarError that = (ArcarError) o;
        return errorText.equals(that.errorText);
    }

    @Override
    public int hashCode() {
        return errorText != null ? errorText.hashCode() : 0;
    }
}
