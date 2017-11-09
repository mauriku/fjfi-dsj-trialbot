package cz.cvut.fjfi.ksi.dsj.trialbot.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

  public static String loadFile(String fileName) throws IOException {
    File f = new File(fileName);
    FileReader fr = new FileReader(f);
    char[] buffer = new char[(int) f.length()];
    fr.read(buffer);
    return new String(buffer);
  }
  
}
