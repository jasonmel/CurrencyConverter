package com.homework.CurrencyConverter;

import java.lang.Float;
import java.util.Hashtable;
import android.os.AsyncTask;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class CurrencyConverterActivity extends Activity {
	// database
	final String[][] currency_keys = new String[][] {
			//{"AED", "United Arab Emirates dirhams", "3.67300014"},
			//{"ANG", "Netherlands Antilles guilders", "1.74999869"},
			//{"ARS", "Argentine pesos", "4.40499526"},
			{"AUD", "Australian dollars", "0.964413155"},
			//{"BGN", "Bulgarian levs", "1.47990073"},
			//{"BHD", "Bahrain dinars", "0.377009934"},
			//{"BND", "Brunei dollars", "1.24889941"},
			//{"BOB", "Bolivian bolivianos", "7.01001731"},
			//{"BRL", "Brazil reais", "1.87000125"},
			//{"BWP", "Botswana pula", "7.3800738"},
			{"CAD", "Canadian dollars", "0.99290076"},
			//{"CHF", "Swiss francs", "0.909099174"},
			//{"CLP", "Chilean pesos", "485.672657"},
			{"CNY", "Chinese yuan", "6.30839205"},
			//{"COP", "Colombian pesos", "1769.9115"},
			//{"CRC", "Costa Rican colones", "502.512563"},
			//{"CZK", "Czech koruny", "18.8640094"},
			//{"DKK", "Danish kroner", "5.62958459"},
			//{"DOP", "Dominican pesos", "39.000039"},
			//{"DZD", "Algerian dinars", "73.594348"},
			//{"EEK", "Estonian kroons", "11.7303429"},
			//{"EGP", "Egyptian pounds", "6.04719229"},
			{"EUR", "Euros", "0.756715853"},
			//{"FJD", "Fiji dollars", "1.79953212"},
			{"GBP", "British pounds", "0.620809536"},
			//{"HKD", "Hong Kong dollars", "7.76078167"},
			//{"HNL", "Honduran lempiras", "19.0599626"},
			//{"HRK", "Croatian kune", "5.68520993"},
			//{"HUF", "Hungarian forints", "224.921278"},
			//{"IDR", "Indonesian rupiahs", "9174.31193"},
			//{"ILS", "Israeli shekels", "3.75069857"},
			{"INR", "Indian rupees", "52.0210165"},
			//{"JMD", "Jamaican dollars", "86.7829558"},
			//{"JOD", "Jordanian dinars", "0.708501307"},
			{"JPY", "Japanese yen", "81.5793767"},
			//{"KES", "Kenyan shillings", "83.2986256"},
			{"KRW", "South Korean won", "1137.65643"},
			//{"KWD", "Kuwaiti dinars", "0.27825003"},
			//{"KYD", "Cayman Islands dollars", "0.820001476"},
			//{"KZT", "Kazakh tenge", "147.95088"},
			//{"LBP", "Lebanese pounds", "1503.7594"},
			//{"LKR", "Sri Lankan rupees", "130.0052"},
			//{"LTL", "Lithuanian litai", "2.61280116"},
			//{"LVL", "Latvian lati", "0.528801155"},
			//{"MAD", "Moroccan dirhams", "8.44530399"},
			//{"MDL", "Moldovan lei", "11.7600019"},
			//{"MKD", "Macedonian denari", "46.6897003"},
			//{"MUR", "Mauritian rupees", "29.2997363"},
			//{"MXN", "Mexican pesos", "13.1147541"},
			//{"MYR", "Malaysian ringgits", "3.06159626"},
			//{"NAD", "Namibian dollars", "7.8118897"},
			//{"NGN", "Nigerian naira", "157.282164"},
			//{"NIO", "Nicaraguan cordobas", "23.3198078"},
			//{"NOK", "Norwegian kroner", "5.72639295"},
			//{"NPR", "Nepalese rupees", "82.822594"},
			{"NZD", "New Zealand dollars", "1.22339124"},
			//{"OMR", "Omani rials", "0.385000385"},
			//{"PEN", "Peruvian nuevos soles", "2.65100102"},
			//{"PGK", "Papua New Guinean kina", "2.11461197"},
			//{"PHP", "Philippine pesos", "42.6003238"},
			//{"PKR", "Pakistan rupees", "90.7523369"},
			//{"PLN", "Polish zloty", "3.17039611"},
			//{"PYG", "Paraguayan guaranies", "4310.34483"},
			//{"QAR", "Qatar riyals", "3.6411965"},
			//{"RON", "Romanian leu", "3.31249772"},
			//{"RSD", "Serbian dinars", "84.5594453"},
			{"RUB", "Russian rubles", "29.4403392"},
			//{"SAR", "Saudi riyals", "3.75009375"},
			//{"SCR", "Seychelles rupees", "13.6000762"},
			//{"SEK", "Swedish kronor", "6.69088768"},
			//{"SGD", "Singapore dollars", "1.24899924"},
			//{"SKK", "Slovak koruna", "22.796699"},
			//{"SLL", "Sierra Leonean leones", "4329.00433"},
			//{"SVC", "Salvadoran colones", "8.74997813"},
			//{"THB", "Thai baht", "30.8804002"},
			//{"TND", "Tunisian dinar", "1.51799891"},
			//{"TRY", "Turkish liras", "1.79209864"},
			//{"TTD", "Trinidad dollars", "6.4"},
			{"TWD", "Taiwan dollars", "29.4498763"},
			//{"TZS", "Tanzanian shillings", "1584.78605"},
			//{"UAH", "Ukrainian grivnas", "8.02651962"},
			//{"UGX", "Ugandan shillings", "2512.56281"},
			{"USD", "U.S. dollar", "1"},
			//{"UYU", "Uruguayan pesos", "19.9501247"},
			//{"UZS", "Uzbekistani sum", "1851.85185"},
			//{"VND", "Vietnamese dong", "20833.3333"},
			//{"YER", "Yemeni rials", "215.007525"},
			{"ZAR", "South African rands", "7.8118897"},
			//{"ZMK", "Zambia kwacha", "5291.00529"},
	};
	Hashtable<String, Float> currencies = new Hashtable<String, Float>();
	CurrencyDataSource datasource;
	
	// EditView
	TextView[] tv_dollars = new TextView[3];
	EditText[] et_dollars = new EditText[3];
	int[] tv_views = {R.id.tv1, R.id.tv2, R.id.tv3};
	int[] et_views = {R.id.et1, R.id.et2, R.id.et3};
	String[] view_keys = {"TWD", "USD", "JPY"};
	int focus = 0;
	
	// Button
	Button btn_convert, btn_clear;
	
	// ProgressDialog
	ProgressDialog progress_dialog;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// init database
		datasource = new CurrencyDataSource(this);
		datasource.open();
		
		initElements();
		initData();
	}
	
	private void initElements() {
		// get personal setting from database
		String[] personal_view_keys = datasource.getPersonal(view_keys.length);
		// check
		boolean isOK = true;
		for (String key : personal_view_keys) {
			if (key == null) {
				isOK = false;
			}
		}
		if (isOK) {
			view_keys = personal_view_keys;
		}
				
		// setup TextView
		for (int i = 0; i < tv_views.length; i++ ) {
			final TextView tv_dollar = (TextView) findViewById(tv_views[i]);
			
			// prepare items
			String[] items = new String[currency_keys.length];
			for (int j = 0; j < currency_keys.length; j++) {
				items[j] = currency_keys[j][1] + " (" + currency_keys[j][0] + ")";
			}
			
			// prepare dialog
			final int index = i;
			AlertDialog.Builder builder = new AlertDialog.Builder(CurrencyConverterActivity.this);
			builder.setTitle("Choose one");
			builder.setItems(items, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					tv_dollar.setText(currency_keys[item][0]);
					view_keys[index] = currency_keys[item][0];
					initData(currency_keys[item][0]);
					calculate();
				}
			});
			final AlertDialog alert = builder.create();
			
			tv_dollar.setText(view_keys[i]);
			tv_dollar.setOnLongClickListener(new OnLongClickListener() {
				public boolean onLongClick(View v) {
					alert.show();
					return true;
				}
			});
			tv_dollars[i] = tv_dollar;
		}
		
		// setup EditText
		for (int i = 0; i < et_views.length; i++ ) {
			final int index = i;
			final EditText et_dollar = (EditText) findViewById(et_views[i]);
			et_dollar.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					et_dollar.setText("");
					focus = index;
				}
			});
			et_dollars[i] = et_dollar;
		}
		
		// setup Button
		btn_convert = (Button) findViewById(R.id.btn_convert);
		btn_convert.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				calculate();
			}
		});
		btn_clear = (Button) findViewById(R.id.btn_clear);
		btn_clear.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				for (int i = 0; i < et_dollars.length; i++) {
					et_dollars[i].setText(R.string.default_value);
				}
			}
		});
	}
	
	private boolean initData() {
		// get existing data from database
		currencies = datasource.getCurrencies();
		
		/*
		for (String key : view_keys) {
			initData(key);
		}
		*/
		new DownloadTask().execute();
		
		return true;
	}
	
	private boolean initData(String key) {
		// if the key does not exist, update it through internet
		if (!currencies.containsKey(key)) {
			//updateData(key);
			new DownloadTask().execute(key);
		}
		
		
		return true;
	}
	
	private void calculate() {
		Float value_src = Float.parseFloat(et_dollars[focus].getText().toString());
		Float value_usd = value_src / currencies.get(view_keys[focus]);
		for (int i = 0; i < view_keys.length; i++) {
			if (i == focus) {
				continue;
			}
			if (currencies.containsKey(view_keys[i])) {
				Float value_dst = value_usd * currencies.get(view_keys[i]);
				et_dollars[i].setText(String.valueOf(value_dst));
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 0, 0, R.string.update)
			.setIcon(R.drawable.refresh_blue)
			.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_WITH_TEXT	| MenuItem.SHOW_AS_ACTION_IF_ROOM);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case 0:
			//updateData();
			new DownloadTask().execute();
			break;
		}
		return true;
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		datasource.setPersonal(view_keys);
		datasource.close();
	}
	
	public class DownloadTask extends AsyncTask<String, Integer, Long> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progress_dialog = new ProgressDialog(CurrencyConverterActivity.this);
			progress_dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progress_dialog.show();
		}
		
		@Override
		protected Long doInBackground(String... arg0) {
			int count = 0;
			if (arg0.length == 0) {
				int length = view_keys.length;
				for (String key : view_keys) {
					getValue(key);
					count++;
					publishProgress(count * 100 / length);
				}
			} else {
				int length = arg0.length;
				for (String key : arg0) {
					getValue(key);
					count++;
					publishProgress(count * 100 / length);
				}
			}
			return (long)count;
		}
		
		private void getValue(String key) {
			try {
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet("http://www.google.com/ig/calculator?hl=en&q=1USD%3D%3F" + key);
				HttpResponse response = client.execute(request);
				String retSrc = EntityUtils.toString(response.getEntity());
				Float value = Float.valueOf(retSrc.split(",")[1].split("\"")[1].split(" ")[0]);
				datasource.setCurrency(key, value);
				currencies.put(key, value);
			} catch (Exception e) {
				// if the key still does not exist, update it using default data
				if (!currencies.containsKey(key)) {
					for (String[] currency_key : currency_keys) {
						if (currency_key[0] == key) {
							datasource.setCurrency(currency_key[0], Float.parseFloat(currency_key[2]));
							currencies.put(currency_key[0], Float.parseFloat(currency_key[2]));
							break;
						}
					}
				}
				e.printStackTrace();
				Log.i("HTTP exception", e.toString());
			}
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			progress_dialog.setProgress(values[0]);
		}
		
		@Override
		protected void onPostExecute(Long result) {
			super.onPostExecute(result);
			calculate();
			progress_dialog.dismiss();
		}
	}
}
