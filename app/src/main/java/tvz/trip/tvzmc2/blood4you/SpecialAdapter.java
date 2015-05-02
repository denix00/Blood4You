package tvz.trip.tvzmc2.blood4you;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

// Klasa za hendlanje lokacija, punjenje TextView elemenata podacima iz lokacija

public class SpecialAdapter extends BaseAdapter {

    private LayoutInflater layInflater;

 // Lista za pohranu proslijedenih lokacija
    List<Lokacija> lokacije;

    //konstruktor, postavljanje dizajna na ekran, pohrana dobivenih lokacija
    public SpecialAdapter(Context context, List<Lokacija> lokacije) {
        layInflater = LayoutInflater.from(context);
        this.lokacije = lokacije;
    }

    //nadjacaj getCount() metodu iz BaseAdapter klase, vrati koliko ima lokacija u listi
    @Override
    public int getCount() {
      //  Log.d("Broj lokacija Special", String.valueOf(lokacije.size()));
        return lokacije.size();
    }

    //nadjacaj getItem() metodu, metoda potrebna android sustavu
    @Override
    public Object getItem(int position) {
        return position;
    }
    //nadjacaj getItemId() metodu, metoda potrebna ListActivity klasi
    @Override
    public long getItemId(int position) {
        return position;
    }

    //kreiranje svakog retka za listu, metodu koristi sustav
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //U holder varijablu ce se spremati reference na elemente u retku
        ListActivity.ViewHolder holder;

        if (convertView == null)
        {
            //ako nema reference na redak u holderu, napravi slijedece
            //dohvacanje dizajna retka, postavljanje reference na elemente retka u holder
            convertView = layInflater.inflate(R.layout.row, null);

            holder = new ListActivity.ViewHolder();
            holder.txtGradUlica = (TextView) convertView.findViewById(R.id.txtGradUlica);
            holder.txtDatumVrijeme = (TextView) convertView.findViewById(R.id.txtDatumVrijeme);
            convertView.setTag(holder);

        }
        else
        {
            //ako ima reference na dizajn retka, dohvati referencirani objekt
            holder = (ListActivity.ViewHolder) convertView.getTag();
        }
        //elementima u holderu dodaj podatke iz liste lokacija
        holder.txtGradUlica.setText(lokacije.get(position).getGrad() + "\n" + lokacije.get(position).getAdresa());
        // getDatum() od Parse.com SDK-a u ListActivity klasi rusi aplikaciju i Date tip podatka je zamijenjen Stringom
       // holder.txtDatumVrijeme.setText(lokacije.get(position).getDatum().toString().substring(0,9) + "\n" + lokacije.get(position).getRadnoVrijeme());
        holder.txtDatumVrijeme.setText(lokacije.get(position).getDatum() + "\n" + lokacije.get(position).getRadnoVrijeme());

        //boja za pozadinu retka i postavljanje iste
        int color = 0xff971515;
        convertView.setBackgroundColor(color);

        //vrati gotov redak
        return convertView;
    }
}