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
	
	// ����tab����
	private RelativeLayout knowLayout, iWantKnowLayout, meLayout;

	// �ײ���ǩ�л���Fragment
	private Fragment knowFragment, iWantKnowFragment, meFragment,
			currentFragment;
	// �ײ���ǩͼƬ
	private ImageView knowImg, iWantKnowImg, meImg;
	// �ײ���ǩ���ı�
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
	 * ��ʼ��UI
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
	 * ��ʼ���ײ���ǩ
	 */
	private void initTab() {
		if (knowFragment == null) {
			knowFragment = new Tab1();
		}

		if (!knowFragment.isAdded()) {
			// �ύ����
			getSupportFragmentManager().beginTransaction()
					.add(R.id.content_layout, knowFragment).commit();

			// ��¼��ǰFragment
			currentFragment = knowFragment;
			// ����ͼƬ�ı��ı仯
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
		case R.id.rl_know: // ֪��
			clickTab1Layout();
			break;
		case R.id.rl_want_know: // ����֪��
			clickTab2Layout();
			break;
		case R.id.rl_me: // �ҵ�
			clickTab3Layout();
			break;
		default:
			break;
		}
	}
	
	/**
	 * �����һ��tab
	 */
	private void clickTab1Layout() {
		if (knowFragment == null) {
			knowFragment = new Tab1();
		}
		addOrShowFragment(getSupportFragmentManager().beginTransaction(), knowFragment);
		
		// ���õײ�tab�仯
		knowImg.setImageResource(R.drawable.btn_know_pre);
		knowTv.setTextColor(getResources().getColor(R.color.bottomtab_press));
		iWantKnowImg.setImageResource(R.drawable.btn_wantknow_nor);
		iWantKnowTv.setTextColor(getResources().getColor(
				R.color.bottomtab_normal));
		meImg.setImageResource(R.drawable.btn_my_nor);
		meTv.setTextColor(getResources().getColor(R.color.bottomtab_normal));
	}
	
	/**
	 * ����ڶ���tab
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
	 * ���������tab
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
	 * ��ӻ�����ʾ��Ƭ
	 * 
	 * @param transaction
	 * @param fragment
	 */
	private void addOrShowFragment(FragmentTransaction transaction,
			Fragment fragment) {
		if (currentFragment == fragment)
			return;

		if (!fragment.isAdded()) { // �����ǰfragmentδ����ӣ�����ӵ�Fragment��������
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
