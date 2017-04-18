package es.pamp.services;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by cice on 18/4/17.
 */

public class TareaAsincrona extends AsyncTask {


    @Override
    protected Object doInBackground(Object[] params) {

        int i=1;
        while (i>0) {
            i=i+1;
            Log.e(Integer.toString(i),"contador");
            if (isCancelled()) {
                break;
            }
        }
        return null;
    }
}
