package disc.mods.core.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigString extends ConfigProperty<String>
{

    public ConfigString(String Name, String DefaultValue, ConfigCategory Category)
    {
        super(Name, DefaultValue, Category);
    }

    @Override
    public void load(Configuration config)
    {
        this.Value = this.getProperty(config).getString();
    }

    @Override
    protected Property getProperty(Configuration config)
    {
        return config.get(this.Category.Name, this.Name, this.DefaultValue, this.Comment);
    }
}
