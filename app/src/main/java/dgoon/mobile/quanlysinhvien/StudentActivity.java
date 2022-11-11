package dgoon.mobile.quanlysinhvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewstudent;

    ArrayList<Student> ArrayListStudent;
    sqlite database;
    StudentAdapter studentAdapter;

    int id_subject = 0;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        toolbar = findViewById(R.id.toolbarStudent);
        listViewstudent = findViewById(R.id.listviewstudent);

        Intent intent = getIntent();
        id_subject = intent.getIntExtra("id_subject", 0);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new sqlite(this);

        ArrayListStudent = new ArrayList<>();
        ArrayListStudent.clear();

        Cursor cursor = database.getDataStudent(id_subject);
        while (cursor.moveToNext()) {
            int id_sub = cursor.getInt(8);
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String code = cursor.getString(2);
            String birthday = cursor.getString(3);
            String lop = cursor.getString(4);
            String math_scores = cursor.getString(5);
            String english_scores = cursor.getString(6);
            String informatics_scores = cursor.getString(7);

            ArrayListStudent.add(new Student(name, code, birthday, lop, math_scores, english_scores, informatics_scores, id_sub));
        }
        studentAdapter = new StudentAdapter(StudentActivity.this, ArrayListStudent);

        listViewstudent.setAdapter(studentAdapter);
        cursor.moveToFirst();
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuaddstudent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuaddstudent:
                Intent intent = new Intent(StudentActivity.this, AddStudentActivity.class);
                intent.putExtra("id_subject", id_subject);
                startActivity(intent);
                break;
            default:
                Intent intent1 = new Intent(StudentActivity.this, SubjectActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        counter++;
        if (counter >= 1) {
            Intent intent = new Intent(this, SubjectActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void information(final int pos) {
        Cursor cursor = database.getDataStudent(id_subject);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);

            if (id == pos) {
                Intent intent = new Intent(StudentActivity.this, StudentInformationActivity.class);

                intent.putExtra("id", pos);

                String name = cursor.getString(1);
                String code = cursor.getString(2);
                String birthday = cursor.getString(3);
                String lop = cursor.getString(4);
                String math_scores = cursor.getString(5);
                String english_scores = cursor.getString(6);
                String informatics_scores = cursor.getString(7);
                int id_subject = cursor.getInt(8);

                intent.putExtra("name", name);
                intent.putExtra("code", code);
                intent.putExtra("birthday", birthday);
                intent.putExtra("lop", lop);
                intent.putExtra("math_scores", math_scores);
                intent.putExtra("english_scores", english_scores);
                intent.putExtra("informatics_scores", informatics_scores);

                startActivity(intent);
            }
        }
        cursor.close();
    }

    public void update(final int id_student) {
        Cursor cursor = database.getDataStudent(id_subject);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);

            if (id == id_student) {
                Intent intent = new Intent(StudentActivity.this, UpdateStudentActivity.class);

                intent.putExtra("id", id_student);

                String name = cursor.getString(1);
                String code = cursor.getString(2);
                String birthday = cursor.getString(3);
                String lop = cursor.getString(4);
                String math_scores = cursor.getString(5);
                String english_scores = cursor.getString(6);
                String informatics_scores = cursor.getString(7);
                int id_subject = cursor.getInt(8);

                intent.putExtra("name", name);
                intent.putExtra("code", code);
                intent.putExtra("birthday", birthday);
                intent.putExtra("lop", lop);
                intent.putExtra("math_scores", math_scores);
                intent.putExtra("english_scores", english_scores);
                intent.putExtra("informatics_scores", informatics_scores);
                intent.putExtra("id_subject", id_subject);

                startActivity(intent);
            }
        }
        cursor.close();
    }

    public void delete(final int id_student) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogdeletestudent);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = findViewById(R.id.btnYesDeleteStudent);
        Button btnNo = findViewById(R.id.btnNoDeleteStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.DeleteStudent(id_student);

                Intent intent = new Intent(StudentActivity.this, StudentActivity.class);
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}