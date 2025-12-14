package hodytrapl.serverDecorator.Misc;

import net.kyori.adventure.text.format.NamedTextColor;

public class Misc {

    public static String getStatusColor(int value, int minCritera, int midCriteria){
        if(value<=minCritera){
            return "§c";
        }else if(value<=midCriteria){
            return "§e";
        }else{
            return "§a";
        }

    }
    public static String getInvertetStatusColor(int value, int minCritera, int midCriteria){
        if(value>minCritera){
            return "§c";
        }else if(value>midCriteria){
            return "§e";
        }else{
            return "§a";
        }

    }
}
