package poder.ufac.br.projetointegrar;

import poder.ufac.br.projetointegrar.cdp.Tarefa;

/**
 * Created by Levi Cacau on 30/09/2015.
 */
public class Tarefas {

    public static final int COTIDIANO = 1;
    public static final int COMPORTAMENTO = 2;
    public static final int HIGIENE = 3;

    public static final int ESCOVAR_DENTES = 1;
    public static final int NA_ESCOLA = 2;
    public static final int AO_ACORDAR = 3;
    public static final int DESPIR_FEMININO = 4;
    public static final int VESTIR_FEMINNO = 5;
    public static final int HORA_DE_DORMIR = 6;
    public static final int IRMAOZINHO = 7;
    public static final int FERIAS_ESCOLARES = 8;
    public static final int CUSPIR = 9;
    public static final int MORDER = 10;
    public static final int SE_JOGAR_NO_CHAO = 11;
    public static final int BANHEIRO_FEMININO = 12;
    public static final int BANHEIRO_MASCULINO = 13;
    public static final int BANHO_FEMININO = 14;
    public static final int BANHO_MASCULINO = 15;
    public static final int CORTANDO_CABELO = 16;
    public static final int CORTANDO_UNHAS = 17;
    public static final int LAVAR_MAOS = 18;

    public static int[] getListaTarefas(){
        int [] lista = {ESCOVAR_DENTES, NA_ESCOLA, AO_ACORDAR,DESPIR_FEMININO,VESTIR_FEMINNO,
                HORA_DE_DORMIR, IRMAOZINHO, FERIAS_ESCOLARES, CUSPIR, MORDER, SE_JOGAR_NO_CHAO,
                BANHEIRO_FEMININO, BANHEIRO_MASCULINO, BANHO_FEMININO, BANHO_MASCULINO,
                CORTANDO_CABELO, CORTANDO_UNHAS, LAVAR_MAOS};
        return lista;
    }

