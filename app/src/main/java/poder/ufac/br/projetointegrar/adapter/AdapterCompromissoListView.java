package poder.ufac.br.projetointegrar.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import poder.ufac.br.projetointegrar.R;
import poder.ufac.br.projetointegrar.cdp.Compromisso;

/**
 * Created by Levi Cacau on 01/10/2015.
 */
public class AdapterCompromissoListView extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<Compromisso> itens;
    private TextView nomeTarefa, horario,nomeTarefaCompleto, horarioCompleto;
    private ImageView miniatura, miniaturaCompleto, star, periodo, periodoCompleto;
    private Context context;
    private Bitmap[] bitmap, bitmapIcones;

    public AdapterCompromissoListView(Context context, List<Compromisso> itens) {
        this.context = context;
        //Itens do listview
        this.itens = itens;
        Collections.sort(itens, new ComparatorCompromisso(true));
        //Objeto responsável por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
        converterImagem();
    }
    public List<Compromisso> getLista(){
        return itens;
    }
    public int getCount() {
        return itens.size();
    }
    public Compromisso getItem(int position) {
        return itens.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View view, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.compromisso_item_list, null);
        Compromisso item = itens.get(position);
        star = (ImageView) v.findViewById(R.id.imageViewStarCompromisso);
        if(item.getStatus() == 0){
//            nomeTarefa = ((TextView) v.findViewById(R.id.textViewCompromissoNomeTarefaItem));
//            horario = (TextView) v.findViewById(R.id.textViewCompromissoHorarioItem);
            miniatura = (ImageView) v.findViewById(R.id.imageViewCompromissoItem);
            periodo = (ImageView) v.findViewById(R.id.imageViewPeriodoCompromisso);
//            nomeTarefa.setText(item.getTarefa().getNome());
//            horario.setText(item.getHorario());
            int horas = Integer.parseInt(item.getHorario().substring(0,2)+item.getHorario().substring(3,5));
            if(horas < 1200){
                periodo.setImageBitmap(bitmapIcones[0]);
            }else{
                if (horas >= 1200 && horas < 1800 ){
                    periodo.setImageBitmap(bitmapIcones[1]);
                }else{
                    periodo.setImageBitmap(bitmapIcones[2]);
                }
            }
            miniatura.setImageBitmap(bitmap[position]);
            star.setImageBitmap(bitmapIcones[4]);
        }else{
//            nomeTarefaCompleto = ((TextView) v.findViewById(R.id.textViewCompromissoNomeTarefaItemCompleto));
//            horarioCompleto = (TextView) v.findViewById(R.id.textViewCompromissoHorarioItemCompleto);
            miniaturaCompleto = (ImageView) v.findViewById(R.id.imageViewCompromissoItemCompleto);
            periodoCompleto = (ImageView) v.findViewById(R.id.imageViewPeriodoCompromissoCompleto);
//            nomeTarefaCompleto.setText(item.getTarefa().getNome());
//            horarioCompleto.setText(item.getHorario());
            int horas = Integer.parseInt(item.getHorario().substring(0,2)+item.getHorario().substring(3,5));
            if(horas < 1200){
                periodoCompleto.setImageBitmap(bitmapIcones[0]);
            }else{
                if (horas >= 1200 && horas < 1800 ){
                    periodoCompleto.setImageBitmap(bitmapIcones[1]);
                }else{
                    periodoCompleto.setImageBitmap(bitmapIcones[2]);
                }
            }
            miniaturaCompleto.setImageBitmap(bitmap[position]);
            star.setImageBitmap(bitmapIcones[3]);
            LinearLayout ll = (LinearLayout) v.findViewById(R.id.layoutCompromisso);
            LinearLayout llc = (LinearLayout) v.findViewById(R.id.layoutCompromissoCompleto);
            ll.setVisibility(v.INVISIBLE);
            llc.setVisibility(v.VISIBLE);
        }
        return v;
    }

    public void deletarItem(Compromisso compromisso){
        itens.remove(compromisso);
    }

    class ComparatorCompromisso implements Comparator {
        boolean crescente = true;

        public ComparatorCompromisso(boolean crescente) {
            this.crescente = crescente;
        }

        public int compare(Object o1, Object o2) {
            Compromisso c1 = (Compromisso) o1;
            Compromisso c2 = (Compromisso) o2;
            int hora1 = Integer.parseInt(c1.getHorario().substring(0,2)+c1.getHorario().substring(3,5));
            int hora2 = Integer.parseInt(c2.getHorario().substring(0,2)+c2.getHorario().substring(3,5));
            if (crescente) {
                return hora1 < hora2 ? -1 : (hora1 > hora2 ? +1 : 0);
            } else {
                return hora1 < hora2 ? +1 : (hora1 > hora2 ? -1 : 0);
            }
        }
    }

    public void converterImagem(){
        bitmap = new Bitmap[itens.size()];
        for(int i=0; i < itens.size(); i++){
            bitmap[i] = BitmapFactory.decodeResource(context.getResources(), itens.get(i).getTarefa().getMiniatura());
        }
        bitmapIcones = new Bitmap[5];
        bitmapIcones[0] = BitmapFactory.decodeResource(context.getResources(),  R.drawable.titulo_manha);
        bitmapIcones[1] = BitmapFactory.decodeResource(context.getResources(),  R.drawable.titulo_tarde);
        bitmapIcones[2] = BitmapFactory.decodeResource(context.getResources(),  R.drawable.titulo_noite);
        bitmapIcones[3] = BitmapFactory.decodeResource(context.getResources(),  R.drawable.icon_star_blue);
        bitmapIcones[4] = BitmapFactory.decodeResource(context.getResources(),  R.drawable.icon_star_grey);
    }
}
