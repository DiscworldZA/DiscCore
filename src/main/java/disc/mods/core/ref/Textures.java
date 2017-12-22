package disc.mods.core.ref;

import disc.mods.core.util.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public class Textures {
	public static class Gui {
		public static final ResourceLocation BaseGui = ResourceLocationHelper.getGuiLocation(References.Mod.Id,
				"BaseGUI.png");
		public static final ResourceLocation Slot = ResourceLocationHelper.getGuiLocation(References.Mod.Id,
				"Slot.png");
		public static final ResourceLocation PowerBar = ResourceLocationHelper.getGuiLocation(References.Mod.Id,
				"PowerBar.png");
		public static final ResourceLocation LiquidBar = ResourceLocationHelper.getGuiLocation(References.Mod.Id,
				"LiquidBar.png");
		public static final ResourceLocation LiquidBarOverlay = ResourceLocationHelper.getGuiLocation(References.Mod.Id,
				"LiquidBarOverlay.png");
	}
}
