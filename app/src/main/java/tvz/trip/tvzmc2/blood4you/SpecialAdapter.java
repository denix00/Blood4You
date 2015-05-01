package tvz.trip.tvzmc2.blood4you;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SpecialAdapter extends BaseAdapter {
    //Defining the background color of rows. The row will alternate between green light and green dark.
//    private int[] colors = new int[] { 0xff971515, 0xff971515 };
    private int color = 0xff971515;
    private LayoutInflater mInflater;

    //The variable that will hold our text data to be tied to list.
   // private String[] data;

    List<Lokacija> lokacije;

    public SpecialAdapter(Context context, List<Lokacija> lokacije) {
        mInflater = LayoutInflater.from(context);
        this.lokacije = lokacije;
    }

    @Override
    public int getCount() {
        Log.d("Broj lokacija Special", String.valueOf(lokacije.size()));
        return lokacije.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //A view to hold each row in the list
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

// A ViewHolder keeps references to children views to avoid unneccessary calls
// to findViewById() on each row.
        ListActivity.ViewHolder holder;

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.row, null);

            holder = new ListActivity.ViewHolder();
            holder.txtGradUlica = (TextView) convertView.findViewById(R.id.txtGradUlica);
            holder.txtDatumVrijeme = (TextView) convertView.findViewById(R.id.txtDatumVrijeme);
            convertView.setTag(holder);

        }
        else
        {
            holder = (ListActivity.ViewHolder) convertView.getTag();
        }
        // Bind the data efficiently with the holder.
        holder.txtGradUlica.setText(lokacije.get(position).getGrad() + "\n" + lokacije.get(position).getAdresa());
       // holder.txtDatumVrijeme.setText(lokacije.get(position).getDatum().toString().substring(0,9) + "\n" + lokacije.get(position).getRadnoVrijeme());
        holder.txtDatumVrijeme.setText(lokacije.get(position).getDatum() + "\n" + lokacije.get(position).getRadnoVrijeme());

        //Set the background color depending of  odd/even colorPos result
//        int colorPos = position % colors.length;
//        convertView.setBackgroundColor(colors[colorPos]);
        convertView.setBackgroundColor(color);

        return convertView;
    }
}