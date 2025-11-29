package hodytrapl.serverDecorator.CommandList;

import hodytrapl.serverDecorator.PlayerDataEducator.PlayerDataManager;
import hodytrapl.serverDecorator.RoleDataEducator.RoleDataConfig;
import hodytrapl.serverDecorator.RoleDataEducator.RoleFormatData;
import hodytrapl.serverDecorator.ServerDecorator;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoleCommand{

    private final ServerDecorator plugin;

    public RoleCommand(ServerDecorator plugin) {
        this.plugin = plugin;

    }

}