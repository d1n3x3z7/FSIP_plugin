package me.d1n3x3z7.fsip_mcplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class FSIP_MCPlugin extends JavaPlugin implements Listener {

    public static SSHManager manager;

    public static void cprint(String l) {
        Bukkit.getLogger().info(l);
    }

    public static void nprint(String l) {
        Bukkit.getConsoleSender().sendMessage(l);
    }

    @Override
    public void onEnable() {
        // get port
        Server srv = getServer();

        int Iport = srv.getPort();
        manager = new SSHManager(Integer.toString(Iport));
        try {
            manager.open();
            while (manager.getConnect() == null) {cprint("Waiting connection...");}
            nprint(ChatColor.DARK_GREEN+"  |==========================================|");
            nprint(ChatColor.DARK_GREEN+"  |------ FSIP STARTED ----- SUCCESS --------|");
            nprint(ChatColor.GREEN+"       ADDRESS: " + ChatColor.DARK_PURPLE + ">>> " + manager.getConnect() + " <<<");
            nprint(ChatColor.DARK_GREEN+"  |---------------- XYZ1.RU -----------------|");
            nprint(ChatColor.DARK_GREEN+"  |==========================================|");
        } catch (IOException e) {
            cprint(String.valueOf(e));
        }

        srv.getPluginManager().registerEvents(this, this);
        srv.getPluginCommand("fsip").setExecutor(new FSIPManager(this));

    }

    @Override
    public void onDisable() {
        manager.close();
    }
}
