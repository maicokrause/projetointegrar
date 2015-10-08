package poder.ufac.br.projetointegrar.cdp;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Levi Cacau on 05/10/2015.
 */
@DatabaseTable(tableName="compromisso")
public class Compromisso implements Serializable {

    @DatabaseField(generatedId=true)
    private Long id;

    @DatabaseField
    private int status;

    @DatabaseField
    private String horario;

    @DatabaseField(dataType = DataType.DATE)
    private Date data;

    @DatabaseField(foreign=true)
    private Tarefa tarefa;

    public Compromisso(){}
    public Compromisso(Long id, String horario, int status, Date data){
        this.id = id;
        this.horario = horario;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }
}
