package skywang.bytearrayoutputstream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Ian.Lu on 2018/3/8.
 * Project : IO
 */

class Test {

    private static final int LEN = 5;
    // 对应英文字母“abcddefghijklmnopqrsttuvwxyz”
    private static final byte[] arrayLetters = {
            0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
    };

    public static void main(String[] args) {
        String str = new String(arrayLetters);
        System.out.println(str);

        byteArrayOutputStreamTest();
    }

    private static void byteArrayOutputStreamTest() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < LEN; i++) {
            byteArrayOutputStream.write(arrayLetters[i]);
        }
        System.out.println("ByteArrayOutputStream : " + byteArrayOutputStream);

        byteArrayOutputStream.write(arrayLetters, 5, LEN);
        System.out.println("ByteArrayOutputStream : " + byteArrayOutputStream);

        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.writeTo(byteArrayOutputStream2);
            System.out.println("ByteArrayOutputStream : " + byteArrayOutputStream2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
