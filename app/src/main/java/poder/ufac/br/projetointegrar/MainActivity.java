package poder.ufac.br.projetointegrar;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    MediaPlayer player;
    private int[] carros = {R.drawable.ferrari_laferrari, R.drawable.bugatti_veyron, R.drawable.pagani_zonda,
            R.drawable.porsche_911, R.drawable.mclaren, R.drawable.maserati_gran_turismo,
            R.drawable.lamborghini_veneno};

    private int [] escovando_dentes = {
            R.drawable.escovando_dentes_01, R.drawable.escovando_dentes_02, R.drawable.escovando_dentes_03,
            R.drawable.escovando_dentes_04, R.drawable.escovando_dentes_05, R.drawable.escovando_dentes_06,
            R.drawable.escovando_dentes_07, R.drawable.escovando_dentes_08
    };

    private int[] escovando_dentes_audio = {
            R.raw.escovar_dentes_01, R.raw.escovar_dentes_02, R.raw.escovar_dentes_03, R.raw.escovar_dentes_04,
            R.raw.escovar_dentes_05, R.raw.escovar_dentes_06, R.raw.escovar_dentes_07, R.raw.escovar_dentes_08
    };

    public void play(View v){
        player= MediaPlayer.create(MainActivity.this, R.raw.escovar_dentes_02);
        player.start();
    }

    public void pause(View v){
        player.pause();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager vp = (ViewPager) findViewById(R.id.viewPager);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//        vp.setLayoutParams(lp);

        vp.setAdapter(new AdapterImg(this, escovando_dentes));

//        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageSelected(int arg0) {
//                Log.i("Script", "onPageSelected()");
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//                Log.i("Script", "onPageScrolled()");
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//                Log.i("Script", "onPageScrollStateChanged()");
//            }
//        });

//        LinearLayout ll = (LinearLayout) findViewById(R.id.LinearLayout1);
//        ll.addView(vp);
    }

}
