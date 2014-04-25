package ru.brokensteel.android.reader;

import ru.brokensteel.android.reader.data.RssItem;
import ru.brokensteel.android.reader.listeners.ListListener;
import ru.brokensteel.android.reader.util.RssReader;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Main application activity.
 * 
 * @author Vladimir Janowski
 *
 */
public class BrokenSteelReaderAppActivity extends Activity {
	/** 
	 * This method creates main application view
	 */
	
	public void Parse(String url) {
		try {
			// Create RSS reader
			RssReader rssReader = new RssReader(url);
			// Get a ListView from main view
			ListView items = (ListView) findViewById(R.id.listMainView);
			
			// Create a list adapter
			ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(this,android.R.layout.simple_list_item_1, rssReader.getItems());
			// Set list adapter for the ListView
			items.setAdapter(adapter);
			
			// Set list view item click listener
			items.setOnItemClickListener(new ListListener(rssReader.getItems(), this));
			
		} catch (Exception e) {
			Log.e("BrokenSteelRssReader", e.getMessage());
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set view
		setContentView(R.layout.main);

		Parse("http://feeds.feedburner.com/BrokenSteel");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}
	
	public void onUpdateMenuClick(MenuItem item)
	{
		Parse("http://feeds.feedburner.com/BrokenSteel");
	}
}