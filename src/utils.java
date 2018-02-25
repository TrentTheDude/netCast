import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class utils {

    public static String color(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static int toTicks(int minutes){
        int use = minutes*60; //seconds
        use = use*20;//ticks
        return use;
    }
    public static String readC(String key){
        String value = config.c.getString(key);
        return value;
    }
    public static String readM(String key){
        String value = config.m.getString(key);
        return value;
    }
    public static String readA(String key){
        String value = config.a.getString(key);
        return value;
    }
    public static void broadcast(String string){
        String top = readA("top");
        String bottom = readA("bottom");
        for (Player p : Bukkit.getOnlinePlayers()){
            if (!top.equalsIgnoreCase("")){
                p.sendMessage(color(top));
            }
            p.sendMessage(color(string.replace("%prefix%", readA("prefix")).replace("%line%", "\n").replace("%player%", p.getName()).replace("%online%", Bukkit.getOnlinePlayers().size()+"").replace("%total%", Bukkit.getMaxPlayers()+"")));
            if (!bottom.equalsIgnoreCase("")){
                p.sendMessage(color(bottom));
            }
        }
    }
    public static List<String> messages(){
        List<String> list = config.a.getStringList("messages");
        return list;
    }

    public static int current = 0;

    public static String currentMessage(){
        String use = "";
        if (messages().size() >= 0 && messages().get(current) != null && !messages().get(current).equalsIgnoreCase("")){//has messages
            use = messages().get(current);
        }else{
            use = readA("no_message");
        }
        return use;
    }
}