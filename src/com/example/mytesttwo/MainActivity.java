package com.example.mytesttwo;


import java.util.HashMap;

import org.ksoap2.serialization.SoapObject;

//import com.xiaowu.bottomtab.demo.IWantKnowFragment;
//import com.xiaowu.bottomtab.demo.MeFragment;
//import com.xiaowu.bottomtab.demo.R;
//import com.xiaowu.bottomtab.demo.ZhidaoFragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener{
	
	// 三个tab布局
	private RelativeLayout knowLayout, iWantKnowLayout, meLayout;

	// 底部标签切换的Fragment
	private Fragment knowFragment, iWantKnowFragment, meFragment,
			currentFragment;
	// 底部标签图片
	private ImageView knowImg, iWantKnowImg, meImg;
	// 底部标签的文本
	private TextView knowTv, iWantKnowTv, meTv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (savedInstanceState == null) {
            // We were just launched 
			initUI();
			initTab();
            
        } else {
            // We are being restored
            //Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
//        	if (map != null) {
//                mSnakeView.restoreState(map);
//            } else {
//                mSnakeView.setMode(SnakeView.PAUSE);
//            }
        }
		
		
	}

	
//	@Override
//	protected void onSaveInstanceState(Bundle savedInstanceState) {
//		System.out.println("************onSaveInstanceState");
//		super.onSaveInstanceState(savedInstanceState);
//		//savedInstanceState.putBundle(ICICLE_KEY, mSnakeView.saveState());
//	}
	
	/**
	 * 初始化UI
	 */
	private void initUI() {
		knowLayout = (RelativeLayout) findViewById(R.id.rl_know);
		iWantKnowLayout = (RelativeLayout) findViewById(R.id.rl_want_know);
		meLayout = (RelativeLayout) findViewById(R.id.rl_me);
		knowLayout.setOnClickListener(this);
		iWantKnowLayout.setOnClickListener(this);
		meLayout.setOnClickListener(this);

		knowImg = (ImageView) findViewById(R.id.iv_know);
		iWantKnowImg = (ImageView) findViewById(R.id.iv_i_want_know);
		meImg = (ImageView) findViewById(R.id.iv_me);
		knowTv = (TextView) findViewById(R.id.tv_know);
		iWantKnowTv = (TextView) findViewById(R.id.tv_i_want_know);
		meTv = (TextView) findViewById(R.id.tv_me);

	}

	/**
	 * 初始化底部标签
	 */
	private void initTab() {
		if (knowFragment == null) {
			knowFragment = new Tab1();
		}

		if (!knowFragment.isAdded()) {
			// 提交事务
			getSupportFragmentManager().beginTransaction()
					.add(R.id.content_layout, knowFragment).commit();

			// 记录当前Fragment
			currentFragment = knowFragment;
			// 设置图片文本的变化
			knowImg.setImageResource(R.drawable.btn_know_pre);
			knowTv.setTextColor(getResources()
					.getColor(R.color.bottomtab_press));
			iWantKnowImg.setImageResource(R.drawable.btn_wantknow_nor);
			iWantKnowTv.setTextColor(getResources().getColor(
					R.color.bottomtab_normal));
			meImg.setImageResource(R.drawable.btn_my_nor);
			meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));

		}

	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.rl_know: // 知道
			clickTab1Layout();
			break;
		case R.id.rl_want_know: // 我想知道
			clickTab2Layout();
			break;
		case R.id.rl_me: // 我的
			clickTab3Layout();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 点击第一个tab
	 */
	private void clickTab1Layout() {
		if (knowFragment == null) {
			knowFragment = new Tab1();
		}
		addOrShowFragment(getSupportFragmentManager().beginTransaction(), knowFragment);
		
		// 设置底部tab变化
		knowImg.setImageResource(R.drawable.btn_know_pre);
		knowTv.setTextColor(getResources().getColor(R.color.bottomtab_press));
		iWantKnowImg.setImageResource(R.drawable.btn_wantknow_nor);
		iWantKnowTv.setTextColor(getResources().getColor(
				R.color.bottomtab_normal));
		meImg.setImageResource(R.drawable.btn_my_nor);
		meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
	}
	
	/**
	 * 点击第二个tab
	 */
	private void clickTab2Layout() {
		if (iWantKnowFragment == null) {
			iWantKnowFragment = new Tab2();
		}
		addOrShowFragment(getSupportFragmentManager().beginTransaction(), iWantKnowFragment);
		
		knowImg.setImageResource(R.drawable.btn_know_nor);
		knowTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
		iWantKnowImg.setImageResource(R.drawable.btn_wantknow_pre);
		iWantKnowTv.setTextColor(getResources().getColor(
				R.color.bottomtab_press));
		meImg.setImageResource(R.drawable.btn_my_nor);
		meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));

	}

	/**
	 * 点击第三个tab
	 */
	private void clickTab3Layout() {
		if (meFragment == null) {
			meFragment = new Tab3();
		}
		
		addOrShowFragment(getSupportFragmentManager().beginTransaction(), meFragment);
		knowImg.setImageResource(R.drawable.btn_know_nor);
		knowTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
		iWantKnowImg.setImageResource(R.drawable.btn_wantknow_nor);
		iWantKnowTv.setTextColor(getResources().getColor(
				R.color.bottomtab_normal));
		meImg.setImageResource(R.drawable.btn_my_pre);
		meTv.setTextColor(getResources().getColor(R.color.bottomtab_press));
		
	}

	/**
	 * 添加或者显示碎片
	 * 
	 * @param transaction
	 * @param fragment
	 */
	private void addOrShowFragment(FragmentTransaction transaction,
			Fragment fragment) {
		if (currentFragment == fragment)
			return;

		if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
			transaction.hide(currentFragment)
					.add(R.id.content_layout, fragment).commit();
		} else {
			transaction.hide(currentFragment).show(fragment).commit();
		}

		currentFragment = fragment;
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
	
}
