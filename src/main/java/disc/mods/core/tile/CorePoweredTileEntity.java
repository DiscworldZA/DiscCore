package disc.mods.core.tile;

import disc.mods.core.util.EnumSide;
import net.minecraft.util.NonNullList;
import net.minecraftforge.energy.IEnergyStorage;

public abstract class CorePoweredTileEntity extends CoreTileEntityInventory implements IEnergyStorage {

	private int maxEnergy = 0;
	private int currentEnergy = 0;
	private int inputRate = 0;
	private int outputRate = 0;

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	public void setInputRate(int inputRate) {
		this.inputRate = inputRate;
	}

	public void setOutputRate(int outputRate) {
		this.outputRate = outputRate;
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		if (maxReceive > inputRate) maxReceive = inputRate;
		if (maxReceive + currentEnergy > maxEnergy) {
			int received = maxReceive + currentEnergy - maxEnergy;
			if (!simulate) currentEnergy = maxEnergy;
			return received;
		}
		else {
			if (!simulate) currentEnergy += maxReceive;
			return maxReceive;
		}
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		if (maxExtract > outputRate) maxExtract = outputRate;
		if (currentEnergy - maxExtract < 0) {
			int extracted = currentEnergy;
			if (!simulate) currentEnergy = 0;
			return extracted;
		}
		else {
			if (!simulate) currentEnergy -= maxExtract;
			return maxExtract;
		}
	}

	@Override
	public int getEnergyStored() {
		return currentEnergy;
	}

	@Override
	public int getMaxEnergyStored() {
		return maxEnergy;
	}

}
