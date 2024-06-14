package me.d1n3x3z7.fsip_mcplugin;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class FSIP_MCPlugin extends JavaPlugin implements Listener {

    public static SSHManager manager;

    public static void cprint(String l) {
        Bukkit.getLogger().info(l);
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
            cprint("==========================================");
            cprint("------ FSIP STARTED ----- SUCCESS --------");
            cprint("DOMAIN ADDRESS: " + manager.getConnect());
            cprint("--------------- XYZ1.RU ------------------");
            cprint("==========================================");
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
