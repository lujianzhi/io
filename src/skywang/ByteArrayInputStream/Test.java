package skywang.ByteArrayInputStream;

import java.io.ByteArrayInputStream;

/**
 * Created by Ian.Lu on 2018/3/8.
 * Project : IO
 */
public class Test {

    private static final int LEN = 5;
    private static final int MARK = 1;
    private static final int SKIP = 3;

    // 对应英文字母“abcddefghijklmnopqrsttuvwxyz”
    private static final byte[] arrayLetters = {
            0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
    };

    public static void main(String[] args) {
        String tempStr = new String(arrayLetters);
        System.out.println("ArrayLetters : " + tempStr);

        testByteArrayInputStream();
    }

    private static void testByteArrayInputStream() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayLetters);

        for (int i = 0; i < LEN; i++) {
            if (byteArrayInputStream.available() >= 0) {//是否能继续读
                int tempByte = byteArrayInputStream.read();
                System.out.printf("%d : 0x%s : %s\n", i,
                        Integer.toHexString(tempByte),      //16进制
                        new String(new int[]{tempByte}, 0, 1)  //int类型转换为String类型
                );
            }
        }

        //是否支持标记功能
        if (byteArrayInputStream.markSupported()) {
            System.out.println("ByteArrayInputStream支持标记功能");
        } else {
            System.out.println("ByteArrayInputStream不支持标记功能，退出程序");
            return;
        }

        // 标记“字节流中下一个被读取的位置”。即--标记“0x66”，因为因为前面已经读取了5个字节，所以下一个被读取的位置是第6个字节”
        // (01), ByteArrayInputStream类的mark(MARK)函数中的“参数MARK”是没有实际意义的。
        // (02), mark()与reset()是配套的，reset()会将“字节流中下一个被读取的位置”重置为“mark()中所保存的位置”
        byteArrayInputStream.mark(MARK);

        //跳过SKIP个字节，下一个字节就是跳过SKIP个位置后的字节
        byteArrayInputStream.skip(SKIP);

        byte[] buf = new byte[LEN];
        //将长度为LEN的数据存入buf中
        int length = byteArrayInputStream.read(buf, 0, LEN);
        String bufStr = new String(buf, 0, length);
        System.out.println("新数组内容为 : " + bufStr);

        //重置“字节流”：即，将“字节流中下一个被读取的位置”重置到“mark()所标记的位置”，即0x66。
        byteArrayInputStream.reset();
        //重新存储buf数组
        byteArrayInputStream.read(buf, 0, LEN);
        bufStr = new String(buf);
        System.out.println("reset之后数组内容为 : " + bufStr);

    }
}
