package poder.ufac.br.projetointegrar;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import poder.ufac.br.projetointegrar.cdp.Compromisso;
import poder.ufac.br.projetointegrar.dao.CompromissoDao;
import poder.ufac.br.projetointegrar.dao.DatabaseHelper;
import poder.ufac.br.projetointegrar.dao.TarefaDao;
import poder.ufac.br.projetointegrar.util.Relogio;

public class ListarCompromissoActivity extends ActionBarActivity {
    //ORMlite
    private DatabaseHelper dh;
    private CompromissoDao compromissoDao;
    private TarefaDao tdao;
    private ListView listViewCompromissos;
    private List<Compromisso> listaCompromissos;
    private TextView textoTitulo;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_compromisso);
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        listViewCompromissos = (ListView) findViewById(R.id.listViewListarCompromissos);
        textoTitulo = (TextView) findViewById(R.id.textViewListarCompromissosTitulo);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = sdf.format(new Date(getIntent().getLongExtra("data", 0)));
        textoTitulo.setText("Tarefas do dia "+dataString);
        //ORMlite
        dh = new DatabaseHelper(this);
        try {
            compromissoDao = new CompromissoDao(dh.getConnectionSource());
            tdao = new TarefaDao(dh.getConnectionSource());

//            QueryBuilder<Compromisso, Integer> queryBuilder = compromissoDao.queryBuilder();
//
//            queryBuilder.where().eq("data", new Date(getIntent().getLongExtra("data", 0)));
//            PreparedQuery<Compromisso> query = queryBuilder.prepare();
//
//            listaCompromissos = compromissoDao.query(query);
            Map<String, Object> values = new HashMap<String, Object>();
            values.put("data", Relogio.zerarHoraDate(new Date(getIntent().getLongExtra("data", 0))));
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
                intent = new Intent(ListarCompromissoActivity.this, AdicionarCompromissosActivity.class);
                intent.putExtra("compromisso", c);
                startActivity(intent);
//                Toast.makeText(ListarCompromissoActivity.this, "Tarefa "+c.getTarefa().getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listar_compromisso, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case android.R.id.home:
//                finish();
                NavUtils.navigateUpFromSameTask(this);
                break;
            case R.id.itemMenuAdicionarCompromisso:
                intent = new Intent(this, AdicionarCompromissosActivity.class);
                intent.putExtra("data", Relogio.zerarHoraLong(getIntent().getLongExtra("data", 0)));
                startActivity(intent);
                break;
        }

        return(true);
    }
}
