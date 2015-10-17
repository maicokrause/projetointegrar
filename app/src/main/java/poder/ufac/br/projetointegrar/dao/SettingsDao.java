package poder.ufac.br.projetointegrar.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import poder.ufac.br.projetointegrar.cdp.Settings;

public class SettingsDao extends BaseDaoImpl<Settings, Integer> {
	public SettingsDao(ConnectionSource cs) throws SQLException{
		super(Settings.class);
		setConnectionSource(cs);
		initialize();
	}
}
