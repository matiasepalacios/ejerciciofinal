package com.matiaspalacios.trabajofinal.tasks;

import com.matiaspalacios.trabajofinal.classes.MyBus;
import com.matiaspalacios.trabajofinal.connections.TheaterMovies;

import android.content.Context;
import android.database.MatrixCursor;
import android.os.AsyncTask;

public class MoviesAsyncTask extends AsyncTask<String, Void, MatrixCursor> {

	private Context mContext;
    public MoviesAsyncTask (Context context){
         mContext = context;
    }
    
	@Override
	protected void onPostExecute(MatrixCursor result) {
		MyBus.getInstance().post(new MoviesAsyncTaskResultEvent(result));
	}

	@Override
	protected MatrixCursor doInBackground(String... params) {
		// TODO Auto-generated method stub
		TheaterMovies tm = new TheaterMovies();
		MatrixCursor mc;
		if (params[0] != null && params[1] == null) {

			mc = tm.getMoviesInTheatersList(mContext, params[0]);
			
		} else if (params[1] != null) {

			mc = tm.getMoviesInTheatersList(mContext, Integer.parseInt(params[0]), params[1]);
			
		} else {

			mc = tm.getMoviesInTheatersList(mContext);
			
		}
			
		
		return mc;
	}

}
