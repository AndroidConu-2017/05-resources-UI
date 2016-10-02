package ca.campbell.layoutprogrammatically;

/*
 * This app is the same app created in week2-rw-views, it has exactly
 * the same functionality.  
 * 
 * See: github week02
 * 
 * The difference is that the layout & associated views are created only programmatically.
 * None of the UI is created through xml.
 */
import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "RWPROGVIEWS";
	private static final String SECRET = (String) "guacamole";
	private LinearLayout.LayoutParams  buttonParams, params;
	private EditText et;
	private CharSequence str;
	TextView tvresulthdr, tvresult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		createLayout();
	}

	public void createLayout() {

		// set up the Linear Layout container)
		LinearLayout ll = new LinearLayout(this);
		// specifying vertical orientation
		ll.setOrientation(LinearLayout.VERTICAL);
		// creating LayoutParams
		// public LinearLayout.LayoutParams (int width, int height, float
		// weight)
		params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		// set LinearLayout as a root element of the screen
		ll.setLayoutParams(params);

		TextView tv = new TextView(this);
		tv.setText(R.string.message);
		// default width wrap, height wrap so no params
		ll.addView(tv);

		// width MATCH_PARENT, set before, here for illustration
		params.width = LayoutParams.MATCH_PARENT;
		params.height = LayoutParams.WRAP_CONTENT;
		et = new EditText(this);
		et.setHint(R.string.hint);
		et.setLayoutParams(params);
		ll.addView(et);

		LinearLayout ll2 = new LinearLayout(this);
		// same params as EditText
		ll2.setLayoutParams(params);
		ll2.setOrientation(LinearLayout.HORIZONTAL);

		buttonParams = new LinearLayout.LayoutParams(0,
				LayoutParams.WRAP_CONTENT, (float) 2);
		Button bt1 = new Button(this);
		bt1.setLayoutParams(buttonParams);
		bt1.setText(R.string.btsubmit);
		ll2.addView(bt1);

		Button bt2 = new Button(this);
		buttonParams.weight = (float) 1;
		bt2.setLayoutParams(buttonParams);
		bt2.setText(R.string.btclear);
		ll2.addView(bt2);

		ll.addView(ll2);

		tvresulthdr = new TextView(this);
		// params same
		tvresulthdr.setVisibility(TextView.INVISIBLE);
		tvresulthdr.setLayoutParams(params);
		ll.addView(tvresulthdr);

		tvresult = new TextView(this);
		// default width wrap, height wrap
		tvresult.setVisibility(TextView.INVISIBLE);
		ll.addView(tvresult);

		// add listeners for on click events
		// button 1
		bt1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				showData(view);
			}
		});
		// button 2
		bt2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				clearData(view);
			}
		});
		// Use the layout objects 
		setContentView(ll);  

	}

	// showData() is the method called by the View.OnClickListener()
	public void showData(View view) {
		// get the references to the view widgets
		// no reference to the header TextView, I don't manipulate it
		// et = (EditText) findViewById(R.id.input);
		// get the data input
		str = et.getText().toString();

		// tvresulthdr = (TextView) findViewById(R.id.resultheader);
		tvresulthdr.setVisibility(TextView.VISIBLE);

		// tvresult = (TextView) findViewById(R.id.result);
		tvresult.setVisibility(TextView.VISIBLE);
		tvresult.setText(str);

		// need minimum api 9 for str.isEmpty() :(
		if (((String) str).isEmpty()) {
			tvresulthdr.setText(R.string.emptymsg);
			Log.w(TAG, "showData(): no data");
		} else {
			if (str.equals(SECRET)) {
				tvresulthdr.setText(R.string.successmsg);
				Log.w(TAG, "showData(): guessed correctly");
			} else {
				tvresulthdr.setText(R.string.tryagainmsg);
				Log.w(TAG, "showData(): bad guess");
			}
		}
	}

	public void clearData(View view) {
		if (tvresulthdr != null) {
			tvresulthdr.setVisibility(TextView.INVISIBLE);
			et.setText("");
			Log.w(TAG, "clearData(): reset fields");
		}
		Log.w(TAG, "clearData(): fields not set yet");
	}

}
