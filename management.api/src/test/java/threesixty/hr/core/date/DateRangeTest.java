package threesixty.hr.core.date;

import java.time.format.DateTimeFormatter;

import com.google.common.truth.Truth;

import org.apache.derby.client.am.DateTime;
import org.junit.jupiter.api.Test;

class DateRangeTest {

    
    @Test
    void dateRangeSequential() {

        DateRange firstDateRange = 
            DateRangeUtility.forHour("2020-10-10T10:00:00", DateTimeFormatter.ISO_DATE_TIME, 2L);

        DateRange secondDateRange = 
            DateRangeUtility.forHour("2020-10-10T12:00:00", DateTimeFormatter.ISO_DATE_TIME, 2L);

        boolean result = firstDateRange.containsExclusive(secondDateRange);

        Truth.assertThat(result).isFalse();
    }
}
