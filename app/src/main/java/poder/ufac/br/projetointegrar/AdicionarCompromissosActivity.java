package poder.ufac.br.projetointegrar;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import poder.ufac.br.projetointegrar.cdp.Compromisso;
import poder.ufac.br.projetointegrar.cdp.Tarefa;
import poder.ufac.br.projetointegrar.dao.DatabaseHelper;
import poder.ufac.br.projetointegrar.dao.TarefaDao;

public class AdicionarCompromissosActivity extends ActionBarActivity {

    //ORMlite
    private DatabaseHelper dh;
    private TarefaDao tarefaDao;

    private List<Tarefa> mList;
    private ListView listaTarefa;
    private List<Tarefa> listaTarefas = new ArrayList<Tarefa>();
    private ImageView miniatura;
    private TimePicker tp;
    private Compromisso c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_compromissos);

        //ORMlite
        dh = new DatabaseHelper(this);
        try {
            tarefaDao = new TarefaDao(dh.getConnectionSource());
        } catch (SQLException e) {e.printStackTrace();}
        carregaTarefas();

        //habilita o botão voltar
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        //
        miniatura = (ImageView) findViewById(R.id.imageViewMiniaturaAdicionarCompromisso);
        tp = (TimePicker) findViewById(R.id.timePickerAdicionarCompromisso);
        AdapterListView adapter = new AdapterListView(this, listaTarefas);
        listaTarefa = (ListView) findViewById(R.id.listViewAdicionarCompromissos);
        listaTarefa.setAdapter(adapter);
        listaTarefa.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int item, long id) {

                Tarefa t = (Tarefa) adapter.getItemAtPosition(item);
//        Toast.makeText(this, "Tarefa: "+t.getNome(), Toast.LENGTH_SHORT).show();
                miniatura.setImageResource(t.getMiniatura());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adicionar_compromissos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.itemMenuCancelarCompromisso:
                finish();
                break;
            case R.id.itemMenuSalvarCompromisso:
                Toast.makeText(this, "Salvar Tarefa "+String.format("%02d", tp.getCurrentHour())+":"+String.format("%02d", tp.getCurrentMinute()), Toast.LENGTH_SHORT).show();
                break;
        }

        return(true);
    }

    public void salvarCompromisso(View v){
        c = new Compromisso();
        c.setHorario(String.format("%02d",tp.getCurrentHour())+":"+String.format("%02d", tp.getCurrentMinute()));
    }

    private void carregaTarefas() {
        // GET ALL LINES
        try {
            listaTarefas = tarefaDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
