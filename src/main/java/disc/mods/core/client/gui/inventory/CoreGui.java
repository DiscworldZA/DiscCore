package disc.mods.core.client.gui.inventory;

import disc.mods.core.inventory.CoreContainer;
import disc.mods.core.ref.Textures;
import disc.mods.core.tile.CoreTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public abstract class CoreGui extends GuiContainer {
	protected CoreTileEntity tile;

	protected InventoryPlayer player;

	public CoreGui(CoreContainer container) {
		super(container);
	}

	public void setTile(CoreTileEntity tile) {
		this.tile = tile;
	}

	public <T> T GetTileEntity() {
		try {
			return (T) tile;
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		if (this.tile != null) this.drawTitle(tile.getName());
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(Textures.Gui.BaseGui);
		int startX = (width - xSize) / 2;
		int startY = (height - ySize) / 2;
		this.drawTexturedModalRect(startX, startY, 0, 0, xSize, ySize);

	}

	public void drawTitle(String title) {
		fontRenderer.drawString(title, 8, 8, Color.darkGray.getRGB());
	}

	public void drawSlot(int xOffset, int yOffset) {
		this.mc.getTextureManager().bindTexture(Textures.Gui.Slot);
		int startX = ((width - xSize) / 2) - 1 + xOffset;
		int startY = ((height - ySize) / 2) - 1 + yOffset;
		this.drawTexturedModalRect(startX, startY, 0, 0, xSize, ySize);
	}


}
