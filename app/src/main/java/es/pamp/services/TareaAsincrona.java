package es.pamp.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.NotificationCompat;

import es.pamp.services.es.pamp.bbdd.BBDD;

import static android.app.PendingIntent.FLAG_ONE_SHOT;


public class TareaAsincrona extends AsyncTask {
    private BBDD bbdd;
    private SQLiteDatabase db;
    private String mensaje;
    private Context contexto;

    @Override
    protected Object doInBackground(Object[] params) {

        bbdd = new BBDD(contexto, "DBNOTIFICACIONES", null, 1 );
        db = bbdd.getWritableDatabase();

        while (!isCancelled()){

            mensaje = bbdd.leerMensaje(db);
            if (!mensaje.equals("")){
                enviarNotificacion(mensaje);
            }

            try{
                Thread.sleep(10000);  }catch (Exception e){}
        }
        return null;
    }

    public void enviarNotificacion(String mensaje){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(contexto);
        mBuilder
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setContentTitle("TEST NOTIFICACIÓN")
                .setContentText(mensaje)
                .setAutoCancel(true);//Para eliminar la notificación una vez pulsada.

        Intent resultIntent = new Intent(contexto, Main2Activity.class);
        PendingIntent contIntent = PendingIntent.getActivity(contexto, 0, resultIntent, FLAG_ONE_SHOT);

        mBuilder.setContentIntent(contIntent);

        Notification notification = mBuilder.build();
        NotificationManager mNotificationManager = (NotificationManager) contexto.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(0,notification);



    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }
}
