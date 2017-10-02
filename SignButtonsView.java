package cn.jiujia.com.buttons;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.jiujia.com.buttons.bean.SignListBean;

/**
 * Created by yuanjunhua on 2017/8/6.
 */

public class SignButtonsView extends RelativeLayout {
    private Context context;
    private int sWidth, sHeight;
    private int sign_num;//签到按钮的个数
    private float sdensity;
    private float btn_sign_distance;//按钮之间的距离
    private int column_num;//line_drawable
    private float margin_parent_lr;//距离父控件左右边缘的距离的margin
    private float line_height;//横线的高度 height
    private float sign_btn_radius;//按钮的半径 radius
    private Drawable btn_sign_drawable;//横线的图片drawable
    private Drawable line_drawable, vertical_drawable;//按钮的图片drawable
    private float line_width;//每个线在屏幕上对应的长度
    private List<SignListBean.DataBean> listBean;
    private int[] lines = {R.id.line_1,
            R.id.line_2,R.id.line_3,
            R.id.line_4,R.id.line_5,R.id.line_6,
            R.id.line_7,R.id.line_8,R.id.line_9,
            R.id.line_10,R.id.line_11,R.id.line_12,
            R.id.line_13, R.id.line_14, R.id.line_15};
    private int[] btns = {R.id.btn_1,
            R.id.btn_2,R.id.btn_3,R.id.btn_4,
            R.id.btn_5,R.id.btn_6,R.id.btn_7,
            R.id.btn_8,R.id.btn_9,R.id.btn_10,
            R.id.btn_11,R.id.btn_12,R.id.btn_13,
            R.id.btn_14};
    private int[] dates = {R.id.date_1,
            R.id.date_2,R.id.date_3,R.id.date_4,
            R.id.date_5,R.id.date_6,R.id.date_7,
            R.id.date_8,R.id.date_9,R.id.date_10,
            R.id.date_11,R.id.date_12,R.id.date_13,
            R.id.date_14};
    private int[] jifens = {R.id.jifen_1,
            R.id.jifen_2,R.id.jifen_3,R.id.jifen_4,
            R.id.jifen_5,R.id.jifen_6,R.id.jifen_7,
            R.id.jifen_8,R.id.jifen_9,R.id.jifen_10,
            R.id.jifen_11,R.id.jifen_12,R.id.jifen_13,
            R.id.jifen_14};

    /**
     *  new_procedure_table 为返回的临时表明
     *                ls_date 为显示的日期，格式：2017-08-04
     *                ls_type 为签到类型，0已签到、1未签可补签、2未签到
     *                reward_id 为活动奖励的ID，0为没有活动奖励
     *                reward_type 为活动奖励类型，0积分 1红包 2经验 3待定
     *                reward_val 为轰动奖励的分值
     * @param context
     */
    public SignButtonsView(Context context) {
        this(context, null);
    }

