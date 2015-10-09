package poder.ufac.br.projetointegrar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import poder.ufac.br.projetointegrar.cdp.Tarefa;
import poder.ufac.br.projetointegrar.dao.DatabaseHelper;
import poder.ufac.br.projetointegrar.dao.TarefaDao;

public class MainActivity extends Activity {

    //ORMlite
    private DatabaseHelper dh;
    private TarefaDao tarefaDao;
    Intent intent;
    ImageView im;

    public void calendarioActivity(View v){
        intent = new Intent(this, CalendarioActivity.class);
        startActivity(intent);
    }

    public void abrirAgendaActivity(View view){
        intent = new Intent(this, AgendaViewPagerActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        im = (ImageView) findViewById(R.id.tituloAplicativoImageView);
        im.setImageResource(R.drawable.logo_projeto_integrar);

        //ORMlite
        dh = new DatabaseHelper(MainActivity.this);
        try {
            tarefaDao = new TarefaDao(dh.getConnectionSource());
            List<Tarefa> listaT = tarefaDao.queryForAll();
            if(listaT.isEmpty()){
                addTarefaBanco();
            }

        } catch (SQLException e) {e.printStackTrace();}

    }

    public void addTarefaBanco(){
        List<Tarefa> listaT = new ArrayList<Tarefa>();
        for(int i = 0; i<Tarefas.getListaTarefas().length; i++){
            listaT.add(Tarefas.getTarefa(Tarefas.getListaTarefas()[i]));
        }

        //ORMlite
        dh = new DatabaseHelper(MainActivity.this);
        try {
            tarefaDao = new TarefaDao(dh.getConnectionSource());
        // CREATE
            for(Tarefa t : listaT){
                tarefaDao.create(t);
            }
        } catch (SQLException e) {
            Toast.makeText(this, "Erro ao salvar no banco", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        Toast.makeText(this, "Tarefas adicionadas no banco", Toast.LENGTH_SHORT).show();
    }
}
