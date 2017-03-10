package disc.mods.core.ref;

import disc.mods.core.config.ConfigBoolean;
import disc.mods.core.config.ConfigCategory;
import disc.mods.core.config.ConfigProperty;
import disc.mods.core.config.ConfigString;
import net.minecraftforge.common.config.Configuration;

public class CoreSettings
{
    public static class Debug
    {
        public static ConfigCategory Debug = new ConfigCategory("Debug");
        public static ConfigProperty<Boolean> EnableDebug = new ConfigBoolean("EnableDebug", false, Debug).SetComment("Enable Debug Mode");
        public static ConfigProperty<Boolean> EnableTestBlock = new ConfigBoolean("EnableTestBlock", false, Debug).SetComment("Enable Test Block");
        public static ConfigProperty<String> TestBlockName = new ConfigString("TestBlockName", "TestBlock", Debug).SetComment("Name of the Test Block");

        private static void Load(Configuration config)
        {
            EnableDebug.load(config);
            EnableTestBlock.load(config);
            TestBlockName.load(config);
        }
    }

    public static void Load(Configuration config)
    {
        Debug.Load(config);
    }
}
