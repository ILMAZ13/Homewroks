package ru.avinews.avinews.providers;

/**
 * Created by ilmaz on 09.12.16.
 */
public class SavesProvider {
    private static final String USER_INFORMATION = "USER_INFORMATION";
    private static final String NAME = "NAME";
    private static final String SURNAME = "SURNAME";
    private static final String CITY = "CITY";
    private static final String PHONE_NUMBER = "PHONE_NUMBER";

    private static final String NEWS = "NEWS";
    private static final String CACHE = "CACHE";
    private static final String LAST_POSITION = "LAST_POSITION";
    private static final String POPULAR_CACHE = "POPULAR_CACHE";
    private static final String LAST_POPULAR_POSITION = "LAST_POPULAR_POSITION";
    private static final String FAVOURITES = "FAVOURITES";
    private static final String TAGS = "TAGS";

    private static SavesProvider ourInstance = new SavesProvider();

    public static SavesProvider getInstance() {
        return ourInstance;
    }

    private SavesProvider() {
    }
}
