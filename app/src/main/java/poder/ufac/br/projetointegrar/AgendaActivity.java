package poder.ufac.br.projetointegrar;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import poder.ufac.br.projetointegrar.adapter.AdapterCompromissoListView;
import poder.ufac.br.projetointegrar.cdp.Compromisso;
import poder.ufac.br.projetointegrar.cdp.Tarefa;
import poder.ufac.br.projetointegrar.dao.CompromissoDao;
import poder.ufac.br.projetointegrar.dao.DatabaseHelper;
import poder.ufac.br.projetointegrar.dao.TarefaDao;
import poder.ufac.br.projetointegrar.util.Relogio;

public class AgendaActivity extends ActionBarActivity {
    private DatabaseHelper dh;
    private CompromissoDao compromissoDao;
    private TarefaDao tdao;
    private ListView listViewCompromissos;
    private List<Compromisso> listaCompromissos;
    private DatePicker data;
    private Date dataSelecionada;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        listViewCompromissos = (ListView) findViewById(R.id.listViewAgendaTarefas);
        data = (DatePicker) findViewById(R.id.datePickerAgendaTarefas);
        selecionaDia();
    }

    public void selecionaDia(View v) {
        selecionaDia();
    }

    private void selecionaDia() {
        dataSelecionada = new Date(Relogio.zerarHoraLong(data.getCalendarView().getDate()));
//        Toast.makeText(this, "Data: " +dataSelecionada.getTime(), Toast.LENGTH_SHORT).show();
//        Log.i("Data", dataSelecionada.getTime() + "");

        //ORMlite
        dh = new DatabaseHelper(this);
        try {
            compromissoDao = new CompromissoDao(dh.getConnectionSource());
            tdao = new TarefaDao(dh.getConnectionSource());

            Map<String, Object> values = new HashMap<String, Object>();
            values.put("data", dataSelecionada);
            listaCompromissos = compromissoDao.queryForFieldValues(values);
            for(Compromisso c : listaCompromissos){
                c.setTarefa(tdao.queryForId(Integer.parseInt(c.getTarefa().getId().toString())));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AdapterCompromissoListView adapter = new AdapterCompromissoListView(this, listaCompromissos);
        listViewCompromissos.setAdapter(adapter);
        listViewCompromissos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int item, long id) {
                Compromisso c = (Compromisso) adapter.getItemAtPosition(item);
                intent = new Intent(AgendaActivity.this, TarefaActivity.class);

                Tarefa t = (Tarefa) c.getTarefa();
//        Toast.makeText(this, "Tarefa: "+t.getNome(), Toast.LENGTH_SHORT).show();
//                intent.putExtra("imagens", t.getImagens());
//                intent.putExtra("audio", t.getAudio());
//                intent.putExtra("titulo", t.getTitulo());
                intent.putExtra("compromisso", c);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agenda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
