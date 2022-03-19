package dev.astro.net.utilities.chat;

import net.minecraft.server.v1_7_R4.ChatClickable;
import net.minecraft.server.v1_7_R4.ChatComponentText;
import net.minecraft.server.v1_7_R4.ChatHoverable;
import net.minecraft.server.v1_7_R4.EnumChatFormat;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.astro.net.utilities.HoverAction;
import dev.astro.net.utilities.Trans;

public class Text extends ChatComponentText {
  public Text() {
    super("");
  }
  
  public Text(String string) {
    super(string);
  }
  
  public Text(Object object) {
    super(String.valueOf(object));
  }
  
  public static Trans fromItemStack(ItemStack stack) {
    return ChatUtil.fromItemStack(stack);
  }
  
  public Text append(Object object) {
    return append(String.valueOf(object));
  }
  
  public Text append(String text) {
    return (Text)a(text);
  }
  
  public Text append(IChatBaseComponent node) {
    return (Text)addSibling(node);
  }
  
  public Text append(IChatBaseComponent... nodes) {
    byte b;
    int i;
    IChatBaseComponent[] arrayOfIChatBaseComponent;
    for (i = (arrayOfIChatBaseComponent = nodes).length, b = 0; b < i; ) {
      IChatBaseComponent node = arrayOfIChatBaseComponent[b];
      addSibling(node);
      b++;
    } 
    return this;
  }
  
  public Text localText(ItemStack stack) {
    return append((IChatBaseComponent)ChatUtil.localFromItem(stack));
  }
  
  public Text appendItem(ItemStack stack) {
    return append((IChatBaseComponent)ChatUtil.fromItemStack(stack));
  }
  
  public Text setBold(boolean bold) {
    getChatModifier().setBold(Boolean.valueOf(bold));
    return this;
  }
  
  public Text setItalic(boolean italic) {
    getChatModifier().setItalic(Boolean.valueOf(italic));
    return this;
  }
  
  public Text setUnderline(boolean underline) {
    getChatModifier().setUnderline(Boolean.valueOf(underline));
    return this;
  }
  
  public Text setRandom(boolean random) {
    getChatModifier().setRandom(Boolean.valueOf(random));
    return this;
  }
  
  public Text setStrikethrough(boolean strikethrough) {
    getChatModifier().setStrikethrough(Boolean.valueOf(strikethrough));
    return this;
  }
  
  public Text setColor(ChatColor color) {
    getChatModifier().setColor(EnumChatFormat.valueOf(color.name()));
    return this;
  }
  
  public Text setClick(ClickAction action, String value) {
    getChatModifier().setChatClickable(new ChatClickable(action.getNMS(), value));
    return this;
  }
  
  public Text setHover(HoverAction action, IChatBaseComponent value) {
    getChatModifier().a(new ChatHoverable(action.getNMS(), value));
    return this;
  }
  
  public Text setHoverText(String text) {
    return setHover(HoverAction.SHOW_TEXT, (IChatBaseComponent)new Text(text));
  }
  
  public Text reset() {
    ChatUtil.reset((IChatBaseComponent)this);
    return this;
  }
  
  public IChatBaseComponent f() {
    return (IChatBaseComponent)h();
  }
  
  public String toRawText() {
    return c();
  }
  
  public void send(CommandSender sender) {
    ChatUtil.send(sender, (IChatBaseComponent)this);
  }
  
  public void broadcast() {
    broadcast((String)null);
  }
  
  public void broadcast(String permission) {
    for (Player player : Bukkit.getOnlinePlayers()) {
      if (permission == null || player.hasPermission(permission))
        send((CommandSender)player); 
    } 
    send((CommandSender)Bukkit.getConsoleSender());
  }
}
