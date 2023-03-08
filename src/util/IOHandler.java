package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class IOHandler {
    private static final String FILE_PATH = "./src/util/Pixel-Art.txt";
    private static final String PIXEL_ART_FILE_DOES_NOT_EXIST = "The file with the pixel art appears to be deleted. Restore the file.";
    private IOHandler() {}

    public static void printPixelArt(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException exception) {
            System.out.println(PIXEL_ART_FILE_DOES_NOT_EXIST);
        }
    }
}
