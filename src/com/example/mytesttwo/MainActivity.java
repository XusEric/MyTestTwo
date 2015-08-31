package com.example.mytesttwo;


import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		t=(TextView) findViewById(R.id.kuanTextView1); 
		
		loginButton=(Button) findViewById(R.id.login_Button);  
		userNameEditText=(EditText) findViewById(R.id.userName); 
        loginButton.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View arg0) {  
                String userName=userNameEditText.getText().toString();  
                if("".equals(userName)){  
                    Toast.makeText(MainActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();  
                }  
                
                DoSomething ds1 = new DoSomething(userName);
                Thread t1 = new Thread(ds1);
                t1.start();
            }  
        });  
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

	private EditText userNameEditText;  
    private EditText userPwdEditText;  
    private Button loginButton; 
    private TextView t;
	
	public class DoSomething implements Runnable {
	    private String name;

	    public DoSomething(String name) {
	        this.name = name;
	    }

	    public void run() {
	    	Message msg = new Message();
	    	msg.what = 1;
	        HashMap<String, Object> paramsMap = new HashMap<String, Object>();  
	        paramsMap.put("name", name);  
	        SoapObject a= WebServiceHelper.getSoapObject("Users", "getUserList", null, paramsMap);
	        System.out.println("result:"+a.toString());
	        msg.obj=a;
            handler.sendMessage(msg);
	    }
	}
	
	private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            //findViewById(R.id.Btn_Login).setEnabled(true);
            switch (msg.what)
            {
                case -1:
                    //exception
                    //MessageHelper.AlertDialog(Activity_Main.this, "异常提示", msg.obj.toString());
                    System.out.println("异常提示:"+msg.obj.toString());
                    break;
                case 0:
                    //fail
                    //MessageHelper.AlertDialog(Activity_Main.this, "错误提示", msg.obj.toString());
                    System.out.println("错误:"+msg.obj.toString());
                    break;
                case 1:
                    //login success
                    //MessageHelper.AlertDialog(Activity_Main.this, "操作提示", "登录成功。");
        	        t.setText(msg.obj.toString()); 
                    System.out.println("操作提示:登录成功");
                    break;
                default:
                    break;
            }
        }
    };
}
