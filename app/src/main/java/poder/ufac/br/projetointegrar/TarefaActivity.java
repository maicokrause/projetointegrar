package poder.ufac.br.projetointegrar;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

    private int[] audios;

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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        //Imagem de titulo da tarefa
        ivTitulo = (ImageView) findViewById(R.id.imageViewTitle);
        ivTitulo.setImageResource(intent.getIntExtra("titulo",0));

        //criação do viewPager
        ViewPager vp = new ViewPager(this); //(ViewPager) findViewById(R.id.viewPager);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        vp.setLayoutParams(lp);
        vp.setAdapter(new AdapterImg(this, imagens, audios));
        //barra que contem as imagens
        ll = (LinearLayout) findViewById(R.id.tarefaLinearLayout);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int posicao) {
                play(audios[posicao]);
//                if(posicao > 0) {
//                    anterior.setImageResource(imagens[posicao - 1]);
//                }
//                if(posicao < imagens.length -1){
//                    proximo.setImageResource(imagens[posicao + 1]);
//                }
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

//        anterior = new ImageView(TarefaActivity.this);
//        anterior.setAdjustViewBounds(true);
//        anterior.setImageResource(imagens[0]);
//        ll.addView(anterior);

        ll.addView(vp);

//        proximo = new ImageView(TarefaActivity.this);
//        proximo.setAdjustViewBounds(true);
//        proximo.setImageResource(imagens[0]);
//        ll.addView(proximo);
        play(audios[0]);
    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_tarefa, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
