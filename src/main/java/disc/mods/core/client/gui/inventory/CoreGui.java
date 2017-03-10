package disc.mods.core.client.gui.inventory;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import disc.mods.core.ref.Textures;
import disc.mods.core.tile.CoreTileEntity;
import disc.mods.core.tile.CoreTileEntityInventory;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.client.FMLClientHandler;

public abstract class CoreGui extends GuiContainer
{
    protected CoreTileEntity tile;

    public CoreGui()
    {
        super(new Container()
        {

            @Override
            public boolean canInteractWith(EntityPlayer p_75145_1_)
            {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }

    public CoreGui(Container container, CoreTileEntityInventory tile)
    {
        super(container);
        this.tile = tile;
    }

    public abstract CoreGui NewInstance(InventoryPlayer player, CoreTileEntityInventory tile);

    public <T> T GetTileEntity()
    {
        try
        {
            return (T) tile;
        }
        catch (ClassCastException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        if (this.tile != null)
            this.drawTitle(tile.getName());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.Gui.BaseGui);
        int startX = (width - xSize) / 2;
        int startY = (height - ySize) / 2;
        this.drawTexturedModalRect(startX, startY, 0, 0, xSize, ySize);

    }

    public void drawTitle(String title)
    {
        fontRendererObj.drawString(title, 8, 8, Color.darkGray.getRGB());
    }

    public void drawLiquidBar(TileEntity tank, int xOffset, int yOffset)
    {
        this.mc.getTextureManager().bindTexture(Textures.Gui.LiquidBar);
        int startX = ((width - xSize) / 2) + xOffset;
        int startY = ((height - ySize) / 2) + yOffset;
        this.drawTexturedModalRect(startX, startY, 0, 0, xSize, ySize);
        if (tank != null)
        {
            if (tank instanceof IFluidHandler)
            {
                /*
                 * int meta = FMLClientHandler.instance().getClient().theWorld.
                 * getBlockState(tank.getPos()).; ForgeDirection facingDir =
                 * BlockOrientationHelper.getOrientation(meta); FluidTankInfo[]
                 * info = ((IFluidHandler) tank).getTankInfo(facingDir); if
                 * (info != null && info.length > 0) { FluidTankInfo tankInfo =
                 * info[0]; int tankCapacity = tankInfo.capacity; if
                 * (tankInfo.fluid != null && tankInfo.fluid.getFluid() != null)
                 * { Fluid fluid = tankInfo.fluid.getFluid(); IIcon fluidIcon =
                 * fluid.getIcon(); int amount = tankInfo.fluid.amount; int
                 * fluidHeight = MathHelper.ceiling_float_int((amount / (float)
                 * tankCapacity) * 48); this.mc.getTextureManager()
                 * .bindTexture(this.mc.getTextureManager().getResourceLocation(
                 * fluid.getSpriteNumber()));
                 * this.drawTexturedModelRectFromIcon(startX + 1, startY + 1,
                 * fluidIcon, 16, 16); this.drawTexturedModelRectFromIcon(startX
                 * + 1, startY + 1 + 16, fluidIcon, 16, 16);
                 * this.drawTexturedModelRectFromIcon(startX + 1, startY + 1 +
                 * 32, fluidIcon, 16, 16);
                 * this.mc.getTextureManager().bindTexture(Textures.Gui.
                 * LiquidBar); this.drawTexturedModalRect(startX, startY, 0, 0,
                 * 18, 49 - fluidHeight); } }
                 */
            }
        }
        this.mc.getTextureManager().bindTexture(Textures.Gui.LiquidBarOverlay);
        this.drawTexturedModalRect(startX, startY, 0, 0, xSize, ySize);
    }

    public void drawSlot(int xOffset, int yOffset)
    {
        this.mc.getTextureManager().bindTexture(Textures.Gui.Slot);
        int startX = ((width - xSize) / 2) - 1 + xOffset;
        int startY = ((height - ySize) / 2) - 1 + yOffset;
        this.drawTexturedModalRect(startX, startY, 0, 0, xSize, ySize);
    }
}
