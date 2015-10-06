package poder.ufac.br.projetointegrar;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import poder.ufac.br.projetointegrar.dao.CompromissoDao;
import poder.ufac.br.projetointegrar.dao.DatabaseHelper;

public class ListarCompromissoActivity extends ActionBarActivity {
    //ORMlite
    private DatabaseHelper dh;
    private CompromissoDao compromissoDao;
    private ListView listViewCompromissos;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_compromisso);
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        listViewCompromissos = (ListView) findViewById(R.id.listViewListarCompromissos);


//        Car car = new Car(1, "octavia");
//        car.setManufactured(Calendar.getInstance().getTime());
//        Dao<Car, Integer> carDao = getHelper().getCarDao();
//
//        carDao.create(car);
//
//        QueryBuilder<Car, Integer> carQb = carDao.queryBuilder();
//
//        Calendar yesteday = Calendar.getInstance();
//        yesteday.add(Calendar.DATE, -1);
//
//        Calendar tommorrow = Calendar.getInstance();
//        yesteday.add(Calendar.DATE, 1);
//
//        carQb.where().between("manufactured", yesteday.getTime(), tommorrow.getTime());
//        PreparedQuery<Car> query = carQb.prepare();
//
//        List<Car> cars = carDao.query(query);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listar_compromisso, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case android.R.id.home:
                Toast.makeText(this, "Logo botão", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itemMenuAdicionarCompromisso:
                intent = new Intent(this, AdicionarCompromissosActivity.class);
                startActivity(intent);
                break;
        }

        return(true);
    }
}
