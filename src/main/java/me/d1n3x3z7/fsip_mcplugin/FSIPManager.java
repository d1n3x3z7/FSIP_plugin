package me.d1n3x3z7.fsip_mcplugin;

import com.sun.org.apache.xerces.internal.xs.StringList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static me.d1n3x3z7.fsip_mcplugin.FSIP_MCPlugin.cprint;
import static me.d1n3x3z7.fsip_mcplugin.FSIP_MCPlugin.manager;

public class FSIPManager implements CommandExecutor, TabCompleter {
    private final Plugin plugin;

    public FSIPManager(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (commandSender.isOp()) {
            switch (args[0]) {
                case "close":
                    manager.close();
                    commandSender.sendMessage("If there was a connection, it is now closed");
                    return true;
                case "open":
                    try {
                        manager.open();
                        commandSender.sendMessage("Your connection - " + manager.getConnect());
                    } catch (IOException e) {
                        cprint(String.valueOf(e));
                    }
                    return true;
                case "get":
                    try {
                        commandSender.sendMessage("R u rlly forgot?? Oki - " + manager.getConnect());
                    } catch (IOException e) {
                        cprint(String.valueOf(e));
                    }
                    return true;
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.isOp()) {
            List<String> list = Arrays.asList("close", "open", "get");
            if (strings.length == 0) {
                return list;
            }
            return list.stream().filter(line -> line.startsWith(strings[0])).collect(Collectors.toList());
        }
        return null;
    }

}
