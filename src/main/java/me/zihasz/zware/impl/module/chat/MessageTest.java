package me.zihasz.zware.impl.module.chat;

import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.api.util.Message;

public class MessageTest extends Module {
    public MessageTest() {
        super("MessageTest", "Test messages", Category.CHAT);
    }

    @Override
    public void onEnable() {
        Message.sendClientMessage("ClientPrefixTest");
        Message.sendAlertMessage("AlertTest");
        Message.sendWarningMessage("WarningTest");
        Message.sendErrorMessage("ErrorTest");
        Message.sendModuleMessage(getName(), "ModuleText");
        Message.sendCustomPrefixMessage("CustomMessageTest", "CustomTest");
    }
}
