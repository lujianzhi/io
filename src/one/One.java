package one;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * Created by Ian.Lu on 2017/7/27.
 * Project : IO
 */
public class One {
    public static void main(String[] args) {
//        one("E:\\JetBrains\\projects\\IO\\testMusic\\");

//        two("E:\\JetBrains\\projects\\IO\\testMusic\\");

//        three();

//        four("E:\\JetBrains\\projects\\IO\\test\\ian.txt");

//        five("E:\\JetBrains\\projects\\IO\\test\\ian.txt");

//        six("E:\\JetBrains\\projects\\IO\\test\\ian.txt");

//        seven("E:\\JetBrains\\projects\\IO\\test\\ian.txt");

//        eight("E:\\JetBrains\\projects\\IO\\test\\ian2.txt");

//        nine("E:\\JetBrains\\projects\\IO\\test\\ian2.txt");

//        ten("E:\\JetBrains\\projects\\IO\\test\\ian2.txt");

//        eleven("E:\\JetBrains\\projects\\IO\\test\\ian.txt", "E:\\JetBrains\\projects\\IO\\test\\ian3.txt");

//        twelve("E:\\JetBrains\\projects\\IO\\test\\ian.txt");

//        thirteen("E:\\JetBrains\\projects\\IO\\test\\ian.txt");

//        fourteen();

//        fifteen();

//        sixteen("E:\\JetBrains\\projects\\IO\\test\\ian.txt");

//        seventeen();

//        eighteen("E:\\JetBrains\\projects\\IO\\test\\ian4.txt");

//        nineteen();

//        twenty();

//        twenty_one();

//        twenty_two("E:\\JetBrains\\projects\\IO\\test\\ian.txt");

//        twenty_three("E:\\JetBrains\\projects\\IO\\test\\ian5.txt");

        twenty_four("E:\\JetBrains\\projects\\IO\\test\\ian5.txt");
    }

