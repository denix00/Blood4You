package tvz.trip.tvzmc2.blood4you;
import com.parse.Parse;
import com.parse.ParseInstallation;

import android.app.Application;

/*
    Ova klasa postavlja osnovnu globalnu konfiguraciju koja je potrebna cijeloj aplikaciji za rad kako se ne bi rusila pri povratku iz ListActivity u MainActivity
    To je omoguceno uz android:name="tvz.trip.tvzmc2.blood4you.App" tag u AndroidManifest.xml
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Parse.com API omogucavanje LocalDatastorea za spremanje podataka
        Parse.enableLocalDatastore(this);
        //Prijava na Parse.com servis s ID-em aplikacije i ID-em korisnika servisa
        Parse.initialize(this, "ktFmKOz8T5IGliFKtextHg6v0h50TgNivRNH3Bgx", "zkPwjxF15u4TGr27p4LutfRkvZfI36rQl5AgUWHl");

        //metoda potrebna za Push notifikacije
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}