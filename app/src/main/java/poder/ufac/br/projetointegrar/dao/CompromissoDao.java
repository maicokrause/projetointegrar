package poder.ufac.br.projetointegrar.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import poder.ufac.br.projetointegrar.cdp.Compromisso;

public class CompromissoDao extends BaseDaoImpl<Compromisso, Integer> {
	public CompromissoDao(ConnectionSource cs) throws SQLException{
		super(Compromisso.class);
		setConnectionSource(cs);
		initialize();
	}
}
