package poder.ufac.br.projetointegrar;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AdapterImg extends PagerAdapter {
    private Context context;
    private int[] imgs;
    private int[] audio;
    MediaPlayer player;

    public AdapterImg(Context context, int[] imgs, int[] audio){
        this.context = context;
        this.imgs = imgs;
        this.audio = audio;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imgs.length;
    }



    @Override
    public boolean isViewFromObject(View view, Object obj) {
        // TODO Auto-generated method stub
//        Log.i("Script", "view == obj: "+((view == obj) ? "1" : "0"));
//        Log.i("Script", "view == ((TextView) obj).getParent(): "+((view == ((TextView) obj).getParent()) ? "1" : "0"));
        return view == ((TextView) obj).getParent();
//        return view == obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){

        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(lp);
        container.addView(ll);

        ImageView iv = new ImageView(context);
        iv.setImageResource(imgs[position]);
        ll.addView(iv);

        TextView tv = new TextView(context);
        tv.setText("Tarefa " + (position + 1));
        ll.addView(tv);

//        String texto = "exemplo toast"+audio[position];
//        int duracao = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, texto, duracao);
//        toast.show();
//
//        player= MediaPlayer.create(context, audio[position]);
//        player.start();

//        Log.i("Script", "Build: Carro: "+(position + 1));

        return(tv);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view){
//        Log.i("Script", "Destroy: Carro: "+(position + 1));
        container.removeView((View)((TextView)view).getParent());
//        container.removeView((View)view);
    }

}
