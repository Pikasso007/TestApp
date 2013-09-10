package ru.myapps.testapp.activity;

import ru.myapps.testapp.R;
import ru.myapps.testapp.utils.TagsOperator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author: Dmitry Nazarov
 * @date: 09.09.13
 */
public class EmployerActivity extends Activity {

	private TextView nameTextView;
	private TextView birthdayTextView;
	private TextView sexTextView;
	private TextView positionTextView;
	private TextView salaryTextView;
	private TextView phoneTextView;
	private TextView emailTextView;
	public EditText letterEditText;
	
	public String email;
	public static boolean isShowLetter = false;
	public static String textLetter = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.employer_activity);
		
		isShowLetter = false;
		
		nameTextView = (TextView) findViewById(R.id.nameTextView);
		birthdayTextView = (TextView) findViewById(R.id.birthdayTextView);
	    sexTextView = (TextView) findViewById(R.id.sexTextView);
	    positionTextView = (TextView) findViewById(R.id.positionTextView);
	    salaryTextView = (TextView) findViewById(R.id.salaryTextView);
	    phoneTextView = (TextView) findViewById(R.id.phoneTextView);
	    emailTextView = (TextView) findViewById(R.id.emailTextView);  
	    letterEditText = (EditText) findViewById(R.id.letterEditText);
	    
		//set textViews
        Intent intent = getIntent();
        email = intent.getStringExtra(TagsOperator.TAG_EMAIL);
        
        nameTextView.setText(getResources().getString(R.string.name)+": "+intent.getStringExtra(TagsOperator.TAG_NAME));
        birthdayTextView.setText(getResources().getString(R.string.birthday)+": "+intent.getStringExtra(TagsOperator.TAG_BIRTHDAY));
        sexTextView.setText(getResources().getString(R.string.sex)+": "+intent.getStringExtra(TagsOperator.TAG_SEX));
        positionTextView.setText(getResources().getString(R.string.position)+": "+intent.getStringExtra(TagsOperator.TAG_POSITION));
        salaryTextView.setText(getResources().getString(R.string.salary)+": "+intent.getStringExtra(TagsOperator.TAG_SALARY));
        
        String phone = getResources().getString(R.string.phone)+": "+intent.getStringExtra(TagsOperator.TAG_PHONE);
        String emailLong = getResources().getString(R.string.email)+": "+intent.getStringExtra(TagsOperator.TAG_EMAIL);
        
        Spannable textPhone = new SpannableString(phone);
        textPhone.setSpan(new UnderlineSpan(), 16, phone.length(),  Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textPhone.setSpan(new ForegroundColorSpan(Color.BLUE), 16, phone.length(),  Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        phoneTextView.setText(textPhone);

        Spannable textEmail = new SpannableString(emailLong);
        textEmail.setSpan(new UnderlineSpan(), 19, emailLong.length(),  Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textEmail.setSpan(new ForegroundColorSpan(Color.BLUE), 19, emailLong.length(),  Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        emailTextView.setText(textEmail);   
        
        phoneTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            	Intent callIntent = new Intent(Intent.ACTION_DIAL);
            	callIntent.setData(Uri.parse("tel:+"+phoneTextView.getText().toString().trim()));
            	startActivity(callIntent );
            }
        });
        
        emailTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
            	final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

            	emailIntent.setType("plain/text");
            	emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});

            	startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            }
        });
	}

	public void onClickSendAnswer(View view) {
		textLetter = letterEditText.getText().toString();
		isShowLetter = true;
		this.onBackPressed();
	}
}
