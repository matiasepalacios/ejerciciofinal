package com.matiaspalacios.trabajofinal.connections;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RottenTomatoesAPI {

	protected final static String _URL = "http://api.rottentomatoes.com/api/public/v1.0/";
	protected final static String _API_KEY = "vxdsp8a4p58wyz9uyjmn4yyq";
	protected final String _GET = "GET";

	public InputStream requestHttp(String uri, String method) {

		InputStream info = null;

		try {
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			/**
			 * example call
			 * 
			 * http://api.rottentomatoes.com/api/public/v1.0/lists/movies/
			 * in_theaters.json?
			 * page_limit=1&page=1&country=ar&apikey=vxdsp8a4p58wyz9uyjmn4yyq
			 */
			connection.setRequestMethod(method);

			try {

				info = connection.getInputStream();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				if (connection != null && info == null)
					connection.disconnect();

			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return info;
	}

	public String getFullUri(String uri, int page_limit, int page,
			String country) {
		String fullUri = _URL + uri + "?page_limit=" + page_limit + "&page="
				+ page + "&country=" + country + "&apikey=" + _API_KEY;

		return fullUri;

	}
}
