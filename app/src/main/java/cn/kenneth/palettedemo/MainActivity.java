package cn.kenneth.palettedemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg2);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);

        Palette palette = Palette.generate(bitmap);

        TextView tv1 = (TextView) findViewById(R.id.tv1);
        //返回一个活力的颜色
        tv1.setTextColor(palette.getVibrantSwatch().getRgb());
        //返回一个柔和的颜色
        tv1.setBackgroundColor(palette.getMutedSwatch().getRgb());

        TextView tv2 = (TextView) findViewById(R.id.tv2);
        //返回一个活力的亮色
        tv2.setTextColor(palette.getLightVibrantSwatch().getRgb());
        //返回一个柔和的亮色
        tv2.setBackgroundColor(palette.getLightMutedSwatch().getRgb());

        TextView tv3 = (TextView) findViewById(R.id.tv3);
        //返回一个活力的暗色
        tv3.setTextColor(palette.getDarkVibrantSwatch().getRgb());
        //返回一个柔和的暗色
        tv3.setBackgroundColor(palette.getDarkMutedSwatch().getRgb());

        TextView tv4 = (TextView) findViewById(R.id.tv4);
        //返回一个适合做标题的颜色
        tv4.setTextColor(palette.getVibrantSwatch().getTitleTextColor());
        //返回一个适合做主题的颜色
        tv4.setBackgroundColor(palette.getDarkMutedSwatch().getBodyTextColor());

//        new MyAsyncTask(bitmap).execute();
        //提供了一个异步方法
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                TextView tv5 = (TextView) findViewById(R.id.tv5);
                //返回一个活力的颜色
                tv5.setTextColor(palette.getVibrantSwatch().getRgb());
                //返回一个柔和的颜色
                tv5.setBackgroundColor(palette.getMutedSwatch().getRgb());
            }
        });
    }

    public class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        private Bitmap bitmap;

        public MyAsyncTask(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //提供了一个异步方法
            Palette.generateAsync(this.bitmap, new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    TextView tv5 = (TextView) findViewById(R.id.tv5);
                    //返回一个活力的颜色
                    tv5.setTextColor(palette.getVibrantSwatch().getRgb());
                    //返回一个柔和的颜色
                    tv5.setBackgroundColor(palette.getMutedSwatch().getRgb());
                }
            });
        }
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
}
