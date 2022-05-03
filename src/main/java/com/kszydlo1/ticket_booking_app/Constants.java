package com.kszydlo1.ticket_booking_app;

public class Constants {
    public static class Controllers {
        public static String EXPIRATION_TIME_CONST = "expirationTimeMinutes";
        public static String NO_SUCH_SCREENING_EXCEPTION = "Exception: There is no such screening";
        public static String BOOKING_STATUS_OK = "ok";
        public static String BOOKING_STATUS_EXCEPTION = "exception";
        public static int NO_BOOKING_TIME_MINUTES_CONST = 15;
        public static int NAME_MIN_LENGTH = 3;

        public static String NO_BOOKING_TIME_EXCEPTION = "Exception: Seats can be booked at latest "
                + NO_BOOKING_TIME_MINUTES_CONST + " before the screening begins";
        public static String NAME_NOT_ACCEPTABLE_EXCEPTION = "Exception: Name not acceptable";
        public static String NO_SEAT_EXCEPTION = "Exception: No seat in request";
        public static String BOOKING_TAKEN_SEAT_EXCEPTION = "Exception: BOOKING_TAKEN_SEAT";
    }
    public static class Views {
        public static String FREE_SEAT_CONST = "free";
        public static String TAKEN_SEAT_CONST = "taken";
        public static String NO_SUCH_SCREENING_EXCEPTION = "Exception: There is no such screening";

        public static String START_END_DATE_EXCEPTION = "Exception: Start date cannot be after end date";

    }
}
