package name.eipi.carter.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Damien on 11/09/2016.
 */
public class DateUtils {


    private static final DateFormat dateFormatOut = new SimpleDateFormat("MM/dd/yyyy");

    public static String format(Date date) {
        return dateFormatOut.format(date);
    }

}
