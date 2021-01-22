package me.zihasz.zware.impl.module.misc;

import me.zihasz.zware.api.module.Module;
import me.zihasz.zware.api.setting.Setting;
import net.minecraft.network.EnumConnectionState;

public class Test extends Module {
    Setting<Float> floatSetting =                               register(new Setting<Float>("TestFloat", "TestFloat", 1.0f, 0.0f, 10.0f));
    Setting<Integer> integerSetting =                           register(new Setting<Integer>("TestInteger", "TestInteger", 1, 0, 10));
    Setting<EnumConnectionState> enumConnectionStateSetting =   register(new Setting<EnumConnectionState>("TestEnum", "TestEnum", EnumConnectionState.LOGIN));
    Setting<String> stringSetting =                             register(new Setting<String>("TestString", "TestString", "ASD"));
    Setting<Boolean> booleanSetting =                           register(new Setting<Boolean>("BooleanTest", "BooleanTest", true));

    public Test() {
        super("Test", "test", Category.MISC);
    }
}
