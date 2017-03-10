package disc.mods.core.tile;

import disc.mods.core.ref.Names;
import disc.mods.core.ref.References;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class CoreTileEntity extends TileEntity implements ITickable
{
    public String name;
    public String customName;
    public String owner;

    public CoreTileEntity()
    {
    }

    public abstract void Register();

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(Names.NBT.CustomName))
        {
            this.customName = nbtTagCompound.getString(Names.NBT.CustomName);
        }

        if (nbtTagCompound.hasKey(Names.NBT.Owner))
        {
            this.owner = nbtTagCompound.getString(Names.NBT.Owner);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        if (this.hasCustomName())
        {
            nbtTagCompound.setString(Names.NBT.CustomName, customName);
        }

        if (this.hasOwner())
        {
            nbtTagCompound.setString(Names.NBT.Owner, owner);
        }
        return nbtTagCompound;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name, String ModID)
    {
        this.name = "container." + ModID + "." + name;
    }

    public String getCustomName()
    {
        return customName;
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public boolean hasCustomName()
    {
        return customName != null && customName.length() > 0;
    }

    public boolean hasOwner()
    {
        return owner != null && owner.length() > 0;
    }
}
