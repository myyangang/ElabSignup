package com.elab.elabsignup.sqlite;

/* SQLite配置文件 */

public class SignupSchema {
    // TODO:可根据科中管理系统数据库的命名习惯进行调整
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "SignupLog.db";
    public static final String TABLE_NAME = "signup_log";
    public static final class Columns{
        public static final String uuid = "uuid";
        public static final String latitude = "latitude";
        public static final String longitude = "longitude";
        public static final String date = "date";
        public static final String isValidLocation = "is_valid_location";
        public static final String isSuccessSubmit = "is_success_submit";
    }

}
