package poder.ufac.br.projetointegrar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import poder.ufac.br.projetointegrar.cdp.Compromisso;

/**
 * Created by Levi Cacau on 01/10/2015.
 */
public class AdapterCompromissoListView extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<Compromisso> itens;
    private TextView nomeTarefa, horario;
    private ImageView miniatura;
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
        nomeTarefa = ((TextView) v.findViewById(R.id.textViewCompromissoNomeTarefaItem));
        horario = (TextView) v.findViewById(R.id.textViewCompromissoHorarioItem);
        miniatura = (ImageView) v.findViewById(R.id.imageViewMiniaturaAdicionarCompromisso);
        //pega os dados da lista
        //e define os valores nos itens.
        Compromisso item = itens.get(position);
        nomeTarefa.setText(item.getTarefa().getNome());
        horario.setText(item.getHorario());
        miniatura.setImageResource(item.getTarefa().getMiniatura());
        return v;
    }

}
