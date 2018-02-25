import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class config {

    public static File announce = new File("plugins/netCast/announcements.yml");
    public static FileConfiguration a = YamlConfiguration.loadConfiguration(announce);
    public static File config = new File("plugins/netCast/config.yml");
    public static FileConfiguration c = YamlConfiguration.loadConfiguration(config);
    public static File motd = new File("plugins/netCast/motd.yml");
    public static FileConfiguration m = YamlConfiguration.loadConfiguration(motd);

    public static void setup(){
        if (!new File("plugins/netCast").exists()){
            new File("plugins/netCast").mkdir();
        }
        if (!config.exists()){
            try{
                config.createNewFile();

                c.set("reload", "&anetCast&c >> Reloaded all data files!");

                saveConfig();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if (!announce.exists()){
            try{
                announce.createNewFile();
               a.set("prefix", "&c[&a&lPREFIX&c]");
               a.set("interval_minutes", 1);
               a.set("no_message", "&cCouldn't find valid message, check config.");
                ArrayList list = new ArrayList();
                list.add("%prefix% &aMessage 1");
                list.add("&bMessage 2");
                list.add("%prefix% &cMessage 3");
                a.set("messages", list);
                a.set("top", "This is the top message! Set this to nothing if you don't wanna use it. Like the bottom.");
                a.set("bottom", "");
                saveAnnounce();

            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if (!motd.exists()){
            try{
                motd.createNewFile();

                ArrayList list2 = new ArrayList();
                list2.add("&aThis is line 1");
                list2.add("&bThis is line 2");
                m.set("motd", list2);
                saveMotd();

            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void saveAnnounce(){
        try{
            a.save(announce);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void loadAnnounce(){
        a = YamlConfiguration.loadConfiguration(announce);
    }
    public static void saveConfig(){
        try{
            c.save(config);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void loadConfig(){
        c = YamlConfiguration.loadConfiguration(config);
    }
    public static void saveMotd(){
        try{
            m.save(motd);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void loadMotd(){
        m = YamlConfiguration.loadConfiguration(motd);
    }
}