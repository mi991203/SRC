package com.example.src;

import java.util.ArrayList;

public class BinaryConversion {

    private static String hexStr =  "0123456789ABCDEF";
    private static String[] binaryArray =
            {"0000","0001","0010","0011",
                    "0100","0101","0110","0111",
                    "1000","1001","1010","1011",
                    "1100","1101","1110","1111"};

//    public static void main(String[] args)
//    {
//        String str = "二进制与十六进制互转测试";
//        System.out.println("源字符串：\n"+str);
//
//        String hexString = BinaryToHexString(str.getBytes());
//        System.out.println("转换为十六进制：\n"+hexString);
//        System.out.println("转换为二进制：\n"+bytes2BinaryStr(str.getBytes()));
//
//        byte [] bArray = HexStringToBinary(hexString);
//        System.out.println("将str的十六进制文件转换为二进制再转为String：\n"+new String(bArray));
//
//    }
    /**
     * @return 转换为二进制字符串
     */
    public static String bytes2BinaryStr(byte[] bArray){

        String outStr = "";
        int pos = 0;
        for(byte b:bArray){
            //高四位
            pos = (b&0xF0)>>4;
            outStr+=binaryArray[pos];
            //低四位
            pos=b&0x0F;
            outStr+=binaryArray[pos];
        }
        return outStr;

    }
    /**
     *
     * @param bytes
     * @return 将二进制转换为十六进制字符输出
     */
    public static String BinaryToHexString(byte[] bytes){

        String result = "";
        String hex = "";
        for(int i=0;i<bytes.length;i++){
            //字节高4位
            hex = String.valueOf(hexStr.charAt((bytes[i]&0xF0)>>4));
            //字节低4位
            hex += String.valueOf(hexStr.charAt(bytes[i]&0x0F));
            result +=hex+" ";
        }
        return result;
    }
    /**
     *
     * @param hexString
     * @return 将十六进制转换为字节数组
     */
    public static byte[] HexStringToBinary(String hexString){
        //hexString的长度对2取整，作为bytes的长度
        int len = hexString.length()/2;
        byte[] bytes = new byte[len];
        byte high = 0;//字节高四位
        byte low = 0;//字节低四位

        for(int i=0;i<len;i++){
            //右移四位得到高位
            high = (byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4);
            low = (byte)hexStr.indexOf(hexString.charAt(2*i+1));
            bytes[i] = (byte) (high|low);//高地位做或运算
        }
        return bytes;
    }

    public static String to16(ArrayList<Integer> arrayList){
        StringBuilder stringBuilder = new StringBuilder();
        int a = arrayList.get(0)*8+arrayList.get(1)*4+arrayList.get(2)*2+arrayList.get(3);
        int b = arrayList.get(4)*8+arrayList.get(5)*4+arrayList.get(6)*2+arrayList.get(7);
        switch (a){
            case 15:stringBuilder.append("F");break;
            case 14:stringBuilder.append("E");break;
            case 13:stringBuilder.append("D");break;
            case 12:stringBuilder.append("C");break;
            case 11:stringBuilder.append("B");break;
            case 10:stringBuilder.append("A");break;
            default:stringBuilder.append(a);break;
        }
        switch (b){
            case 15:stringBuilder.append("F");break;
            case 14:stringBuilder.append("E");break;
            case 13:stringBuilder.append("D");break;
            case 12:stringBuilder.append("C");break;
            case 11:stringBuilder.append("B");break;
            case 10:stringBuilder.append("A");break;
            default:stringBuilder.append(b);break;
        }
        return stringBuilder.toString();
    }
}