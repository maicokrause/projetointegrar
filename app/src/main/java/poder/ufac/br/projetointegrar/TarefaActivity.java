package poder.ufac.br.projetointegrar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TarefaActivity extends Activity {
    MediaPlayer player;
    AdapterImg adapter;
    LinearLayout ll;
    ImageView anterior;
    ImageView proximo;
    ImageView ivTitulo;
//    Bundle bundle = getIntent().getExtras();
    Intent intent;
    private int [] imagens;
    private Bitmap[] bitmap;
    private int[] audios;
    private int posicaoAnterior = -1;
    private int posicaoProximo = 1;
    ViewPager vp;
    public void play(int i){
        player= MediaPlayer.create(this,i);
        player.start();
    }

    public void pause(){
        player.pause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        imagens = intent.getIntArrayExtra("imagens");
        audios = intent.getIntArrayExtra("audio");
        converterImagem();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        //Imagem de titulo da tarefa
        ivTitulo = (ImageView) findViewById(R.id.imageViewTitle);
        ivTitulo.setImageResource(intent.getIntExtra("titulo",0));

        anterior = (ImageView) findViewById(R.id.imageViewTarefaAnterior);
        final GestureDetector gdt = new GestureDetector(this, new GestureListener());
        anterior.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (gdt.onTouchEvent(event)) {
                    return false;
                }
                return true;
            }
        });
        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posicaoAnterior > -1) {
                    vp.setCurrentItem(posicaoAnterior);
                }
            }
        });
        proximo = (ImageView) findViewById(R.id.imageViewTarefaProximo);
        proximo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (gdt.onTouchEvent(event)) {
                    return false;
                }
                return true;
            }
        });
        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posicaoProximo > -1){
                    vp.setCurrentItem(posicaoProximo);
                }
            }
        });
        proximo.setImageBitmap(bitmap[1]);
        //criação do viewPager
        vp = (ViewPager) findViewById(R.id.viewPagerTarefa); //new ViewPager(this); //
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        vp.setLayoutParams(lp);
        AdapterImg adapterImg = new AdapterImg(this, bitmap, audios);

        vp.setAdapter(adapterImg);
        //barra que contem as imagens
        //ll = (LinearLayout) findViewById(R.id.tarefaLinearLayoutHorizontal);
//        vp.setCurrentItem();
        //adapter.
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(final int posicao) {
                play(audios[posicao]);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (posicao > 0) {
                            anterior.setImageBitmap(bitmap[posicao - 1]);
                            posicaoAnterior = posicao - 1;
                            if (posicao < bitmap.length - 1) {
                                proximo.setImageBitmap(bitmap[posicao + 1]);
                                posicaoProximo = posicao + 1;
                            }else {
                                if (posicao == bitmap.length - 1) {
                                    proximo.setImageBitmap(BitmapFactory.decodeResource(getResources(), 0));
                                    posicaoProximo = -1;
                                }
                            }
                        }else {
                            if (posicao == 0) {
                                anterior.setImageBitmap(BitmapFactory.decodeResource(getResources(), 0));
                                posicaoAnterior = -1;
                            }
                        }
                    }
                });

//                String texto = getIntent().getStringExtra("teste")+" teste"+getIntent().hasExtra("IMAGENS");
//                int duracao = Toast.LENGTH_SHORT;
//                Toast toast = Toast.makeText(TarefaActivity.this, texto, duracao);
//                toast.show();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

//        proximo.setOnTouchListener(new View.OnTouchListener() {
//            GestureDetector gestureDetector = new GestureDetector(new SwingGestureDetection((mContext), image, a));
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return gestureDetector.onTouchEvent(event);
//            }
//        });

        play(audios[0]);
    }

    public void converterImagem(){
        bitmap = new Bitmap[imagens.length];
        for(int i=0; i < imagens.length; i++){
           bitmap[i] = BitmapFactory.decodeResource(this.getResources(), imagens[i]);
        }
    }

    public void imagemAnterior(View v){
        if(posicaoAnterior > -1){
            vp.setCurrentItem(posicaoAnterior);
        }
    }
    public void imagemProxima(View v){
        if(posicaoProximo > -1){
            vp.setCurrentItem(posicaoProximo);
        }
    }

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//                Toast.makeText(TarefaActivity.this, "Right to left", Toast.LENGTH_SHORT).show();
                if(posicaoProximo > -1){
                    vp.setCurrentItem(posicaoProximo);
                }
                return false; // Right to left
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                if(posicaoAnterior > -1){
                    vp.setCurrentItem(posicaoAnterior);
                }
                return false; // Left to right
            }

            if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                return false; // Bottom to top
            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                return false; // Top to bottom
            }
            return false;
        }
    }
}
