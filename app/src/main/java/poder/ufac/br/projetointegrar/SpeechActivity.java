package poder.ufac.br.projetointegrar;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class SpeechActivity extends Activity implements TextToSpeech.OnInitListener {
    private Button   btnFalarTexto;
    private EditText edtTexto;

    private TextToSpeech TTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TTS = new TextToSpeech(this, this);

        btnFalarTexto = (Button) findViewById(R.id.btn_falar_texto);

        edtTexto = (EditText) findViewById(R.id.edt_texto);


        btnFalarTexto.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                ConvertendoTextoParaVoz(edtTexto.getText().toString());

            }
        });

    }

    @Override
    public void onDestroy() {

        if (TTS != null) {
            TTS.stop();
            TTS.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = TTS.setLanguage(Locale.getDefault()); // Aqui estou definindo o idioma padrão do celular poderia ser um idioma específico(ex:Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                //Aqui você verifica se o aparelho possui suporte ao pacote de voz
                Log.e("TTS", "Não tem suporte para este idioma");
                btnFalarTexto.setEnabled(false);
            } else {
                btnFalarTexto.setEnabled(true);

                ConvertendoTextoParaVoz("Seja Bem Vindo ao Teste de Voz");
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    private void ConvertendoTextoParaVoz( String Texto) {

        TTS.setPitch(1); // Afinação da Voz
        TTS.setSpeechRate(1);//Velocidade da Voz
        TTS.speak(Texto, TextToSpeech.QUEUE_FLUSH, null);
    }


}
