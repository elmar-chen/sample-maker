package elmar.test.sample.maker;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class AA {
    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        for (int i = 0;; i++) {
            FileOutputStream os = new FileOutputStream("D:\\" + i + ".data");
            System.out.println("writing nth file: " + i);
            for (int j = 0; j < Integer.MAX_VALUE; j++) {
                byte[] bytes = new byte[1024 * 4];
                rand.nextBytes(bytes);
                os.write(bytes);
            }
            os.close();
        }
    }
}
