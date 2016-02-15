package xyz.acmer.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hypo on 16-2-15.
 */
public class StringHelper {

    public String getCurrentDateTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(date);
    }
}
