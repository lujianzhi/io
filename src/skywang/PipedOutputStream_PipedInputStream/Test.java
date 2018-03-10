package skywang.PipedOutputStream_PipedInputStream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by Ian.Lu on 2018/3/9.
 * Project : IO
 */

class Test {

    public static void main(String[] args) {
        SendPiped sendPiped = new SendPiped("发送线程");
        ReceivePiped receivePiped = new ReceivePiped("接受线程");

        PipedOutputStream pipedOutputStream = sendPiped.getPipedOutputStream();
        PipedInputStream pipedInputStream = receivePiped.getPipedInputStream();
        try {
            //将PipedOutputStream和PipedInputStream连接起来
            pipedOutputStream.connect(pipedInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        sendPiped.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        receivePiped.start();
    }
}
