package poder.ufac.br.projetointegrar.cdp;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Levi Cacau on 01/10/2015.
 */
@DatabaseTable(tableName="settings")
public class Settings implements Serializable{
    @DatabaseField(generatedId=true)
    private Long id;
    @DatabaseField
    private int leitura;
    @DatabaseField
    private int legenda;

    public Settings(){}

    public int getLeitura() {
        return leitura;
    }

    public void setLeitura(int leitura) {
        this.leitura = leitura;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLegenda() {
        return legenda;
    }

    public void setLegenda(int legenda) {
        this.legenda = legenda;
    }
}
