package pmhealthcare.hatinroo.datamart.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static LocalDate convertDate(String sDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTime = LocalDate.parse(sDate, formatter);
        return dateTime;
    }


}
