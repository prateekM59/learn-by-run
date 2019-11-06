package mahajan.prateek;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * Created by: pramahajan on 7/11/19 1:48 PM GMT+05:30
 *
 * Refer:
 *
 * https://www.baeldung.com/java-8-date-time-intro
 * https://www.baeldung.com/java-zone-offset
 *
 * https://howtodoinjava.com/java/date-time/java8-datetimeformatter-example/
 * https://www.baeldung.com/java-datetimeformatter
 * {@link DateTimeFormatter javadoc}
 *
 */

@RunWith(JUnit4.class)
public class JavaDateTimeTest {

    @Test
    public void obtain_ZonedDatetime_from_fixed_LocalDateTime() throws Exception {
        // LocalDateTime has no zone - it is local to system on which code is running
        LocalDateTime someDateTime = LocalDateTime.of(2018, 7, 17, 13, 5);

        // Convert LocalDateTime to ZonedDateTime

        ZoneId zoneIdKolkata = ZoneId.of("Asia/Kolkata");
        ZonedDateTime kolkataDateTimeWithAGivenDateTimeValue = ZonedDateTime.of(someDateTime, zoneIdKolkata);

        ZoneId zoneIdLondon = ZoneId.of("Europe/London");
        ZonedDateTime londonDateTimeWithAGivenDateTimeValue = ZonedDateTime.of(someDateTime, zoneIdLondon);

        System.out.println("July 17, 2018 13:05PM (without any zone): " + someDateTime);
        System.out.println("July 17, 2018 13:05PM in Kolkata: " + kolkataDateTimeWithAGivenDateTimeValue);
        System.out.println("July 17, 2018 13:05PM in London: " + londonDateTimeWithAGivenDateTimeValue);
        System.out.println("\nNotice how a single fixed LocalDateTime is converted to multiple ZonedDateTime instances");
        System.out.println("\nUSAGE - Use this when you need a fixed clock in some zone e.g. July 17, 2018 13:05PM in London !!!");

    }

    @Test
    public void now_in_ZonedDateTime_vs_now_in_LocalDateTime() throws Exception {
        // LocalDateTime has no zone - it is local to system on which code is running
        LocalDateTime localDateTime = LocalDateTime.now();

        // Convert LocalDateTime to ZonedDateTime

        ZoneId zoneIdLondon = ZoneId.of("Europe/London");
        ZonedDateTime londonDateTimeFromFixedCurrentLocalDatetime = ZonedDateTime.of(localDateTime, zoneIdLondon);

        ZonedDateTime currentDateTimeInLondon = ZonedDateTime.now(zoneIdLondon);

        System.out.println("Current LocalDateTime (without any zone): " + localDateTime);
        System.out.println("\nEurope/London ZonedDateTime obtained from fixed LocalDateTime: " + londonDateTimeFromFixedCurrentLocalDatetime + " -- This is same value of given LocalDateTime but a zone of London is appended. USAGE - Use this when you need a fixed clock in some zone e.g. July 17, 2018 13:05PM in London !!!");
        System.out.println("\n\nCurrent ZonedDateTime in Zone of Europe/London: " + currentDateTimeInLondon + " -- This is the current time in London zone and would be shown in a clock placed in London. USAGE - When you need current datetime in London");

    }

    @Test
    public void all_zoneIds() {
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds(); // List all ZoneIds in java.time
        System.out.println(allZoneIds);
    }


    @Test
    public void ZoneOffset_from_ZoneId() { // ZoneOffset extends ZoneId
        ZoneId zoneIdLondon = ZoneId.of("Europe/London");
        ZoneOffset zoneOffsetLondon = zoneIdLondon.getRules().getOffset(LocalDateTime.now()); // to take into account DST, we need to specify LocalDateTime here

        System.out.println("zoneIdLondon: "+ zoneIdLondon);
        System.out.println("zoneOffsetLondon: "+ zoneOffsetLondon);
    }

    @Test
    public void ZonedDateTime_with_ZoneOffset() {  // ZoneOffset extends ZoneId
        ZoneOffset zoneOffsetLondon = ZoneOffset.of("+01:00");
        System.out.println("zoneOffsetLondon: "+ zoneOffsetLondon);

        ZonedDateTime currentDateTimeInLondon = ZonedDateTime.now(zoneOffsetLondon);
        System.out.println("currentDateTimeInLondon: "+ currentDateTimeInLondon);
    }

    @Test
    public void ZonedDateTime_with_ZoneId() {  // ZoneOffset extends ZoneId
        ZoneId zoneIdLondon = ZoneId.of("Europe/London");

        ZonedDateTime currentDateTimeInLondon = ZonedDateTime.now(zoneIdLondon);
        System.out.println("currentDateTimeInLondon: "+ currentDateTimeInLondon);
    }

    @Test
    /**
     * Refer https://howtodoinjava.com/java/date-time/java8-datetimeformatter-example/
     * Refer https://www.baeldung.com/java-datetimeformatter
     * Refer {@link DateTimeFormatter javadoc}
     */
    public void DateTimeFormatter() {
        LocalDateTime localDateTime = LocalDateTime.parse("Sat, Jul 13, 2019 22:37", DateTimeFormatter.ofPattern("E, MMM dd, yyyy HH:mm"));
        System.out.println("parsed localDateTime: "+ localDateTime);


        //ZonedDateTime zonedDateTime = ZonedDateTime.parse("Sat, Jul 13, 2019 22:37", DateTimeFormatter.ofPattern("E, MMM dd, yyyy HH:mm")); //error because of no time zone info in "Sat, Jul 13, 2019 22:37", so can't be parsed into ZonedDateTime

        ZonedDateTime zonedDateTime2 = ZonedDateTime.parse("Sat, Jul 13, 2019 22:37 GMT+0100", DateTimeFormatter.ofPattern("E, MMM dd, yyyy HH:mm 'GMT'ZZ"));
        System.out.println("parsed zonedDateTime2: "+ zonedDateTime2);

        ZonedDateTime zonedDateTime3 = ZonedDateTime.parse("Sat, Jul 13, 2019 22:37 +0100", DateTimeFormatter.ofPattern("E, MMM dd, yyyy HH:mm ZZ"));
        System.out.println("parsed zonedDateTime3: "+ zonedDateTime3);

        ZonedDateTime zonedDateTime4 = ZonedDateTime.parse("Sat, Jul 13, 2019 22:37 +01:00", DateTimeFormatter.ofPattern("E, MMM dd, yyyy HH:mm z"));
        System.out.println("parsed zonedDateTime4: "+ zonedDateTime4);

//        LocalDate zonedDateTime5 = LocalDate.parse("2019-03-31", DateTimeFormatter.ofPattern("MMM dd, yyyy")); // error because given format does not match given date formate to parse

    }


}