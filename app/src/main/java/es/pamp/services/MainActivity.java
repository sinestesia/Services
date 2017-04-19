package es.pamp.services;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.pamp.services.es.pamp.bbdd.BBDD;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private BBDD bbdd;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bbdd = new BBDD(this, "DBNOTIFICACIONES", null, 1 );
        db = bbdd.getWritableDatabase();

        final EditText editText = (EditText) findViewById(R.id.editText);

        intent = new Intent(this, MiServicio.class);

        Button guardarBoton = (Button) findViewById(R.id.guardarBoton);
        guardarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bbdd.guardarRegistro(db,editText.getText().toString());
                editText.setText("");
                Toast toast = Toast.makeText(getApplicationContext(), "Mensaje guardado", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        Button startBoton = (Button) findViewById(R.id.starBoton);
        startBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });
        Button stopBoton = (Button) findViewById(R.id.stopBoton);
        stopBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });


    }
}
