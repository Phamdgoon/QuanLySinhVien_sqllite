package dgoon.mobile.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class StudentInformationActivity extends AppCompatActivity {

    TextView txtName, txtCode, txtBirthday, txtLop, txtMathscores, txtEnglishscores, txtInfomarticsScores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_information);

        txtName = findViewById(R.id.txtStudentTitle);
        txtCode = findViewById(R.id.txtStudentCode);
        txtBirthday = findViewById(R.id.txtStudentBirthday);
        txtLop = findViewById(R.id.txtStudentLop);
        txtMathscores = findViewById(R.id.txtStudentMathScores);
        txtEnglishscores = findViewById(R.id.txtStudentEnglishScores);
        txtInfomarticsScores = findViewById(R.id.txtStudentInformaticScores);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String code = intent.getStringExtra("code");
        String birthday = intent.getStringExtra("birthday");
        String lop = intent.getStringExtra("lop");
        String math_scores = intent.getStringExtra("math_scores");
        String english_scores = intent.getStringExtra("english_scores");
        String informatics_scores = intent.getStringExtra("informatics_scores");

        txtName.setText(name);
        txtCode.setText(code);
        txtBirthday.setText(birthday);
        txtLop.setText(lop);
        txtMathscores.setText(math_scores);
        txtEnglishscores.setText(english_scores);
        txtInfomarticsScores.setText(informatics_scores);
    }
}