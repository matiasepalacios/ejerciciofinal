package com.matiaspalacios.trabajofinal.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.matiaspalacios.trabajofinal.R;
import com.matiaspalacios.trabajofinal.classes.MyBus;
import com.matiaspalacios.trabajofinal.tasks.MoviesAsyncTask;
import com.matiaspalacios.trabajofinal.tasks.MoviesAsyncTaskResultEvent;
import com.squareup.otto.Subscribe;

public class MainActivity extends ActionBarActivity {

	public static SimpleCursorAdapter mAdapter;
	public static Cursor mCursor;
	public static MoviesAsyncTask task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
			task = new MoviesAsyncTask(this);
			task.execute("5", "ar");

			MyBus.getInstance().register(this);

		}

	}

	@Subscribe
	public void onAsyncTaskResult(MoviesAsyncTaskResultEvent event) {
		View spinner = (View) findViewById(R.id.progressBar1);

		if (mCursor == null) {
			spinner.setVisibility(View.INVISIBLE);

			mCursor = event.getResult();
			// the columns from where I will fill the data
			String[] from = new String[] { "title", "runtime" };

			// where I will put the data
			int[] to = new int[] { R.id.textView1, R.id.textView2 };
			mAdapter = new SimpleCursorAdapter(this, R.layout.movies_list_info,
					mCursor, from, to, 0);

			/**
			 * TODO: I still need to get the image from an URL and display it on
			 * the list.
			 */

			ListView mMyListView = (ListView) findViewById(R.id.listView1);
			mMyListView.setAdapter(mAdapter);

			mMyListView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					Cursor mycursor = (Cursor) arg0.getItemAtPosition(position);
					String _id = mycursor.getString(0);

					Intent intent = new Intent(MainActivity.this,
							ViewMovieDetailActivity.class);
					intent.putExtra("_id", _id);

					startActivity(intent);

					// Toast.makeText(MainActivity.this,"mycursor.getString(1) "
					// + mycursor.getString(4) +"   ", Toast.LENGTH_SHORT
					// ).show();
				}
			});

			mMyListView
					.setOnItemLongClickListener(new OnItemLongClickListener() {

						@Override
						public boolean onItemLongClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							Cursor mycursor = (Cursor) parent
									.getItemAtPosition(position);
							String id1 = mycursor.getString(0);
							return false;
						}
					});

		} else {
			mCursor = event.getResult();
			mAdapter.swapCursor(mCursor);
			mAdapter.notifyDataSetChanged();
		}

		// Toast.makeText(this, "llegué hasta acá", Toast.LENGTH_LONG).show();
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
		if (id == R.id.countries_submenu1) { // argentina
			task = new MoviesAsyncTask(this);
			task.execute("5", "ar");

			MyBus.getInstance().register(this);
		} else if (id == R.id.countries_submenu2) { // colombia
			task = new MoviesAsyncTask(this);
			task.execute("2", "co");

			MyBus.getInstance().register(this);
		} else if (id == R.id.countries_submenu3) { // estados unidos
			task = new MoviesAsyncTask(this);
			task.execute("9", "us");

			MyBus.getInstance().register(this);
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			if (savedInstanceState != null) {
				View spinner = (View) rootView.findViewById(R.id.progressBar1);
				spinner.setVisibility(View.INVISIBLE);
				ListView mMyListView = (ListView) rootView
						.findViewById(R.id.listView1);
				mMyListView.setAdapter(MainActivity.mAdapter);

//				ListView mMyListView1 = (ListView) rootView
//						.findViewById(R.id.listView1);
//				mMyListView1.setAdapter(mAdapter);
				mMyListView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						Cursor mycursor = (Cursor) arg0
								.getItemAtPosition(position);
						String _id = mycursor.getString(0);

						Intent intent = new Intent(getActivity(),
								ViewMovieDetailActivity.class);
						intent.putExtra("_id", _id);

						startActivity(intent);
					}
				});

			}

			return rootView;

		}

	}
}
