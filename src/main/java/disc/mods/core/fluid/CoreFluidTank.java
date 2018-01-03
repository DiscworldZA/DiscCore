package disc.mods.core.fluid;

import javax.swing.plaf.SliderUI;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import scala.reflect.internal.HasFlags;

public abstract class CoreFluidTank implements IFluidTank {

	protected FluidStack fluidStack;

	@Override
	public FluidStack getFluid() {
		return fluidStack;
	}

	public boolean hasFluid() {
		return fluidStack != null;
	}

	public boolean isEmpty() {
		if (hasFluid()) {
			return this.getFluidAmount() == 0;
		}
		return true;
	}

	public int getOverflow(FluidStack stack) {
		if (stack.amount + getFluidAmount() > getCapacity()) {
			return getCapacity() - (stack.amount + getFluidAmount());
		}
		else {
			return 0;
		}
	}

	@Override
	public int getFluidAmount() {
		if (hasFluid())
			return getFluid().amount;
		else return 0;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(this);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (resource == null) return 0;
		if (!doFill) {
			if (getFluid() == null) {
				return Math.min(getCapacity(), resource.amount);
			}
			if (!getFluid().isFluidEqual(resource)) {
				return 0;
			}
			return Math.min(getCapacity() - getFluidAmount(), resource.amount);
		}
		if (!hasFluid()) {
			fluidStack = new FluidStack(resource, Math.min(getCapacity(), resource.amount));
			return getFluidAmount();
		}
		else if (!this.getFluid().isFluidEqual(resource)) {
			return 0;
		}

		int filled = getCapacity() - getFluidAmount();

		if (resource.amount < filled) {
			fluidStack.amount += resource.amount;
			filled = resource.amount;
		}
		else {
			fluidStack.amount = getCapacity();
		}
		return filled;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (fluidStack == null) {
			return null;
		}
		int drained = maxDrain;
		if (fluidStack.amount < drained) {
			drained = fluidStack.amount;
		}
		FluidStack stack = new FluidStack(fluidStack, drained);
		if (doDrain) {
			fluidStack.amount -= drained;
			if (fluidStack.amount <= 0) {
				fluidStack = null;
			}
		}
		return stack;
	}

}
