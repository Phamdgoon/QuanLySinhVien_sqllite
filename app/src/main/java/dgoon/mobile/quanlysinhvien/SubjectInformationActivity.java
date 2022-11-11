package dgoon.mobile.quanlysinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SubjectInformationActivity extends AppCompatActivity {

    TextView txtTitle, txtCredit, txtTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_information);

        txtCredit = findViewById(R.id.txtSubjectCredit);
        txtTime = findViewById(R.id.txtSubjectTime);
        txtTitle = findViewById(R.id.txtSubjectTitle);

        Intent intent = getIntent();
        String title = intent.getStringExtra("name");
        int credit = intent.getIntExtra("number", 0);
        String time = intent.getStringExtra("time");

        txtTitle.setText(title);
        txtTime.setText(time);
        txtCredit.setText(credit+"");
    }
}