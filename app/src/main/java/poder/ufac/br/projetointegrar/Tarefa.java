package poder.ufac.br.projetointegrar;

import java.io.Serializable;

/**
 * Created by Levi Cacau on 01/10/2015.
 */
public class Tarefa implements Serializable{
    private int miniatura;
    private String nome;
    private int[] imagens;
    private int[] audio;
    private int titulo;

    public Tarefa(String nome, int [] imagens, int[] audio, int titulo, int miniatura){
        this.miniatura = miniatura;
        this.nome = nome;
        this.imagens = imagens;
        this.audio = audio;
        this.titulo = titulo;
    }


    public int getMiniatura() {
        return miniatura;
    }

    public void setMiniatura(int miniatura) {
        this.miniatura = miniatura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int[] getImagens() {
        return imagens;
    }

    public void setImagens(int[] imagens) {
        this.imagens = imagens;
    }

    public int[] getAudio() {
        return audio;
    }

    public void setAudio(int[] audio) {
        this.audio = audio;
    }

    public int getTitulo() {
        return titulo;
    }

    public void setTitulo(int titulo) {
        this.titulo = titulo;
    }
}
