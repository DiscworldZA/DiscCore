package disc.mods.core.block;

import disc.mods.core.creativetab.Tabs;

public class TestBlock extends CoreBlock {

	public TestBlock() {
		super("testblock", "testblock");
		this.setCreativeTab(Tabs.BlocksTab);
	}

	public boolean canRotate() {
		return true;
	}
}
