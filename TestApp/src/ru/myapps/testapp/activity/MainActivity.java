package ru.myapps.testapp.activity;

import ru.myapps.testapp.R;
import ru.myapps.testapp.listener.dialog.DialogOnClickClose;
import ru.myapps.testapp.utils.TagsOperator;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * @author: Dmitry Nazarov
 * @date: 09.09.13
 */
public class MainActivity extends Activity {
	
	private EditText nameEditTextView;
	private DatePicker birthdayDatePickerView;
	private Spinner sexSpinnerView;
	private EditText positionEditTextView;
	private EditText salaryEditTextView;
	private EditText phoneEditTextView;
	private EditText emailEditTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		nameEditTextView = (EditText) findViewById(R.id.nameEditText);
		birthdayDatePickerView = (DatePicker) findViewById(R.id.birthdayDatePicker);
	    sexSpinnerView = (Spinner) findViewById(R.id.spinner);
	    positionEditTextView = (EditText) findViewById(R.id.positionEditText);
	    salaryEditTextView = (EditText) findViewById(R.id.salaryEditText);
	    phoneEditTextView = (EditText) findViewById(R.id.phoneEditText);
	    emailEditTextView = (EditText) findViewById(R.id.emailEditText);
	}

	protected void onResume() {
		super.onResume();
		if (EmployerActivity.isShowLetter == true) {
			EmployerActivity.isShowLetter = false;
			
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setMessage(EmployerActivity.textLetter);

	        try {
	        	alertDialog.setTitle(getResources().getString(R.string.letterEmployer));
	            alertDialog.setPositiveButton(getText(R.string.alertDialogClose), new DialogOnClickClose());
	            alertDialog.show();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}  
	}
	
	public void onClickSendResume(View view) {
		Intent intent = new Intent(this, EmployerActivity.class);
        Bundle bundle = new Bundle();
         
        String dateBirthday = String.valueOf(birthdayDatePickerView.getDayOfMonth()) + "." +
        		String.valueOf(birthdayDatePickerView.getMonth()+1) + "." +
        		String.valueOf(birthdayDatePickerView.getYear());
        
        bundle.putString(TagsOperator.TAG_NAME, nameEditTextView.getText().toString());
        bundle.putString(TagsOperator.TAG_BIRTHDAY, dateBirthday);
        bundle.putString(TagsOperator.TAG_SEX, sexSpinnerView.getSelectedItem().toString());
        bundle.putString(TagsOperator.TAG_POSITION, positionEditTextView.getText().toString());
        bundle.putString(TagsOperator.TAG_SALARY, salaryEditTextView.getText().toString());
        bundle.putString(TagsOperator.TAG_PHONE, phoneEditTextView.getText().toString());
        bundle.putString(TagsOperator.TAG_EMAIL, emailEditTextView.getText().toString());
        intent.putExtras(bundle);
        this.startActivity(intent);
	}
}
