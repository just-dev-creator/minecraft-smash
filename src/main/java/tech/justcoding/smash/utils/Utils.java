package tech.justcoding.smash.utils;

import java.io.File;

public class Utils {
    public static boolean doesWorldFileExist(String name) {
        File file = new File(name);
        return file.exists();
    }
}
