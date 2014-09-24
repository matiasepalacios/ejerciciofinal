package com.matiaspalacios.trabajofinal.connections;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.database.MatrixCursor;
import android.util.JsonReader;

import com.matiaspalacios.trabajofinal.classes.Movies;
import com.matiaspalacios.trabajofinal.database.MoviesDatabaseHelper;

public class TheaterMovies extends RottenTomatoesAPI {

	private static String MOVIES_IN_THEATERS = "lists/movies/in_theaters.json";

	public MatrixCursor getMoviesInTheatersList(Context context) {
		String uri = super.getFullUri(MOVIES_IN_THEATERS, 5, 1, "ar");
		InputStream is = requestHttp(uri, super._GET);
		MatrixCursor movies = readMoviesInfo(context, is);
		return movies;
	}

	public MatrixCursor getMoviesInTheatersList(Context context, String country) {
		String uri = super.getFullUri(MOVIES_IN_THEATERS, 5, 1, country);
		InputStream is = requestHttp(uri, super._GET);
		MatrixCursor movies = readMoviesInfo(context, is);
		return movies;
	}

	public MatrixCursor getMoviesInTheatersList(Context context,
			int page_limit, String country) {
		String uri = super.getFullUri(MOVIES_IN_THEATERS, page_limit, 1,
				country);
		InputStream is = requestHttp(uri, super._GET);
		MatrixCursor movies = readMoviesInfo(context, is);
		return movies;
	}

	private MatrixCursor readMoviesInfo(Context context, InputStream is) {
		Movies info = null;
		JsonReader reader = null;
		MoviesDatabaseHelper db = new MoviesDatabaseHelper(context);

		MatrixCursor mc = new MatrixCursor(new String[] { "_id", "title",
				"year", "runtime", "release_date", "critics_score",
				"audience_score", "thumbnail" });

		reader = new JsonReader(new InputStreamReader(is));
		try {
			reader.beginObject();

			while (reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals("movies")) { // search for the movies node -
												// contains array
					reader.beginArray();
					while (reader.hasNext()) {
						reader.beginObject(); // starts the movie object
						info = new Movies();
						while (reader.hasNext()) {
							String name2 = reader.nextName();
							if (name2.equals("id")) {
								info.setId((long) reader.nextLong());
							} else if (name2.equals("title")) {
								info.setTitle(reader.nextString());
							} else if (name2.equals("year")) {
								info.setYear(reader.nextInt());
							} else if (name2.equals("runtime")) {
								info.setRuntime(reader.nextInt());
							} else if (name2.equals("release_dates")) {
								reader.beginObject();
								while (reader.hasNext()) {
									String releaseDate = reader.nextName();
									if (releaseDate.equals("theater")) {
										info.setRelase_date(reader.nextString());
									} else
										reader.skipValue();
								}
								reader.endObject();
							} else if (name2.equals("ratings")) {
								reader.beginObject();
								while (reader.hasNext()) {
									String ratings = reader.nextName();
									if (ratings.equals("critics_score")) {
										info.setCritics_score(reader
												.nextString());
									} else if (ratings.equals("audience_score")) {
										info.setAudience_score(reader
												.nextString());
									} else
										reader.skipValue();
								}
								reader.endObject();
							} else if (name2.equals("posters")) { // get the
																	// thumbnail
								reader.beginObject();
								while (reader.hasNext()) {
									String name3 = reader.nextName();
									if (name3.equals("thumbnail")) {
										info.setPosters_thumbnail(reader
												.nextString());
									} else
										reader.skipValue();
								}
								reader.endObject();
							} else
								reader.skipValue();
						}
						reader.endObject();
						Boolean check = db.checkIfExists(info.getId());
						if (check == false) {
							db.insertMovie(info);
						}
						mc.addRow(new Object[] { info.getId(), info.getTitle(),
								info.getYear(), info.getRuntime() + " minutes",
								info.getRelase_date(), info.getCritics_score(),
								info.getAudience_score(),
								info.getPosters_thumbnail() });

					}
					reader.endArray();
				} else
					reader.skipValue();

			}

			reader.endObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// return movies;
		return mc;

	}

}
