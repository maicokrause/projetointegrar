package poder.ufac.br.projetointegrar;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import poder.ufac.br.projetointegrar.adapter.AdapterCompromissoListView;
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
    private Intent intent;
    private AdapterCompromissoListView adapterListView;
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
        textoTitulo.setShadowLayer(5, 10, 5, Color.BLACK);
        Typeface font = Typeface.createFromAsset(getAssets(), "snap_itc.ttf");
        textoTitulo.setTypeface(font);
        textoTitulo.setText(Relogio.getDiaSemana(dataString)+" "+ dataString);
        //ORMlite
        dh = new DatabaseHelper(this);
        try {
            compromissoDao = new CompromissoDao(dh.getConnectionSource());
            tdao = new TarefaDao(dh.getConnectionSource());
            Date dataAgenda = Relogio.zerarHoraDate(new Date(getIntent().getLongExtra("data", 0)));
//            Map<String, Object> values = new HashMap<String, Object>();
//            values.put("data", Relogio.zerarHoraDate(new Date(getIntent().getLongExtra("data", 0))));
//            listaCompromissos = compromissoDao.queryForFieldValues(values);
//            for(Compromisso c : listaCompromissos){
//                c.setTarefa(tdao.queryForId(Integer.parseInt(c.getTarefa().getId().toString())));
//            }
            QueryBuilder<Compromisso, Integer> queryBuilder = compromissoDao.queryBuilder();
            queryBuilder.where().eq("data", dataAgenda).or().eq("repetir", 1);
            PreparedQuery<Compromisso> query = queryBuilder.prepare();
            listaCompromissos = compromissoDao.query(query);

            List<Compromisso> lc = new ArrayList<Compromisso>();
            List<Compromisso> lcCompletos = new ArrayList<Compromisso>();
            for(Compromisso c : listaCompromissos){
                if(c.getRepetir() == 1){        //Se repetir
                    if(Relogio.comparaData(c.getData(), dataAgenda)){//se a data for o dia atual
                        if (c.getTarefa() != null)
                            c.setTarefa(tdao.queryForId(Integer.parseInt(c.getTarefa().getId().toString())));
                        if(c.getStatus() == 1)
                            lcCompletos.add(c);
                        else
                            lc.add(c);
                    }else {       //se nao for a data atual, verificar se é o dia da semana
                        for (int i = 0; i < c.getDiasSemana().length; i++) {
                            if (c.getDiasSemana()[i] == Relogio.getDayOfWeek(dataAgenda)) {
                                if (c.getTarefa() != null)
                                    c.setTarefa(tdao.queryForId(Integer.parseInt(c.getTarefa().getId().toString())));
                                if(c.getStatus() == 1)
                                    lcCompletos.add(c);
                                else
                                    lc.add(c);
                            }
                        }
                    }
                }else {
                    if (c.getTarefa() != null)
                        c.setTarefa(tdao.queryForId(Integer.parseInt(c.getTarefa().getId().toString())));
                    if(c.getStatus() == 1)
                        lcCompletos.add(c);
                    else
                        lc.add(c);
                }
            }

            listaCompromissos = lcCompletos;
            for (Compromisso c: lc) {
                boolean diferente = true;
                for (Compromisso o: lcCompletos) {
                    if(c.getHorario().equals(o.getHorario()) && c.getTarefa().getId() == o.getTarefa().getId()){
                        diferente = false;
                        break;
                    }
                }
                if(diferente)
                    listaCompromissos.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapterListView = new AdapterCompromissoListView(this, listaCompromissos);
        listViewCompromissos.setAdapter(adapterListView);
        listViewCompromissos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int item, long id) {
                editarCompromisso((Compromisso) adapter.getItemAtPosition(item));
//                Toast.makeText(ListarCompromissoActivity.this, "Tarefa "+c.getTarefa().getId(), Toast.LENGTH_SHORT).show();
            }
        });
        listViewCompromissos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(final AdapterView<?> adapter, View arg1,
                                           final int item, long id) {
                final Compromisso c = (Compromisso) adapter.getItemAtPosition(item);
//                Toast.makeText(ListarCompromissoActivity.this, "On long press: "+c.getTarefa().getNome(), Toast.LENGTH_SHORT).show();
                final CharSequence colors[] = new CharSequence[] {"Editar", "Excluir", "Cancelar"};

                AlertDialog.Builder builder = new AlertDialog.Builder(ListarCompromissoActivity.this);
                builder.setTitle("Selecione uma opção");
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                editarCompromisso((Compromisso) adapter.getItemAtPosition(item));
                                break;
                            case 1:
                                excluirCompromisso((Compromisso) adapter.getItemAtPosition(item));
                        }
                    }
                });
                builder.show();


                return true;
            }
        });
    }

    public void editarCompromisso(Compromisso c){
        intent = new Intent(ListarCompromissoActivity.this, AdicionarCompromissosActivity.class);
        intent.putExtra("compromisso", c);
        startActivity(intent);
    }

    public void excluirCompromisso(final Compromisso c){
        new AlertDialog.Builder(ListarCompromissoActivity.this).setTitle("Excluir")
                .setMessage("Tem certeza que deseja excluir esta tarefa?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            compromissoDao.delete(c);
                            adapterListView.deletarItem(c);
                            adapterListView.notifyDataSetChanged();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }).setNegativeButton("Nao", null).show();
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
