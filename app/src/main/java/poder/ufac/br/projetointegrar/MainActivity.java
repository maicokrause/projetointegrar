package poder.ufac.br.projetointegrar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<Tarefa> mList;
    Intent intent;
    private ListView lista;
    private List<Tarefa> listaTarefas = new ArrayList<Tarefa>();
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdapterListView adapter = new AdapterListView(this, listaTarefas);
        lista = (ListView) findViewById(R.id.tarefaListView);
        lista.setAdapter(adapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

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
        for(int i = 0; i<Tarefas.getListaTarefas().length; i++){
            listaTarefas.add(Tarefas.getTarefa(Tarefas.getListaTarefas()[i]));
        }
    }

    public void onClickListener(View view, int position) {

    }

}