    public static Tarefa getTarefa(int i){
        switch (i){
            case ESCOVAR_DENTES: return new Tarefa(
                    "Escovando os Dentes",
                    escovarDentesImagens,
                    escovarDentesAudio,
                    R.drawable.escovando_dentes_titulo,
                    R.drawable.escovando_dentes_icone,
                    HIGIENE,
                    0
            );
            case NA_ESCOLA: return new Tarefa(
                    "Na Escola",
                    naEscolaImagens,
                    naEscolaAudio,
                    R.drawable.escola_titulo,
                    R.drawable.escola_icone,
                    COTIDIANO,
                    0
            );
            case AO_ACORDAR: return new Tarefa(
                    "Ao Acordar",
                    aoAcordarImagens,
                    aoAcordarAudio,
                    R.drawable.ao_acordar_titulo,
                    R.drawable.ao_acordar_icone,
                    COTIDIANO,
                    0
            );
            case DESPIR_FEMININO: return new Tarefa(
                    "Despir Feminino",
                    despirFemininoImagens,
                    despirFemininoAudio,
                    R.drawable.despir_feminino_titulo,
                    R.drawable.despir_feminino_icone,
                    COTIDIANO,
                    0
            );
            case VESTIR_FEMINNO: return new Tarefa(
                    "Vestir Feminino",
                    vestirFemininoImagens,
                    vestirFemininoAudio,
                    R.drawable.vestir_feminino_titulo,
                    R.drawable.vestir_feminino_icone,
                    COTIDIANO,
                    0
            );
            case HORA_DE_DORMIR: return new Tarefa(
                    "Hora de Dormir",
                    horaDeDormirImagens,
                    horaDeDormirAudio,
                    R.drawable.hora_de_dormir_titulo,
                    R.drawable.hora_de_dormir_icone,
                    COTIDIANO,
                    0
            );
            case IRMAOZINHO: return new Tarefa(
                    "Irmaozinho",
                    irmaozinhoImagens,
                    irmaozinhoAudio,
                    R.drawable.irmaozinho_titulo,
                    R.drawable.irmaozinho_icone,
                    COTIDIANO,
                    0
            );
            case FERIAS_ESCOLARES: return new Tarefa(
                    "Férias Escolares",
                    feriasEscolaresImagens,
                    feriasEscolaresAudio,
                    R.drawable.ferias_escolares_titulo,
                    R.drawable.ferias_escolares_icone,
                    COTIDIANO,
                    0
            );
            case CUSPIR: return new Tarefa(
                    "Cuspir não Pode",
                    cuspirImagens,
                    cuspirAudio,
                    R.drawable.cuspir_titulo,
                    R.drawable.cuspir_icone,
                    COMPORTAMENTO,
                    0
            );
            case MORDER: return new Tarefa(
                    "Morder não Pode",
                    morderImagens,
                    morderAudio,
                    R.drawable.morder_titulo,
                    R.drawable.morder_icone,
                    COMPORTAMENTO,
                    0
            );
            case SE_JOGAR_NO_CHAO: return new Tarefa(
                    "Se Jogar no Chão",
                    seJogarNoChaoImagens,
                    seJogarNoChaoAudio,
                    R.drawable.se_jogar_no_chao_titulo,
                    R.drawable.se_jogar_no_chao_icone,
                    COMPORTAMENTO,
                    0
            );
            case BANHEIRO_FEMININO: return new Tarefa(
                    "Banheiro Feminino",
                    banheiroFemininoImagens,
                    banheiroFemininoAudio,
                    R.drawable.banheiro_feminino_titulo,
                    R.drawable.banheiro_feminino_icone,
                    HIGIENE,
                    0
            );
            case BANHEIRO_MASCULINO: return new Tarefa(
                    "Banheiro Masculino",
                    banheiroMasculinoImagens,
                    banheiroMasculinoAudio,
                    R.drawable.banheiro_masculino_titulo,
                    R.drawable.banheiro_masculino_icone,
                    HIGIENE,
                    0
            );
            case BANHO_FEMININO: return new Tarefa(
                    "Banho Feminino",
                    banhoFemininoImagens,
                    banhoFemininoAudio,
                    R.drawable.banho_feminino_titulo,
                    R.drawable.banho_feminino_icone,
                    HIGIENE,
                    0
            );
            case BANHO_MASCULINO: return new Tarefa(
                    "Banho Masculino",
                    banhoMasculinoImagens,
                    banhoMasculinoAudio,
                    R.drawable.banho_masculino_titulo,
                    R.drawable.banho_masculino_icone,
                    HIGIENE,
                    0
            );
            case CORTANDO_CABELO: return new Tarefa(
                    "Cortando o Cabelo",
                    cortandoCabeloImagens,
                    cortandoCabeloAudio,
                    R.drawable.cortando_cabelo_titulo,
                    R.drawable.cortando_cabelo_icone,
                    HIGIENE,
                    0
            );
            case CORTANDO_UNHAS: return new Tarefa(
                    "Cortando as Unhas",
                    cortandoUnhasImagens,
                    cortandoUnhasAudio,
                    R.drawable.cortando_unhas_titulo,
                    R.drawable.cortando_unhas_icone,
                    HIGIENE,
                    0
            );
            case LAVAR_MAOS: return new Tarefa(
                    "lavar as Mãos",
                    lavarMaosImagens,
                    lavarMaosAudio,
                    R.drawable.lavar_maos_titulo,
                    R.drawable.lavar_maos_icone,
                    HIGIENE,
                    0
            );
            default: return null;
        }
    }

//    public static int[] getImagens(int i){
//        switch (i){
//            case 1: return escovarDentesImagens;
//            case 2: return naEscolaImagens;
//            default: return null;
//        }
//    }
//    public static int[] getAudio(int i){
//        switch (i){
//            case 1: return escovarDentesAudio;
//            case 2: return naEscolaAudio;
//            default: return null;
//        }
//    }

    public static final int[] escovarDentesImagens = {
            R.drawable.escovando_dentes_01, R.drawable.escovando_dentes_02, R.drawable.escovando_dentes_03,
            R.drawable.escovando_dentes_04, R.drawable.escovando_dentes_05, R.drawable.escovando_dentes_06,
            R.drawable.escovando_dentes_07, R.drawable.escovando_dentes_08
    };

    public static final int[] escovarDentesAudio = {
            R.raw.escovando_dentes_01, R.raw.escovando_dentes_02, R.raw.escovando_dentes_03, R.raw.escovando_dentes_04,
            R.raw.escovando_dentes_05, R.raw.escovando_dentes_06, R.raw.escovando_dentes_07, R.raw.escovando_dentes_08
    };

    public static final int[] naEscolaImagens = {
            R.drawable.escola_01, R.drawable.escola_02, R.drawable.escola_03, R.drawable.escola_04,
            R.drawable.escola_05, R.drawable.escola_06, R.drawable.escola_07, R.drawable.escola_08
    };

    public static final int[] naEscolaAudio = {
            R.raw.escola_01, R.raw.escola_02, R.raw.escola_03, R.raw.escola_04, R.raw.escola_05,
            R.raw.escola_06, R.raw.escola_07, R.raw.escola_08
    };

    public static final int[] aoAcordarImagens = {
            R.drawable.ao_acordar_01, R.drawable.ao_acordar_02, R.drawable.ao_acordar_03, R.drawable.ao_acordar_04,
            R.drawable.ao_acordar_05, R.drawable.ao_acordar_06
    };

    public static final int[] aoAcordarAudio = {
            R.raw.ao_acordar_01, R.raw.ao_acordar_02, R.raw.ao_acordar_03, R.raw.ao_acordar_04, R.raw.ao_acordar_05,
            R.raw.ao_acordar_06
    };

