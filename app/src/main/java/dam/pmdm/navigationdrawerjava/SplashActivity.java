package dam.pmdm.navigationdrawerjava;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000; // 3 segundos para la Splash

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Si la versión de la API es inferior a 31, mostraremos la Splash personalizada
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            // Si la versión es inferior a Android 12 (API 31), manejamos la Splash manualmente
            setContentView(R.layout.splash);  // Asegúrate de tener este layout para la Splash

            // Usamos un Handler para retrasar el inicio de la MainActivity
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Después del tiempo de la Splash, se inicia la MainActivity
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();  // Terminamos la SplashActivity para que no vuelva a abrirse
                }
            }, SPLASH_TIME_OUT);
        } else {
            // Si la versión es 31 o superior, dejamos que Android maneje la Splash automáticamente
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();  // Terminamos la SplashActivity ya que no necesitamos hacer nada más
        }
    }
}