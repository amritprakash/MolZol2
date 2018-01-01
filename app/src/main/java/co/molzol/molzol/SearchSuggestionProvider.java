package co.molzol.molzol;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by hp on 21-02-2016.
 */
public class SearchSuggestionProvider extends SearchRecentSuggestionsProvider {

    public final static String AUTHORITY = "co.molzol.molzol.SearchSuggestionProvider";
    public final static int MODE = DATABASE_MODE_QUERIES | DATABASE_MODE_2LINES;

    public SearchSuggestionProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }

}
