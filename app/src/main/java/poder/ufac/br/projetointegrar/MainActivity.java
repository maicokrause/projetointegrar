package poder.ufac.br.projetointegrar;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements RecyclerViewOnClickListenerHack {

    private RecyclerView mRecyclerView;
    private List<Tarefa> mList;
    Intent intent;
    private ListView lista;
    private List<Tarefa> listaTarefas = new ArrayList<>();
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

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                TarefaAdapter adapter = (TarefaAdapter) mRecyclerView.getAdapter();

//                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
//                    List<Tarefa> listAux = listaTarefas;
//
//                    for (int i = 0; i < listAux.size(); i++) {
//                        adapter.addListItem(listAux.get(i), mList.size());
//                    }
//                }
            }
        });


        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);


        mList = listaTarefas;
        TarefaAdapter adapter = new TarefaAdapter(this, mList);
        adapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);
    }

    private void carregaTarefas(){
        for(int i = 0; i<Tarefas.getListaTarefas().length; i++){
            listaTarefas.add(Tarefas.getTarefa(Tarefas.getListaTarefas()[i]));
        }
    }

    @Override
    public void onClickListener(View view, int position) {
        TarefaAdapter adapter = (TarefaAdapter) mRecyclerView.getAdapter();
        Tarefa t = adapter.getTarefa(position);
//        Toast.makeText(this, "Tarefa: "+t.getNome(), Toast.LENGTH_SHORT).show();
        intent = new Intent(this, TarefaActivity.class);
        intent.putExtra("imagens", t.getImagens());
        intent.putExtra("audio", t.getAudio());
        intent.putExtra("titulo", t.getTitulo());
        startActivity(intent);
//        adapter.removeListItem(position);
    }

}
