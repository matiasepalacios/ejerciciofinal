package com.matiaspalacios.trabajofinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.matiaspalacios.trabajofinal.classes.Configuration;
import com.matiaspalacios.trabajofinal.classes.Movies;

public class MoviesDatabaseHelper extends SQLiteOpenHelper {

	private final static String DATABASE_NAME = "movie_db.db";
	private final static int DATABASE_VERSION = 1;
	public final static String TABLE_NAME = "movies";

	public MoviesDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + Movies._ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + Movies.TITLE
				+ " TEXT NOT NULL, " + Movies.YEAR + " TEXT DEFAULT NULL, "
				+ Movies.RUNTIME + " TEXT DEFAULT NULL, " + Movies.RELEASE_DATE
				+ " TEXT DEFAULT NULL, " + Movies.CRITICS_SCORE
				+ " TEXT DEFAULT NULL, " + Movies.AUDIENCE_SCORE
				+ " TEXT DEFAULT NULL, " + Movies.POSTERS_THUMBNAIL
				+ " TEXT DEFAULT NULL, " + Movies.POSTERS_PROFILE
				+ " TEXT DEFAULT NULL, " + Movies.POSTERS_DETAILED
				+ " TEXT DEFAULT NULL, " + Movies.POSTERS_ORIGINAL
				+ " TEXT DEFAULT NULL, " + Movies.COMMENTS
				+ " TEXT DEFAULT NULL " + ");");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		if (oldVersion < newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
			onCreate(db);
		}
	}

	public void insertMovie(Movies movie) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(Movies._ID, movie.getId());
		values.put(Movies.TITLE, movie.getTitle());
		values.put(Movies.YEAR, movie.getYear());
		values.put(Movies.RUNTIME, movie.getRuntime());
		values.put(Movies.RELEASE_DATE, movie.getRelase_date());
		values.put(Movies.CRITICS_SCORE, movie.getCritics_score());
		values.put(Movies.AUDIENCE_SCORE, movie.getAudience_score());
		values.put(Movies.POSTERS_THUMBNAIL, movie.getPosters_thumbnail());
		values.put(Movies.POSTERS_PROFILE, movie.getPosters_profile());
		values.put(Movies.POSTERS_DETAILED, movie.getPosters_detailed());
		values.put(Movies.COMMENTS, movie.getComments());

		db.insert(TABLE_NAME, DATABASE_NAME, values);
	}

	public Boolean checkIfExists(Long id) {
		SQLiteDatabase db = this.getReadableDatabase();

		String[] columns = new String[] { Movies._ID };

		Cursor c = db.query(TABLE_NAME, columns, Movies._ID + " = " + id, null,
				null, null, null);
		if (c.moveToFirst()) {
			c.close();
			return true;
		}
		c.close();
		return false;
	}

	public Movies getMovieById(Long id) {
		SQLiteDatabase db = this.getReadableDatabase();
		
		Movies movie = new Movies();

		String[] columns = new String[] { Movies._ID, Movies.TITLE, Movies.YEAR,
				Movies.RUNTIME, Movies.RELEASE_DATE, Movies.CRITICS_SCORE,
				Movies.AUDIENCE_SCORE, Movies.COMMENTS };

		Cursor c = db.query(TABLE_NAME, columns, Movies._ID + " = " + id, null,
				null, null, null);
		if (c.moveToFirst()) {
			int idIndex = c.getColumnIndex(Movies._ID);
			int titleIndex = c.getColumnIndex(Movies.TITLE);
			int yearIndex = c.getColumnIndex(Movies.YEAR);
			int runtimeIndex = c.getColumnIndex(Movies.RUNTIME);
			int releaseDateIndex = c.getColumnIndex(Movies.RELEASE_DATE);
			int criticsScoreIndex = c.getColumnIndex(Movies.CRITICS_SCORE);
			int AudienceScoreIndex = c.getColumnIndex(Movies.AUDIENCE_SCORE);
			int commentsIndex = c.getColumnIndex(Movies.COMMENTS);
			movie.setId(c.getLong(idIndex));
			movie.setTitle(c.getString(titleIndex));
			movie.setYear(c.getInt(yearIndex));
			movie.setRuntime(c.getInt(runtimeIndex));
			movie.setRelase_date(c.getString(releaseDateIndex));
			movie.setCritics_score(c.getString(criticsScoreIndex));
			movie.setAudience_score(c.getString(AudienceScoreIndex));
			movie.setComments(c.getString(commentsIndex));
			c.close();
			return movie;
		}
		c.close();
		return null;

	}
	
	public Integer updateMovie(Movies movie) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put(Movies._ID, movie.getId());
		values.put(Movies.TITLE, movie.getTitle());
		values.put(Movies.YEAR, movie.getYear());
		values.put(Movies.RUNTIME, movie.getRuntime());
		values.put(Movies.RELEASE_DATE, movie.getRelase_date());
		values.put(Movies.CRITICS_SCORE, movie.getCritics_score());
		values.put(Movies.AUDIENCE_SCORE, movie.getAudience_score());
		values.put(Movies.POSTERS_THUMBNAIL, movie.getPosters_thumbnail());
		values.put(Movies.POSTERS_PROFILE, movie.getPosters_profile());
		values.put(Movies.POSTERS_DETAILED, movie.getPosters_detailed());
		values.put(Movies.COMMENTS, movie.getComments());
		
		String id = String.valueOf(movie.getId());

		Integer count = db.update(TABLE_NAME, values, Movies._ID + "=" + id , null);
		return count;
	}

}
