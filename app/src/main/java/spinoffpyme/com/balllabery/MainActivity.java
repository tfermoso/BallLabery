package spinoffpyme.com.balllabery;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView texto;
    GameView gameView;
    SensorManager sensorManager;
    private Sensor acelerometro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //texto=(TextView) findViewById(R.id.texto);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        acelerometro=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        /*List<Sensor> miLista=sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (int i = 1; i <miLista.size() ; i++) {
            texto.append("\n" +miLista.get(i).getName()+"\n"+miLista.get(i).getVendor()+"\n"+miLista.get(i).getVersion());
        }*/
        gameView=new GameView(this);
        setContentView(gameView);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        gameView.comunicateData(event.values.clone());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,acelerometro,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
