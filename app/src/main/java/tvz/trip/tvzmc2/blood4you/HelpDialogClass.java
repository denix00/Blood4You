package tvz.trip.tvzmc2.blood4you;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;


//klasa za ispis informacija o aplikaciji i timu, u obliku Dialoga

public class HelpDialogClass extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        //ucitaj dizajn za dialog
        builder.setView(inflater.inflate(R.layout.help_dialog, null))

                //postavi OK gumb za zatvaranje dialoga
                .setNegativeButton(R.string.dialogGumbOk, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        HelpDialogClass.this.getDialog().cancel();
                    }
                });

        //prikazi dialog
        return builder.create();
    }

}
