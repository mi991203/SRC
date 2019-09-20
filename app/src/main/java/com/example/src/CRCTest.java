package com.example.src;

import java.util.ArrayList;

public class CRCTest {
//    static String  string = "1011111100110011001";
//    static String string;
//
//    static String  res = Test01.bytes2BinaryStr(string.getBytes());
//    static String[] c = res.split("");
//    static String[]c = string.split("");

    public static ArrayList<Integer> getCRC(ArrayList<Integer> arrayList){
        int[] cc = new int[]{1,0,0,0,0,0,1,1,1};//生成多项式
        //末尾补0
        for (int i = 0; i < 8; i++) {
            arrayList.add(0);
        }
        int tag = 0;
        ArrayList<Integer> resultList = new ArrayList<>(); //结果list
        ArrayList<Integer> tempList = new ArrayList<>(); //余数缓存list
        //循环开始
        while (tag < arrayList.size() - 8){
            ArrayList<Integer> arrayList1 = new ArrayList<>(); //本次运算取出的前9位
            //若有余数，将余数先加入待计算串
            for (int i = 0; i < tempList.size(); i++) {
                arrayList1.add(tempList.get(i));
            }
            tempList.clear();
            //补齐9位
            for (int i = tag + arrayList1.size(); i < tag + cc.length; i++) {
                arrayList1.add(arrayList.get(i));
            }
            //按位异或
            for (int i = 0; i < cc.length;i++){
                int res = arrayList1.get(i)^cc[i];
                if (res == 0 && tempList.size() == 0)//首位0不计入余数
                    tag ++;
                else tempList.add(res);
            }
            resultList = tempList;
        }
        //循环结束后，如果余数不足8位，则要在前面补0
        if (resultList.size()<8){
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < resultList.size(); i++) {
                temp.add(resultList.get(i));
            }
            resultList.clear();
            for (int i = 0; i < 8 - temp.size(); i++) {
                resultList.add(0);
            }
            for (int i = 0; i < temp.size(); i++) {
                resultList.add(temp.get(i));
            }
        }
        return  resultList;//最终的FCS校验码
    }


    public static void main(String[] args) {
        String res = "01000110";
        String[] c = res.split("");

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < c.length; i++) {
            arrayList.add(Integer.valueOf(c[i]));
        }
//        System.out.println(arrayList);
        System.out.println(getCRC(arrayList));
    }
}
