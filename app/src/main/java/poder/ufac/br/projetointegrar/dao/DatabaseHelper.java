package poder.ufac.br.projetointegrar.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import poder.ufac.br.projetointegrar.cdp.Compromisso;
import poder.ufac.br.projetointegrar.cdp.Tarefa;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	private static final String databaseName = "integrar.db";
	private static final int databaseVersion = 2;
	
	public DatabaseHelper(Context context) {
		super(context, databaseName, null, databaseVersion);
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase sd, ConnectionSource cs) {
		try {
			TableUtils.createTable(cs, Compromisso.class);
			TableUtils.createTable(cs, Tarefa.class);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onUpgrade(SQLiteDatabase sd, ConnectionSource cs, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(cs, Compromisso.class, true);
			TableUtils.dropTable(cs, Tarefa.class, true);
			onCreate(sd, cs);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void close(){
		super.close();
	}
}
