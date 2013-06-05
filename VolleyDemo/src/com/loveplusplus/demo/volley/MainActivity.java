package com.loveplusplus.demo.volley;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {

	protected static final String TAG = "MainActivity";
	private RequestQueue reqQueue;
	private TextView hello;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		hello = (TextView) findViewById(R.id.hello);
		reqQueue = Volley.newRequestQueue(this);

		JsonObjectRequest jr = new JsonObjectRequest(
				Request.Method.GET,
				"http://api.eoe.cn/client/news?k=lists&t=top",
				null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						Log.d(TAG, response.toString());
						hello.setText(response.toString());
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.d(TAG, error.toString());
					}
				});

		reqQueue.add(jr);

		ImageLoader imageLoader = new ImageLoader(reqQueue, new BitmapLruCache());
		
		NetworkImageView imageView = (NetworkImageView) findViewById(R.id.image);
		imageView.setImageUrl("http://ww3.sinaimg.cn/bmiddle/60718250jw1e5ddlk3xntj20c807d3yx.jpg", imageLoader);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
