package dev.astro.net.utilities.chat;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import dev.astro.net.utilities.HoverAction;
import dev.astro.net.utilities.Trans;
import net.minecraft.server.v1_7_R4.ChatClickable;
import net.minecraft.server.v1_7_R4.ChatComponentText;
import net.minecraft.server.v1_7_R4.ChatHoverable;
import net.minecraft.server.v1_7_R4.ChatModifier;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.EnumChatFormat;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.ItemStack;
import net.minecraft.server.v1_7_R4.NBTTagCompound;
import net.minecraft.server.v1_7_R4.Packet;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;

@SuppressWarnings("deprecation")
public class ChatUtil {
	public static String getName(ItemStack stack) {
		if (stack.tag != null && stack.tag.hasKeyOfType("display", 10)) {
			NBTTagCompound nbttagcompound = stack.tag.getCompound("display");
			if (nbttagcompound.hasKeyOfType("Name", 8)) {
				return nbttagcompound.getString("Name");
			}
		}
		return stack.getItem().a(stack) + ".name";
	}

	public static Trans localFromItem(org.bukkit.inventory.ItemStack stack) {
		if (stack.getType() == Material.POTION && stack.getData().getData() == 0) {
			Potion potion = Potion.fromItemStack(stack);
			if (potion != null) {
				PotionType type = potion.getType();
				if (type != null && type != PotionType.WATER) {
					String effectName = (potion.isSplash() ? "Splash " : "")
							+ WordUtils.capitalizeFully(type.name().replace('_', ' ')) + " L" + potion.getLevel();
					return fromItemStack(stack).append(" of " + effectName);
				}
			}
		}
		return fromItemStack(stack);
	}

	public static Trans fromItemStack(org.bukkit.inventory.ItemStack stack) {
		ItemStack nms = CraftItemStack.asNMSCopy(stack);
		NBTTagCompound tag = new NBTTagCompound();
		nms.save(tag);
		return new Trans(getName(nms), new Object[0]).setColor(ChatColor.getByChar(nms.w().e.getChar()))
				.setHover(HoverAction.SHOW_ITEM, (IChatBaseComponent) new ChatComponentText(tag.toString()));
	}

	public static void reset(IChatBaseComponent text) {
		ChatModifier modifier = text.getChatModifier();
		modifier.a((ChatHoverable) null);
		modifier.setChatClickable((ChatClickable) null);
		modifier.setBold(false);
		modifier.setColor(EnumChatFormat.RESET);
		modifier.setItalic(false);
		modifier.setRandom(false);
		modifier.setStrikethrough(false);
		modifier.setUnderline(false);
	}

	public static void send(CommandSender sender, IChatBaseComponent text) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			PacketPlayOutChat packet = new PacketPlayOutChat(text, true);
			EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
			entityPlayer.playerConnection.sendPacket((Packet) packet);
		} else {
			sender.sendMessage(text.c());
		}
	}
}