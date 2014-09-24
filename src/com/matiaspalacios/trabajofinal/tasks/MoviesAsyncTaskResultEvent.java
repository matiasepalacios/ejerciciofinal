package com.matiaspalacios.trabajofinal.tasks;

import android.database.MatrixCursor;

public class MoviesAsyncTaskResultEvent {

	private MatrixCursor result;

	public MoviesAsyncTaskResultEvent(MatrixCursor result) {
		this.result = result;
	}
	
	public MatrixCursor getResult() {
		return result;
	}

}
