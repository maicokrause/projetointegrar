package poder.ufac.br.projetointegrar.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import poder.ufac.br.projetointegrar.R;
import poder.ufac.br.projetointegrar.TarefaActivity;
import poder.ufac.br.projetointegrar.cdp.Compromisso;
import poder.ufac.br.projetointegrar.cdp.Tarefa;
import poder.ufac.br.projetointegrar.dao.CompromissoDao;
import poder.ufac.br.projetointegrar.dao.DatabaseHelper;
import poder.ufac.br.projetointegrar.dao.TarefaDao;
import poder.ufac.br.projetointegrar.util.Relogio;

public class AgendaPagerAdapter extends PagerAdapter {

    private Vector<View> pages;
    private Context mContext;
    Intent intent;
    private Date dataAgenda;
    private DatabaseHelper dh;
    private CompromissoDao compromissoDao;
    private TarefaDao tdao;
    private List<Compromisso> listaCompromissos;
    ListView listview;
    private String[] datas;
    private AdapterCompromissoListView adapter;

    public AgendaPagerAdapter(Context context, String[] datas) {
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        selecionaDia(datas[position]);
//        View list = pages.get(position);
        container.addView(listview);
        listview.setSelection(selecionaCompromisso());
        return listview;
    }

    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private int selecionaCompromisso(){
        DateFormat df = new SimpleDateFormat("HHmm");
        String horaAtual = df.format(new Date());
        int horas = Integer.parseInt(horaAtual);
        List<Compromisso> listaAdapter = adapter.getLista();

        for(Compromisso o : listaAdapter){
            int h = Integer.parseInt(o.getHorario().substring(0, 2) + o.getHorario().substring(3, 5));
            if(horas < 1200 && h < 1200){
                return listaAdapter.indexOf(o);
            }else{
                if ((horas >= 1200 && horas < 1800) && (h >= 1200 && h < 1800)){
                    return listaAdapter.indexOf(o);
                }else{
                    if ((horas >= 1800) && (h >= 1800)) {
                        return listaAdapter.indexOf(o);
                    }
                }
            }
        }
        return 0;
    }

    private void selecionaDia(final String data) {
        dataAgenda = Relogio.dataStr(data);
//        Toast.makeText(this, "Data: " +dataAgenda.getTime(), Toast.LENGTH_SHORT).show();
//        Log.i("Data", dataAgenda.getTime() + "");

        //ORMlite
        dh = new DatabaseHelper(mContext);
        try {
            compromissoDao = new CompromissoDao(dh.getConnectionSource());
            tdao = new TarefaDao(dh.getConnectionSource());

            //listaCompromissos = compromissoDao.queryForAll();

//            Map<String, Object> values = new HashMap<String, Object>();
//            values.put("data", dataAgenda);
//            listaCompromissos = compromissoDao.queryForFieldValues(values);

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
        adapter = new AdapterCompromissoListView(mContext, listaCompromissos);
        listview = new ListView(mContext);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int item, long id) {
                Compromisso c = (Compromisso) adapter.getItemAtPosition(item);
                intent = new Intent(mContext, TarefaActivity.class);

                Tarefa t = (Tarefa) c.getTarefa();
//        Toast.makeText(this, "Tarefa: "+t.getNome(), Toast.LENGTH_SHORT).show();
                intent.putExtra("compromisso", c);
                intent.putExtra("data", Relogio.dataStr(data).getTime());
//                Log.i("Log", "Data "+data);
                mContext.startActivity(intent);
            }
        });
    }
}