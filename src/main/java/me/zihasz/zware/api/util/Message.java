package me.zihasz.zware.api.util;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.TextComponentString;

public class Message {
    private static final EntityPlayerSP player = Minecraft.getMinecraft().player;

    private static final String clientPrefix = ChatFormatting.GRAY + "[" +  ChatFormatting.GREEN    + "ZWare" + ChatFormatting.DARK_GRAY + "." + ChatFormatting.GREEN + "cc" + ChatFormatting.GRAY + "] " + ChatFormatting.RESET;
    private static final String alertPrefix = ChatFormatting.GRAY + "["  + ChatFormatting.YELLOW   + "ALERT" + ChatFormatting.GRAY + "] ";
    private static final String warningPrefix = ChatFormatting.GRAY + "["  + ChatFormatting.RED + "ERROR" + ChatFormatting.GRAY + "] ";
    private static final String errorPrefix = ChatFormatting.GRAY + "["  + ChatFormatting.DARK_RED + "ERROR" + ChatFormatting.GRAY + "] ";
    private static final String combatPrefixStart = ChatFormatting.GRAY + "["  + ChatFormatting.DARK_GREEN;
    private static final String combatPrefixEnd = ChatFormatting.GRAY + "] " + ChatFormatting.RESET;

    public static void sendRaw(String message) {
        player.sendMessage(new TextComponentString(message));
    }
    public static void sendClientMessage(String message) {
        sendRaw(clientPrefix + message);
    }
    public static void sendAlertMessage(String message) {
        sendClientMessage(alertPrefix + message);
    }
    public static void sendWarningMessage(String message) {
        sendClientMessage(warningPrefix + message);
    }
    public static void sendErrorMessage(String message) {
        sendClientMessage(errorPrefix + message);
    }
    public static void sendModuleMessage(String module, String message) {
        sendClientMessage(combatPrefixStart + module + combatPrefixEnd + message);
    }
    public static void sendCustomPrefixMessage(String prefix, String message) {
        sendClientMessage(ChatFormatting.GRAY + "[" + ChatFormatting.LIGHT_PURPLE + prefix + ChatFormatting.GRAY + "] " + ChatFormatting.RESET + message);
    }
}
