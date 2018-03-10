package skywang.ObjectInputStream_ObjectOutputStream;

import java.io.Serializable;

/**
 * Created by Ian.Lu on 2018/3/9.
 * Project : IO
 */

class Box implements Serializable {
    private int width;
    private int height;
    private String name;

    public Box(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "[" + name + ": (" + width + ", " + height + ") ]";
    }
}
