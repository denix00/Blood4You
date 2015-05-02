package tvz.trip.tvzmc2.blood4you;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //zakljucavanje orijentacije ekrana u portret
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

    //metoda se pokrece klikom na btnWhyDonate, pokrece Instruction aktivnost
    public void btnWhyDonateCLickHandler(View view)
    {
        Intent intentInstructions = new Intent(this, Instructions.class);
        startActivity(intentInstructions);
    }

    //metoda se pokrece klikom na btnHelp, pokrece HelpDialogClass koja prikazuje Dialog prozor s kratkim opisom aplikacije i clanova tima
    public void btnHelpClickHandler (View view)
    {
        DialogFragment helpDialogFragment = new HelpDialogClass();
        helpDialogFragment.show(getFragmentManager(), "helpProzor");

    }

    //metoda se pokrece klikom na btnDonate, pokrece ListActivity aktivnost
    public void btnDonateClickHandler (View view)
    {
        Intent intentListActivity = new Intent (this, ListActivity.class);
        startActivity(intentListActivity);
    }
}
