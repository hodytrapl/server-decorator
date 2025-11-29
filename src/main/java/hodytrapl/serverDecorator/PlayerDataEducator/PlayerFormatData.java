package hodytrapl.serverDecorator.PlayerDataEducator;

import hodytrapl.serverDecorator.RoleDataEducator.RoleFormatData;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class PlayerFormatData {
    private NamedTextColor nameColor;
    private NamedTextColor subcolor;
    private String badge;
    private Player player;
    private final PlayerDataManager dataManager;
    private final RoleFormatData roleData;

    public PlayerFormatData(NamedTextColor nameColor, Player player, NamedTextColor subcolor,
                            String badge, PlayerDataManager dataManager, RoleFormatData roleData) {
        this.nameColor = nameColor;
        this.subcolor = subcolor;
        this.badge = badge;
        this.player = player;
        this.dataManager = dataManager;
        this.roleData = roleData;
    }

    // Геттеры
    public NamedTextColor getNameColor() {
        return nameColor;
    }

    public NamedTextColor getSubcolor() {
        return subcolor;
    }

    public String getBadge() {
        return badge;
    }

    public Player getPlayer() {
        return player;
    }

    public RoleFormatData getRoleData() {
        return roleData;
    }

    // Сеттеры с автосохранением
    public void setNameColor(NamedTextColor nameColor) {
        this.nameColor = nameColor;
        if (dataManager != null) {
            //dataManager.savePlayerData(this);
        }
    }

    public void setSubcolor(NamedTextColor subcolor) {
        this.subcolor = subcolor;
        if (dataManager != null) {
            //dataManager.savePlayerData(this);
        }
    }

    public void setBadge(String badge) {
        this.badge = badge;
        if (dataManager != null) {
            //dataManager.savePlayerData(this);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}