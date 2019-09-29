package com.xcheko51x.mpandroidchartsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BarChart grafica;

    List<Venta> listaVentas = new ArrayList<>();
    List<BarEntry> entradas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grafica = findViewById(R.id.graficaBarras);

        String[] dias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
        String[] ventas = {"100", "550", "370", "600", "150"};

        insertarVentas(dias, ventas);

        obtenerVentas();

        for(int i = 0 ; i < listaVentas.size() ; i++) {
            entradas.add(new BarEntry(i, Float.parseFloat(listaVentas.get(i).getTotalVendido())));
        }

        BarDataSet datos = new BarDataSet(entradas, "GRAFICA DE VENTAS");
        BarData data = new BarData(datos);

        datos.setColors(ColorTemplate.COLORFUL_COLORS);

        data.setBarWidth(0.9f);

        grafica.setData(data);
        grafica.setFitBars(true);
        grafica.invalidate();
    }

    public void insertarVentas(String[] dias, String[] ventas) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "dbSistema", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();

        for(int i = 0 ; i < dias.length ; i++) {
            registro.put("dia", dias[i]);
            registro.put("totalVendido", ventas[i]);

            db.insert("ventas", null, registro);
        }

        db.close();
    }

    public void obtenerVentas() {
        listaVentas.clear();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "dbSistema", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        Cursor fila = db.rawQuery("select * from ventas", null);
        if(fila != null && fila.getCount() != 0) {
            fila.moveToFirst();
            do {
                listaVentas.add(
                        new Venta(
                                fila.getString(0),
                                fila.getString(1),
                                fila.getString(2)
                        )
                );
            } while(fila.moveToNext());
        } else {
            Toast.makeText(this, "NO HAY REGISTROS", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

}
