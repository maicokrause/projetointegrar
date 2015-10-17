package poder.ufac.br.projetointegrar;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import poder.ufac.br.projetointegrar.adapter.AdapterListView;
import poder.ufac.br.projetointegrar.cdp.Compromisso;
import poder.ufac.br.projetointegrar.cdp.Tarefa;
import poder.ufac.br.projetointegrar.dao.CompromissoDao;
import poder.ufac.br.projetointegrar.dao.DatabaseHelper;
import poder.ufac.br.projetointegrar.dao.TarefaDao;
import poder.ufac.br.projetointegrar.util.Relogio;

public class AdicionarCompromissosActivity extends ActionBarActivity {

    //ORMlite
    private DatabaseHelper dh;
    private TarefaDao tarefaDao;

    private List<Tarefa> mList;
    private ListView listaTarefa;
    private List<Tarefa> listaTarefas = new ArrayList<Tarefa>();
    private ImageView miniatura;
    private TextView nomeTarefa;
    private TimePicker tp;
    private Compromisso c;
    private CompromissoDao compromissoDao;
    private Intent intent;
    private Tarefa t;
    private Date data;
    private CheckBox[] diasSemana;
    private int[] semana;
    private boolean repetir = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_compromissos);

        //ORMlite
        dh = new DatabaseHelper(this);
        try {
            tarefaDao = new TarefaDao(dh.getConnectionSource());
            compromissoDao = new CompromissoDao(dh.getConnectionSource());
        } catch (SQLException e) {e.printStackTrace();}
        carregaTarefas();

        //habilita o botão voltar
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        //
        miniatura = (ImageView) findViewById(R.id.imageViewMiniaturaAdicionarCompromisso);
        nomeTarefa = (TextView) findViewById(R.id.textViewAdicionarCompromissoNomeTarefa);
        tp = (TimePicker) findViewById(R.id.timePickerAdicionarCompromisso);
        carregaCheckBox();
        AdapterListView adapter = new AdapterListView(this, listaTarefas);
        listaTarefa = (ListView) findViewById(R.id.listViewAdicionarCompromissos);
        listaTarefa.setAdapter(adapter);
        listaTarefa.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int item, long id) {

                t = (Tarefa) adapter.getItemAtPosition(item);
//        Toast.makeText(this, "Tarefa: "+t.getNome(), Toast.LENGTH_SHORT).show();
                miniatura.setImageResource(t.getMiniatura());
                nomeTarefa.setText(t.getNome());
            }
        });

        intent = getIntent();
        data = Relogio.zerarHoraDate(new Date(intent.getLongExtra("data", 0)));

        if(intent.hasExtra("compromisso")) {
            c = (Compromisso) intent.getSerializableExtra("compromisso");
            miniatura.setImageResource(c.getTarefa().getMiniatura());
            nomeTarefa.setText(c.getTarefa().getNome());
            tp.setCurrentHour(Integer.parseInt(c.getHorario().substring(0, 2)));
            tp.setCurrentMinute(Integer.parseInt(c.getHorario().substring(3, 5)));
            t = c.getTarefa();
            data = Relogio.zerarHoraDate(c.getData());
            if(c.getRepetir() == 1) {
                for (int i : c.getDiasSemana()) {
                    switch (i) {
                        case Calendar.SUNDAY:
                            diasSemana[0].setChecked(true);
                            break;
                        case Calendar.MONDAY:
                            diasSemana[1].setChecked(true);
                            break;
                        case Calendar.TUESDAY:
                            diasSemana[2].setChecked(true);
                            break;
                        case Calendar.WEDNESDAY:
                            diasSemana[3].setChecked(true);
                            break;
                        case Calendar.THURSDAY:
                            diasSemana[4].setChecked(true);
                            break;
                        case Calendar.FRIDAY:
                            diasSemana[5].setChecked(true);
                            break;
                        case Calendar.SATURDAY:
                            diasSemana[6].setChecked(true);
                            break;
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//            intent = new Intent(AdicionarCompromissosActivity.this, ListarCompromissoActivity.class);
//            intent.putExtra("data", data.getTime());
//            startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adicionar_compromissos, menu);
        if(!intent.hasExtra("compromisso"))
            menu.findItem(R.id.itemMenuExcluirCompromisso).setVisible(false);
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
                intent = new Intent(AdicionarCompromissosActivity.this, ListarCompromissoActivity.class);
                intent.putExtra("data", Relogio.zerarHoraLong((data).getTime()));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                NavUtils.navigateUpTo(this, intent);
                break;
            case R.id.itemMenuExcluirCompromisso:
                new AlertDialog.Builder(AdicionarCompromissosActivity.this).setTitle("Excluir")
                        .setMessage("Tem certeza que deseja excluir esta tarefa?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    compromissoDao.delete(c);
                                    intent = new Intent(AdicionarCompromissosActivity.this, ListarCompromissoActivity.class);
                                    intent.putExtra("data", Relogio.zerarHoraLong((data).getTime()));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).setNegativeButton("Nao", null).show();
                break;
            case R.id.itemMenuSalvarCompromisso:
                if(salvarCompromisso()){
                    intent = new Intent(AdicionarCompromissosActivity.this, ListarCompromissoActivity.class);
                    intent.putExtra("data", Relogio.zerarHoraLong((data).getTime()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                break;
        }

        return(true);
    }

    public boolean salvarCompromisso(){
        if(t == null){
            Toast.makeText(this, "Selecione uma tarefa na lista", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            if( c == null) {
                c = new Compromisso();
                data = Relogio.zerarHoraDate(new Date(intent.getLongExtra("data",0)));
                c.setData(data);
                c.setHorario(String.format("%02d", tp.getCurrentHour()) + ":" + String.format("%02d", tp.getCurrentMinute()));
                c.setTarefa(t);
                c.setStatus(0);
                c.setDiasSemana(setDiasSemana());
                if(repetir)
                    c.setRepetir(1);
                else
                    c.setRepetir(0);
                Log.i("Repetir:::::::::", c.getRepetir()+"");
                compromissoDao.create(c);
            }else {
                data = Relogio.zerarHoraDate(c.getData());
                c.setHorario(String.format("%02d", tp.getCurrentHour()) + ":" + String.format("%02d", tp.getCurrentMinute()));
                c.setTarefa(t);
                c.setDiasSemana(setDiasSemana());
                if(repetir)
                    c.setRepetir(1);
                else
                    c.setRepetir(0);
                Log.i("Repetir:::::::::", c.getRepetir()+"");
                compromissoDao.update(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void carregaTarefas() {
        // GET ALL LINES
        try {
            listaTarefas = tarefaDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void carregaCheckBox(){
        CheckBox[] diasSemana =  {
                (CheckBox) findViewById(R.id.checkBoxDom),
                (CheckBox) findViewById(R.id.checkBoxSeg),
                (CheckBox) findViewById(R.id.checkBoxTer),
                (CheckBox) findViewById(R.id.checkBoxQua),
                (CheckBox) findViewById(R.id.checkBoxQui),
                (CheckBox) findViewById(R.id.checkBoxSex),
                (CheckBox) findViewById(R.id.checkBoxSab),
        };
        this.diasSemana = diasSemana;
    }

    private int[] setDiasSemana(){
        semana = new int[7];
        for(int i=0; i<7; i++){
            if(diasSemana[i].isChecked()) {
                repetir = true;
                semana[i] = i+1;
            }
        }
        return semana;
    }
}
