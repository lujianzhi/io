package one;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * Created by Ian.Lu on 2017/8/3.
 * Project : IO
 */
public class Receive implements Runnable {

    private PipedInputStream pipedInputStream;

    public Receive() {
        this.pipedInputStream = new PipedInputStream();
    }

    public PipedInputStream getPipedInputStream() {
        return pipedInputStream;
    }

    @Override
    public void run() {
        try {
            byte[] datas = new byte[1024];
            int data = pipedInputStream.read(datas);
            pipedInputStream.close();
            System.out.println("接收到的内容 : " + new String(datas, 0, data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
