package dgoon.mobile.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSubjectActivity extends AppCompatActivity {

    Button btnAddSubject;
    EditText edtSubjectTitle, edtSubjectCredit, edtSubjectTime, edtSubjectId;
    sqlite database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        btnAddSubject = findViewById(R.id.buttonAddSubject);
        edtSubjectCredit = findViewById(R.id.EditTextSubjectCredit);
        edtSubjectTime = findViewById(R.id.EditTextSubjectTime);
        edtSubjectTitle = findViewById(R.id.EditTextSubjectTitle);
        edtSubjectId = findViewById(R.id.EditTextSubjectId);

        database = new sqlite(this);

        btnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd();
            }
        });
    }

    private void DialogAdd() {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogadd);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtSubjectId.getText().toString().trim();
                String number =edtSubjectCredit.getText().toString().trim();
                String name = edtSubjectTitle.getText().toString().trim();
                String time = edtSubjectTime.getText().toString().trim();

                if(name.equals("") || time.equals("") || number.equals("") || id.equals("")) {
                    Toast.makeText(AddSubjectActivity.this, "Did not enter enough infornation", Toast.LENGTH_SHORT).show();
                }else {
                    Subject subject = CreatSubject();

                    database.AddSubject(subject);

                    Intent intent = new Intent(AddSubjectActivity.this, SubjectActivity.class);
                    startActivity(intent);

                    Toast.makeText(AddSubjectActivity.this, "more success", Toast.LENGTH_SHORT).show();
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

    private Subject CreatSubject() {
        int id = Integer.parseInt(edtSubjectId.getText().toString().trim());
        int number = Integer.parseInt(edtSubjectCredit.getText().toString().trim());
        String name = edtSubjectTitle.getText().toString().trim();
        String time = edtSubjectTime.getText().toString().trim();

        Subject subject = new Subject(id,number,name,time);
        return subject;
    }
}