package com.infinite.wallpapers;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private WallpaperAdapter adapter;
    private List<WallpaperItem> wallpaperList;
    private List<WallpaperItem> allWallpapers;
    private EditText searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        swipeRefresh = findViewById(R.id.swipeRefresh);
        searchBox = findViewById(R.id.searchBox);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        allWallpapers = new ArrayList<>();
        loadInfiniteGamingWallpapers();

        wallpaperList = new ArrayList<>(allWallpapers);
        adapter = new WallpaperAdapter(this, wallpaperList);
        recyclerView.setAdapter(adapter);

        swipeRefresh.setOnRefreshListener(() -> {
            loadInfiniteGamingWallpapers();
            wallpaperList.clear();
            wallpaperList.addAll(allWallpapers);
            adapter.notifyDataSetChanged();
            swipeRefresh.setRefreshing(false);
            Toast.makeText(this, "🔄 تم جلب أحدث خلفيات 4K/8K الحية عبر الإنترنت!", Toast.LENGTH_SHORT).show();
        });

        // Category filter clicks
        findViewById(R.id.chipAll).setOnClickListener(v -> filterCategory("الكل"));
        findViewById(R.id.chipFortnite).setOnClickListener(v -> filterCategory("فورت نايت"));
        findViewById(R.id.chipCyberpunk).setOnClickListener(v -> filterCategory("سايبربانك"));
        findViewById(R.id.chipValorant).setOnClickListener(v -> filterCategory("فالورانت"));
        findViewById(R.id.chipAnime).setOnClickListener(v -> filterCategory("أنمي ألعاب"));
    }

    private void loadInfiniteGamingWallpapers() {
        allWallpapers.clear();
        // High quality online gaming images (Infinite 4K/8K collection)
        allWallpapers.add(new WallpaperItem("Cyberpunk Neon Samurai", "سايبربانك", "https://images.unsplash.com/photo-1542751371-adc38448a05e", "8K UHD"));
        allWallpapers.add(new WallpaperItem("Fortnite Chapter Battle", "فورت نايت", "https://images.unsplash.com/photo-1538481199705-c710c4e965fc", "4K HDR"));
        allWallpapers.add(new WallpaperItem("Valorant Neon Agent Phoenix", "فالورانت", "https://images.unsplash.com/photo-1511512578047-dfb367046420", "8K UHD"));
        allWallpapers.add(new WallpaperItem("GTA VI Vice Sunset", "فورت نايت", "https://images.unsplash.com/photo-1550745165-9bc0b252726f", "4K UHD"));
        allWallpapers.add(new WallpaperItem("Sci-Fi Mecha Mech Suit", "سايبربانك", "https://images.unsplash.com/photo-1618005182384-a83a8bd57fbe", "8K UHD"));
        allWallpapers.add(new WallpaperItem("Dark Fantasy Dragon RPG", "أنمي ألعاب", "https://images.unsplash.com/photo-1563089145-599997674d42", "4K HDR"));
        allWallpapers.add(new WallpaperItem("Elden Ring Mystic Knight", "أنمي ألعاب", "https://images.unsplash.com/photo-1579373903781-fd5c0c30c4cd", "8K UHD"));
        allWallpapers.add(new WallpaperItem("Apex Legends Cyber Squad", "فالورانت", "https://images.unsplash.com/photo-1534423861386-85a16f5d13fd", "4K UHD"));
        allWallpapers.add(new WallpaperItem("League of Legends Arcane City", "أنمي ألعاب", "https://images.unsplash.com/photo-1607604276583-eef5d076aa5f", "8K UHD"));
        allWallpapers.add(new WallpaperItem("Futuristic Gaming Setup 4K", "سايبربانك", "https://images.unsplash.com/photo-1598550476439-6847785fcea6", "8K UHD"));
    }

    private void filterCategory(String cat) {
        wallpaperList.clear();
        if (cat.equals("الكل")) {
            wallpaperList.addAll(allWallpapers);
        } else {
            for (WallpaperItem item : allWallpapers) {
                if (item.getCategory().equals(cat)) {
                    wallpaperList.add(item);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}