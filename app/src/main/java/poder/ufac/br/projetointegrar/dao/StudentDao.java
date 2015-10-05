package poder.ufac.br.projetointegrar.dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import poder.ufac.br.projetointegrar.cdp.Student;

public class StudentDao extends BaseDaoImpl<Student, Integer> {
	public StudentDao(ConnectionSource cs) throws SQLException{
		super(Student.class);
		setConnectionSource(cs);
		initialize();
	}
}
