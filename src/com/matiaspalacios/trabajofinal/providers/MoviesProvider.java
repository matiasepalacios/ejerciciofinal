package com.matiaspalacios.trabajofinal.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

import com.matiaspalacios.trabajofinal.classes.Movies;
import com.matiaspalacios.trabajofinal.database.MoviesDatabaseHelper;

/**
 * Disclaimer: Este provider fue creado con el solo objeto de cumplir con la
 * consigna de guardar un dato con un provider y solo implementa el método
 * update(), que a su vez hace uso del update() de la clase
 * MoviesDatabaseHelper. El resto de las funciones crud se invocan directo del
 * Helper. El motivo que sustenta esta decisión es la misma documentación de
 * android, donde sugiere los casos en los que se debe implementar un Content
 * Provider.
 * http://developer.android.com/guide/topics/providers/content-provider
 * -creating.html#BeforeYouStart
 * 
 * @author matiaspalacios
 * 
 */

public class MoviesProvider extends ContentProvider {

	private MoviesDatabaseHelper mDbHelper;
	private static final String AUTHORITY = "com.matiaspalacios.trabajofinal.providers";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/movies");
	private static final int URI_MOVIES = 1;
	private static final int URI_MOVIE_ITEM = 2;

	private static final UriMatcher mUriMatcher;

	static {
		mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		mUriMatcher.addURI(AUTHORITY, "movies", URI_MOVIES);
		mUriMatcher.addURI(AUTHORITY, "movies/#", URI_MOVIE_ITEM);
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		mDbHelper = new MoviesDatabaseHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		int uriType = mUriMatcher.match(uri);
		SQLiteDatabase sqlDB = mDbHelper.getWritableDatabase();
		int rowsUpdated = 0;
		switch (uriType) {
		case URI_MOVIES:

			break;
		case URI_MOVIE_ITEM:
			String id = uri.getLastPathSegment();
			if (TextUtils.isEmpty(selection)) {
				rowsUpdated = sqlDB.update(MoviesDatabaseHelper.TABLE_NAME,
						values, Movies._ID + "=" + id, null);
			} else {
				rowsUpdated = sqlDB.update(MoviesDatabaseHelper.TABLE_NAME,
						values, Movies._ID + "=" + id + " and " + selection,
						selectionArgs);
			}
			break;
		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsUpdated;
	}

}
