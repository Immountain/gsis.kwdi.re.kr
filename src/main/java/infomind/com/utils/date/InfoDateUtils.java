package infomind.com.utils.date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InfoDateUtils extends DateUtils {
    public InfoDateUtils() {
    }

    public static boolean isValidDate(String date, String format) {
        if (date == null) {
            return false;
        } else {
            SimpleDateFormat _format = new SimpleDateFormat(format);
            boolean tmp = _format.isLenient();
            _format.setLenient(false);

            boolean var5;
            try {
                _format.parse(date);
                return true;
            } catch (ParseException var9) {
                var5 = false;
            } finally {
                _format.setLenient(tmp);
            }

            return false;
        }
    }

    public static Date getDate(String date, String format) {
        Date ch = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format, LocaleContextHolder.getLocale());
            ch = sdf.parse(date);
        } catch (Exception ignored) {
            ;
        }

        return ch;
    }

    public static Date getCurrentDate() {
        return Calendar.getInstance(LocaleContextHolder.getLocale()).getTime();
    }

    public static String getCurrentDate(String format) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format, LocaleContextHolder.getLocale());
        return sdf.format(cal.getTime());
    }

    public static String convertToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, LocaleContextHolder.getLocale());
        return sdf.format(date);
    }

    public static String formatDate(String sd, String cf) {
        if (sd == null) {
            sd = "";
        }

        String str = sd.trim().replaceAll("[^\\d]", "");
        String rtn = "";
        String y = "";
        String m = "";
        String d = "";
        if (str.length() == 8) {
            y = str.substring(0, 4);
            if (y.equals("0000")) {
                rtn = "";
            }

            m = str.substring(4, 6);
            if (m.equals("00")) {
                ;
            }

            d = str.substring(6, 8);
            if (d.equals("00")) {
                (new StringBuilder()).append(y).append(cf).append(m).toString();
            }

            rtn = y + cf + m + cf + d;
        } else if (str.length() == 6) {
            y = str.substring(0, 4);
            if (y.equals("0000")) {
                rtn = "";
            }

            m = str.substring(4, 6);
            if (m.equals("00")) {
                ;
            }

            rtn = y + cf + m;
        } else if (str.length() == 4) {
            y = str.substring(0, 4);
            if (y.equals("0000")) {
                rtn = "";
            } else {
                rtn = y;
            }
        }

        return rtn;
    }

    public static String formatTime(String st, String cf) {
        if (st == null) {
            st = "";
        }

        String str = st.trim().replaceAll("[^\\d]", "");
        String rtn = "";
        String h = "";
        String m = "";
        String s = "";
        if (str.length() == 6) {
            h = str.substring(0, 2);
            m = str.substring(2, 4);
            s = str.substring(4, 6);
            rtn = h + cf + m + cf + s;
        } else if (str.length() == 4) {
            h = str.substring(0, 2);
            m = str.substring(2, 4);
            rtn = h + cf + m;
        } else if (str.length() == 2) {
            rtn = str;
        }

        return rtn;
    }

    public static String getDayOfWeek(Date date, boolean korean) {
        String[][] week = new String[][]{{"일", "Sun"}, {"월", "Mon"}, {"화", "Tue"}, {"수", "Wen"}, {"목", "Thu"}, {"금", "Fri"}, {"토", "Sat"}};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return korean ? week[cal.get(7) - 1][0] : week[cal.get(7) - 1][1];
    }

    public static String getDayOfWeek(String date, String format, boolean korean) {
        Date d = getDate(date, format);
        return getDayOfWeek(d, korean);
    }

    public static boolean isLeapYear(String year) {
        if (NumberUtils.isNumber(year)) {
            return false;
        } else {
            int nYear = Integer.parseInt(year);
            return nYear % 4 == 0 && nYear % 100 != 0 || nYear % 400 == 0;
        }
    }

    public static int getDiffDays(String start, String end) {
        Date dateStart = getDate(start, "yyyyMMdd");
        Date dateEnd = getDate(end, "yyyyMMdd");
        long difDays = (dateEnd.getTime() - dateStart.getTime()) / 86400000L;
        Double diff = Double.valueOf(Math.ceil((double) difDays));
        return diff.intValue();
    }

    public static double getDiffMonths(String start, String end) {
        double diffMonths = 0.0D;
        String startYear = start.substring(0, 4);
        String endYear = end.substring(0, 4);
        int startMonth = Integer.parseInt(start.substring(4, 6)) - 1;
        int endMonth = Integer.parseInt(end.substring(4, 6)) - 1;
        String startDay = start.substring(6, 8);
        String endDay = end.substring(6, 8);
        int i;
        if (Integer.parseInt(startYear) < Integer.parseInt(endYear)) {
            String newStart;
            if (startMonth > endMonth) {
                String newEnd = startYear + "1231";
                newStart = endYear + "0101";
                diffMonths = getDiffMonths(start, newEnd) + getDiffMonths(newStart, end);
                diffMonths = Double.parseDouble(String.format("%.2f", new Object[]{Double.valueOf(diffMonths)}));
            } else {
                int formMonth = startMonth + 1;
                newStart = endYear + "" + StringUtils.leftPad(String.valueOf(formMonth), 2) + "" + startDay;
                i = (Integer.parseInt(endYear) - Integer.parseInt(startYear)) * 12;
                diffMonths = (double) i + getDiffMonths(newStart, end);
                diffMonths = Double.parseDouble(String.format("%.2f", new Object[]{Double.valueOf(diffMonths)}));
            }
        } else {
            int[] diffDaysOnMonth = new int[]{1, -2, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1};
            int diffDaysTotal = getDiffDays(start, end);

            for (i = startMonth; i < endMonth; ++i) {
                if (i == 1 && isLeapYear(startYear)) {
                    diffDaysTotal -= diffDaysOnMonth[i] + 1;
                } else {
                    diffDaysTotal -= diffDaysOnMonth[i];
                }
            }

            diffMonths = (double) diffDaysTotal / 30.0D;
            diffMonths = Double.parseDouble(String.format("%.2f", new Object[]{Double.valueOf(diffMonths)}));
        }

        System.out.println("- run getDifMonths()\n- " + start + " ~ " + end + " => " + diffMonths);
        return diffMonths;
    }
}