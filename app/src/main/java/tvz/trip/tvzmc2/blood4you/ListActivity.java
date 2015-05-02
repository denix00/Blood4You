package tvz.trip.tvzmc2.blood4you;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


//aktivnost za izlistavanje lokacija za doniranje krvi

public class ListActivity extends ActionBarActivity {

    //Pohrana lokacija za prijenos u SpecialAdapter
    List<Lokacija> lokacije = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //zakljucavanje orijentacije ekrana u portret
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Ukoliko mrezni adapteri nisu ukljuceni,baci Toast i vrati se na prijasnji ekran (MainActivity)
        if(isNetworkAvailable() == false)
        {
            Toast.makeText(this, R.string.toastNemaInterneta, Toast.LENGTH_LONG).show();
            finish();
        }

        //Dohvacanje podataka s parse.com, spremanje u listu Lokacija
        //Dohvati sve iz baze Lokacije
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Lokacije");
        //Dohvati sve entitete koji imaju unesen grad
        query.whereExists("grad");
        //Dohvati
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    //ako nije doslo do pogreske izvrsi ovo

                    //prodi kroz sve dohvacene entitete
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
                        //   Date datum = new Date(0, 0, 1);  //za potrebe testiranja
                        String datum = list.get(i).getString("datumString");
                        Boolean izvanredna = list.get(i).getBoolean("izvanredna");
                        //ako nema datuma i nije izvanredna, znaci da se svakodnevno moze dati krv
                        //ako nema datuma, akcija je najavljena i datum ce biti uskoro dostupan
                        if (datum == null && izvanredna == false) {
                            datum = getString(R.string.stalnaAkcija);
                        } else if (datum == null) {
                            datum = getString(R.string.datumUskoro);
                        }
                        //   Log.d("datum" + i, datum.toString());
                        //   Log.d("izvanredna" + i, izvanredna.toString());
                        String radnoVrijeme = list.get(i).getString("radnoVrijeme");
                        if (radnoVrijeme == null) {
                            radnoVrijeme = getString(R.string.radnoVrijeme);
                        }
                        //   Log.d("radnoVrijeme" + i, radnoVrijeme);

                        //stvaranje privremenog objekta lokacija i spremanje u listu lokacija
                        Lokacija tmpLokacija = new Lokacija(grad, adresa, datum, izvanredna, radnoVrijeme);
                        lokacije.add(tmpLokacija);
                    }
                }
                //doslo je do pogreske, umjesto naziva grada sorenu napomenu da je doslo do pogreske pri dohvatu podataka
                else {
                    String grad = getString(R.string.errorDohvat);
                    String adresa = "";
                    String datum = "error";
                    Boolean izvanredna = true;
                    String radnoVrijeme = "error";

                    Lokacija tmpLokacija = new Lokacija(grad, adresa, datum, izvanredna, radnoVrijeme);
                    lokacije.add(tmpLokacija);
                }
                //    Log.d("Broj lokacija List:", String.valueOf(lokacije.size()));

                //dohvati ListView iz activity_list.xml u koji ce se ubacivati retci definirani u row.xml sa podacima spremljenim u lokacije pomocu SpecialAdapter klase
                ListView lista = (ListView) findViewById(R.id.list);
                SpecialAdapter adapter = new SpecialAdapter(ListActivity.this, lokacije);
                lista.setAdapter(adapter);

                //Registriraj klik na pojedini redak, potrebno dodati nakon klika otvaranje lokacije na google karti
                /*
                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(ListActivity.this, "Odabran je redak: " + String.valueOf(position), Toast.LENGTH_SHORT).show();
                    }
                });
                */
            }
        });
        //Dohvacanje podataka s parse.com - kraj koda

    }

    //privremeno spremiste za TextView elemente s unesenim podacima
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

    // Provjera da li je omogucen neki od mreznih adaptera
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
