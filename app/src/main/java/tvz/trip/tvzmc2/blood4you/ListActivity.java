package tvz.trip.tvzmc2.blood4you;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;


public class ListActivity extends ActionBarActivity {

    List<Lokacija> lokacije = new ArrayList<Lokacija>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Dohvacanje podataka s parse.com
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Lokacije");
        query.whereExists("grad");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                //ako nije doslo do pogreske izvrsi ovo
                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {
                        String grad = list.get(i).getString("grad");
                     //   Log.d("grad" + i, grad);
                        String adresa = list.get(i).getString("adresa");
                     //   Log.d("adresa" + i, adresa);

                        /*
                        getDate() metoda iz neobjasnjivog razloga rusi aplikaciju, stoga je u bazi datum spremljen kao String, te se tako i dohvaca.
                         */

                     //   Date datumString = list.get(i).getDate("datum");
                     //   Log.d("DatumString", list.get(i).getDate("datum").toString());
                     //   Date datum = new Date(0, 0, 1);
                        String datum = list.get(i).getString("datumString");
                        Boolean izvanredna = list.get(i).getBoolean("izvanredna");
                        if(datum == null && izvanredna == false)
                        {
                            datum = "Stalna akcija";
                        }
                        else if(datum == null)
                        {
                            datum = "Datum uskoro";
                        }
                     //   Log.d("datum" + i, datum.toString());
                     //   Log.d("izvanredna" + i, izvanredna.toString());
                        String radnoVrijeme = list.get(i).getString("radnoVrijeme");
                        if(radnoVrijeme == null)
                        {
                            radnoVrijeme = "Cijeli dan";
                        }
                     //   Log.d("radnoVrijeme" + i, radnoVrijeme);

                        Lokacija tmpLokacija = new Lokacija(grad, adresa, datum, izvanredna, radnoVrijeme);
                        lokacije.add(tmpLokacija);
                    }
                }
                //doslo je do pogreske
                else {
                    String grad = "Pogreska pri dohvatu podataka";
                    String adresa = "";
                    String datum = "error";
                    Boolean izvanredna = true;
                    String radnoVrijeme = "error";

                    Lokacija tmpLokacija = new Lokacija(grad, adresa, datum, izvanredna, radnoVrijeme);
                    lokacije.add(tmpLokacija);
                }
            //    Log.d("Broj lokacija List:", String.valueOf(lokacije.size()));

                ListView lista = (ListView) findViewById(R.id.list);
                SpecialAdapter adapter = new SpecialAdapter(ListActivity.this, lokacije);
                lista.setAdapter(adapter);
            }
        });
        //Dohvacanje podataka s parse.com


    }

    public static class ViewHolder
    {
        TextView txtGradUlica;
        TextView txtDatumVrijeme;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
