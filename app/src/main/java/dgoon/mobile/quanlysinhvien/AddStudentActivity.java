package dgoon.mobile.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.SpringAnimation;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {

    Button btnAddStudent;
    EditText edtStudentName, edtStudentCode,edtStudentBirthday, edtStudentLop, edtStudentMathscores, edtStudentEnglishscores, edtStudentInformaticsscores, edtStudentID;
    sqlite database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        btnAddStudent = findViewById(R.id.buttonAddStudent);
        edtStudentName = findViewById(R.id.EditTextStudentName);
        edtStudentCode = findViewById(R.id.EditTextStudentCode);
        edtStudentBirthday = findViewById(R.id.EditTextStudentBirthday);
        edtStudentLop = findViewById(R.id.EditTextStudentLop);
        edtStudentMathscores = findViewById(R.id.EditTextStudentMathScores);
        edtStudentEnglishscores = findViewById(R.id.EditTextStudentEnglishScores);
        edtStudentInformaticsscores = findViewById(R.id.EditTextStudentInformaticsScores);
        edtStudentID = findViewById(R.id.EditTextStudentId);

        Intent intent = getIntent();
        int id_subject = intent.getIntExtra("id_subject",0);

        database = new sqlite(this);
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd(id_subject);
            }
        });
    }

    private void DialogAdd(int id_subject) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogaddstudent);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesAddStudent);
        Button btnNo = dialog.findViewById(R.id.btnNoAddStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtStudentName.getText().toString().trim();
                String code = edtStudentCode.getText().toString().trim();
                String birthday = edtStudentBirthday.getText().toString().trim();
                String lop = edtStudentLop.getText().toString().trim();
                String math_scores = edtStudentMathscores.getText().toString().trim();
                String english_scores = edtStudentEnglishscores.getText().toString().trim();
                String informatics_scores = edtStudentInformaticsscores.getText().toString().trim();
                String id = edtStudentID.getText().toString().trim();

                if(name.equals("") || code.equals("")|| birthday.equals("") || lop.equals("") ||
                    math_scores.equals("") || english_scores.equals("") || informatics_scores.equals("")) {
                    Toast.makeText(AddStudentActivity.this, "Did not enter enough information", Toast.LENGTH_SHORT).show();
                }else {
                    Student student = CreatStudent(id_subject);

                    database.AddStudent(student);

                    Intent intent = new Intent(AddStudentActivity.this, StudentActivity.class);
                    intent.putExtra("id_subject", id_subject);
                    startActivity(intent);

                    Toast.makeText(AddStudentActivity.this, "more success", Toast.LENGTH_SHORT).show();
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
    private Student CreatStudent(int id_subject) {
        String name = edtStudentName.getText().toString().trim();
        String code = edtStudentCode.getText().toString().trim();
        String birthday = edtStudentBirthday.getText().toString().trim();
        String lop = edtStudentLop.getText().toString().trim();
        String math_scores = edtStudentMathscores.getText().toString().trim();
        String english_scores = edtStudentEnglishscores.getText().toString().trim();
        String informatics_scores = edtStudentInformaticsscores.getText().toString().trim();


        Student student = new Student(name,code,birthday,lop,math_scores,english_scores,informatics_scores,id_subject);
        return student;
    }
}