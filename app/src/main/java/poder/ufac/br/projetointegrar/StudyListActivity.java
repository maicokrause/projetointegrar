package poder.ufac.br.projetointegrar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import poder.ufac.br.projetointegrar.adapter.AdapterStudentListView;
import poder.ufac.br.projetointegrar.cdp.Discipline;
import poder.ufac.br.projetointegrar.cdp.Student;
import poder.ufac.br.projetointegrar.dao.DatabaseHelper;
import poder.ufac.br.projetointegrar.dao.DisciplineDao;
import poder.ufac.br.projetointegrar.dao.StudentDao;

public class StudyListActivity extends Activity {

    private List<Student> students;
    private ListView studentsListView;
    private DatabaseHelper dh;
    private StudentDao studentDao;
    private DisciplineDao disciplineDao;
    private Student s;
    private int firstId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_list);
        studentsListView = (ListView) findViewById(R.id.studyListView);

        // GET ALL LINES
        try {
            dh = new DatabaseHelper(StudyListActivity.this);
            studentDao = new StudentDao(dh.getConnectionSource());
            //studentDao.delete(studentDao.queryForAll());
            disciplineDao = new DisciplineDao(dh.getConnectionSource());
            students = new ArrayList<Student>();
            students = studentDao.queryForAll();
            for(Student student : students){
                Log.i("Script", "Name: " + student.getName() + "\nID: " + student.getId() + "\nDisciplines: " + student.getDisciplines().size());
                for(Discipline d : student.getDisciplines()){
                    Log.i("Script", "Discipline: "+d.getName()+"\nID: "+d.getId()+"\nCode: "+d.getCode());
                }

                Discipline d = new Discipline("Portugues", "PT");
                d.setStudent(student);
                disciplineDao.create(d);

                student.setName(student.getName()+" - ANDROID CLASS");
                studentDao.update(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        AdapterStudentListView adapter = new AdapterStudentListView(this, students);
        studentsListView.setAdapter(adapter);
        studentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int item, long id) {
                Student s = (Student)adapter.getItemAtPosition(item);
                String d = "";
                for(Discipline o : s.getDisciplines()){
                    d += o.getName()+" ";
                }
                Toast.makeText(StudyListActivity.this, "Aluno selecionado: "+s.getName()+d, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
