package poder.ufac.br.projetointegrar.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public AgendaPagerAdapter(Context context, String[] datas) {
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        selecionaDia(datas[position]);
//        View list = pages.get(position);
        container.addView(listview);
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
    private void selecionaDia(String data) {
        dataAgenda = Relogio.dataStr(data);
//        Toast.makeText(this, "Data: " +dataAgenda.getTime(), Toast.LENGTH_SHORT).show();
//        Log.i("Data", dataAgenda.getTime() + "");

        //ORMlite
        dh = new DatabaseHelper(mContext);
        try {
            compromissoDao = new CompromissoDao(dh.getConnectionSource());
            tdao = new TarefaDao(dh.getConnectionSource());

            //listaCompromissos = compromissoDao.queryForAll();
//            QueryBuilder<Compromisso, Integer> queryBuilder = compromissoDao.queryBuilder();
//            queryBuilder.where().eq("data", new Date(dataAgenda.getYear(),dataAgenda.getMonth(),dataAgenda.getDay()));
//            PreparedQuery<Compromisso> query = queryBuilder.prepare();
//            listaCompromissos = compromissoDao.query(query);

            Map<String, Object> values = new HashMap<String, Object>();
            values.put("data", dataAgenda);
            listaCompromissos = compromissoDao.queryForFieldValues(values);
            for(Compromisso c : listaCompromissos){
                if(c.getTarefa() != null)
                    c.setTarefa(tdao.queryForId(Integer.parseInt(c.getTarefa().getId().toString())));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AdapterCompromissoListView adapter = new AdapterCompromissoListView(mContext, listaCompromissos);
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
                mContext.startActivity(intent);
            }
        });
    }
}