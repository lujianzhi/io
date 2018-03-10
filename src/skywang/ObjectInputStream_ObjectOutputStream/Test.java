package skywang.ObjectInputStream_ObjectOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Ian.Lu on 2018/3/9.
 * Project : IO
 */

class Test {

    private static final String FILE_PATH = "E:\\\\JetBrains\\\\projects\\\\IO\\\\test\\\\skyWang_ObjectInputStream_ObjectOutputStream.txt";

    public static void main(String[] args) {
        my();
//        sky();
    }

    private static void my() {
        testWrite();
        testRead();
    }

    private static void sky() {
        skyWrite();
        skyRead();
    }

    private static void testWrite() {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH));

            objectOutputStream.writeBoolean(false);
            //这里写入了四个字节
            objectOutputStream.writeBytes("what");
            objectOutputStream.writeInt(31415926);
            objectOutputStream.writeDouble(3.14D);
            objectOutputStream.writeFloat(3.1415926F);

            HashMap<Integer, String> hashMap = new HashMap<>();
            hashMap.put(1, "value_1");
            hashMap.put(2, "value_2");
            hashMap.put(3, "value_3");
            objectOutputStream.writeObject(hashMap);

            Book book = new Book("第一本书", 79);
            objectOutputStream.writeObject(book);

            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testRead() {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
            boolean booleanData = objectInputStream.readBoolean();
            System.out.println("booleanData : " + booleanData);
            //读取的时候也应该把之前的字节全部读取
            byte[] byteData = new byte[4];
            objectInputStream.read(byteData, 0, 4);
            String byteDataStr = new String(byteData);
            System.out.println("byteData : " + byteDataStr);
            int intData = objectInputStream.readInt();
            System.out.println("intData : " + intData);
            double doubleData = objectInputStream.readDouble();
            System.out.println("doubleData : " + doubleData);
            float floatData = objectInputStream.readFloat();
            System.out.println("floatData : " + floatData);
            HashMap<Integer, String> hashMap = (HashMap<Integer, String>) objectInputStream.readObject();
            Iterator<Map.Entry<Integer, String>> iterator = hashMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, String> entry = iterator.next();
                System.out.println("HashMapObject : key-" + entry.getKey() + " ; value-" + entry.getValue());
            }
            Book bookData = (Book) objectInputStream.readObject();
            System.out.println("BookObject : " + bookData.toString());

            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void skyWrite() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(FILE_PATH));
            out.writeBoolean(true);
            out.writeByte((byte) 65);
            out.writeChar('a');
            out.writeInt(20131015);
            out.writeFloat(3.14F);
            out.writeDouble(1.414D);
            // 写入HashMap对象
            HashMap map = new HashMap();
            map.put("one", "red");
            map.put("two", "green");
            map.put("three", "blue");
            out.writeObject(map);
            // 写入自定义的Box对象，Box实现了Serializable接口
            Box box = new Box("desk", 80, 48);
            out.writeObject(box);

            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void skyRead() {
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(FILE_PATH));
            System.out.printf("boolean:%b\n", in.readBoolean());
            System.out.printf("byte:%d\n", (in.readByte() & 0xff));
            System.out.printf("char:%c\n", in.readChar());
            System.out.printf("int:%d\n", in.readInt());
            System.out.printf("float:%f\n", in.readFloat());
            System.out.printf("double:%f\n", in.readDouble());
            // 读取HashMap对象
            HashMap map = (HashMap) in.readObject();
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                System.out.printf("%-6s -- %s\n", entry.getKey(), entry.getValue());
            }
            // 读取Box对象，Box实现了Serializable接口
            Box box = (Box) in.readObject();
            System.out.println("box: " + box);

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
