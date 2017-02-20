package disc.mods.core.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigBoolean extends ConfigProperty<Boolean>
{

	public ConfigBoolean(String Name, Boolean DefaultValue, ConfigCategory Category)
	{
		super(Name, DefaultValue, Category);
	}

	@Override
	public void load(Configuration config)
	{
		this.Value = this.getProperty(config).getBoolean();
	}

	@Override
	protected Property getProperty(Configuration config)
	{
		return config.get(this.Category.Name, this.Name, this.DefaultValue, this.Comment);
	}

}
