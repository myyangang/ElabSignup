package com.elab.elabsignup.sqlite;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.elab.elabsignup.signup.SignupEvent;

import java.util.Date;
import java.util.UUID;

public class SignupEventCursorWrapper extends CursorWrapper {

    /* 根据SQLite返回的Cursor搜索结果进程拆包 */
    public SignupEventCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public SignupEvent getSignupEvent(){
        String uuidString = getString(getColumnIndex(SignupSchema.Columns.uuid));
        double latitude = getDouble(getColumnIndex(SignupSchema.Columns.latitude));
        double longitude = getDouble(getColumnIndex(SignupSchema.Columns.longitude));
        long date = getLong(getColumnIndex(SignupSchema.Columns.date));
        int isValidLocation = getInt(getColumnIndex(SignupSchema.Columns.isValidLocation));
        int isSuccessSubmit = getInt(getColumnIndex(SignupSchema.Columns.isSuccessSubmit));

        SignupEvent signupEvent = new SignupEvent();
        signupEvent.uuid = UUID.fromString(uuidString);
        signupEvent.latitude = latitude;
        signupEvent.longitude = longitude;
        signupEvent.date = new Date(date);
        signupEvent.isValidLocation = isValidLocation;
        signupEvent.isSuccessSubmit = isSuccessSubmit;

        return signupEvent;
    }
}
