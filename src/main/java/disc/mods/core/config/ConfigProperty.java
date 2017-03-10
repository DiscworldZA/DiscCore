package disc.mods.core.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public abstract class ConfigProperty<T>
{
    public String Name;
    public T Value;
    public T DefaultValue;
    public ConfigCategory Category;
    public T[] DefaultValues;
    public String Comment;

    public ConfigProperty(String Name, T DefaultValue, ConfigCategory Category)
    {
        this.Name = Name;
        this.DefaultValue = DefaultValue;
        this.Category = Category;
        this.Comment = "[default: " + this.DefaultValue + "]";
    }

    public abstract void load(Configuration config);

    protected abstract Property getProperty(Configuration config);

    public ConfigProperty<T> SetComment(String Comment)
    {
        this.Comment = Comment + " [default: " + this.DefaultValue + "]";
        return this;
    }
}
