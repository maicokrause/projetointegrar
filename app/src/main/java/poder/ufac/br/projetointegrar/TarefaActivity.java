package poder.ufac.br.projetointegrar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.sql.SQLException;

import poder.ufac.br.projetointegrar.adapter.AdapterImg;
import poder.ufac.br.projetointegrar.cdp.Compromisso;
import poder.ufac.br.projetointegrar.cdp.Tarefa;
import poder.ufac.br.projetointegrar.dao.CompromissoDao;
import poder.ufac.br.projetointegrar.dao.DatabaseHelper;
import poder.ufac.br.projetointegrar.dao.TarefaDao;
import poder.ufac.br.projetointegrar.util.GestureHelper;

public class TarefaActivity extends Activity {
    MediaPlayer player;
    AdapterImg adapter;
    LinearLayout ll;
    ImageView anterior;
    ImageView proximo;
    ImageView ivTitulo;
    Intent intent;
    private int [] imagens;
    private Bitmap[] bitmap;
    private int[] audios;
    private int posicaoAnterior = -1;
    private int posicaoProximo = 1;
    private Compromisso compromisso;
    private ViewPager vp;
    private LinearLayout layoutCompleta;
    private Handler handler = new Handler();
    public void play(int i){
        if(audios.length > 0) {
            player = MediaPlayer.create(this, audios[i]);
            player.start();
        }
    }

    public void pause(){
        player.pause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        compromisso = (Compromisso) intent.getSerializableExtra("compromisso");
        imagens = compromisso.getTarefa().getImagens();
        audios = compromisso.getTarefa().getAudio();
        converterImagem();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        //Imagem de titulo da tarefa
        ivTitulo = (ImageView) findViewById(R.id.imageViewTitle);
        ivTitulo.setImageResource(compromisso.getTarefa().getTitulo());

        anterior = (ImageView) findViewById(R.id.imageViewTarefaAnterior);
        proximo = (ImageView) findViewById(R.id.imageViewTarefaProximo);
        anterior.setOnTouchListener(new GestureHelper(this) {
            public void onClick() {
                imagemAnterior();
            }

            public void onSwipeRight() {
                imagemAnterior();
            }

            ;
        });
        proximo.setOnTouchListener(new GestureHelper(this) {
            public void onClick() {
                imagemProxima();
            }

            public void onSwipeLeft() {
                imagemProxima();
            }

            ;
        });
        proximo.setImageBitmap(bitmap[1]);
        //criação do viewPager
        vp = (ViewPager) findViewById(R.id.viewPagerTarefa); //new ViewPager(this); //
        AdapterImg adapterImg = new AdapterImg(this, bitmap, audios);
        vp.setAdapter(adapterImg);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(final int posicao) {
                play(posicao);
                if (posicao > 0) {
                    anterior.setImageBitmap(bitmap[posicao - 1]);
                    posicaoAnterior = posicao - 1;
                    if (posicao < bitmap.length - 1) {
                        proximo.setImageBitmap(bitmap[posicao + 1]);
                        posicaoProximo = posicao + 1;
                    } else {
                        if (posicao == bitmap.length - 1) {
                            proximo.setImageBitmap(BitmapFactory.decodeResource(getResources(), 0));
                            posicaoProximo = -1;
                            tarefaCompleta();
                        }
                    }
                } else {
                    if (posicao == 0) {
                        anterior.setImageBitmap(BitmapFactory.decodeResource(getResources(), 0));
                        proximo.setImageBitmap(bitmap[1]);
                        posicaoAnterior = -1;
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        play(0);
    }

    public void repetirTarefa(View v){
        vp.setCurrentItem(0);
        layoutCompleta.setVisibility(v.INVISIBLE);
    }

    public void finalizarTarefa(View v){
        intent = new Intent(this, AgendaViewPagerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void tarefaCompleta(){
        Thread thread = new Thread(run);
        thread.start();
    }

    private Runnable run = new Runnable() {

        @Override
        public void run() {
            Log.i("ProjetoIntegrar", "working..");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //ORMlite
            DatabaseHelper dh = new DatabaseHelper(TarefaActivity.this);
            try {
                CompromissoDao  compromissoDao = new CompromissoDao(dh.getConnectionSource());
                compromisso.setStatus(1);
                compromissoDao.update(compromisso);
            } catch (SQLException e) {e.printStackTrace();}
            layoutCompleta = (LinearLayout) findViewById(R.id.layoutTarefaCompleta);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    layoutCompleta.setVisibility(View.VISIBLE);
                }
            });
        }
    };

    public void converterImagem(){
        bitmap = new Bitmap[imagens.length];
        for(int i=0; i < imagens.length; i++){
           bitmap[i] = BitmapFactory.decodeResource(this.getResources(), imagens[i]);
        }
    }

    public void imagemAnterior(){
        if(posicaoAnterior > -1){
            vp.setCurrentItem(posicaoAnterior);
        }
    }
    public void imagemProxima(){
        if(posicaoProximo > -1){
            vp.setCurrentItem(posicaoProximo);
        }
    }
}
