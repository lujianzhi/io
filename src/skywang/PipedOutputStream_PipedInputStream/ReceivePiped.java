package skywang.PipedOutputStream_PipedInputStream;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * Created by Ian.Lu on 2018/3/9.
 * Project : IO
 */

class ReceivePiped extends Thread {

    private PipedInputStream pipedInputStream;

    public ReceivePiped(String name) {
        super(name);
        pipedInputStream = new PipedInputStream();
    }

    public PipedInputStream getPipedInputStream() {
        return pipedInputStream;
    }

    @Override
    public void run() {
        super.run();
//        receiveOnce();
        receiveAll();
    }

    private void receiveOnce() {
        byte[] data = new byte[2048];
        try {
            int length = pipedInputStream.read(data);
            String contentStr = new String(data, 0, length);
            System.out.println(getName() + "收到的消息 : " + contentStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveAll() {
        byte[] data = new byte[1024];
        StringBuilder sb = new StringBuilder();
        try {
            while (true) {
                if (pipedInputStream.read() == -1) {
                    System.out.println(getName() + "接受完毕");
                    break;
                }

                int length = pipedInputStream.read(data, 0, data.length);
                sb.append(new String(data, 0, length) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
    }
}
