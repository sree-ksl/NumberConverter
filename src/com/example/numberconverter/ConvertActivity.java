package com.example.numberconverter;

import java.util.Stack;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConvertActivity extends Activity implements TextWatcher,View.OnClickListener{
	
	EditText txtDecimal;
	TextView txtBinary,txtOctal,txtHexa;
	Button btnAbout;
	
 //Called when the activity is first created	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_convert);
		txtDecimal = (EditText)findViewById(R.id.txtDecimal);
		txtBinary = (TextView)findViewById(R.id.txtBinary);
		txtOctal = (TextView)findViewById(R.id.txtOctal);
		txtHexa = (TextView)findViewById(R.id.txtHexa);
		txtDecimal.addTextChangedListener(this);
		btnAbout = (Button)findViewById(R.id.button1);
		btnAbout.setOnClickListener(this);
	}
	

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		calculate(2,txtBinary);
		calculate(8,txtOctal);
		calculate(16,txtHexa);
		
	}
	
	public void calculate(int base,TextView txtView){
		if(txtDecimal.getText().toString().trim().length() == 0)
		{
			txtView.setText("");
			return ;
		}
		try{
			Stack<Object> stack = new Stack<Object>();
			int number = Integer.parseInt(txtDecimal.getText().toString());
			while(number>0)
			{
				int remainder = number%base;
				if(remainder<10)
				{
					stack.push(remainder);
				}
				else{
					switch(remainder)
					{
					case 10:
						stack.push("A");
						break;
					case 11:
						stack.push("B");
						break;
					case 12:
						stack.push("C");
						break;
					case 13:
						stack.push("D");
						break;
					case 14:
						stack.push("E");
						break;
					case 15:
						stack.push("F");
						break;
					}
				}
				number/=base;
			}
			StringBuffer buffer = new StringBuffer();
			while(!stack.isEmpty())
			{
				buffer.append(stack.pop().toString());
				
			}
			txtView.setText(buffer.toString());
		}
		catch(Exception e){
			txtView.setText(e.getMessage());
		}
	}
	
	public void onClick(View view){
		Builder builder = new Builder(this);
		builder.setCancelable(true);
		builder.setTitle("About NumberConverter");
		builder.setMessage("Conversion of numbers made easy");
		builder.show();
	}
	
}
