package spinoffpyme.com.balllabery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

/**
 * Created by tomas on 18/01/2018.
 */

public class Bola {
    int x,y; //posición de la bola
    int velx,vely; //velocidad actual de la bola
    Bitmap bola;
    Context ctx;
    static final int MIN_VEL=-10;
    static final int MAX_VEL=10;
    int width;
    int height;

    public Bola(Context ctx) {
        this.ctx = ctx;
        bola= BitmapFactory.decodeResource(ctx.getResources(),R.drawable.ball);
        x=y=100;
    }

    public void Draw(Canvas canvas){
        canvas.drawBitmap(bola,x,y,null);
    }

    public void comunicateData(float[] data) {
        int newx=x;
        int newy=y;
        Point dsize=new Point();
        Display d=((WindowManager)ctx.getSystemService(ctx.WINDOW_SERVICE)).getDefaultDisplay();
        d.getSize(dsize);
        height=dsize.x-bola.getHeight();
        width=dsize.y-bola.getWidth();

        //nuevas velocidades
        if(d.getRotation()== Surface.ROTATION_90 || d.getRotation()==Surface.ROTATION_270){
            //ejes rotados, los datos se leen al reves
            velx+=-data[1];
            vely+=data[0];
        }else{
            velx+=-data[0];
            vely+=data[1];
        }

        //limitar la velocidad
        velx=velx>MAX_VEL?MAX_VEL:velx;
        velx=velx<MIN_VEL?MIN_VEL:velx;
        vely=vely>MAX_VEL?MAX_VEL:vely;
        vely=vely<MIN_VEL?MIN_VEL:vely;

        //actualizamos la posición
        newx+=velx;
        newy+=vely;

        //comprobar límites de la pantalla
        if(newx>height){
            velx=-velx; //rebota
            x=height;
        }else if(newx<0){
            velx=-velx;//rebota
            x=0;
        }else{
            x=newx;
        }
        if(newy>width){
            vely=-vely; //rebota
            y=width;
        }else if(newy<0){
            vely=-vely;//rebota
            y=0;
        }else{
            y=newy;
        }





    }
}
