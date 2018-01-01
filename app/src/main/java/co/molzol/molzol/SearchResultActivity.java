package co.molzol.molzol;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

public class SearchResultActivity extends AppCompatActivity implements SearchResultFragment.OnFragmentInteractionListener{

    private ProgressBar spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        spinner = (ProgressBar)findViewById(R.id.searchProgressBar);
        spinner.setVisibility(View.GONE);
        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView =
                (SearchView) searchItem.getActionView();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconified(false);
        searchView.setActivated(true);
        return true;
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
    }

    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            spinner.setVisibility(View.VISIBLE);
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("query",query);
            saveQuery(query);

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment searchResultFragment = SearchResultFragment.newInstance(query, 20, spinner);
            fragmentTransaction.add(R.id.searchResultContainer, searchResultFragment);
            fragmentTransaction.commit();
        }
    }

    private void saveQuery(String query){
        SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                SearchSuggestionProvider.AUTHORITY, SearchSuggestionProvider.MODE);
        suggestions.saveRecentQuery(query, null);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
