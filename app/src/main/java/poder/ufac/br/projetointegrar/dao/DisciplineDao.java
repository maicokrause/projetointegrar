package poder.ufac.br.projetointegrar.dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import poder.ufac.br.projetointegrar.cdp.Discipline;

public class DisciplineDao extends BaseDaoImpl<Discipline, Integer> {
	public DisciplineDao(ConnectionSource cs) throws SQLException{
		super(Discipline.class);
		setConnectionSource(cs);
		initialize();
	}
}
