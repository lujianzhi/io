package skywang.PipedOutputStream_PipedInputStream;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * Created by Ian.Lu on 2018/3/9.
 * Project : IO
 */

class SendPiped extends Thread {

    private PipedOutputStream pipedOutputStream;

    public SendPiped(String name) {
        super(name);
        pipedOutputStream = new PipedOutputStream();
    }

    public PipedOutputStream getPipedOutputStream() {
        return pipedOutputStream;
    }

    @Override
    public void run() {
        super.run();
//        sendMessageOnce();
        sendLongMessage();
    }

    private void sendMessageOnce() {
        try {
            String message = "a这是" + getName() + "发送的消息";
            pipedOutputStream.write(message.getBytes());
            pipedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendLongMessage() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1020; i++) {
            sb.append("0123456789");
        }
        sb.append("abcdefghijklmnopqrstuvwxyz");
        String sbStr = sb.toString();
        try {
            pipedOutputStream.write(sbStr.getBytes());
            pipedOutputStream.close();
            System.out.println(getName() + "写入完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
