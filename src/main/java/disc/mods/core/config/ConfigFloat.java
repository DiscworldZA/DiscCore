package disc.mods.core.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigFloat extends ConfigProperty<Float>
{

    public ConfigFloat(String Name, float DefaultValue, ConfigCategory Category)
    {
        super(Name, DefaultValue, Category);
    }

    @Override
    public void load(Configuration config)
    {
        this.Value = (float) this.getProperty(config).getDouble();
    }

    @Override
    protected Property getProperty(Configuration config)
    {
        return config.get(this.Category.Name, this.Name, this.DefaultValue, this.Comment);
    }
}
