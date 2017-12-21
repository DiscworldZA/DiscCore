package disc.mods.core.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigInt extends ConfigProperty<Integer> {
	public int Min;
	public int Max;

	public ConfigInt(String Name, int DefaultValue, ConfigCategory Category) {
		super(Name, DefaultValue, Category);
		this.Min = DefaultValue;
		this.Max = DefaultValue;
	}

	public ConfigInt(String Name, int DefaultValue, ConfigCategory Category, int Max, int Min) {
		super(Name, DefaultValue, Category);
		this.Min = Min;
		this.Max = Max;
	}

	@Override
	protected Property getProperty(Configuration config) {
		return config.get(Category.Name, Name, DefaultValue, Comment, Min, Max);
	}

	@Override
	public void load(Configuration config) {
		this.Value = this.getProperty(config).getInt();
	}

}
