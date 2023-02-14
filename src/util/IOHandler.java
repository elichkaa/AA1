package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class IOHandler {
    private static final String FILE_PATH = "./src/util/Pixel-Art.txt";
    private IOHandler() {}

    public static void printPixelArt(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            for (String line; (line = br.readLine()) != null;) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException exception) {
            System.out.println(ErrorMessage.PIXEL_ART_FILE_DOES_NOT_EXIST);
        }
    }
}