    public static final int[] despirFemininoImagens = {
            R.drawable.despir_feminino_01, R.drawable.despir_feminino_02, R.drawable.despir_feminino_03, R.drawable.despir_feminino_04,
            R.drawable.despir_feminino_05, R.drawable.despir_feminino_06
    };

    public static final int[] despirFemininoAudio = {
            R.raw.despir_feminino_01, R.raw.despir_feminino_02, R.raw.despir_feminino_03, R.raw.despir_feminino_04, R.raw.despir_feminino_05,
            R.raw.despir_feminino_06
    };

    public static final int[] vestirFemininoImagens = {
            R.drawable.vestir_feminino_01, R.drawable.vestir_feminino_02, R.drawable.vestir_feminino_03, R.drawable.vestir_feminino_04,
            R.drawable.vestir_feminino_05, R.drawable.vestir_feminino_06
    };

    public static final int[] vestirFemininoAudio = {
            R.raw.vestir_feminino_01, R.raw.vestir_feminino_02, R.raw.vestir_feminino_03, R.raw.vestir_feminino_04, R.raw.vestir_feminino_05,
            R.raw.vestir_feminino_06
    };

    public static final int[] horaDeDormirImagens = {
            R.drawable.hora_de_dormir_01, R.drawable.hora_de_dormir_02, R.drawable.hora_de_dormir_03, R.drawable.hora_de_dormir_04
    };

    public static final int[] horaDeDormirAudio = {
            R.raw.hora_de_dormir_01, R.raw.hora_de_dormir_02, R.raw.hora_de_dormir_03, R.raw.hora_de_dormir_04
    };

    public static final int[] irmaozinhoImagens = {
            R.drawable.irmaozinho_01, R.drawable.irmaozinho_02, R.drawable.irmaozinho_03, R.drawable.irmaozinho_04,
            R.drawable.irmaozinho_05, R.drawable.irmaozinho_06
    };

    public static final int[] irmaozinhoAudio = {
            R.raw.irmaozinho_01, R.raw.irmaozinho_02, R.raw.irmaozinho_03, R.raw.irmaozinho_04,
            R.raw.irmaozinho_05, R.raw.irmaozinho_06
    };

    public static final int[] feriasEscolaresImagens = {
            R.drawable.ferias_escolares_01, R.drawable.ferias_escolares_02, R.drawable.ferias_escolares_03, R.drawable.ferias_escolares_04,
            R.drawable.ferias_escolares_05, R.drawable.ferias_escolares_06, R.drawable.ferias_escolares_07, R.drawable.ferias_escolares_08,
            R.drawable.ferias_escolares_09, R.drawable.ferias_escolares_10
    };

    public static final int[] feriasEscolaresAudio = {
            R.raw.ferias_escolares_01, R.raw.ferias_escolares_02, R.raw.ferias_escolares_03, R.raw.ferias_escolares_04, R.raw.ferias_escolares_05,
            R.raw.ferias_escolares_06, R.raw.ferias_escolares_07, R.raw.ferias_escolares_08, R.raw.ferias_escolares_09, R.raw.ferias_escolares_10
    };

    public static final int[] cuspirImagens = {
            R.drawable.cuspir_01, R.drawable.cuspir_02, R.drawable.cuspir_03, R.drawable.cuspir_04
    };

    public static final int[] cuspirAudio = {
            R.raw.cuspir_01, R.raw.cuspir_02, R.raw.cuspir_03, R.raw.cuspir_04
    };

    public static final int[] morderImagens = {
            R.drawable.morder_01, R.drawable.morder_02, R.drawable.morder_03, R.drawable.morder_04
    };

    public static final int[] morderAudio = {
            R.raw.morder_01, R.raw.morder_02, R.raw.morder_03, R.raw.morder_04
    };
    
    public static final int[] seJogarNoChaoImagens = {
            R.drawable.se_jogar_no_chao_01, R.drawable.se_jogar_no_chao_02, R.drawable.se_jogar_no_chao_03
    };

    public static final int[] seJogarNoChaoAudio = {
            R.raw.se_jogar_no_chao_01, R.raw.se_jogar_no_chao_02, R.raw.se_jogar_no_chao_03
    };

    public static final int[] banheiroFemininoImagens = {
            R.drawable.banheiro_feminino_01, R.drawable.banheiro_feminino_02, R.drawable.banheiro_feminino_03, R.drawable.banheiro_feminino_04,
            R.drawable.banheiro_feminino_05, R.drawable.banheiro_feminino_06, R.drawable.banheiro_feminino_07, R.drawable.banheiro_feminino_08
    };

