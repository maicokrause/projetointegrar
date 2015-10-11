package poder.ufac.br.projetointegrar;

import poder.ufac.br.projetointegrar.cdp.Tarefa;

/**
 * Created by Levi Cacau on 30/09/2015.
 */
public class Tarefas {

    public static final int COTIDIANO = 1;
    public static final int COMPORTAMENTO = 2;

    public static final int ESCOVAR_DENTES =    1;
    public static final int NA_ESCOLA =         2;
    public static final int AO_ACORDAR =        3;
    public static final int DESPIR_FEMININO =   4;
    public static final int VESTIR_FEMINNO =    5;
    public static final int HORA_DE_DORMIR =    6;
    public static final int IRMAOZINHO =    7;

    public static int[] getListaTarefas(){
        int [] lista = {ESCOVAR_DENTES, NA_ESCOLA, AO_ACORDAR,DESPIR_FEMININO,VESTIR_FEMINNO,HORA_DE_DORMIR, IRMAOZINHO};
        return lista;
    }

    public static Tarefa getTarefa(int i){
        switch (i){
            case 1: return new Tarefa(
                    "Escovando os Dentes",
                    escovarDentesImagens,
                    escovarDentesAudio,
                    R.drawable.escovando_dentes_00,
                    R.drawable.escovando_dentes_m,
                    COMPORTAMENTO,
                    0
            );
            case 2: return new Tarefa(
                    "Na Escola",
                    naEscolaImagens,
                    naEscolaAudio,
                    R.drawable.escola_titulo,
                    R.drawable.escola_icone,
                    COMPORTAMENTO,
                    0
            );
            case 3: return new Tarefa(
                    "Ao Acordar",
                    aoAcordarImagens,
                    aoAcordarAudio,
                    R.drawable.ao_acordar_titulo,
                    R.drawable.ao_acordar_icone,
                    COMPORTAMENTO,
                    0
            );
            case 4: return new Tarefa(
                    "Despir",
                    despirFemininoImagens,
                    despirFemininoAudio,
                    R.drawable.despir_feminino_titulo,
                    R.drawable.despir_feminino_icone,
                    COMPORTAMENTO,
                    0
            );
            case 5: return new Tarefa(
                    "Vestir",
                    vestirFemininoImagens,
                    vestirFemininoAudio,
                    R.drawable.vestir_feminino_titulo,
                    R.drawable.vestir_feminino_icone,
                    COMPORTAMENTO,
                    0
            );
            case 6: return new Tarefa(
                    "Hora de Dormir",
                    horaDeDormirImagens,
                    horaDeDormirAudio,
                    R.drawable.hora_de_dormir_titulo,
                    R.drawable.hora_de_dormir_icone,
                    COMPORTAMENTO,
                    0
            );
            case 7: return new Tarefa(
                    "Irmaozinho",
                    irmaozinhoImagens,
                    irmaozinhoAudio,
                    R.drawable.irmaozinho_titulo,
                    R.drawable.irmaozinho_icone,
                    COMPORTAMENTO,
                    0
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
            R.drawable.escola_01, R.drawable.escola_02, R.drawable.escola_03, R.drawable.escola_04,
            R.drawable.escola_05, R.drawable.escola_06, R.drawable.escola_07, R.drawable.escola_08
    };

    public static final int[] naEscolaAudio = {
            R.raw.na_escola_01, R.raw.na_escola_02, R.raw.na_escola_03, R.raw.na_escola_04, R.raw.na_escola_05,
            R.raw.na_escola_06, R.raw.na_escola_07, R.raw.na_escola_08
    };

    public static final int[] aoAcordarImagens = {
            R.drawable.ao_acordar_01, R.drawable.ao_acordar_02, R.drawable.ao_acordar_03, R.drawable.ao_acordar_04,
            R.drawable.ao_acordar_05, R.drawable.ao_acordar_06
    };

    public static final int[] aoAcordarAudio = {
//            R.raw.ao_acordar_01, R.raw.ao_acordar_02, R.raw.ao_acordar_03, R.raw.ao_acordar_04, R.raw.ao_acordar_05,
//            R.raw.ao_acordar_06, R.raw.ao_acordar_07, R.raw.ao_acordar_08
    };

    public static final int[] despirFemininoImagens = {
            R.drawable.despir_feminino_01, R.drawable.despir_feminino_02, R.drawable.despir_feminino_03, R.drawable.despir_feminino_04,
            R.drawable.despir_feminino_05, R.drawable.despir_feminino_06
    };

    public static final int[] despirFemininoAudio = {
//            R.raw.despir_feminino_01, R.raw.despir_feminino_02, R.raw.despir_feminino_03, R.raw.despir_feminino_04, R.raw.despir_feminino_05,
//            R.raw.despir_feminino_06
    };

    public static final int[] vestirFemininoImagens = {
            R.drawable.vestir_feminino_01, R.drawable.vestir_feminino_02, R.drawable.vestir_feminino_03, R.drawable.vestir_feminino_04,
            R.drawable.vestir_feminino_05, R.drawable.vestir_feminino_06
    };

    public static final int[] vestirFemininoAudio = {
//            R.raw.vestir_feminino_01, R.raw.vestir_feminino_02, R.raw.vestir_feminino_03, R.raw.vestir_feminino_04, R.raw.vestir_feminino_05,
//            R.raw.vestir_feminino_06
    };

    public static final int[] horaDeDormirImagens = {
            R.drawable.hora_de_dormir_01, R.drawable.hora_de_dormir_02, R.drawable.hora_de_dormir_03, R.drawable.hora_de_dormir_04
    };

    public static final int[] horaDeDormirAudio = {
//            R.raw.hora_de_dormir_01, R.raw.hora_de_dormir_02, R.raw.hora_de_dormir_03, R.raw.hora_de_dormir_04
    };

    public static final int[] irmaozinhoImagens = {
            R.drawable.irmaozinho_01, R.drawable.irmaozinho_02, R.drawable.irmaozinho_03, R.drawable.irmaozinho_04,
            R.drawable.irmaozinho_05, R.drawable.irmaozinho_06
    };

    public static final int[] irmaozinhoAudio = {
//            R.raw.irmaozinho_01, R.raw.irmaozinho_02, R.raw.irmaozinho_03, R.raw.irmaozinho_04,
//            , R.raw.irmaozinho_05, R.raw.irmaozinho_06
    };
}
