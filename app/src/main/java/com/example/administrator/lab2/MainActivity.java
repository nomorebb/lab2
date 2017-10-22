package com.example.administrator.lab2;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

//import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    ImageView mImage;
    AlertDialog.Builder builder;
    RadioGroup radioGroup;
    RadioButton student;
    RadioButton teacher;
    CoordinatorLayout coordinatorLayout;
    Button button1;
    Button button2;
    EditText number;
    EditText password;
    TextInputLayout layout1;
    TextInputLayout layout2;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImage = (ImageView) findViewById(R.id.imageView);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coor);
        linearLayout = (LinearLayout) findViewById(R.id.traceroute_rootview);
        linearLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.traceroute_rootview:
                        InputMethodManager imm = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        break;
                }
            }
        });
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog(view);
            }
        });
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        student = (RadioButton) findViewById(R.id.radioButton4);
        teacher = (RadioButton) findViewById(R.id.radioButton3);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                String temp = null;
                if(student.getId()==i){
                    temp = student.getText().toString();
                }
                if(teacher.getId()==i){
                    temp = teacher.getText().toString();
                }
                Snackbar.make(coordinatorLayout,"你选择了"+temp, Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
        button1 = (Button) findViewById(R.id.button3);
        layout1 = (TextInputLayout) findViewById(R.id.textInputLayout);
        layout2 = (TextInputLayout) findViewById(R.id.textInputLayout2);
        number = layout1.getEditText();
        password = layout2.getEditText();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = number.getText().toString();
                String pass = password.getText().toString();
                if(TextUtils.isEmpty(num)) {
                    layout1.setErrorEnabled(true);
                    layout1.setError("学号不能为空");
                }
                else if(TextUtils.isEmpty(pass)) {
                    layout1.setErrorEnabled(false);
                    layout2.setErrorEnabled(true);
                    layout2.setError("密码不能为空");
                }
                else if(num.equals("123456")&&pass.equals("6666")){
                    layout2.setErrorEnabled(false);
                    Snackbar.make(coordinatorLayout,"登录成功", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this,"Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                }
                else{
                    Snackbar.make(coordinatorLayout,"学号或密码错误", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this,"Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                }
            }
        });
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(student.isChecked()){
                    Snackbar.make(coordinatorLayout,"学生注册功能尚未启用", Snackbar.LENGTH_LONG).setAction("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(MainActivity.this,"Snackbar的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                        }
                    }).show();
                }
                if(teacher.isChecked()){
                    Toast.makeText(MainActivity.this,"教职工注册功能尚未启用",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void Dialog(View view){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("上传头像");
        final String[] Items={"拍摄","从相册选择"};
        builder.setItems(Items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "您选择了[" + Items[i] + "]", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "您选择了[取消]", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        dialog.show();
    }
}
