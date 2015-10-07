package poder.ufac.br.projetointegrar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import poder.ufac.br.projetointegrar.cdp.Discipline;
import poder.ufac.br.projetointegrar.cdp.Tarefa;
import poder.ufac.br.projetointegrar.dao.DatabaseHelper;
import poder.ufac.br.projetointegrar.dao.TarefaDao;

public class MainActivity extends Activity {

    //ORMlite
    private DatabaseHelper dh;
    private TarefaDao tarefaDao;

    Intent intent;
//    private List<Tarefa> mList;
//    private ListView listaTarefa;
//    private List<Tarefa> listaTarefas = new ArrayList<Tarefa>();
//    private ListView listaTarefa2;
//    private List<Tarefa> listaTarefas2 = new ArrayList<Tarefa>();
    ImageView im;
    public void abrirActivityEscovarDentes(View view){
//        String texto = Tarefas.escovarDentesAudio[1]+" teste";
//        int duracao = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(this, texto, duracao);
//        toast.show();
        intent = new Intent(this, TarefaActivity.class);
        intent.putExtra("tarefa", Tarefas.ESCOVAR_DENTES);
        intent.putExtra("titulo", R.drawable.escovando_dentes_00);
        startActivity(intent);
    }

    public void abreListaCompromissos(View v){
        intent = new Intent(this, ListarCompromissoActivity.class);

        startActivity(intent);
    }

    public void calendarioActivity(View v){
        intent = new Intent(this, CalendarioActivity.class);
        startActivity(intent);
    }

    public void abreListaStudents(View v){
        intent = new Intent(this, StudyListActivity.class);

        startActivity(intent);
    }

    public void abrirAgendaActivity(View view){
        intent = new Intent(this, AgendaActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        carregaTarefas();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        im = (ImageView) findViewById(R.id.tituloAplicativoImageView);
        im.setImageResource(R.drawable.logo_projeto_integrar);

//        AdapterListView adapter = new AdapterListView(this, listaTarefas);
//        listaTarefa = (ListView) findViewById(R.id.tarefaListView);
//        listaTarefa.setAdapter(adapter);
//        listaTarefa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapter, View v, int item, long id) {
//
//                Tarefa t = (Tarefa) adapter.getItemAtPosition(item);
////        Toast.makeText(this, "Tarefa: "+t.getNome(), Toast.LENGTH_SHORT).show();
//                intent = new Intent(MainActivity.this, TarefaActivity.class);
//                intent.putExtra("imagens", t.getImagens());
//                intent.putExtra("audio", t.getAudio());
//                intent.putExtra("titulo", t.getTitulo());
//                startActivity(intent);
//            }
//        });
//        AdapterListView adapter2 = new AdapterListView(this, listaTarefas2);
//        listaTarefa2 = (ListView) findViewById(R.id.tarefa2ListView);
//        listaTarefa2.setAdapter(adapter);
//        listaTarefa2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapter, View v, int item, long id) {
//
//                Tarefa t = (Tarefa) adapter.getItemAtPosition(item);
////        Toast.makeText(this, "Tarefa: "+t.getNome(), Toast.LENGTH_SHORT).show();
//                intent = new Intent(MainActivity.this, TarefaActivity.class);
//                intent.putExtra("imagens", t.getImagens());
//                intent.putExtra("audio", t.getAudio());
//                intent.putExtra("titulo", t.getTitulo());
//                startActivity(intent);
//            }
//        });

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

    private void carregaTarefas() {
//        for(int i = 0; i<Tarefas.getListaTarefas().length; i++){
//            if(i%2 == 1){
//                Tarefa t = Tarefas.getTarefa(Tarefas.getListaTarefas()[i]);
//                t.setStatus(true);
//                listaTarefas.add(t);
//            }else {
//                listaTarefas.add(Tarefas.getTarefa(Tarefas.getListaTarefas()[i]));
//            }
//        }

        //ORMlite
//        dh = new DatabaseHelper(MainActivity.this);
//        try {
//            tarefaDao = new TarefaDao(dh.getConnectionSource());
//            listaTarefas = tarefaDao.queryForAll();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//            Tarefa t = Tarefas.getTarefa(Tarefas.getListaTarefas()[0]);
//
//            listaTarefas2.add(t);
//            listaTarefas2.add(Tarefas.getTarefa(Tarefas.getListaTarefas()[1]));
//            listaTarefas2.add(t);
//            t = Tarefas.getTarefa(Tarefas.getListaTarefas()[0]);
//
//            listaTarefas2.add(t);
//            listaTarefas2.add(Tarefas.getTarefa(Tarefas.getListaTarefas()[1]));
//            listaTarefas2.add(t);
//            t = Tarefas.getTarefa(Tarefas.getListaTarefas()[0]);
//
//            listaTarefas2.add(t);
//            listaTarefas2.add(Tarefas.getTarefa(Tarefas.getListaTarefas()[1]));
//            listaTarefas2.add(t);
//
    }

}
