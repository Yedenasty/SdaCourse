package com.example.rent.sdacourse.Drawer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.rent.sdacourse.R;
import com.example.rent.sdacourse.gallery.GalleryActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Drawer extends AppCompatActivity {


    public static final String DRAWING_GALLERY_DIR = "drawing_gallery";
    private SimpleDrawingView simpleDrawingView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        getSupportActionBar().setTitle("Rysowiacz");

        simpleDrawingView = (SimpleDrawingView) findViewById(R.id.simpleDrawingView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        drawerLayout = (DrawerLayout) findViewById(R.id.activity_drawer);
        Button blueButton = (Button) findViewById(R.id.blue_button);
        Button redButton = (Button) findViewById(R.id.red_button);
        Button greenButton = (Button) findViewById(R.id.green_button);
        Button blackButton = (Button) findViewById(R.id.black_button);
        Button whiteButton = (Button) findViewById(R.id.white_button);
        Button fioletekButton = (Button) findViewById(R.id.fioletek_button);

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentCollor(ContextCompat.getColor(Drawer.this, R.color.blue));
            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentCollor(ContextCompat.getColor(Drawer.this, R.color.red));
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setCurrentCollor(ContextCompat.getColor(Drawer.this, R.color.green));
            }
        });

        blackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleDrawingView.setWielkoscgumy(10);
                simpleDrawingView.setCurrentCollor(ContextCompat.getColor(Drawer.this, R.color.black));
            }
        });

        whiteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                simpleDrawingView.setWielkoscgumy(100);
                simpleDrawingView.setCurrentCollor(ContextCompat.getColor(Drawer.this, R.color.white));
            }
        });

        fioletekButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                simpleDrawingView.setWielkoscgumy(10);
                simpleDrawingView.setCurrentCollor(ContextCompat.getColor(Drawer.this, R.color.fioletek));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear: {
                simpleDrawingView.clear();
                break;
            }
            case R.id.save: {
                saveDrawingToFile();
                break;
            }
            case android.R.id.home: {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                break;
            }
            case R.id.drawing_gallery: {
                Intent intent = new Intent(this, GalleryActivity.class);
                startActivity(intent);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveDrawingToFile() {
        File drawingFile = new File(getDrawingGalleryDirectory(), createFileName());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(drawingFile);
            Bitmap bitmap = convertViewToBitmap(simpleDrawingView);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private String createFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return "my_drawing" + timeStamp + ".png";
    }

    private File getDrawingGalleryDirectory() {
        return getExternalFilesDir(DRAWING_GALLERY_DIR);
    }

    private Bitmap convertViewToBitmap(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return bitmap;
    }
}
