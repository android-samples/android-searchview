package com.example.mysearch;

import java.util.ArrayList;

import android.R.anim;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.SearchView.OnQueryTextListener;

public class MainActivity extends Activity {
	ListView mListView;
	ArrayList<String> mData = new ArrayList<String>();
	ArrayAdapter<String> mAdapter; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// ListView準備
		mListView = (ListView)findViewById(R.id.listView1);
		mData.add("Google");
		mData.add("Yahoo");
		mData.add("Apple");
		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mData);
		mListView.setAdapter(mAdapter);
		mListView.setTextFilterEnabled(true);
		
		// SearchView
		SearchView search = (SearchView)findViewById(R.id.searchView1);
		// search.setIconifiedByDefault(false); // true(デフォルト): 初期はアイコン表示のみ  false: 初めから入力欄を出す
		// search.setSubmitButtonEnabled(true); // true: 右側にsubmitを出す  false(デフォルト): 右側にsubmitを出さない
		search.setQueryHint("INPUT!!");
		
		// リスナ
		search.setOnQueryTextListener(new OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				TextView t = (TextView)findViewById(R.id.textView1);
				t.setText(query + "!!!");
				return true; // 何か処理をしたらtrue
			}
			
			@Override
			public boolean onQueryTextChange(String newText) {
				TextView t = (TextView)findViewById(R.id.textView1);
				t.setText(newText);
				if(newText.length() == 0){
					mListView.clearTextFilter();
				}
				else{
					mListView.setFilterText(newText);
				}
				return true; // 何か処理をしたらtrue
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