    /**
     * 序列化输出流
     */
    private static void twenty_four(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            Student student = (Student) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println(student);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 序列化输入流
     */
    private static void twenty_three(String filePath) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            Student student = new Student("Ian", 24);
            objectOutputStream.writeObject(student);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Scanner的小例子，从文件中读内容
     */
    private static void twenty_two(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Scanner的小例子，从键盘读数据
     */
    private static void twenty_one() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        System.out.println(str);
    }

    /**
     * BufferedReader只能接受字符流的缓冲区
     * 使用缓冲区从键盘上读入内容
     */
    private static void twenty() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        System.out.println("请输入内容");
        try {
            str = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("输入的内容 : " + str);
    }

    /**
     * System.in重定向
     */
    private static void nineteen() {
        NumberFormat nf = new DecimalFormat("0.0");
        Double dou = Double.parseDouble(nf.format(2.333333333333));
        System.out.println(dou + "");
    }

    /**
     * 输入输出重定向
     * 为System.out.println()重定向输出
     */
    private static void eighteen(String filePath) {
        System.out.println("接下来我要重定向了");
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            System.setOut(new PrintStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("重定向输出");
    }


    /**
     * 使用OutputStream向屏幕上输出内容
     */
    private static void seventeen() {
        OutputStream outputStream = System.out;
        try {
            outputStream.write("使用OutputStream向屏幕上输出内容".getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印流
     */
    private static void sixteen(String filePath) {
        File file = new File(filePath);
        try {
            PrintStream printStream = new PrintStream(file);
            //print什么，文件中就写入什么
            printStream.println(123);
            printStream.println("nihao");
            //write是根据char来的，这里56代表数字8
            printStream.write(56);
            //格式化
            printStream.println();
            printStream.printf("姓名:%s,年龄:%d", "Ian", 24);
            printStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用管道流
     */
    private static void fifteen() {
        Send send = new Send();
        Receive receive = new Receive();
        try {
            send.getOut().connect(receive.getPipedInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(send).start();
        new Thread(receive).start();
    }

    /**
     * 使用内存操作流
     */
    private static void fourteen() {
        try {
            String str = "IAN HELLO";
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int temp;
            while ((temp = byteArrayInputStream.read()) != -1) {
                char data = (char) temp;
                byteArrayOutputStream.write(Character.toLowerCase(data));
            }
            byteArrayInputStream.close();
            byteArrayOutputStream.close();
            System.out.println(byteArrayOutputStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节输入流转换为字符输入流
     */
    private static void thirteen(String filePath) {
        File file = new File(filePath);

        try {
            Reader reader = new InputStreamReader(new FileInputStream(file));
            char[] datas = new char[(int) file.length()];
            reader.read(datas);
            System.out.println(new String(datas));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节输出流转换为字符输出流
     */
    private static void twelve(String filePath) {
        File file = new File(filePath);

        try {
            Writer writer = new OutputStreamWriter(new FileOutputStream(file, true));
            writer.write("\n\n\n字节输出流转换为字符输出流");

            writer.close();
            ten(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件
     */
    private static void eleven(String oldPath, String newPath) {
        File oldFile = new File(oldPath);
        File newFile = new File(newPath);

        try {
            if (!oldFile.exists()) {
                System.out.println("被复制文件不存在");
                return;
            }

            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            InputStream inputStream = new FileInputStream(oldFile);
            OutputStream outputStream = new FileOutputStream(newFile);

            int temp;
            while ((temp = inputStream.read()) != -1) {
                outputStream.write(temp);
            }

            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 字符流
     * 读取文件内容
     */
    private static void ten(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }

        try {
            Reader reader = new FileReader(file);
            char[] datas = new char[100];
            int len;
            int count = 0;
            while ((len = reader.read()) != -1) {
                datas[count++] = (char) len;
            }
            reader.close();
            System.out.println(new String(datas));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 字符流
     * 读取文件内容
     */
    private static void nine(String filePath) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                System.out.println("文件不存在");
                return;
            }
            Reader reader = new FileReader(file);
            char[] datas = new char[(int) file.length()];
            reader.read(datas);
            reader.close();
            System.out.println(new String(datas));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符流
     * 向文件写入数据
     */
    private static void eight(String filePath) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
//            Writer writer = new FileWriter(file);
//            String str = "向文件写入数据\n字符流";
            //追加内容
            Writer writer = new FileWriter(file, true);
            String str = "\n追加内容";
            //可以直接输入字符串，而不需要你将字符串转化为字节数组
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节流
     * 读取文件中的内容输出到控制台
     */
    private static void seven(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }

        try {
            InputStream inputStream = new FileInputStream(file);
            byte[] datas = new byte[(int) file.length()];
            int b;
            int count = 0;
            while ((b = inputStream.read()) != -1) {
                datas[count++] = (byte) b;
            }
            inputStream.close();
            System.out.println(new String(datas));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 字节流
     * 读取文件中的内容输出到控制台
     */
    private static void six(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        try {
            InputStream inputStream = new FileInputStream(file);
            //根据文件的长度来决定这个数组的长度
            byte[] datas = new byte[(int) file.length()];
            int len = inputStream.read(datas);
            inputStream.close();
            System.out.println("长度 : " + len);
            System.out.println(new String(datas));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字节流
     * 向文件中写入字符串
     */
    private static void five(String filePath) {
        try {
            File file = new File(filePath);
            //不带第二个参数
//            OutputStream outputStream = new FileOutputStream(file);
//            String str = "向文件中写入字符串";
            //带第二个参数,代表是不是追加
            OutputStream outputStream = new FileOutputStream(file, true);
            String str = "\n追加新的字符串了" +
                    "\n         ";
            byte[] datas = str.getBytes();
            outputStream.write(datas);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 使用RandomAccessFile写入文件
     */
    private static void four(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

            randomAccessFile.writeBoolean(false);
            randomAccessFile.writeBytes("ian");
            randomAccessFile.writeChars("哟ian哟");
            randomAccessFile.writeDouble(11.1);
            randomAccessFile.writeFloat(22.2F);
            randomAccessFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件、重命名文件、删除文件
     */
    private static void three() {
        String[] tips = new String[3];
        System.out.println("输入操作: -r(重命名)/-d(删除)/-m(创建) oldFile newFile");
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while (true) {
            outputTip(i);
            String userInput = scanner.nextLine();
            tips[i] = userInput;
            i++;

            if ("exit".equals(userInput) || i == 3) {
                break;
            }
        }

        judgeOperate(tips);

    }

    /**
     * 执行相应的操作
     */
    private static void judgeOperate(String[] tips) {
        String operate = tips[0];
        String oldFile = tips[1];
        String newFile = tips[2];

        switch (operate) {
            case "-r":
                rename(oldFile, newFile);
                break;
            case "-d":
                delete(oldFile);
                break;
            case "-m":
                make(oldFile);
                break;
        }

    }

    /**
     * 创建
     */
    private static void make(String oldFileStr) {
        File oldFile = new File(oldFileStr);
        if (!oldFile.exists()) {
            if (oldFile.mkdir())
                System.out.println("创建成功");
            else
                System.out.println("创建失败");
        } else {
            System.out.println("文件已存在");
        }
    }

    /**
     * 删除
     */
    private static void delete(String oldFileStr) {
        File oldFile = new File(oldFileStr);
        if (oldFile.exists()) {
            if (oldFile.delete())
                System.out.println("删除成功");
            else
                System.out.println("删除失败");
        } else {
            System.out.println("文件不存在");
        }
    }

    /**
     * 重命名
     */
    private static void rename(String oldFileStr, String newFileStr) {
        File oldFile = new File(oldFileStr);
        File newFile = new File(newFileStr);

        if (oldFile.renameTo(newFile))
            System.out.println("重命名成功");
        else
            System.out.println("重命名失败");

    }

    /**
     * 提示
     */
    private static void outputTip(int i) {
        String tip = "";
        switch (i) {
            case 0:
                tip = "输入操作符 : -r(重命名)/-d(删除)/-m(创建)";
                break;
            case 1:
                tip = "输入原始文件";
                break;
            case 2:
                tip = "输入新文件";
                break;
        }
        System.out.println(tip);
    }

    /**
     * 递归遍历指定文件夹下的文件
     */
    private static void two(String filePath) {
        File mainFile = new File(filePath);
        File[] files = mainFile.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    two(file.getAbsolutePath());
                } else {
                    System.out.println(file.toString() + " TotalSpace : " + (float) file.length() / 1024 / 1024 + "MB");
                }
            }
        }
    }

    /**
     * 递归遍历指定文件夹下的文件路径名
     */
    private static void one(String filePath) {
        File file = new File(filePath);
//        Pattern pattern = Pattern.compile(".*\\.mp4");
//        String[] pathList = file.list((dir, name) -> pattern.matcher(name).matches());
        String[] pathList = file.list();
        if (pathList != null) {
            for (String path : pathList) {
                File subFile = new File(filePath + "\\" + path);
                if (subFile.isDirectory()) {
                    one(subFile.getAbsolutePath());
                } else {
                    System.out.println(subFile.getAbsolutePath() + " -> size : " + (float) subFile.length() / 1024 / 1024 + "MB");
                }
            }
        }
    }
}
