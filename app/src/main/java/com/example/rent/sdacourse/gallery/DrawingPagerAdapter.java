package com.example.rent.sdacourse.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rent.sdacourse.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.id.list;

/**
 * Created by RENT on 2017-02-21.
 */

public class DrawingPagerAdapter extends PagerAdapter {


    private File[] files;

    public DrawingPagerAdapter(File[] files) {
      this.files = files;
    }

    public void deleteItem(int currentItem) {
        if(currentItem < files.length) {
            List<File> list = new ArrayList<>(Arrays.asList(files));
            list.remove(currentItem).delete();

            File[] newFiles = new File[list.size()];
            list.toArray(newFiles);
            files = newFiles;
            notifyDataSetChanged();
        }

    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       LayoutInflater inflater = LayoutInflater.from(container.getContext());
       View view = inflater.inflate(R.layout.single_page_item, container, false);
        ImageView image = (ImageView) view.findViewById(R.id.drawing_image);


        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(files[position]);
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
            image.setImageBitmap(bitmap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return files.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }


}
