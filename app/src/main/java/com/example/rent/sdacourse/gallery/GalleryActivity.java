package com.example.rent.sdacourse.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rent.sdacourse.Drawer.Drawer;
import com.example.rent.sdacourse.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by RENT on 2017-02-21.
 */

public class GalleryActivity extends AppCompatActivity {


    private DrawingPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_activity);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        File dir = getExternalFilesDir(Drawer.DRAWING_GALLERY_DIR);
        File[] files = dir.listFiles();
        pagerAdapter = new DrawingPagerAdapter(files);

        viewPager.setAdapter(pagerAdapter);

        getSupportActionBar().setTitle("Galeryja" + files.length);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gallery_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            pagerAdapter.deleteItem(viewPager.getCurrentItem());

        }
        return super.onOptionsItemSelected(item);
    }
}
