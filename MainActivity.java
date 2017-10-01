package cn.jiujia.com.buttons;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.jiujia.com.buttons.bean.SignListBean;

public class MainActivity extends AppCompatActivity {
    SignButtonsView signButtonsView;
    List<SignListBean.DataBean> beanList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signButtonsView = (SignButtonsView) findViewById(R.id.signBtn);
        signButtonsView.setOnSignListener(new SignButtonsView.clickSignListener() {
            @Override
            public void setOnSignListener(int index, Button btn, TextView date, TextView jifen, View line) {
                Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
                btn.setBackground(ContextCompat.getDrawable(MainActivity.this, R.mipmap.jiu));
                line.setBackground(ContextCompat.getDrawable(MainActivity.this, R.mipmap.yellow_bg));
                date.setText("8月8号");
                jifen.setText("积分+100");
            }
        });
        setBeans();
        signButtonsView.initSignLayoutData(beanList);
        findViewById(R.id.btn_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signButtonsView.initSignLayoutData(beanList);
            }
        });


    }

    private void setBeans(){
        for (int i = 0; i < 14; i++) {
            SignListBean.DataBean bean = new SignListBean.DataBean();
            bean.setLs_date("8月"+(i+1)+"日");
            bean.setLs_type(1);


            bean.setReward_type(0);
//            if(i == 13){
//                bean.setReward_id(1);
//                bean.setReward_type(1);
//                bean.setLs_type(2);
//            }


            beanList.add(bean);
        }
    }
}
