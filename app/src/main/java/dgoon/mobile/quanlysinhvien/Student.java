package dgoon.mobile.quanlysinhvien;

public class Student {
    private int id_student;
    private String student_name;
    private String student_code;
    private String date_of_birth;
    private String lop;
    private String math_scores;
    private String english_scores;
    private String informatics_scores;
    private int id_subject;


    public Student(String student_name, String student_code, String date_of_birth, String lop, String math_scores, String english_scores, String informatics_scores) {
        this.student_name = student_name;
        this.student_code = student_code;
        this.date_of_birth = date_of_birth;
        this.lop = lop;
        this.math_scores = math_scores;
        this.english_scores = english_scores;
        this.informatics_scores = informatics_scores;
    }

    public Student(String student_name, String student_code, String date_of_birth, String lop, String math_scores, String english_scores, String informatics_scores, int id_subject) {
        this.student_name = student_name;
        this.student_code = student_code;
        this.date_of_birth = date_of_birth;
        this.lop = lop;
        this.math_scores = math_scores;
        this.english_scores = english_scores;
        this.informatics_scores = informatics_scores;
        this.id_subject = id_subject;
    }

    public Student(int id_student, String student_name, String student_code, String date_of_birth, String lop, String math_scores, String english_scores, String informatics_scores, int id_subject) {
        this.id_student = id_student;
        this.student_name = student_name;
        this.student_code = student_code;
        this.date_of_birth = date_of_birth;
        this.lop = lop;
        this.math_scores = math_scores;
        this.english_scores = english_scores;
        this.informatics_scores = informatics_scores;
        this.id_subject = id_subject;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_code() {
        return student_code;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getMath_scores() {
        return math_scores;
    }

    public void setMath_scores(String math_scores) {
        this.math_scores = math_scores;
    }

    public String getEnglish_scores() {
        return english_scores;
    }

    public void setEnglish_scores(String english_scores) {
        this.english_scores = english_scores;
    }

    public String getInformatics_scores() {
        return informatics_scores;
    }

    public void setInformatics_scores(String informatics_scores) {
        this.informatics_scores = informatics_scores;
    }

    public int getId_subject() {
        return id_subject;
    }

    public void setId_subject(int id_subject) {
        this.id_subject = id_subject;
    }
}
