package poder.ufac.br.projetointegrar.dao;

import android.util.Log;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.StringType;

import org.apache.commons.lang3.StringUtils;

public class ArrayPersister extends StringType
{
    private static final String delimiter = ",";
    private static final ArrayPersister singleTon = new ArrayPersister();

    private ArrayPersister()
    {
        super(SqlType.STRING, new Class<?>[]{ String[].class });
    }

    public static ArrayPersister getSingleton()
    {
        return singleTon;
    }

    @Override
    public Object javaToSqlArg(FieldType fieldType, Object javaObject)
    {
        String[] array = (String[]) javaObject;

        if (array == null)
        {
            return null;
        }
        else {
            return StringUtils.join(array, delimiter);
        }
    }

    @Override
    public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos)
    {
        String string = (String)sqlArg;

        if ( string == null )
        {
            return null;
        }
        else
        {
            return string.split(delimiter);
        }
    }
}