    public static final int[] banheiroFemininoAudio = {
            R.raw.banheiro_feminino_01, R.raw.banheiro_feminino_02, R.raw.banheiro_feminino_03, R.raw.banheiro_feminino_04, R.raw.banheiro_feminino_05,
            R.raw.banheiro_feminino_06, R.raw.banheiro_feminino_07, R.raw.banheiro_feminino_08
    };

    public static final int[] banheiroMasculinoImagens = {
            R.drawable.banheiro_masculino_01, R.drawable.banheiro_masculino_02, R.drawable.banheiro_masculino_03, R.drawable.banheiro_masculino_04,
            R.drawable.banheiro_masculino_05, R.drawable.banheiro_masculino_06, R.drawable.banheiro_masculino_07, R.drawable.banheiro_masculino_08
    };

    public static final int[] banheiroMasculinoAudio = {
            R.raw.banheiro_masculino_01, R.raw.banheiro_masculino_02, R.raw.banheiro_masculino_03, R.raw.banheiro_masculino_04, R.raw.banheiro_masculino_05,
            R.raw.banheiro_masculino_06, R.raw.banheiro_masculino_07, R.raw.banheiro_masculino_08
    };

    public static final int[] banhoFemininoImagens = {
            R.drawable.banho_feminino_01, R.drawable.banho_feminino_02, R.drawable.banho_feminino_03, R.drawable.banho_feminino_04,
            R.drawable.banho_feminino_05, R.drawable.banho_feminino_06, R.drawable.banho_feminino_07, R.drawable.banho_feminino_08,
            R.drawable.banho_feminino_09, R.drawable.banho_feminino_10, R.drawable.banho_feminino_11, R.drawable.banho_feminino_12
    };

    public static final int[] banhoFemininoAudio = {
            R.raw.banho_feminino_01, R.raw.banho_feminino_02, R.raw.banho_feminino_03, R.raw.banho_feminino_04, R.raw.banho_feminino_05,
            R.raw.banho_feminino_06, R.raw.banho_feminino_07, R.raw.banho_feminino_08, R.raw.banho_feminino_09, R.raw.banho_feminino_10,
            R.raw.banho_feminino_11, R.raw.banho_feminino_12
    };

    public static final int[] banhoMasculinoImagens = {
            R.drawable.banho_masculino_01, R.drawable.banho_masculino_02, R.drawable.banho_masculino_03, R.drawable.banho_masculino_04,
            R.drawable.banho_masculino_05, R.drawable.banho_masculino_06, R.drawable.banho_masculino_07, R.drawable.banho_masculino_08,
            R.drawable.banho_masculino_09, R.drawable.banho_masculino_10, R.drawable.banho_masculino_11, R.drawable.banho_masculino_12
    };

    public static final int[] banhoMasculinoAudio = {
            R.raw.banho_masculino_01, R.raw.banho_masculino_02, R.raw.banho_masculino_03, R.raw.banho_masculino_04, R.raw.banho_masculino_05,
            R.raw.banho_masculino_06, R.raw.banho_masculino_07, R.raw.banho_masculino_08, R.raw.banho_masculino_09, R.raw.banho_masculino_10,
            R.raw.banho_masculino_11, R.raw.banho_masculino_12
    };

    public static final int[] cortandoCabeloImagens = {
            R.drawable.cortando_cabelo_01, R.drawable.cortando_cabelo_02, R.drawable.cortando_cabelo_03, R.drawable.cortando_cabelo_04,
            R.drawable.cortando_cabelo_05
    };

    public static final int[] cortandoCabeloAudio = {
            R.raw.cortando_cabelo_01, R.raw.cortando_cabelo_02, R.raw.cortando_cabelo_03, R.raw.cortando_cabelo_04, R.raw.cortando_cabelo_05,
    };

    public static final int[] cortandoUnhasImagens = {
            R.drawable.cortando_unhas_01, R.drawable.cortando_unhas_02, R.drawable.cortando_unhas_03, R.drawable.cortando_unhas_04,
            R.drawable.cortando_unhas_05, R.drawable.cortando_unhas_06
    };

    public static final int[] cortandoUnhasAudio = {
            R.raw.cortando_unhas_01, R.raw.cortando_unhas_02, R.raw.cortando_unhas_03, R.raw.cortando_unhas_04, R.raw.cortando_unhas_05,
            R.raw.cortando_unhas_06
    };

    public static final int[] lavarMaosImagens = {
            R.drawable.lavar_maos_01, R.drawable.lavar_maos_02, R.drawable.lavar_maos_03, R.drawable.lavar_maos_04,
            R.drawable.lavar_maos_05, R.drawable.lavar_maos_06
    };

    public static final int[] lavarMaosAudio = {
            R.raw.lavar_maos_01, R.raw.lavar_maos_02, R.raw.lavar_maos_03, R.raw.lavar_maos_04, R.raw.lavar_maos_05,
            R.raw.lavar_maos_06
    };
}
