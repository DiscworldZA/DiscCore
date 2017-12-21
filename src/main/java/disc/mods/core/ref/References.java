package disc.mods.core.ref;

public class References {
	public static class Mod {
		public static final String Id = "disccore";
		public static final String Name = "DiscCore";
		public static final String Version = "0.1 Alpha";
	}

	public static class Proxy {
		public static final String Common = "disc.mods.core.proxy.CommonProxy";
		public static final String Server = "disc.mods.core.proxy.ServerProxy";
		public static final String Client = "disc.mods.core.proxy.ClientProxy";
	}

	public static final class NBT {
		public static final String Items = "dc.nbt.items";
		public static final String CustomName = "dc.nbt.customname";
		public static final String Direction = "direction";
		public static final String Owner = "dc.nbt.owner";
	}
}
