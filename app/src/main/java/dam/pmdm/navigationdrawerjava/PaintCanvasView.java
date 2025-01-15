package dam.pmdm.navigationdrawerjava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.MotionEvent;

import java.util.ArrayList;

public class PaintCanvasView extends View {

    // Lista para almacenar las coordenadas de los toques
    private ArrayList<float[]> touches = new ArrayList<>();

    public PaintCanvasView(Context context) {
        super(context);
    }

    public PaintCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintCanvasView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Crear un objeto Paint para definir cómo se dibuja
        Paint paint = new Paint();
        paint.setColor(Color.RED); // Establecer color para el círculo

        // Dibujar todos los círculos en las posiciones guardadas
        for (float[] touch : touches) {
            canvas.drawCircle(touch[0], touch[1], 50, paint);
        }

        // Cambiar color para el texto
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);

        // Dibujar texto
        canvas.drawText("Deja el rastro tocando", 100, 100, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Guardar la nueva posición del toque en la lista
        touches.add(new float[]{event.getX(), event.getY()});

        // Llamar a invalidate() para redibujar la vista
        invalidate();

        return true;
    }
}