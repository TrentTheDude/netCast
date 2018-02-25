import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {
        getCommand("netCast").setExecutor(this);
        Bukkit.getLogger().info("ENABLING netCast!");
        config.setup();
        timer();
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("DISABLING netCast!");
    }

    public void timer(){
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                //broadcast current message

                if (utils.current < utils.messages().size()){
                    utils.broadcast(utils.currentMessage());
                    utils.current++;
                }else{
                    utils.current = 0;
                    utils.broadcast(utils.currentMessage());
                    utils.current++;
                }
            }
        },0, utils.toTicks(Integer.parseInt(utils.readA("interval_minutes"))));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("netCast")){
            if (sender.hasPermission("netCast.admin")){
                if (args.length == 1){
                    if (args[0].equalsIgnoreCase("reload")){
                        //reload
                        config.loadConfig();
                        config.loadMotd();
                        config.loadAnnounce();
                        sender.sendMessage(utils.color(utils.readC("reload")));
                    }
                }else{
                    sender.sendMessage(utils.color("&AnetCast&c >> &aDeveloped by: &9Aysteria Development"));
                }
            }else{
                sender.sendMessage(utils.color("&AnetCast&c >> &aDeveloped by: &9Aysteria Development"));
            }
        }
        return false;
    }
    @EventHandler
    public void ping(ServerListPingEvent e) {
        config.loadMotd();
        List<String> motd = config.m.getStringList("motd");
        if (!motd.isEmpty()) {
            String line1 = motd.get(0) + "\n";
            String line2 = motd.get(1);
            e.setMotd(utils.color(line1 + line2));
        }
    }
}