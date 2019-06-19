package com.thok.iem.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.thok.iem.model.UserBean;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DataBaseHelp<T> extends SQLiteOpenHelper {
    public static final String LOG_TAG = DataBaseHelp.class.getSimpleName();
    /** Name of the database file */
    private static final String DATABASE_NAME = "iem.db";
    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;
    private Class mClazz;

    public DataBaseHelp(Context context,Class  clazz) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mClazz = clazz;
    }

    public DataBaseHelp( Context context, String name, SQLiteDatabase.CursorFactory factory, int version,Class clazz) {
        super(context, name, factory, version);
        mClazz = clazz;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(mClazz == null)
            return;
        StringBuffer SQL_CREATE_PETS_TABLE =  new StringBuffer("CREATE TABLE " + mClazz.getSimpleName()+ " (");
        /*"CREATE TABLE " + mClazz.getName()+ " ("
                +1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + 1+ " TEXT NOT NULL, "
                + 1+ " TEXT, "
                + 1 + " INTEGER NOT NULL, "
                + 1 + " INTEGER NOT NULL DEFAULT 0);";*/
        for(Field field:mClazz.getDeclaredFields()){
            if(field.getName().contains("$"))
                continue;
            SQL_CREATE_PETS_TABLE .append( field.getName()+whichType(field.getType().getName())).append(getDefine(field));
        }
        SQL_CREATE_PETS_TABLE.deleteCharAt(SQL_CREATE_PETS_TABLE.length() - 1).append(");");
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PETS_TABLE.toString());
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    private String whichType(String type){
        switch (type){
            case "char":
            case "java.lang.Character":
            case "java.lang.String":
                return " TEXT";
            case "int":
            case "java.lang.Integer":
            case "short":
            case "java.lang.Short":
            case "byte":
            case "java.lang.Byte":
                return " INTEGER";
            case "long":
            case "java.lang.Long":
                return "NUMERIC";
            case "double":
            case "java.lang.Double":
            case "float":
            case "java.lang.Float":
                return " REAL";
            default:
                return " BLOB";
        }
    }
    private String getDefine(Field field){
        StringBuffer str = new StringBuffer();
        if(field.isAnnotationPresent(DBAnno.class)){
            DBAnno dbAnno = field.getAnnotation(DBAnno.class);

            if(dbAnno.isKey()){
                str.append(" PRIMARY KEY");
                if("INTEGER".equals(whichType(field.getType().getName()))){
                    str.append(" AUTOINCREMENT");
                }
            }
            if(!dbAnno.isCanNull()){
                str.append(" NOT NULL");
                if(dbAnno.hasDefaultVault()){
                    str.append(" DEFAULT ").append(TextUtils.isEmpty(dbAnno.defaultStringVaule())?dbAnno.defaultIntgerVaule():"'" + dbAnno.defaultStringVaule() + "'");
                }
            }
        }

        return str.append(",").toString();
    }
    public boolean insertData(T obj){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        //obj.getClass().getDeclaredFields()
        for(Field field:mClazz.getDeclaredFields()){
            //values.put(field.getName(), obj.getClass().get);
        }


        return false;
    }
    public  Object getFieldValueByName(String name, Object  obj) {

        String firstletter = name.substring(0, 1).toUpperCase();
        String getter = "get" + firstletter + name.substring(1);

        Method method;
        Object value;
        try {
            method = obj.getClass().getMethod(getter, new Class[] {});
            value = method.invoke(obj, new Object[] {} );
        return value;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
        }


}
