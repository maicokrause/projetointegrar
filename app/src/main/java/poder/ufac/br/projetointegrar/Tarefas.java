package poder.ufac.br.projetointegrar;

/**
 * Created by Levi Cacau on 30/09/2015.
 */
public class Tarefas {

    public static final int ESCOVAR_DENTES = 1;
    public static final int NA_ESCOLA = 2;

    public static Tarefa getTarefa(int i){
        switch (i){
            case 1: return new Tarefa(
                    "Escovando os Dentes",
                    escovarDentesImagens,
                    escovarDentesAudio,
                    R.drawable.escovando_dentes_00,
                    R.drawable.escovando_dentes_m
            );
            case 2: return new Tarefa(
                    "Na Escola",
                    naEscolaImagens,
                    naEscolaAudio,
                    R.drawable.na_escola_00,
                    R.drawable.na_escola_m
            );
            default: return null;
        }
    }

    public static int[] getImagens(int i){
        switch (i){
            case 1: return escovarDentesImagens;
            case 2: return naEscolaImagens;
            default: return null;
        }
    }
    public static int[] getAudio(int i){
        switch (i){
            case 1: return escovarDentesAudio;
            case 2: return naEscolaAudio;
            default: return null;
        }
    }

    public static int[] getListaTarefas(){
        int [] lista = {ESCOVAR_DENTES, NA_ESCOLA};
        return lista;
    }

    public static final int[] escovarDentesImagens = {
            R.drawable.escovando_dentes_01, R.drawable.escovando_dentes_02, R.drawable.escovando_dentes_03,
            R.drawable.escovando_dentes_04, R.drawable.escovando_dentes_05, R.drawable.escovando_dentes_06,
            R.drawable.escovando_dentes_07, R.drawable.escovando_dentes_08
    };

    public static final int[] escovarDentesAudio = {
            R.raw.escovar_dentes_01, R.raw.escovar_dentes_02, R.raw.escovar_dentes_03, R.raw.escovar_dentes_04,
            R.raw.escovar_dentes_05, R.raw.escovar_dentes_06, R.raw.escovar_dentes_07, R.raw.escovar_dentes_08
    };

    public static final int[] naEscolaImagens = {
            R.drawable.na_escola_01, R.drawable.na_escola_02, R.drawable.na_escola_03, R.drawable.na_escola_04,
            R.drawable.na_escola_05, R.drawable.na_escola_06, R.drawable.na_escola_07, R.drawable.na_escola_08
    };

    public static final int[] naEscolaAudio = {
            R.raw.na_escola_01, R.raw.na_escola_02, R.raw.na_escola_03, R.raw.na_escola_04, R.raw.na_escola_05,
            R.raw.na_escola_06, R.raw.na_escola_07, R.raw.na_escola_08
    };

}
