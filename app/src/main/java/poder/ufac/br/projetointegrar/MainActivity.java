package poder.ufac.br.projetointegrar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<Tarefa> mList;
    Intent intent;
    private ListView listaTarefa;
    private ListView listaTarefa2;
    private List<Tarefa> listaTarefas = new ArrayList<Tarefa>();
    private List<Tarefa> listaTarefas2 = new ArrayList<Tarefa>();
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

    public void abreORMlite(View v){
        intent = new Intent(this, TesteSQLiteActivity.class);

        startActivity(intent);
    }

    public void abrirActivityNaEscola(View view){
        intent = new Intent(this, TarefaActivity.class);
        intent.putExtra("tarefa", Tarefas.NA_ESCOLA);
        intent.putExtra("titulo", R.drawable.na_escola_00);
        startActivity(intent);
    }

    public void abrirActivitySpeechText(View view){
        intent = new Intent(this, SpeechActivity.class);
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
        AdapterListView adapter = new AdapterListView(this, listaTarefas);
        listaTarefa = (ListView) findViewById(R.id.tarefaListView);
        listaTarefa.setAdapter(adapter);
        listaTarefa.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int item, long id) {

                Tarefa t = (Tarefa) adapter.getItemAtPosition(item);
//        Toast.makeText(this, "Tarefa: "+t.getNome(), Toast.LENGTH_SHORT).show();
                intent = new Intent(MainActivity.this, TarefaActivity.class);
                intent.putExtra("imagens", t.getImagens());
                intent.putExtra("audio", t.getAudio());
                intent.putExtra("titulo", t.getTitulo());
                startActivity(intent);
            }
        });
        AdapterListView adapter2 = new AdapterListView(this, listaTarefas2);
        listaTarefa2 = (ListView) findViewById(R.id.tarefa2ListView);
        listaTarefa2.setAdapter(adapter);
        listaTarefa2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int item, long id) {

                Tarefa t = (Tarefa) adapter.getItemAtPosition(item);
//        Toast.makeText(this, "Tarefa: "+t.getNome(), Toast.LENGTH_SHORT).show();
                intent = new Intent(MainActivity.this, TarefaActivity.class);
                intent.putExtra("imagens", t.getImagens());
                intent.putExtra("audio", t.getAudio());
                intent.putExtra("titulo", t.getTitulo());
                startActivity(intent);
            }
        });
    }

    private void carregaTarefas(){
//        for(int i = 0; i<Tarefas.getListaTarefas().length; i++){
//            if(i%2 == 1){
//                Tarefa t = Tarefas.getTarefa(Tarefas.getListaTarefas()[i]);
//                t.setStatus(true);
//                listaTarefas.add(t);
//            }else {
//                listaTarefas.add(Tarefas.getTarefa(Tarefas.getListaTarefas()[i]));
//            }
//        }
        Tarefa t = Tarefas.getTarefa(Tarefas.getListaTarefas()[0]);
        t.setStatus(true);
        listaTarefas.add(t);
        listaTarefas.add(Tarefas.getTarefa(Tarefas.getListaTarefas()[1]));
        t = Tarefas.getTarefa(Tarefas.getListaTarefas()[0]);
        t.setStatus(true);
        listaTarefas.add(t);
        listaTarefas.add(Tarefas.getTarefa(Tarefas.getListaTarefas()[1]));
        t = Tarefas.getTarefa(Tarefas.getListaTarefas()[0]);
        t.setStatus(true);
        listaTarefas.add(t);
        listaTarefas.add(Tarefas.getTarefa(Tarefas.getListaTarefas()[1]));
        t = Tarefas.getTarefa(Tarefas.getListaTarefas()[0]);
        t.setStatus(true);
        listaTarefas2.add(t);
        listaTarefas2.add(Tarefas.getTarefa(Tarefas.getListaTarefas()[1]));
        listaTarefas2.add(t);
        t = Tarefas.getTarefa(Tarefas.getListaTarefas()[0]);
        t.setStatus(true);
        listaTarefas2.add(t);
        listaTarefas2.add(Tarefas.getTarefa(Tarefas.getListaTarefas()[1]));
        listaTarefas2.add(t);
        t = Tarefas.getTarefa(Tarefas.getListaTarefas()[0]);
        t.setStatus(true);
        listaTarefas2.add(t);
        listaTarefas2.add(Tarefas.getTarefa(Tarefas.getListaTarefas()[1]));
        listaTarefas2.add(t);
    }

    public void onClickListener(View view, int position) {

    }

}
