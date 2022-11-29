package com.example.shop_shevtsova303;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView strawberry;
    private ImageView apple;
    private ImageView blueberry;
    private ImageView potatoes;


    private CheckBox cB[] = new CheckBox[4];
    private EditText et_Count[] = new EditText[4];
    private EditText et_Price[] = new EditText[4];
    RadioButton rb_Dialog;
    RadioButton rb_TextView;
    RadioButton rb_Toast;
    TextView tv_Itog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //настройка imageview
        strawberry = findViewById(R.id.imageView_Strawb);
        strawberry.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.strawberry));
        apple = findViewById(R.id.imageView_Apple);
        apple.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.apple));
        blueberry = findViewById(R.id.imageView_Blueb);
        blueberry.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.blueberry));
        potatoes = findViewById(R.id.imageView_Potatoes);
        potatoes.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.potatoes));

        rb_Dialog=findViewById(R.id.rb_Dialog);
        rb_TextView=findViewById(R.id.rb_TextView);
        rb_Toast = findViewById(R.id.rb_Toast);
        tv_Itog = findViewById(R.id.tV_Itog);


        //настройка checkbox, edittext
        cB[0] = findViewById(R.id.cB_strawb);
        cB[1] = findViewById(R.id.cB_apple);
        cB[2] = findViewById(R.id.cB_blueb);
        cB[3] = findViewById(R.id.cB_potatoes);

        et_Count[0] = findViewById(R.id.eT_Count_straw);
        et_Count[1] = findViewById(R.id.eT_Count_Apple);
        et_Count[2] = findViewById(R.id.eT_Count_blueb);
        et_Count[3] = findViewById(R.id.eT_Count_potatoes);

        et_Price[0] = findViewById(R.id.eT_Price_straw);
        et_Price[1] = findViewById(R.id.eT_Price_Apple);
        et_Price[2] = findViewById(R.id.eT_Price_blueb);
        et_Price[3] = findViewById(R.id.eT_Price_potatoes);
    }
    public void onClick(View view){
        Double sum=0.00;
        Double sum_total=0.00;
        String rep2 = "";
        int n = cB.length;

        for(int i=0; i<n;i++){
            if(!et_Count[i].getText().toString().isEmpty()&&!et_Price[i].getText().toString().isEmpty()){
                if(cB[i].isChecked()){
                    Double Count = Double.parseDouble(et_Count[i].getText().toString());
                    Double Price = Double.parseDouble(et_Price[i].getText().toString());
                    sum=Count * Price;
                    sum_total+=Count*Price;
                    rep2+=String.format("%s: %.2f*%.2f = %.2f rubles\n",cB[i].getText().toString(),Count,Price, sum);

                }
            }
            else{
                tv_Itog.setText("The fields should not be empty!");
            }


        }
        rep2+=String.format("Total = %.2f rubles\n",sum_total);

        if(!et_Count[0].getText().toString().isEmpty()||!et_Count[1].getText().toString().isEmpty()||!et_Count[2].getText().toString().isEmpty()||
                !et_Count[3].getText().toString().isEmpty()){
            if(!et_Price[0].getText().toString().isEmpty()||
                    !et_Price[1].getText().toString().isEmpty()||!et_Price[2].getText().toString().isEmpty()||!et_Price[3].getText().toString().isEmpty()){
            if (rb_TextView.isChecked()) {
                tv_Itog.setText(rep2);
            }

            if (rb_Toast.isChecked()) {
                Toast.makeText(this, rep2, Toast.LENGTH_SHORT).show();

            }
            if (rb_Dialog.isChecked()) {
                AlertDialog dialog = createDialog(rep2);
                dialog.show();
            }
            }
        }
        else {
            tv_Itog.setText("The fields should not be empty!");
        }
    }

    public AlertDialog createDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Your cheque")
                .setIcon(R.drawable.icon)
                .setMessage(message)
                .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Закрываем диалоговое окно
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

}