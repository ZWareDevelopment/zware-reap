package me.zihasz.zware.api.auth.utils;

import java.io.IOException;
import java.net.NetworkInterface;

public class NIC {
    public String name;
    public byte[] address;
    public boolean isVirtual;

    public NIC(String name, byte[] address, boolean isVirtual) {
        this.name = name;
        this.address = address;
        this.isVirtual = isVirtual;
    }

    public NIC(NetworkInterface ni) throws IOException {
        this(ni.getDisplayName(), ni.getHardwareAddress(), ni.isVirtual());
    }
}
