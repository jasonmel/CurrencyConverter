package com.homework.CurrencyConverter.test;

import com.homework.CurrencyConverter.CurrencyConverterActivity;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CurrencyConverterActivityTest extends
		ActivityInstrumentationTestCase2<CurrencyConverterActivity> {
	
	CurrencyConverterActivity mActivity;
	Instrumentation mInstrumentation;
	Instrumentation.ActivityMonitor mMonitor;
	
	TextView[] tv_dollars = new TextView[3];
	EditText[] et_dollars = new EditText[3];
	Button btn_convert, btn_clear;

	public CurrencyConverterActivityTest(String name) {
		super(CurrencyConverterActivity.class);
		setName(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		mActivity = getActivity();
		mInstrumentation = getInstrumentation();
		btn_convert = (Button) mActivity.findViewById(com.homework.CurrencyConverter.R.id.btn_convert);
		btn_clear   = (Button) mActivity.findViewById(com.homework.CurrencyConverter.R.id.btn_clear);
		tv_dollars[0] = (TextView) mActivity.findViewById(com.homework.CurrencyConverter.R.id.tv1);
		tv_dollars[1] = (TextView) mActivity.findViewById(com.homework.CurrencyConverter.R.id.tv2);
		tv_dollars[2] = (TextView) mActivity.findViewById(com.homework.CurrencyConverter.R.id.tv3);
		et_dollars[0] = (EditText) mActivity.findViewById(com.homework.CurrencyConverter.R.id.et1);
		et_dollars[1] = (EditText) mActivity.findViewById(com.homework.CurrencyConverter.R.id.et2);
		et_dollars[2] = (EditText) mActivity.findViewById(com.homework.CurrencyConverter.R.id.et3);
		btn_convert = (Button) mActivity.findViewById(com.homework.CurrencyConverter.R.id.btn_convert);
		btn_clear   = (Button) mActivity.findViewById(com.homework.CurrencyConverter.R.id.btn_clear);
	}
	
	public void testUI() {
		for (TextView tv_dollar : tv_dollars) {
			assertNotNull(tv_dollar);
		}
		for (EditText et_dollar : et_dollars) {
			assertNotNull(et_dollar);
		}
		assertNotNull(btn_convert);
		assertNotNull(btn_clear);
	}
}
