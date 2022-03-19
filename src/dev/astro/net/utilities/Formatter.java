package dev.astro.net.utilities;

import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;

import java.text.DecimalFormat;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class Formatter {

    private static long MINUTE = TimeUnit.MINUTES.toMillis(1L);
    private static long HOUR = TimeUnit.HOURS.toMillis(1L);

    public static String getRemaining(long millis, boolean milliseconds) {
        return getRemaining(millis, milliseconds, true);
    }

    public static String getRemaining(long duration, boolean milliseconds, boolean trail) {
        if (milliseconds && duration < MINUTE) {
            return (trail ? REMAINING_SECONDS_TRAILING : REMAINING_SECONDS).get().format(duration * 0.001) + 's';
        } else {
            return DurationFormatUtils.formatDuration(duration, (duration >= HOUR ? "HH:" : "") + "mm:ss");
        }
    }

    public static TimeZone SERVER_TIME_ZONE = TimeZone.getTimeZone("EST");
    public static ZoneId SERVER_ZONE_ID = SERVER_TIME_ZONE.toZoneId();
    public static FastDateFormat DAY_MTH_HR_MIN_SECS = FastDateFormat.getInstance("dd/MM HH:mm:ss", SERVER_TIME_ZONE, Locale.ENGLISH);
    public static FastDateFormat DAY_MTH_YR_HR_MIN_AMPM = FastDateFormat.getInstance("dd/MM/yy hh:mma", SERVER_TIME_ZONE, Locale.ENGLISH);
    public static FastDateFormat DAY_MTH_HR_MIN_AMPM = FastDateFormat.getInstance("dd/MM hh:mma", SERVER_TIME_ZONE, Locale.ENGLISH);
    public static FastDateFormat HR_MIN_AMPM = FastDateFormat.getInstance("hh:mma", SERVER_TIME_ZONE, Locale.ENGLISH);
    public static FastDateFormat HR_MIN_AMPM_TIMEZONE = FastDateFormat.getInstance("hh:mma z", SERVER_TIME_ZONE, Locale.ENGLISH);
    public static FastDateFormat HR_MIN = FastDateFormat.getInstance("hh:mm", SERVER_TIME_ZONE, Locale.ENGLISH);
    public static FastDateFormat MIN_SECS = FastDateFormat.getInstance("mm:ss", SERVER_TIME_ZONE, Locale.ENGLISH);

    public static FastDateFormat KOTH_FORMAT = FastDateFormat.getInstance("m:ss", SERVER_TIME_ZONE, Locale.ENGLISH);

    // The format used to show one decimal without a trailing zero.
    public static ThreadLocal<DecimalFormat> REMAINING_SECONDS = new ThreadLocal<DecimalFormat>() {
        @Override
        protected DecimalFormat initialValue() {
            return new DecimalFormat("0.#");
        }
    };

    public static ThreadLocal<DecimalFormat> REMAINING_SECONDS_TRAILING = new ThreadLocal<DecimalFormat>() {
        @Override
        protected DecimalFormat initialValue() {
            return new DecimalFormat("0.0");
        }
    };
}
