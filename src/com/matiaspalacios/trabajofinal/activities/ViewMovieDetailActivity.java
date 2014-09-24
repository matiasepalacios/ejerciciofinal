package com.matiaspalacios.trabajofinal.activities;

import com.matiaspalacios.trabajofinal.R;
import com.matiaspalacios.trabajofinal.R.layout;
import com.matiaspalacios.trabajofinal.classes.Movies;
import com.matiaspalacios.trabajofinal.database.MoviesDatabaseHelper;
import com.matiaspalacios.trabajofinal.providers.MoviesProvider;

import android.support.v7.app.ActionBarActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewMovieDetailActivity extends ActionBarActivity {

	protected Movies mMovie;
	protected MoviesDatabaseHelper mDb;
	protected Long id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_movie_detail);

//		if (savedInstanceState == null) {

			mDb = new MoviesDatabaseHelper(this);

			Intent intent = getIntent();

			id = Long.parseLong(intent.getExtras().getString("_id"));

			mMovie = mDb.getMovieById(id);

			TextView txt_title = (TextView) findViewById(R.id.textView1);
			txt_title.setText(mMovie.getTitle());
			TextView txt_year = (TextView) findViewById(R.id.textView3);
			txt_year.setText(String.valueOf(mMovie.getYear()));
			TextView txt_runtime = (TextView) findViewById(R.id.textView5);
			txt_runtime.setText(String.valueOf(mMovie.getRuntime()));
			TextView txt_release_date = (TextView) findViewById(R.id.textView7);
			txt_release_date.setText(mMovie.getRelase_date());
			TextView txt_critics_score = (TextView) findViewById(R.id.textView9);
			txt_critics_score.setText(mMovie.getCritics_score());
			TextView txt_audience_score = (TextView) findViewById(R.id.textView11);
			txt_audience_score.setText(mMovie.getAudience_score());
			EditText txt_comments = (EditText) findViewById(R.id.editText1);
			txt_comments.setText(mMovie.getComments());

//		}

		
		setButtonsActions(id);

	}

	protected void setButtonsActions(final Long id) {

		final Button button = (Button) findViewById(R.id.button2);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Perform action on click
				EditText txt_comments = (EditText) findViewById(R.id.editText1);
				String comments = txt_comments.getText().toString();
				mMovie.setComments(comments);
				ContentResolver cr = getContentResolver();
				
				Uri uri = Uri.parse(MoviesProvider.CONTENT_URI + "/"
						+ id);
				ContentValues values = new ContentValues();

				values.put(Movies._ID, mMovie.getId());
				values.put(Movies.TITLE, mMovie.getTitle());
				values.put(Movies.YEAR, mMovie.getYear());
				values.put(Movies.RUNTIME, mMovie.getRuntime());
				values.put(Movies.RELEASE_DATE, mMovie.getRelase_date());
				values.put(Movies.CRITICS_SCORE, mMovie.getCritics_score());
				values.put(Movies.AUDIENCE_SCORE, mMovie.getAudience_score());
				values.put(Movies.POSTERS_THUMBNAIL, mMovie.getPosters_thumbnail());
				values.put(Movies.POSTERS_PROFILE, mMovie.getPosters_profile());
				values.put(Movies.POSTERS_DETAILED, mMovie.getPosters_detailed());
				values.put(Movies.COMMENTS, mMovie.getComments());
				cr.update(uri, values, null, null);
				
//				mDb.updateMovie(mMovie);
				Intent intent = new Intent();

				setResult(RESULT_OK, intent);
				finish();

			}
		});
	}
}
