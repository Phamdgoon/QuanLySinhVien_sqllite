package dgoon.mobile.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateStudentActivity extends AppCompatActivity {

    EditText edtStudentName, edtStudentCode,edtStudentBirthday, edtStudentLop, edtStudentMathscores, edtStudentEnglishscores, edtStudentInformaticsscores, edtStudentID;
    Button btnUpdateStudent;
    sqlite database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        btnUpdateStudent = findViewById(R.id.buttonUpdateStudent);
        edtStudentName = findViewById(R.id.EditTextStudentNameUpdate);
        edtStudentCode = findViewById(R.id.EditTextStudentCodeUpdate);
        edtStudentBirthday = findViewById(R.id.EditTextStudentBirthdayUpdate);
        edtStudentLop = findViewById(R.id.EditTextStudentLopUpdate);
        edtStudentMathscores = findViewById(R.id.EditTextStudentMathScoresUpdate);
        edtStudentEnglishscores = findViewById(R.id.EditTextStudentEnglishScoresUpdate);
        edtStudentInformaticsscores = findViewById(R.id.EditTextStudentInformaticsScoresUpdate);
        edtStudentID = findViewById(R.id.EditTextStudentIdUpdate);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");
        String lop = intent.getStringExtra("lop");
        String math_scores = intent.getStringExtra("math_scores");
        String english_scores = intent.getStringExtra("english_scores");
        String informatics_scores = intent.getStringExtra("informatics_scores");
        int id_subject = intent.getIntExtra("id_subject",0);

        edtStudentName.setText(name);
        edtStudentCode.setText(code);
        edtStudentBirthday.setText(birthday);
        edtStudentLop.setText(lop);
        edtStudentMathscores.setText(math_scores);
        edtStudentEnglishscores.setText(english_scores);
        edtStudentInformaticsscores.setText(informatics_scores);

        database = new sqlite(this);

        btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUpdate(id,id_subject);
            }
        });
    }

    private void DialogUpdate(int id,int id_subject) {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogupdatestudent);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesupdateStudent);
        Button btnNo = dialog.findViewById(R.id.btnNoupdateStudent);

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

                Student student = createstudent();

                if(name.equals("") || code.equals("")|| birthday.equals("") || lop.equals("") ||
                        math_scores.equals("") || english_scores.equals("") || informatics_scores.equals("")){
                    Toast.makeText(UpdateStudentActivity.this, "Dia not enter enough information", Toast.LENGTH_SHORT).show();
                }else {
                    database.UpdateStudent(student,id);
                    Intent intent = new Intent(UpdateStudentActivity.this,StudentActivity.class);
                    intent.putExtra("id_subject",id_subject);
                    startActivity(intent);
                    Toast.makeText(UpdateStudentActivity.this, "more success", Toast.LENGTH_SHORT).show();
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

    private Student createstudent() {
        String name = edtStudentName.getText().toString().trim();
        String code = edtStudentCode.getText().toString().trim();
        String birthday = edtStudentBirthday.getText().toString().trim();
        String lop = edtStudentLop.getText().toString().trim();
        String math_scores = edtStudentMathscores.getText().toString().trim();
        String english_scores = edtStudentEnglishscores.getText().toString().trim();
        String informatics_scores = edtStudentInformaticsscores.getText().toString().trim();

        Student student = new Student(name,code,birthday,lop,math_scores,english_scores,informatics_scores);
        return student;
    }
}