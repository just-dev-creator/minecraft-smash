package tech.justcoding.smash.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Config {
    private static YamlConfiguration configuration;
    private static File file;
    public static void registerConfiguration() {
        File dir = new File("./plugins/Smash");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        file = new File(dir, "config.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public static boolean contains(String path) {
        return configuration.contains(path);
    }
    public static void set(String path, Object value) {
        configuration.set(path, value);
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object get(String path) {
        try {
            return configuration.get(path);
        } catch (Exception e) {
            return null;
        }
    }

    public static Object get(String path, Object normalValue) {
        if (!Config.contains(path)) {
            Config.set(path, normalValue);
        }
        return contains(path);
    }

    public static String getString(String path) {
        try {
            return configuration.getString(path);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getString(String path, Object normalValue) {
        if (!Config.contains(path) || !Config.isString(path)) {
            Config.set(path, normalValue);
        }
        return configuration.getString(path);
    }

    public static boolean getBoolean(String path) {
        try {
            return configuration.getBoolean(path);
        } catch (Exception e) {
            return false;
        }
    }

    public static int getInt(String path, Object normalValue) {
        if (!Config.contains(path) || !Config.isInteger(path)) {
            Config.set(path, normalValue);
        }
        return configuration.getInt(path);
    }

    public static int getInt(String path) {
        return configuration.getInt(path);
    }

    public static boolean getBoolean(String path, Object normalValue) {
        if (!Config.contains(path) || !Config.isBoolean(path)) {
            Config.set(path, normalValue);
        }
        try {
            return getBoolean(path);
        } catch (Exception e) {
            return false;
        }
    }

    public static List<String> getStringList(String path) {
        return configuration.getStringList(path);
    }

    public static List<String> getStringList(String path, Object normalValue) {
        if (!Config.contains(path)) {
            Config.set(path, normalValue);
        }
        return getStringList(path);
    }

    public static boolean isBoolean(String path) {
        return configuration.isBoolean(path);
    }

    public static boolean isString(String path) {
        return configuration.isString(path);
    }

    public static boolean isInteger(String path) {
        return configuration.isInt(path);
    }

    public static void setLocation(String path, Location location) {
        configuration.set(path, getLocationString(location));
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Location getLocation(String path) {
        return getLocationFromString(configuration.getString(path));
    }

    private static String getLocationString(Location location) {
        return location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ() +
                ":" + location.getYaw() + ":" + location.getPitch();
//        ([a-zA-z]*):([0-9]*\.[0-9]*):([0-9]*\.[0-9]*):([0-9]*\.[0-9]*):(0|1)\.[0-9]*:(0|1)\.[0-9]*:
    }
    private static Location getLocationFromString(String string) {
        String[] elements = string.split(":");
        if (elements.length != 6) return null;
        World world = Bukkit.getWorld(elements[0]);
        Double x = Double.parseDouble(elements[1]);
        Double y = Double.parseDouble(elements[2]);
        Double z = Double.parseDouble(elements[3]);
        float yaw = Float.parseFloat(elements[4]);
        float pitch = Float.parseFloat(elements[5]);
        return new Location(world, x, y, z, yaw, pitch);
    }
}
