package com.example.src;

import android.util.Log;

import java.util.ArrayList;

public class CRC {
//    static String  string = "1011111100110011001";
    String string;
    ArrayList<Integer> arrayList = new ArrayList<>();

//
//    static String  res = BinaryConversion.bytes2BinaryStr(string.getBytes());
//    static String[] c = res.split("");
//    static String[]c = string.split("");

    public ArrayList<Integer> getCRC(){



        int[] cc = new int[]{1,0,0,0,0,0,1,1,1};
        for (int i = 0; i < 8; i++) {
            arrayList.add(0);
        }

        int tag = 0;
        ArrayList<Integer> resultList = new ArrayList<>(); //结果list
        ArrayList<Integer> tempList = new ArrayList<>();
        while (tag < arrayList.size() - 8 && arrayList != null && arrayList.size()>0){
            ArrayList<Integer> arrayList1 = new ArrayList<>(); //本次运算取出的前9位
            for (int i = 0; i < tempList.size(); i++) {
                arrayList1.add(tempList.get(i));
            }
            tempList.clear();
            for (int i = tag + arrayList1.size(); i < tag + cc.length; i++) {
                arrayList1.add(arrayList.get(i));
            }
            for (int i = 0; i < cc.length;i++){
                int res = arrayList1.get(i)^cc[i];
                if (res == 0 && tempList.size() == 0)
                    tag ++;
                else tempList.add(res);
            }
            Log.e("余数", tempList.toString() );
            resultList = tempList;
        }
        if (resultList.size()<8){
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < resultList.size(); i++) {
                temp.add(resultList.get(i));
            }
//            temp = resultList;
            resultList.clear();
            for (int i = 0; i < 8 - temp.size(); i++) {
                resultList.add(0);
            }
            for (int i = 0; i < temp.size(); i++) {
                resultList.add(temp.get(i));
            }
        }
        return  resultList;
    }

    public CRC(String string) {
        this.string = string;
        String  res = BinaryConversion.bytes2BinaryStr(string.getBytes()).trim();
        String[] c = res.split("");

        for (int i = 0; i < c.length; i++) {
            if (!c[i].equals(""))
            arrayList.add(Integer.valueOf(c[i]));
        }
    }

//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        String string = scan.nextLine();
//
//
//        String  res = BinaryConversion.bytes2BinaryStr(string.getBytes());
//        String[] c = res.split("");
//
//        ArrayList<Integer> arrayList = new ArrayList<>();
//        for (int i = 0; i < c.length; i++) {
//            arrayList.add(Integer.valueOf(c[i]));
//        }
////        System.out.println(arrayList);
////        System.out.println(getCRC(arrayList));
//    }
}
