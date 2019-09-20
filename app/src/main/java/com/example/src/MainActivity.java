package com.example.src;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.src.BinaryConversion.to16;

public class MainActivity extends AppCompatActivity {

    TextInputEditText sour_MAC;
    TextInputEditText des_MAC;
    TextInputEditText data;
    TextView tv_result;
    Button btn_start;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sour_MAC = findViewById(R.id.text_input_source_MAC);
        des_MAC = findViewById(R.id.text_input_des_MAC);
        data = findViewById(R.id.text_input_data);
        tv_result = findViewById(R.id.tv_result);
        btn_start = findViewById(R.id.btn_start);
        tv1 = findViewById(R.id.tv_preamble);
        tv2 = findViewById(R.id.tv_preframe_delimiter);
        tv3 = findViewById(R.id.tv_desMac);
        tv4 = findViewById(R.id.tv_sourMac);
        tv5 = findViewById(R.id.tv_length);
        tv6 = findViewById(R.id.tv_datas);
        tv7 = findViewById(R.id.tv_check_code);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = "";
                findViewById(R.id.linear_layout).setVisibility(View.VISIBLE);
                String source = sour_MAC.getText().toString().trim();
                String destination = des_MAC.getText().toString().trim();
                String datas = data.getText().toString().trim();
                String addString = "";


                tv1.setText("0xAAAAAAAAAAAAAA");
                tv2.setText("0xAB");
                tv3.setText(destination);
                tv4.setText(source);
                tv5.setText("0x" + String.format("%04x", datas.length() * 4));
                tv6.setText(datas);
                String length = String.format("%04x", datas.length()).trim();
                ArrayList<Integer> result = new CRC(destination + source + length + datas).getCRC();
                if (datas.length() < 92) {
                    for (int i = 0; i < 92 - datas.length(); i++) {
                        addString += "0";
                    }
                }
                tv7.setText(result.toString());
//                if (to16(result).length() < 8) {
//                    addString += " ";
//                    for (int i = 0; i < 8 - to16(result).length(); i++) {
//                        addString += "0";
//                    }
//                }

                string = "AAAAAAAAAAAAAAAB " + destination.replace("-", "") + "" + source.replace("-", "") + "" + String.format("%04x", datas.length() * 4) + "" + datas + addString + to16(result);
                tv_result.setText(string);

            }
            //A8:1E:84:05:D2:F0
        });


        //A6-E9-79-AA-58-99
    }

    public String hexToBinary(String str) {
        String string = "";
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case '0':
                    str += "0000";
                    break;
                case '1':
                    str += "0001";
                    break;
                case '2':
                    str += "0010";
                    break;
                case '3':
                    str += "0011";
                    break;
                case '4':
                    str += "0100";
                    break;
                case '5':
                    str += "0101";
                    break;
                case '6':
                    str += "0110";
                    break;
                case '7':
                    str += "0111";
                    break;
                case '8':
                    str += "1000";
                    break;
                case '9':
                    str += "1001";
                    break;
                case 'A':
                    str += "1010";
                    break;
                case 'B':
                    str += "1011";
                    break;
                case 'C':
                    str += "1100";
                    break;
                case 'D':
                    str += "1101";
                    break;
                case 'E':
                    str += "1110";
                    break;
                case 'F':
                    str += "1111";
                    break;
                default:
                    break;
            }
        }
        return string;

    }
}
