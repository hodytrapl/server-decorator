package hodytrapl.serverDecorator.Misc;
import hodytrapl.serverDecorator.Misc.fileManager.ConfigManager;

public class Misc {


    public static String getStatusColor(ConfigManager confM,int value, int minCritera, int midCriteria){
        if(value<=minCritera){
            return confM.getTerribleColor();
        }else if(value<=midCriteria){
            return confM.getWarningColor();
        }else{
            return confM.getGoodColor();
        }

    }
    public String getInvertetStatusColor(ConfigManager confM,int value, int minCritera, int midCriteria){
        if(value>minCritera){
            return confM.getTerribleColor();
        }else if(value>midCriteria){
            return confM.getWarningColor();
        }else{
            return confM.getGoodColor();
        }

    }

    public static String convertMinecraftColorToHex(String minecraftColor) {
        if (minecraftColor == null || minecraftColor.length() < 2) {
            return "#FFFFFF"; // Белый по умолчанию
        }

        char colorCode = minecraftColor.charAt(1);

        switch (colorCode) {
            case '0': return "#000000"; // Чёрный
            case '1': return "#0000AA"; // Тёмно-синий
            case '2': return "#00AA00"; // Тёмно-зелёный
            case '3': return "#00AAAA"; // Тёмно-голубой
            case '4': return "#AA0000"; // Тёмно-красный
            case '5': return "#AA00AA"; // Фиолетовый
            case '6': return "#FFAA00"; // Золотой
            case '7': return "#AAAAAA"; // Серый
            case '8': return "#555555"; // Тёмно-серый
            case '9': return "#5555FF"; // Синий
            case 'a': return "#55FF55"; // Зелёный
            case 'b': return "#55FFFF"; // Голубой
            case 'c': return "#FF5555"; // Красный
            case 'd': return "#FF55FF"; // Розовый
            case 'e': return "#FFFF55"; // Жёлтый
            case 'f': return "#FFFFFF"; // Белый
            default: return "#FFFFFF"; // Белый по умолчанию
        }
    }
}
