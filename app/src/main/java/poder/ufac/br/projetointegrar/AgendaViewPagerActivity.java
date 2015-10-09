package poder.ufac.br.projetointegrar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import poder.ufac.br.projetointegrar.adapter.AgendaPagerAdapter;
import poder.ufac.br.projetointegrar.cdp.Compromisso;
import poder.ufac.br.projetointegrar.dao.CompromissoDao;
import poder.ufac.br.projetointegrar.dao.DatabaseHelper;
import poder.ufac.br.projetointegrar.dao.TarefaDao;
import poder.ufac.br.projetointegrar.util.Relogio;

public class AgendaViewPagerActivity extends Activity {
    private Context mContext;
    Intent intent;
    private Date dataAgenda;
    private DatabaseHelper dh;
    private CompromissoDao compromissoDao;
    private TarefaDao tdao;
    private List<Compromisso> listaCompromissos;
    private TextView titulo;
    private String[] datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_view_pager);
        mContext = this;

        Vector<View> pages = new Vector<View>();

        datas = new String[15];
        inicializaDatas();
        titulo = (TextView) findViewById(R.id.textViewTituloAgendaViewPager);
        titulo.setText("Agenda do dia " + datas[8]);
        ViewPager vp = (ViewPager) findViewById(R.id.viewpager);
//        AgendaPagerAdapter adapter = new AgendaPagerAdapter(mContext,pages);
        vp.setAdapter(new AgendaPagerAdapter(mContext, datas));
        vp.setCurrentItem(7);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(final int posicao) {
                titulo.setText("Agenda do dia " + datas[posicao]);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });



    }

    private void inicializaDatas(){
        Date hoje = new Date();
        for (int i=-7; i<=7; i++){
            datas[i+7] = Relogio.converteParaString(new Date(hoje.getTime()+((1000*24*60*60)*i)));
            Log.i("Data", datas[i+7]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agente_view_pager, menu);
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
