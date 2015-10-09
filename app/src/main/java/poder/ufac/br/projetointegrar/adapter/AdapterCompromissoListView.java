package poder.ufac.br.projetointegrar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private ImageView miniatura, miniaturaCompleto, star;
    private Context context;

    public AdapterCompromissoListView(Context context, List<Compromisso> itens) {
        this.context = context;
        //Itens do listview
        this.itens = itens;
        //Objeto responsável por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
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
//            nomeTarefa.setText(item.getTarefa().getNome());
//            horario.setText(item.getHorario());
            miniatura.setImageResource(item.getTarefa().getMiniatura());
            star.setImageResource(R.drawable.icon_star_grey);
        }else{
//            nomeTarefaCompleto = ((TextView) v.findViewById(R.id.textViewCompromissoNomeTarefaItemCompleto));
//            horarioCompleto = (TextView) v.findViewById(R.id.textViewCompromissoHorarioItemCompleto);
            miniaturaCompleto = (ImageView) v.findViewById(R.id.imageViewCompromissoItemCompleto);
//            nomeTarefaCompleto.setText(item.getTarefa().getNome());
//            horarioCompleto.setText(item.getHorario());
            miniaturaCompleto.setImageResource(item.getTarefa().getMiniatura());
            star.setImageResource(R.drawable.icon_star_blue);
            LinearLayout ll = (LinearLayout) v.findViewById(R.id.layoutCompromisso);
            LinearLayout llc = (LinearLayout) v.findViewById(R.id.layoutCompromissoCompleto);
            ll.setVisibility(v.INVISIBLE);
            llc.setVisibility(v.VISIBLE);
        }
        return v;
    }

}
