package dev.astro.net.utilities.chat;

import org.bukkit.*;
import java.util.*;

public class CC
{
    public static String translate(String s){
        return ChatColor.translateAlternateColorCodes('&', s).replace("»", "\u00BB");
    }

    public static List<String> translate(List<String> list) {
        List<String> buffered = new ArrayList<String>();

        list.forEach(string -> buffered.add(translate(string).replace("»","\u00BB")));
        return buffered;
    }

    public static String[] translate(String... strings) {
        return translate(Arrays.asList(strings)).stream().toArray(String[]::new);
    }
}
