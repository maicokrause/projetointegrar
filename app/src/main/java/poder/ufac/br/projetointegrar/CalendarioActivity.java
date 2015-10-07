package poder.ufac.br.projetointegrar;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarioActivity extends ActionBarActivity {
    private CalendarView calendar;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        calendar = (CalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String selectedDate = sdf.format(new Date(calendar.getDate()));
                Toast.makeText(CalendarioActivity.this, "Data: " + new Date(calendar.getDate()), Toast.LENGTH_SHORT).show();
                intent = new Intent(CalendarioActivity.this, ListarCompromissoActivity.class);
                intent.putExtra("data", calendar.getDate());
                startActivity(intent);
            }
        });
        //calendar.setDate((new Date(115,2,2)).getTime());

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void getCalendarDay(View v){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String selectedDate = sdf.format(new Date(calendar.getDate()));
        Toast.makeText(this, selectedDate, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.itemMenuCalendarioSelecionarDia:
                Calendar c = Calendar.getInstance();
                c.setTime(new Date(calendar.getDate())); //colocando o objeto Date no Calendar
                c.set(Calendar.HOUR_OF_DAY, 0); //zerando as horas, minuots e segundos..
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                Toast.makeText(CalendarioActivity.this, "Data: "+c.getTime(), Toast.LENGTH_SHORT).show();
                intent = new Intent(CalendarioActivity.this, ListarCompromissoActivity.class);
                intent.putExtra("data", c.getTime().getTime());
                startActivity(intent);
        }
        return true;
    }
}
