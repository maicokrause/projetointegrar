package poder.ufac.br.projetointegrar;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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

import poder.ufac.br.projetointegrar.util.Relogio;

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
//                Toast.makeText(CalendarioActivity.this, "Data: " + new Date(calendar.getDate()), Toast.LENGTH_SHORT).show();
                intent = new Intent(CalendarioActivity.this, ListarCompromissoActivity.class);
                intent.putExtra("data", Relogio.zerarHoraLong(calendar.getDate()));
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
//                finish();
                NavUtils.navigateUpFromSameTask(this);
                break;
            case R.id.itemMenuCalendarioSelecionarDia:
                intent = new Intent(CalendarioActivity.this, ListarCompromissoActivity.class);
//                Toast.makeText(CalendarioActivity.this, "Data: " + Relogio.zerarHoraLong(calendar.getDate()), Toast.LENGTH_SHORT).show();
//                Log.i("Data", Relogio.zerarHoraLong(calendar.getDate())+"");
                intent.putExtra("data", Relogio.zerarHoraLong(calendar.getDate()));
                startActivity(intent);
        }
        return true;
    }
}
