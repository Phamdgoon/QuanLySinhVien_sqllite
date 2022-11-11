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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewsubject;
    ArrayList<Subject> ArrayListSubject;
    sqlite database;
    SubjectAdapter subjectAdapter;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        toolbar = findViewById(R.id.toolbarSubject);
        listViewsubject = findViewById(R.id.listViewSubject);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new sqlite(this);

        ArrayListSubject = new ArrayList<>();

        Cursor cursor = database.getDataSubject();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int number = cursor.getInt(1);
            String name = cursor.getString(2);
            String time = cursor.getString(3);

            ArrayListSubject.add(new Subject(id, number, name, time));
        }
        subjectAdapter = new SubjectAdapter(SubjectActivity.this, ArrayListSubject);
        listViewsubject.setAdapter(subjectAdapter);
        cursor.moveToFirst();
        cursor.close();

        listViewsubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(SubjectActivity.this, StudentActivity.class);
                    int id_subject = ArrayListSubject.get(i).getId();
                    intent.putExtra("id_subject",id_subject);
                    startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuadd:
                Intent intent1 = new Intent(SubjectActivity.this, AddSubjectActivity.class);
                startActivity(intent1);
                break;
            default:
                Intent intent = new Intent(SubjectActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        count++;
        if(count>=1) {
            Intent intent = new Intent(SubjectActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void information(final int pos) {
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if(id == pos) {
                Intent intent = new Intent(SubjectActivity.this, SubjectInformationActivity.class);

                intent.putExtra("id",id);
                String name = cursor.getString(1);
                int number = cursor.getInt(2);
                String time = cursor.getString(3);

                intent.putExtra("number", number);
                intent.putExtra("name", name);
                intent.putExtra("time", time);
                startActivity(intent);
            }
        }
    }
    public void delete(final int position) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogdeletesubject);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesDelete);
        Button btnNo = dialog.findViewById(R.id.btnNoDelete);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database.DeleteSubject(position);

                database.DeleteSubjectStudent(position);

                Intent intent = new Intent(SubjectActivity.this, SubjectActivity.class);
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

    public void update(final int pos) {
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if(id == pos) {
                Intent intent = new Intent(SubjectActivity.this,UpdateSubjectActivity.class);
                intent.putExtra("id", id);

                String title = cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);

                intent.putExtra("name", title);
                intent.putExtra("number", credit);
                intent.putExtra("time", time);

                startActivity(intent);
            }
        }
    }
}