    public SignButtonsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SignButtonsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        initAttrs(attrs);
        initLineRow1();
        initLineRow2();
        initLineRow3();
        drawBtnsRow1();
        drawBtnsRow2();
        drawBtnsRow3();
    }

    private void initAttrs(AttributeSet attrs) {
        initScreen();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SignView);

        line_height = ta.getDimensionPixelOffset(R.styleable.SignView_line_height, 15);
        btn_sign_distance = ta.getDimensionPixelOffset(R.styleable.SignView_btn_sign_distance, 30);
        margin_parent_lr = ta.getDimensionPixelOffset(R.styleable.SignView_margin_parent_lr, 20);
        sign_btn_radius = ta.getDimensionPixelOffset(R.styleable.SignView_sign_btn_radius, 20);
        btn_sign_drawable = ta.getDrawable(R.styleable.SignView_btn_sign_drawable);
        line_drawable = ta.getDrawable(R.styleable.SignView_line_drawable);
        vertical_drawable = ta.getDrawable(R.styleable.SignView_vertical_drawable);
        column_num = ta.getInt(R.styleable.SignView_column_num, 5);
        sign_num = ta.getInt(R.styleable.SignView_sign_num, 14);
        line_width = (sWidth - margin_parent_lr*2)  / 4;

       ta.recycle();
    }

    /**
     * 功能：绘制第一行 line
     */
    private void initLineRow1() {
        View line = new View(context);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, TRUE);
        params.topMargin = (int) btn_sign_distance;
        params.width = (int) margin_parent_lr;
        params.height = (int) line_height;
        line.setId(lines[0]);
        line.setLayoutParams(params);
        line.setBackground(line_drawable);
        addView(line);

        for (int i = 0; i < 4; i++) {
            LayoutParams params1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params1.addRule(RelativeLayout.ALIGN_TOP, line.getId());
            params1.width = (int) line_width;
            params1.height = (int) line_height;
            params1.leftMargin = (int) (line_width * i + margin_parent_lr);
            View line1 = new View(context);
            line1.setId(lines[i+1]);
            line1.setLayoutParams(params1);
            line1.setBackground(line_drawable);
            addView(line1);
        }

        View lineV = new View(context);
        LayoutParams vParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        vParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        vParams.width = (int) line_height;
        vParams.height = (int) btn_sign_distance;
        vParams.topMargin = (int) (btn_sign_distance + line_height);
        vParams.rightMargin = (int) margin_parent_lr;
        lineV.setLayoutParams(vParams);
        lineV.setId(lines[5]);
        lineV.setBackground(vertical_drawable);
        addView(lineV);

    }

    /**
     * 功能：绘制第二行 line
     */
    private void initLineRow2() {
        for (int i = 0; i < 4; i++) {
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
            params.width = (int) line_width;
            params.height = (int) line_height;
            params.topMargin = (int) (2*btn_sign_distance + line_height);
            params.leftMargin = (int) (line_width * i + margin_parent_lr);
            View line1 = new View(context);
            line1.setLayoutParams(params);
            line1.setId(lines[9-i]);
            line1.setBackground(line_drawable);
            addView(line1);
        }

        View lineV = new View(context);
        LayoutParams vParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        vParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        vParams.topMargin = (int) (2*btn_sign_distance + 2*line_height);
        vParams.width = (int) line_height;
        vParams.height = (int) btn_sign_distance;
        vParams.leftMargin = (int) margin_parent_lr;
        lineV.setLayoutParams(vParams);
        lineV.setId(lines[10]);
        lineV.setBackground(vertical_drawable);
        addView(lineV);

    }

    /**
     * 功能：绘制第三行 line
     */
    private void initLineRow3() {
        for (int i = 0; i < 3; i++) {
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
            params.width = (int) line_width;
            params.height = (int) line_height;
            params.topMargin = (int) (3*btn_sign_distance + 2*line_height);
            params.leftMargin = (int) (line_width * i + margin_parent_lr);

            View line1 = new View(context);
            line1.setLayoutParams(params);
            line1.setId(lines[i+11]);
            line1.setBackground(line_drawable);
            addView(line1);
        }

    }


    /**
     * 功能：绘制第一行 button
     */
    private void drawBtnsRow1(){
        for (int i = 0; i < 5; i++) {
            final Button button = new Button(context);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
            params.leftMargin = (int) (margin_parent_lr - sign_btn_radius + dip2px(context, 3) + i * (line_width-dip2px(context, 3)));
            params.topMargin = (int) (line_height/2 + btn_sign_distance-sign_btn_radius);
            params.width = (int) sign_btn_radius*2;
            params.height = (int) sign_btn_radius*2;
            button.setLayoutParams(params);
            button.setId(btns[i]);
            button.setBackground(btn_sign_drawable);

            final TextView txtDate = new TextView(context);
            LayoutParams paramsDate = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            paramsDate.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
            paramsDate.leftMargin = (int) (margin_parent_lr - sign_btn_radius + 3 + i*(line_width-3));
            paramsDate.addRule(RelativeLayout.ALIGN_LEFT, btns[i]);
            paramsDate.addRule(RelativeLayout.BELOW, btns[i]);
            txtDate.setTextColor(ContextCompat.getColor(context, R.color.white));
            txtDate.setId(dates[i]);
            txtDate.setText("8月6日");
            txtDate.setTextSize(sp2px(context, 4));
            txtDate.setLayoutParams(paramsDate);

            final TextView txtJifen = new TextView(context);
            LayoutParams paramsJifen = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            paramsJifen.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
            paramsJifen.leftMargin = (int) (margin_parent_lr - sign_btn_radius + dip2px(context, 3) + i*(line_width-dip2px(context, 3)));
            paramsJifen.addRule(RelativeLayout.BELOW, dates[i]);
            paramsJifen.addRule(RelativeLayout.ALIGN_LEFT, btns[i]);
            txtJifen.setTextColor(ContextCompat.getColor(context, R.color.yello));
            txtJifen.setId(jifens[i]);
            txtJifen.setText("积分+10");
            txtJifen.setTextSize(sp2px(context, 4));
            txtJifen.setLayoutParams(paramsJifen);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < 5; j++) {
                        if(button.getId() == btns[j]){
                            if(null == listBean)return;
                            if(listBean.get(j).getLs_type() == 1){
                                Log.e("kankan", "click="+(j));
                                Toast.makeText(context, "click="+(j+1), Toast.LENGTH_SHORT).show();
                                button.setBackground(ContextCompat.getDrawable(context, R.mipmap.btn_yiqian_red));
                                findViewById(lines[j]).setBackground(ContextCompat.getDrawable(context, R.mipmap.yellow_bg));
                                txtDate.setText(listBean.get(j).getLs_date());
                                txtJifen.setText("积分+"+listBean.get(j).getReward_val());
                                listener.setOnSignListener(j, button, txtDate, txtJifen, findViewById(lines[j]));
                            }
                        }

                    }
                }
            });

            addView(button);
            addView(txtDate);
            addView(txtJifen);
        }
    }

    /**
     * 功能：绘制第二行 button
     */
    private void drawBtnsRow2(){
        for (int i = 0; i < 5; i++) {
            final Button button = new Button(context);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
            params.leftMargin = (int) (margin_parent_lr - sign_btn_radius  + dip2px(context, 3) + i*(line_width-dip2px(context, 3)));
            params.topMargin = (int) (3*line_height/2 + btn_sign_distance*2- sign_btn_radius);
            params.width = (int) sign_btn_radius*2;
            params.height = (int) sign_btn_radius*2;
            button.setLayoutParams(params);
            button.setId(btns[9 - i]);
            button.setBackground(btn_sign_drawable);

            final TextView txtDate = new TextView(context);
            LayoutParams paramsDate = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            paramsDate.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
            paramsDate.leftMargin = (int) (margin_parent_lr - sign_btn_radius + dip2px(context, 3) + i*(line_width-dip2px(context, 3)));
            paramsDate.addRule(RelativeLayout.ALIGN_LEFT, btns[9 - i]);
            paramsDate.addRule(RelativeLayout.BELOW, btns[9 - i]);
            txtDate.setId(dates[9 - i]);
            txtDate.setTextColor(ContextCompat.getColor(context, R.color.white));
            txtDate.setText("积分+10");
            txtDate.setTextSize(sp2px(context, 4));
            txtDate.setLayoutParams(paramsDate);

            final TextView txtJifen = new TextView(context);
            LayoutParams paramsJifen = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            paramsJifen.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
            paramsJifen.leftMargin = (int) (margin_parent_lr - sign_btn_radius + dip2px(context, 3) + i*(line_width-dip2px(context, 3)));
            paramsJifen.addRule(RelativeLayout.BELOW, dates[9 - i]);
            paramsJifen.addRule(RelativeLayout.ALIGN_LEFT, btns[9 - i]);
            txtJifen.setTextColor(ContextCompat.getColor(context, R.color.yello));
            txtJifen.setId(jifens[9 - i]);
            txtJifen.setText("8月6日");
            txtJifen.setTextSize(sp2px(context, 4));
            txtJifen.setLayoutParams(paramsJifen);

            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < 5; j++) {
                        if(button.getId() == btns[9 - j]){
                            if(null == listBean)return;
                            if(listBean.get(9 - j).getLs_type() == 1){
                                Toast.makeText(context, "click="+(9 - j+1), Toast.LENGTH_SHORT).show();
                                button.setBackground(ContextCompat.getDrawable(context, R.mipmap.btn_yiqian_red));
                                findViewById(lines[9 - j]).setBackground(ContextCompat.getDrawable(context, R.mipmap.yellow_bg));
                                txtDate.setText(listBean.get(9 - j).getLs_date());
                                txtJifen.setText("积分+"+listBean.get(9 - j).getReward_val());
                                listener.setOnSignListener(j+5, button, txtDate, txtJifen, findViewById(lines[9 - j]));

                            }
                        }

                    }

                }
            });

            addView(button);
            addView(txtDate);
            addView(txtJifen);
        }
    }

    /**
     * 功能：绘制第三行 button
     */
    private void drawBtnsRow3(){
        for (int i = 0; i < 4; i++) {
            final Button button = new Button(context);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
            params.leftMargin = (int) (margin_parent_lr - sign_btn_radius + dip2px(context, 3) + i*(line_width-dip2px(context, 3)));
            params.topMargin = (int) (5*line_height/2 + btn_sign_distance*3-sign_btn_radius);
            params.width = (int) sign_btn_radius*2;
            params.height = (int) sign_btn_radius*2;
            button.setLayoutParams(params);
            button.setId(btns[i+10]);

            button.setBackground(btn_sign_drawable);

            final TextView txtDate = new TextView(context);
            LayoutParams paramsDate = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            paramsDate.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
            paramsDate.leftMargin = (int) (margin_parent_lr - sign_btn_radius + dip2px(context, 3) + i*(line_width-dip2px(context, 3)));
            paramsDate.addRule(RelativeLayout.ALIGN_LEFT, btns[i+10]);
            paramsDate.addRule(RelativeLayout.BELOW, btns[i+10]);
            txtDate.setId(dates[i+10]);
            txtDate.setTextColor(ContextCompat.getColor(context, R.color.white));
            txtDate.setText("积分+10");
            txtDate.setTextSize(sp2px(context, 4));
            txtDate.setLayoutParams(paramsDate);

            final TextView txtJifen = new TextView(context);
            LayoutParams paramsJifen = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            paramsJifen.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
            paramsJifen.leftMargin = (int) (margin_parent_lr - sign_btn_radius + dip2px(context, 3) + i*(line_width-dip2px(context, 3)));
            paramsJifen.addRule(RelativeLayout.BELOW, dates[i+10]);
            paramsJifen.addRule(RelativeLayout.ALIGN_LEFT, btns[i+10]);
            txtJifen.setTextColor(ContextCompat.getColor(context, R.color.yello));
            txtJifen.setId(jifens[i+10]);
            txtJifen.setText("8月6日");
            txtJifen.setTextSize(sp2px(context, 4));
            txtJifen.setLayoutParams(paramsJifen);

            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < 4; j++) {
                        if(button.getId() == btns[j+10]){
                            if(null == listBean)return;
                            if(listBean.get(j+10).getLs_type() == 1){
                                Toast.makeText(context, "click="+(j+10+1), Toast.LENGTH_SHORT).show();
                                button.setBackground(ContextCompat.getDrawable(context, R.mipmap.btn_yiqian_red));
                                findViewById(lines[j+10]).setBackground(ContextCompat.getDrawable(context, R.mipmap.yellow_bg));
                                txtDate.setText(listBean.get(j+10).getLs_date());
                                txtJifen.setText("积分+"+listBean.get(j+10).getReward_val());
                                listener.setOnSignListener(j+10, button, txtDate, txtJifen, findViewById(lines[j+10]));

                            }
                        }

                    }

                }
            });

            addView(button);
            addView(txtDate);
            addView(txtJifen);
        }
    }


    /**
     * 功能：第一次进入 初始化各个按钮控件的状态
     * @param bean
     */
    public void initSignLayoutData(List<SignListBean.DataBean> bean){
        listBean = bean;
        for (int i = 0; i < 14; i++) {
            Button sign = (Button) findViewById(btns[i]);
            TextView date = (TextView) findViewById(dates[i]);
            TextView jifen = (TextView) findViewById(jifens[i]);
            View line = findViewById(lines[i]);


            date.setText(bean.get(i).getLs_date());
            final int rewardType = bean.get(i).getReward_type();//为活动奖励类型
            String strType = "积分+";
            switch (rewardType) {
                case 0:
                    strType = "积分+";
                    break;
//                case 1:
//                    strType = "红包+";
//                    break;
                case 2:
                    strType = "经验+";
                    break;
            }
            final int lsType = bean.get(i).getLs_type();//为签到类型
            if (lsType == 0 && (rewardType == 0 || rewardType == 2)) {
                jifen.setText(strType + bean.get(i).getReward_val());
            } else {
                jifen.setText("");
            }
            switch (lsType) {
                case 0:
                    sign.setBackground(ContextCompat.getDrawable(context, R.mipmap.btn_yiqian_red));
                    if (i == 5 || i == 10) {
                        line.setBackground(ContextCompat.getDrawable(context, R.mipmap.yellow_vertical_bg));

                    } else {
                        line.setBackground(ContextCompat.getDrawable(context, R.mipmap.yellow_bg));
                    }
                    break;

                case 1:
                    sign.setBackground(ContextCompat.getDrawable(context, R.mipmap.btn_buqian_red));
                    line.setBackground(ContextCompat.getDrawable(context, R.mipmap.touming_bg));
                    break;

                case 2:
                    sign.setBackground(ContextCompat.getDrawable(context, R.mipmap.btn_buqian_gray));
                    line.setBackground(ContextCompat.getDrawable(context, R.mipmap.touming_bg));

                    break;
            }

            if (rewardType == 1) {
               sign.setBackground(ContextCompat.getDrawable(context, R.mipmap.btn_money));
            }
        }
    }




    /**
     * convert the dp to px depend on the device density.
     *
     * @param context the context
     * @param dpValue a value of dp
     * @return the result of px
     */
    public static int dip2px(Context context, float dpValue) {
        return (int) (dpValue * getDensity(context) + 0.5f);
    }

    /**
     * convert the px to dp depend on the device density.
     *
     * @param context the context
     * @param pxValue a value of px
     * @return the result of dp
     */
    public static int px2dip(Context context, float pxValue) {
        return (int) (pxValue / getDensity(context) + 0.5f);
    }

    /**
     * convert the sp to px depend on the device scaledDensity.
     *
     * @param context the context
     * @param spValue a value of sp
     * @return the result of px
     */
    public static int sp2px(Context context, float spValue) {
        return (int) (spValue * getFontDensity(context) + 0.5);
    }

    /**
     * convert the px to sp depend on the device scaledDensity.
     *
     * @param context the context
     * @param pxValue a value of px
     * @return the result of sp
     */
    public static int px2sp(Context context, float pxValue) {
        return (int) (pxValue / getFontDensity(context) + 0.5);
    }

    /**
     * get the density of device screen.
     *
     * @param context the context
     * @return the screen density
     */
    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * get the scale density of device screen.
     * usually this value is the same as density.
     * but it can adjust by user.
     *
     * @param context the context
     * @return the screen scale density.
     */
    public static float getFontDensity(Context context) {
        return context.getResources().getDisplayMetrics().scaledDensity;
    }

    private void initScreen(){
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        sdensity = dm.density;
        sWidth= dm.widthPixels;
        sHeight = dm.heightPixels;
    }


    clickSignListener listener;
    public interface clickSignListener{
        void setOnSignListener(int index, Button btn, TextView date, TextView jifen, View line);
    }

    public void setOnSignListener(clickSignListener listener){
        this.listener = listener;
    }
}
