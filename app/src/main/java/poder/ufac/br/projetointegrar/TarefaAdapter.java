package poder.ufac.br.projetointegrar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Levi Cacau on 02/10/2015.
 */
public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder> {
    private List<Tarefa> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;


    public TarefaAdapter(Context c, List<Tarefa> l){
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.i("LOG", "onCreateViewHolder()");
        View v = mLayoutInflater.inflate(R.layout.tarefa_item_list, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Log.i("LOG", "onBindViewHolder()");
        myViewHolder.miniatura.setImageResource(mList.get(position).getMiniatura());
        myViewHolder.nomeTarefa.setText(mList.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;
    }


    public void addListItem(Tarefa c, int position){
        mList.add(c);
        notifyItemInserted(position);
    }


    public void removeListItem(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public Tarefa getTarefa(int position){
        return mList.get(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView miniatura;
        public TextView nomeTarefa;

        public MyViewHolder(View itemView) {
            super(itemView);

            miniatura = (ImageView) itemView.findViewById(R.id.miniaturaImageView);
            nomeTarefa = (TextView) itemView.findViewById(R.id.nomeTarefaTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListenerHack != null){
                mRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());
            }
        }
    }
}
