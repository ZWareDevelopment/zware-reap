package me.zihasz.zware.api.auth.utils;

import java.io.IOException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NICUtils {
    public static List<NIC> getAllNICInfo() throws IOException {
        List<NIC> NICs = new ArrayList<>();
        Collections.list(NetworkInterface.getNetworkInterfaces()).forEach(a -> {
            try {
                NICs.add(new NIC(a.getName(), a.getHardwareAddress(), a.isVirtual()));
            } catch (SocketException e) {
                e.printStackTrace();
            }
        });
        return NICs;
    }
}
