package com.salary.common.utils;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeHelper {


        //时间格式化
        private final static SimpleDateFormat simpleDateFormat_yyyy_MM_dd_HH_ss_mm = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");

        //时间格式化
        private final static SimpleDateFormat simpleDateFormat_yyyyMMdd = new SimpleDateFormat("yyyyMMdd");

        //时间格式化
        private final static SimpleDateFormat simpleDateFormat_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");

        //时间格式化
        private final static SimpleDateFormat simpleDateFormat_yyyy_MM = new SimpleDateFormat("yyyy-MM");

        //时间格式化
        private final static SimpleDateFormat simpleDateFormat_yyyyMM = new SimpleDateFormat("yyyyMM");

        //时间格式化
        private final static SimpleDateFormat simpleDateFormat_yyyy_MM_dd_HH_ss_mm_SSS = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm,SSS");

        //时间正则表达式,匹配yyyy-mm-dd HH:mm:ss,SSS时间的格式
        private static final String TIME_REG1 = "^\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2},\\d{3}.*$";

        //时间正则表达式,匹配yyyy-mm-dd HH:mm:ss时间的格式
        private static final String TIME_REG2 = "^\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}.*$";

        // 得到一个Calendar的实例
        private static Calendar CALENDAR = Calendar.getInstance();

        //解析日志时间
        private static Timestamp parseTime ;

        /**
         * 时间格式 yyyyMM
         * date转string
         * */
        public static String date2String_yyyyMM(Date date){
            if(date==null) return null;
            return simpleDateFormat_yyyyMM.format(date);
        }

        /**
         * 时间格式 yyyy-MM-dd
         * string转date
         * */
        public static Date string2Date_yyyy_MM_dd(String str){
            if(str==null) return null;
            try {
                return simpleDateFormat_yyyy_MM_dd.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;

        }

        /**
         * 时间格式 yyyy-MM
         * string转date
         * */
        public static Date string2Date_yyyy_MM(String str){
            if(str==null) return null;
            try {
                return simpleDateFormat_yyyy_MM.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;

        }

        /**
         * 时间格式 yyyyMMdd
         * string转date
         * */
        public static Date string2Date_yyyyMMdd(String str){
            if(str==null) return null;
            try {
                return simpleDateFormat_yyyyMMdd.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;

        }

        /**
         * 时间格式 yyyyMM
         * string转date
         * */
        public static Date string2Date_yyyyMM(String str){
            if(str==null) return null;
            try {
                return simpleDateFormat_yyyyMM.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;

        }

        /**
         * 时间格式 yyyy-MM
         * date转string
         * */
        public static String date2String_yyyy_MM(Date date){
            if(date==null) return null;
            return simpleDateFormat_yyyy_MM.format(date);
        }

        /**
         * 时间格式 yyyy-MM-dd
         * date转string
         * */
        public static String date2String_yyyy_MM_dd(Date date){
            if(date==null) return null;
            return simpleDateFormat_yyyy_MM_dd.format(date);
        }

        /**
         * 时间格式 yyyyMMdd
         * date转string
         * */
        public static String date2String_yyyyMMdd(Date date){
            if(date==null) return null;
            return simpleDateFormat_yyyyMMdd.format(date);
        }

        //返回当前时间(毫秒)
        public static long getNowTime_long(){
            long time = new Date().getTime();
            return time;
        }

        /**
         * 得到当前时间
         * */
        public static String getNowTime_yyyy_MM_dd_HH_ss_mm(){
            return simpleDateFormat_yyyy_MM_dd_HH_ss_mm.format(new Date());
        }

        /**
         * 得到当前时间
         * */
        public static Timestamp  getNowTime_timestamp(){
            return new Timestamp(new Date().getTime());
        }

        //返回下一个时间(毫秒)
        public static long getNextTime(long interval){
            long time = new Date().getTime();
            if(interval < 0){
                return time;
            }
            return time + interval;
        }





        /**
         * 将Timestamp转为string( 2017-02-14 21:57:30 格式)
         * */
        public static String timestamp2Str_yyyy_MM_dd_HH_ss_mm(long timestamp){
            return simpleDateFormat_yyyy_MM_dd_HH_ss_mm.format(new Timestamp(timestamp) );
        }


        /**
         * 将Timestamp转为string( 20170214格式)
         * */
        public static String timestamp2Str2_yyyyMMdd(long timestamp){
            return simpleDateFormat_yyyyMMdd.format(new Timestamp(timestamp) );
        }

        /**
         * 将long转为Timestamp
         * */
        public static Timestamp long2timestamp(long _long){
            return new Timestamp(_long);
        }



        /**
         * string转Timestamp
         * 时间格式 yyyy-MM-dd HH:ss:mm
         * */
        public static Timestamp str2Timestamp_yyyy_MM_dd_HH_ss_mm(String time){
            try {
                if(time==null) return null;
                Date timeDate = simpleDateFormat_yyyy_MM_dd_HH_ss_mm.parse(time);
                return new Timestamp( timeDate.getTime() );
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * 得到从大数据平台获取数据时的当前时间
         * */
        public static String getNow() {
            String date = "";
            Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DATE);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            cal.set(Calendar.DATE, day - 1);
            date = simpleDateFormat_yyyy_MM_dd.format(cal.getTime());
            return date;
        }


}
