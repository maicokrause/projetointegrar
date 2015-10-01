package poder.ufac.br.projetointegrar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TarefaActivity extends AppCompatActivity {
    MediaPlayer player;
    AdapterImg adapter;
//    Bundle bundle = getIntent().getExtras();
    private int [] imagens = Tarefas.escovarDentesImagens; //getIntent().getIntArrayExtra("IMAGENS");//bundle.getIntArray("IMAGENS");//
//    {
//            R.drawable.escovando_dentes_01, R.drawable.escovando_dentes_02, R.drawable.escovando_dentes_03,
//            R.drawable.escovando_dentes_04, R.drawable.escovando_dentes_05, R.drawable.escovando_dentes_06,
//            R.drawable.escovando_dentes_07, R.drawable.escovando_dentes_08
//    };

    private int[] audios = Tarefas.escovarDentesAudio; //getIntent().getIntArrayExtra("AUDIO");//bundle.getIntArray("AUDIO");//

//    {
//            R.raw.escovar_dentes_01, R.raw.escovar_dentes_02, R.raw.escovar_dentes_03, R.raw.escovar_dentes_04,
//            R.raw.escovar_dentes_05, R.raw.escovar_dentes_06, R.raw.escovar_dentes_07, R.raw.escovar_dentes_08
//    };

    public void play(int i){
        player= MediaPlayer.create(this,i);
        player.start();
    }

    public void pause(){
        player.pause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        ViewPager vp = new ViewPager(this); //(ViewPager) findViewById(R.id.viewPager);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vp.setLayoutParams(lp);

        vp.setAdapter(new AdapterImg(this, imagens, audios));

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                play(audios[arg0]);
                String texto = getIntent().getStringExtra("teste")+" teste"+getIntent().hasExtra("IMAGENS");
                int duracao = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(TarefaActivity.this, texto, duracao);
                toast.show();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        LinearLayout ll = (LinearLayout) findViewById(R.id.tarefaLinearLayout);
        ll.addView(vp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tarefa, menu);
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
