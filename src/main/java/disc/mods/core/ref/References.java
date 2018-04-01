package disc.mods.core.ref;

public class References {
	public static class Mod {
		public static final String Id = "disccore";
		public static final String Name = "Disc Core";
		public static final String Version = "@VERSION@";
		public static final String DependencyString = "";
	}

	public static class Proxy {
		private static final String root = "disc.mods.core.proxy.";
		public static final String Common = root + "CommonProxy";
		public static final String Server = root + "ServerProxy";
		public static final String Client = root + "ClientProxy";
	}

	public static final class NBT {
		public static final String Items = "dc.nbt.items";
		public static final String CustomName = "dc.nbt.customname";
		public static final String Direction = "direction";
		public static final String Owner = "dc.nbt.owner";
	}
}
