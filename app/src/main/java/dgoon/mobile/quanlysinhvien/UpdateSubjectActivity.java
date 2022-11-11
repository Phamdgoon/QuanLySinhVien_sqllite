package dgoon.mobile.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateSubjectActivity extends AppCompatActivity {

    EditText edtUpdateSubjectTitle, edtUpdateSubjectCredit, edtUpdateSubjectTime, edtUpdateSubjectId;
    Button btnUpdateSubject;

    sqlite database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);

        edtUpdateSubjectCredit = findViewById(R.id.EditTextUpdateSubjectCredit);
        edtUpdateSubjectId = findViewById(R.id.EditTextUpdateSubjectId);
        edtUpdateSubjectTime = findViewById(R.id.EditTextUpdateSubjectTime);
        edtUpdateSubjectTitle = findViewById(R.id.EditTextUpdateSubjectTitle);
        btnUpdateSubject = findViewById(R.id.buttonUpdateSubject);

        Intent intent = getIntent();

        int id = intent.getIntExtra("id",0);
        String title = intent.getStringExtra("name");
        int credit = intent.getIntExtra("number", 0);
        String time = intent.getStringExtra("time");

        edtUpdateSubjectId.setText(id);
        edtUpdateSubjectTime.setText(time);
        edtUpdateSubjectTitle.setText(title);
        edtUpdateSubjectCredit.setText(credit);

        database = new sqlite(this);

        btnUpdateSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUpdate(id);
            }
        });
    }

    private void DialogUpdate(int id) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogupdatesubject);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesupdate);
        Button btnNo = dialog.findViewById(R.id.btnNoupdate);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subjecttitle = edtUpdateSubjectTitle.getText().toString().trim();
                String credit = edtUpdateSubjectCredit.getText().toString().trim();
                String time = edtUpdateSubjectTime.getText().toString().trim();

                if(subjecttitle.equals("") || credit.equals("") || time.equals("")) {
                    Toast.makeText(UpdateSubjectActivity.this, "Did you enter enough information", Toast.LENGTH_SHORT).show();
                }else {
                    Subject subject = updatesubject();

                    database.UpdateSubject(subject,id);

                    Intent intent = new Intent(UpdateSubjectActivity.this,SubjectActivity.class);
                    startActivity(intent);
                    Toast.makeText(UpdateSubjectActivity.this, "more success", Toast.LENGTH_SHORT).show();
                }
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
    private Subject updatesubject() {
        int id = Integer.parseInt(edtUpdateSubjectId.getText().toString().trim());
        String name = edtUpdateSubjectTitle.getText().toString().trim();
        int number =Integer.parseInt(edtUpdateSubjectCredit.getText().toString().trim());
        String time = edtUpdateSubjectTime.getText().toString().trim();

        Subject subject = new Subject(id,number,name,time);
        return subject;
    }
}