package item33;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author angus on 2020/6/27
 * @since 1.0.0
 */
public class Favorites {

    private Map<Class<?>, Object> favorites = new HashMap<>();


    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(type, instance);
    }

    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }

    public static void main(String[] args) {
        Favorites favorites = new Favorites();
        favorites.putFavorite(String.class, "This");
        favorites.putFavorite(Integer.class, 0xcafebabe);
        favorites.putFavorite(Class.class, Favorites.class);

        List.of(String.class, Integer.class, Class.class)
                .forEach(type -> System.out.println(favorites.getFavorite(type)));
    }
}
