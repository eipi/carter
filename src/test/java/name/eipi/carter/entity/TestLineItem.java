package name.eipi.carter.entity;

import java.io.Serializable;

/**
 * Created by Damien on 08/09/2016.
 */
public class TestLineItem implements LineItem, Serializable {

    private String key = "KEY";

    private String value = "VALUE";

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
