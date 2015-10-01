package poder.ufac.br.projetointegrar;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
    Intent intent;
    public void abrirActivityEscovarDentes(View view){
//        String texto = Tarefas.escovarDentesAudio[1]+" teste";
//        int duracao = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(this, texto, duracao);
//        toast.show();
        intent = new Intent(this, TarefaActivity.class);

        setListas(Tarefas.escovarDentesImagens, Tarefas.escovarDentesAudio);

//        intent.putExtra("teste", "teste");
        startActivity(intent);
    }

    public void abrirActivitySpeechText(View view){
        intent = new Intent(this, SpeechActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setListas(int[] imagens, int[] audio){
        Bundle b = new Bundle();
        b.putIntArray("IMAGENS", imagens);
        b.putIntArray("AUDIO", audio);
        intent.putExtras(b);
    }

}
