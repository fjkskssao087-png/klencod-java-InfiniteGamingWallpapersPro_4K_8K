package com.infinite.wallpapers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(6);
    private static final Handler handler = new Handler(Looper.getMainLooper());
    private static final Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<>());

    public static void load(String url, ImageView imageView) {
        imageViews.put(imageView, url);
        imageView.setImageResource(R.drawable.ic_placeholder);

        executorService.execute(() -> {
            Bitmap bitmap = downloadBitmap(url);
            handler.post(() -> {
                String currentTag = imageViews.get(imageView);
                if (currentTag != null && currentTag.equals(url)) {
                    if (bitmap != null) {
                        imageView.setImageBitmap(bitmap);
                    } else {
                        imageView.setImageResource(R.drawable.ic_placeholder);
                    }
                }
            });
        });
    }

    private static Bitmap downloadBitmap(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
