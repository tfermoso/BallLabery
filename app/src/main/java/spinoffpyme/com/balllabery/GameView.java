package spinoffpyme.com.balllabery;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

/**
 * Created by tomas on 18/01/2018.
 */

public class GameView extends SurfaceView implements View.OnClickListener {
    SurfaceHolder holder;
    Context ctx;
    Bola bola;
    public GameView(Context context) {
        super(context);
        ctx=context;
        holder=getHolder();
        this.setOnClickListener(this);
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                bola=new Bola(getContext());
                Canvas c = holder.lockCanvas(null);
                draw(c);
                holder.unlockCanvasAndPost(c);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        bola.Draw(canvas);
    }

    public void comunicateData(float[] data) {
        bola.comunicateData(data);
        Canvas c = holder.lockCanvas(null);
        draw(c);
        holder.unlockCanvasAndPost(c);
        bola.Draw(c);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(ctx,"ADIOS",Toast.LENGTH_SHORT).show();
        ((MainActivity) ctx).finish();
    }
}
