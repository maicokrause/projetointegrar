package poder.ufac.br.projetointegrar.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import poder.ufac.br.projetointegrar.cdp.Discipline;
import poder.ufac.br.projetointegrar.cdp.Tarefa;

public class TarefaDao extends BaseDaoImpl<Tarefa, Integer> {
	public TarefaDao(ConnectionSource cs) throws SQLException{
		super(Tarefa.class);
		setConnectionSource(cs);
		initialize();
	}
}
