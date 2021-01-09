package me.zihasz.zware.api.auth.methods.hwid;

import java.net.NetworkInterface;
import java.util.*;

import me.zihasz.zware.api.auth.encryption.SHA;

public class HWID {
    private static String hwid;

    public HWID() {
        try {
            hwid = genHWID();
        }
        catch (Exception ignored) {}
    }

    public static String genHWID() throws Exception {
        String data1 = System.getProperty("os.name") + System.getProperty("os.ver") + System.getProperty("os.arch");
        String data2 = System.getProperty("user.name");
        String data3 = Base64.getEncoder().encodeToString((data1 + "-" + data2).getBytes());
        int var1 = 0;

        List<Integer> list1 = new ArrayList<>();
        Collections.list(NetworkInterface.getNetworkInterfaces()).forEach(adapter -> {
            try {
                if (!adapter.isVirtual())
                    list1.add(
                        Arrays.hashCode(adapter.getHardwareAddress()) *
                        (adapter.getName().length() / adapter.getIndex())
                    );
            } catch (Exception ignored) {}
        });

        for (int p1 : list1) {
             var1 += p1 * data3.hashCode();
        }

        // byte[] temp2 = Integer.toString(temp).getBytes();
        // byte[] temp3 = SHA.hash(temp2);
        return Base64.getEncoder().encodeToString(SHA.hash(Integer.toString(var1).getBytes()));
    }

    public static String getHwid() {
        return hwid;
    }
}
