package poder.ufac.br.projetointegrar;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import poder.ufac.br.projetointegrar.cdp.Compromisso;
import poder.ufac.br.projetointegrar.dao.CompromissoDao;
import poder.ufac.br.projetointegrar.dao.DatabaseHelper;

public class ListarCompromissoActivity extends ActionBarActivity {
    //ORMlite
    private DatabaseHelper dh;
    private CompromissoDao compromissoDao;
    private ListView listViewCompromissos;
    private List<Compromisso> listaCompromissos;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_compromisso);
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        listViewCompromissos = (ListView) findViewById(R.id.listViewListarCompromissos);
        //ORMlite
        dh = new DatabaseHelper(this);
        try {
            compromissoDao = new CompromissoDao(dh.getConnectionSource());

            listaCompromissos = compromissoDao.queryForAll();

            QueryBuilder<Compromisso, Integer> queryBuilder = compromissoDao.queryBuilder();

            queryBuilder.where().eq("data", getIntent().getLongExtra("data",1L));
            PreparedQuery<Compromisso> query = queryBuilder.prepare();

            listaCompromissos = compromissoDao.query(query);
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
                Toast.makeText(this, "Logo botão", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itemMenuAdicionarCompromisso:
                intent = new Intent(this, AdicionarCompromissosActivity.class);
                startActivity(intent);
                break;
        }

        return(true);
    }
}
