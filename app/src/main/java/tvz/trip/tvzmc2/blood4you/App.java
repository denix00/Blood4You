package tvz.trip.tvzmc2.blood4you;
import com.parse.Parse;
import com.parse.ParseInstallation;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "ktFmKOz8T5IGliFKtextHg6v0h50TgNivRNH3Bgx", "zkPwjxF15u4TGr27p4LutfRkvZfI36rQl5AgUWHl");

        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}