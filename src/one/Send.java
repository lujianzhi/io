package one;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * Created by Ian.Lu on 2017/8/3.
 * Project : IO
 */
public class Send implements Runnable {

    private PipedOutputStream out;

    public Send() {
        this.out = new PipedOutputStream();
    }

    public PipedOutputStream getOut() {
        return out;
    }

    @Override
    public void run() {
        try {
            String message = "你好我是Send";
            out.write(message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
