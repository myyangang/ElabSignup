package com.elab.elabsignup.event;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.elab.elabsignup.sqlite.SignupEventCursorWrapper;
import com.elab.elabsignup.sqlite.SignupSQLiteHelper;
import com.elab.elabsignup.sqlite.SignupSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class SignupEventList {

    private static SignupEventList sSignupEventList;
    private List<SignupEvent> mSignupEvents;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    /* 私有构造方法,该类的实例由get(Context)方法创建 */
    private SignupEventList(Context context){
        mSignupEvents = new ArrayList<>();
        mContext = context.getApplicationContext();
        mDatabase = new SignupSQLiteHelper(mContext).getWritableDatabase();
    }

    public static SignupEventList get(Context context){
        if(sSignupEventList == null){
            sSignupEventList = new SignupEventList(context);
        }
        return sSignupEventList;
    }

    /* 将SignEvent转化为SQLite能识别的形式 */
    private static ContentValues getContentValues(SignupEvent signupEvent){
        ContentValues values = new ContentValues();
        values.put(SignupSchema.Columns.uuid,signupEvent.uuid.toString());
        values.put(SignupSchema.Columns.latitude,String.valueOf(signupEvent.latitude));
        values.put(SignupSchema.Columns.longitude,String.valueOf(signupEvent.longitude));
        values.put(SignupSchema.Columns.date,signupEvent.date.getTime());
        values.put(SignupSchema.Columns.isValidLocation,signupEvent.isValidLocation);
        values.put(SignupSchema.Columns.isSuccessSubmit,signupEvent.isSuccessSubmit);
        return values;
    }

    /* 执行SQL查询 */
    private SignupEventCursorWrapper querySignupEvents(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                SignupSchema.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new SignupEventCursorWrapper(cursor);
    }

    /* 添加签到记录 */
    public void addSignupEvent(SignupEvent signupEvent){
        ContentValues values = getContentValues(signupEvent);
        mDatabase.insert(SignupSchema.TABLE_NAME,null,values);
    }

    /* 获取所有签到记录 */
    public List<SignupEvent> getSignupEvents(){
        List<SignupEvent> signupEvents = new ArrayList<>();
        SignupEventCursorWrapper cursorWrapper = querySignupEvents(null,null);
        try {
            cursorWrapper.moveToFirst();
            while(!cursorWrapper.isAfterLast()){
                signupEvents.add(cursorWrapper.getSignupEvent());
                cursorWrapper.moveToNext();
            }
        }finally {
            cursorWrapper.close();
        }
        return signupEvents;
    }

    /* 根据UUID获得指定签到记录
    *  TODO:这里使用的是线性查找,后续可优化搜索算法 */
    public SignupEvent getSignupEventByUUID(UUID uuid){
        SignupEventCursorWrapper cursorWrapper = querySignupEvents(
                SignupSchema.Columns.uuid + " = ?",
                new String[]{uuid.toString()}
        );
        try {
            if(cursorWrapper.getCount() == 0){
                return null;
            }
            cursorWrapper.moveToFirst();
            return cursorWrapper.getSignupEvent();
        }finally {
            cursorWrapper.close();
        }
    }

    /* 直接对mSignupEvents进行更改,生成测试用签到记录 */
    public void AddDemoData(){
        for(int i = 0;i < 20;i++){
            SignupEvent signupEvent = new SignupEvent();
            signupEvent.latitude = i + Math.sin(i);
            signupEvent.latitude = 90 - i + Math.cos(i);
            signupEvent.isSuccessSubmit = i % 2;
            signupEvent.isSuccessSubmit = (new Random()).nextInt(2);
            mSignupEvents.add(signupEvent);
        }
    }

}
