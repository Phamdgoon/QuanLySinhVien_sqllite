package dgoon.mobile.quanlysinhvien;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLDataException;

public class sqlite extends SQLiteOpenHelper {

    //Tên database
    private static String DATABASE_NAME = "studentmanagement";
    //Bảng môn học
    private static String TABLE_SUBJECTS = "subject";
    private static String ID_SUBJECTS = "idsubject";
    private static String SUBJECT_TITLE = "subjecttitle";
    private static String CREDITS = "credits";
    private static String TIME = "time";
    private static int VERSION = 1;

    //Bảng sinh viên
    private static String TABLE_STUDENT = "student";
    private static String ID_STUDENT = "idstudent";
    private static String STUDENT_NAME = "sudentname";
    private static String STUDENT_CODE = "studentcode";
    private static String DATE_OF_BIRTH = "dateofbirth";
    private static String LOP = "lop";
    private static String MATH_SCORES = "math_scores";
    private static String ENGLISH_SCORES = "english_scores";
    private static String INFORMATICS_SCORES = "informatics_scores";


    //Tạo bảng môn học
    private String SQLQuery = "CREATE TABLE "+ TABLE_SUBJECTS +" ( "+ID_SUBJECTS+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +SUBJECT_TITLE+" TEXT, "
            +CREDITS+" INTEGER, "
            +TIME+" TEXT)";

    //Tạo bảng sinh viên
    private String SQLQuery1 = "CREATE TABLE "+ TABLE_STUDENT +" ( "+ID_STUDENT+" integer primary key AUTOINCREMENT, "
            +STUDENT_NAME+" TEXT, "
            +STUDENT_CODE+" TEXT, "
            +DATE_OF_BIRTH+" TEXT, "
            +LOP+"TEXT,"
            +MATH_SCORES+"TEXT,"
            +ENGLISH_SCORES+"TEXT,"
            +INFORMATICS_SCORES+"TEXT,"
            +ID_SUBJECTS+" INTEGER , FOREIGN KEY ( "+ ID_SUBJECTS +" ) REFERENCES "+
            TABLE_SUBJECTS+"("+ID_SUBJECTS+"))";

    public sqlite(@Nullable Context context) {
        super(context, DATABASE_NAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
        sqLiteDatabase.execSQL(SQLQuery1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void AddSubject(Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(SUBJECT_TITLE, subject.getName());
        values.put(CREDITS, subject.getNumber());
        values.put(TIME, subject.getTime());

        db.insert(TABLE_SUBJECTS, null,values);
        db.close();
    }
    public boolean UpdateSubject(Subject subject, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUBJECT_TITLE, subject.getName());
        values.put(CREDITS, subject.getNumber());
        values.put(TIME, subject.getTime());

        db.update(TABLE_SUBJECTS, values, ID_SUBJECTS+" = "+id, null);
        return true;
    }
    public Cursor getDataSubject() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_SUBJECTS, null);
        return cursor;
    }

    public int DeleteSubject(int i ) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_SUBJECTS, ID_SUBJECTS+" = "+i, null);
        return res;
    }

    public int DeleteSubjectStudent(int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_STUDENT, ID_STUDENT+ " = "+i, null);
        return res;
    }

    public void AddStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME,student.getStudent_name());
        values.put(STUDENT_CODE, student.getStudent_code());
        values.put(DATE_OF_BIRTH, student.getDate_of_birth());
        values.put(LOP, student.getLop());
        values.put(MATH_SCORES, student.getMath_scores());
        values.put(ENGLISH_SCORES, student.getEnglish_scores());
        values.put(INFORMATICS_SCORES, student.getInformatics_scores());
        values.put(ID_SUBJECTS, student.getId_subject());

        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }

    public Cursor getDataStudent(int id_subject) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_STUDENT+" WHERE "+ ID_SUBJECTS+" = "+id_subject, null);
        return res;
    }

    public int DeleteStudent(int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_STUDENT, ID_STUDENT+" = "+i, null);
        return res;
    }

    public boolean UpdateStudent(Student student, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME,student.getStudent_name());
        values.put(STUDENT_CODE, student.getStudent_code());
        values.put(DATE_OF_BIRTH, student.getDate_of_birth());
        values.put(LOP, student.getLop());
        values.put(MATH_SCORES, student.getMath_scores());
        values.put(ENGLISH_SCORES, student.getEnglish_scores());
        values.put(INFORMATICS_SCORES, student.getInformatics_scores());
        values.put(ID_SUBJECTS, student.getId_subject());

        db.update(TABLE_STUDENT,values, ID_STUDENT+" = "+id,null);
        return true;
    }
}
