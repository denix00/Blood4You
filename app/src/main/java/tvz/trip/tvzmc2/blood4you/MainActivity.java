package tvz.trip.tvzmc2.blood4you;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.Parse;
import com.parse.ParseInstallation;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Parse.com API omogucavanje LocalDatastorea za spremanje podatak
        Parse.enableLocalDatastore(this);
          // Postavljanje ID i klijent kljuca za Parse.org push notifikacije
        Parse.initialize(this, "ktFmKOz8T5IGliFKtextHg6v0h50TgNivRNH3Bgx", "zkPwjxF15u4TGr27p4LutfRkvZfI36rQl5AgUWHl");
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void clickHandlerInstructions(View view)
    {
        Intent intentInstructions = new Intent(this, Instructions.class);
        startActivity(intentInstructions);
    }


    public void clickHandlerHelp (View view)
    {
        DialogFragment helpDialogFragment = new HelpDialogClass();
        helpDialogFragment.show(getFragmentManager(), "helpProzor");

    }

    public void clickHandlerBtnPopisAkcija (View view)
    {
        Intent intentListActivity = new Intent (this, ListActivity.class);
        startActivity(intentListActivity);

    }

}
