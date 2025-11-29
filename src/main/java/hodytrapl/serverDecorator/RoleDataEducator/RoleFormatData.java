package hodytrapl.serverDecorator.RoleDataEducator;

import net.kyori.adventure.text.format.NamedTextColor;

public class RoleFormatData {
    private final String role;
    private final NamedTextColor nameColor;
    private final NamedTextColor subcolor;
    private final String badge;
    private final RoleDataConfig dataManager;

    public RoleFormatData(String role, NamedTextColor nameColor, NamedTextColor subcolor, String badge, RoleDataConfig dataManager) {
        this.role = role;
        this.nameColor = nameColor;
        this.subcolor = subcolor;
        this.badge = badge;
        this.dataManager = dataManager;
    }

    // Геттеры
    public String getRole() {
        return role;
    }

    public NamedTextColor getNameColor() {
        return nameColor;
    }

    public NamedTextColor getSubcolor() {
        return subcolor;
    }

    public String getBadge() {
        return badge;
    }

    public RoleDataConfig getDataManager() {
        return dataManager;
    }
}