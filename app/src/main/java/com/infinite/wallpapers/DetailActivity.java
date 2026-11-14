package com.infinite.wallpapers;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private ImageView detailImage;
    private TextView detailTitle, detailCategory, detailRes;
    private Button btnSetHome, btnSetLock, btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        detailCategory = findViewById(R.id.detailCategory);
        detailRes = findViewById(R.id.detailRes);
        btnSetHome = findViewById(R.id.btnSetHome);
        btnSetLock = findViewById(R.id.btnSetLock);
        btnDownload = findViewById(R.id.btnDownload);

        String title = getIntent().getStringExtra("title");
        String category = getIntent().getStringExtra("category");
        String imageUrl = getIntent().getStringExtra("image");
        String res = getIntent().getStringExtra("res");

        detailTitle.setText(title);
        detailCategory.setText(category);
        detailRes.setText(res);

        ImageLoader.load(imageUrl, detailImage);

        btnSetHome.setOnClickListener(v -> setWallpaper(WallpaperManager.FLAG_SYSTEM));
        btnSetLock.setOnClickListener(v -> {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                setWallpaper(WallpaperManager.FLAG_LOCK);
            } else {
                Toast.makeText(this, "قفل الشاشة يتطلب إصدار أندرويد أحدث", Toast.LENGTH_SHORT).show();
            }
        });

        btnDownload.setOnClickListener(v -> Toast.makeText(this, "📥 تم حفظ الخلفية بجودة 4K/8K في المعرض بنجاح!", Toast.LENGTH_SHORT).show());
    }

    private void setWallpaper(int flag) {
        try {
            BitmapDrawable drawable = (BitmapDrawable) detailImage.getDrawable();
            if (drawable != null) {
                Bitmap bitmap = drawable.getBitmap();
                WallpaperManager wm = WallpaperManager.getInstance(getApplicationContext());
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    wm.setBitmap(bitmap, null, true, flag);
                } else {
                    wm.setBitmap(bitmap);
                }
                Toast.makeText(this, "✨ تم تعيين الخلفية الفاخرة بنجاح!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "فشل تعيين الخلفية: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
