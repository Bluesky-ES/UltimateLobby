package me.bluesky.plugin.ultimatelobby.utils;

//    public static boolean works;
//    public static String nmsver;
//    private static Class<?> classCraftPlayer;
//    private static Class<?> classPacketChat;
//    private static Class<?> classChatSerializer;
//    private static Class<?> classIChatComponent;
//    private static Method methodSeralizeString;
//    private static Class<?> classChatComponentText;
//    private static Method methodGetHandle;
//    private static Field fieldConnection;
//    private static Method methodSendPacket;
//
//    static {
//        try {
//            nmsver = org.bukkit.Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
//            classCraftPlayer = Class.forName("org.bukkit.craftbukkit." + nmsver + ".entity.CraftPlayer");
//            classPacketChat = Class.forName("net.minecraft.server." + nmsver + ".PacketPlayOutChat");
//            Class<?> classPacket = Class.forName("net.minecraft.server." + nmsver + ".Packet");
//            if (nmsver.equalsIgnoreCase("v1_8_R1") || nmsver.equalsIgnoreCase("v1_7_")) {
//                classChatSerializer = Class.forName("net.minecraft.server." + nmsver + ".ChatSerializer");
//                classIChatComponent = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
//                methodSeralizeString = classChatSerializer.getDeclaredMethod("a", String.class);
//            } else {
//                classChatComponentText = Class.forName("net.minecraft.server." + nmsver + ".ChatComponentText");
//                classIChatComponent = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
//            }
//            methodGetHandle = classCraftPlayer.getDeclaredMethod("getHandle");
//            Class<?> classEntityPlayer = Class.forName("net.minecraft.server." + nmsver + ".EntityPlayer");
//            fieldConnection = classEntityPlayer.getDeclaredField("playerConnection");
//            Class<?> classPlayerConnection = Class.forName("net.minecraft.server." + nmsver + ".PlayerConnection");
//            methodSendPacket = classPlayerConnection.getDeclaredMethod("sendPacket", classPacket);
//            works = true;
//        } catch (Exception e) {
//            works = false;
//        }
//    }
//
//    public static void sendActionBar(Player player, String message) {
//        if (!works) return;
//        try {
//            Object p = classCraftPlayer.cast(player);
//            Object ppoc;
//            if (nmsver.equalsIgnoreCase("v1_8_R1") || nmsver.equalsIgnoreCase("v1_7_")) {
//                Object cbc = classIChatComponent.cast(methodSeralizeString.invoke(classChatSerializer, "{\"text\": \"" + message + "\"}"));
//                ppoc = classPacketChat.getConstructor(new Class<?>[]{classIChatComponent, byte.class}).newInstance(cbc, (byte) 2);
//            } else {
//                Object o = classChatComponentText.getConstructor(new Class<?>[]{String.class}).newInstance(message);
//                ppoc = classPacketChat.getConstructor(new Class<?>[]{classIChatComponent, byte.class}).newInstance(o, (byte) 2);
//            }
//            Object h = methodGetHandle.invoke(p);
//            Object pc = fieldConnection.get(h);
//            methodSendPacket.invoke(pc, ppoc);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            works = false;
//        }
//    }

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.utils.nms.ReflectionUtils;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Objects;

public class ActionBarUtils {

    private static final JavaPlugin PLUGIN = JavaPlugin.getProvidingPlugin(Main.class);
    private static final MethodHandle CHAT_COMPONENT_TEXT;
    private static final MethodHandle PACKET;
    private static final Object CHAT_MESSAGE_TYPE;

    static {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        Class<?> packetPlayOutChatClass = ReflectionUtils.getNMSClass("PacketPlayOutChat");
        Class<?> iChatBaseComponentClass = ReflectionUtils.getNMSClass("IChatBaseComponent");

        MethodHandle packet = null;
        MethodHandle chatComp = null;
        Object chatMsgType = null;

        try {
            Class<?> chatMessageTypeClass = Class.forName("net.minecraft.server." + ReflectionUtils.VERSION + ".ChatMessageType");
            for (Object obj : chatMessageTypeClass.getEnumConstants()) {
                if (obj.toString().equals("GAME_INFO")) {
                    chatMsgType = obj;
                    break;
                }
            }

            Class<?> chatComponentTextClass = ReflectionUtils.getNMSClass("ChatComponentText");
            chatComp = lookup.findConstructor(chatComponentTextClass, MethodType.methodType(void.class, String.class));

            packet = lookup.findConstructor(packetPlayOutChatClass, MethodType.methodType(void.class, iChatBaseComponentClass, chatMessageTypeClass));
        } catch (NoSuchMethodException | IllegalAccessException | ClassNotFoundException ignored) {
            try {
                chatMsgType = (byte) 2;

                Class<?> chatComponentTextClass = ReflectionUtils.getNMSClass("ChatComponentText");
                chatComp = lookup.findConstructor(chatComponentTextClass, MethodType.methodType(void.class, String.class));

                packet = lookup.findConstructor(packetPlayOutChatClass, MethodType.methodType(void.class, iChatBaseComponentClass, byte.class));
            } catch (NoSuchMethodException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }

        CHAT_MESSAGE_TYPE = chatMsgType;
        CHAT_COMPONENT_TEXT = chatComp;
        PACKET = packet;
    }

    public static void sendActionBar(Player player, String message) {
        Objects.requireNonNull(player, "Cannot send action bar to null player");
        Object packet = null;

        try {
            Object component = CHAT_COMPONENT_TEXT.invoke(message);
            packet = PACKET.invoke(component, CHAT_MESSAGE_TYPE);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        ReflectionUtils.sendPacket(player, packet);
    }
}
