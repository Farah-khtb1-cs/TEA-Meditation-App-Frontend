package lb.edu.ul.tea;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class FavoritesManager {
    private static final String PREF_NAME = "favorites_prefs";
    private static final String FAVORITES_KEY = "favorites";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public FavoritesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Add an item to favorites
    public void addFavorite(String itemId) {
        Set<String> favorites = getFavorites();
        favorites.add(itemId);
        editor.putStringSet(FAVORITES_KEY, favorites);
        editor.apply();
    }

    // Remove an item from favorites
    public void removeFavorite(String itemId) {
        Set<String> favorites = getFavorites();
        favorites.remove(itemId);
        editor.putStringSet(FAVORITES_KEY, favorites);
        editor.apply();
    }

    // Get all favorite items
    public Set<String> getFavorites() {
        return sharedPreferences.getStringSet(FAVORITES_KEY, new HashSet<>());
    }

    // Check if an item is a favorite
    public boolean isFavorite(String itemId) {
        return getFavorites().contains(itemId);
    }
}

