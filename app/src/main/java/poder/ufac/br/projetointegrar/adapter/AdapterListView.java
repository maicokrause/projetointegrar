package poder.ufac.br.projetointegrar.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import poder.ufac.br.projetointegrar.R;
import poder.ufac.br.projetointegrar.cdp.Tarefa;


public class AdapterListView extends BaseAdapter{
    private final Context context;
    private LayoutInflater mInflater;
    private List<Tarefa> itens;
    private Bitmap[] bitmap;

    public AdapterListView(Context context, List<Tarefa> itens) {
        //Itens do listview
        this.itens = itens;
        this.context = context;
        //Objeto responsável por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
        converterImagem();
    }
    public int getCount() {
        return itens.size();
    }
    public Tarefa getItem(int position) {
        return itens.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View view, ViewGroup parent) {

        view = mInflater.inflate(R.layout.tarefa_item_list, null);
        TextView txtTitle = ((TextView) view.findViewById(R.id.nomeTarefaTextView));
        ImageView imgIcon = ((ImageView) view.findViewById(R.id.imageViewCompromissoItem));
        Tarefa item = itens.get(position);
        txtTitle.setText(item.getNome());
        imgIcon.setImageBitmap(bitmap[position]);
        return view;
    }

    public void converterImagem(){
        bitmap = new Bitmap[itens.size()];
        for(int i=0; i < itens.size(); i++){
            bitmap[i] = BitmapFactory.decodeResource(context.getResources(), itens.get(i).getMiniatura());
        }
    }
}
