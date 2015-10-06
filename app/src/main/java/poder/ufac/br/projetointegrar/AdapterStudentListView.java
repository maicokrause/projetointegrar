package poder.ufac.br.projetointegrar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import poder.ufac.br.projetointegrar.cdp.Student;

/**
 * Created by Levi Cacau on 01/10/2015.
 */
public class AdapterStudentListView extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<Student> itens;
    private TextView nomeStudent;
    private TextView idStudent;
    private Context context;

    public AdapterStudentListView(Context context, List<Student> itens) {
        this.context = context;
        //Itens do listview
        this.itens = itens;
        //Objeto responsável por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }
    public int getCount() {
        return itens.size();
    }
    public Student getItem(int position) {
        return itens.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View view, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.student_item_list, null);
        nomeStudent = ((TextView) v.findViewById(R.id.textViewNomeStudent));
        idStudent = (TextView) v.findViewById(R.id.textViewIdStudent);

        //pega os dados da lista
        //e define os valores nos itens.
        Student item = itens.get(position);
        nomeStudent.setText(item.getName());
        idStudent.setText(item.getId()+"");
        return v;
    }

}
