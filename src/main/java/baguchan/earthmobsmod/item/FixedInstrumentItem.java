package baguchan.earthmobsmod.item;

import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.InstrumentItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;

public class FixedInstrumentItem extends InstrumentItem {
	private TagKey<Instrument> instruments;

	public FixedInstrumentItem(Item.Properties tab, TagKey<Instrument> goatHorns) {
		super(tab, goatHorns);
		this.instruments = goatHorns;
	}

	public void fillItemCategory(CreativeModeTab p_220105_, NonNullList<ItemStack> p_220106_) {
		if (this.allowedIn(p_220105_)) {
			for (Holder<Instrument> holder : Registry.INSTRUMENT.getTagOrEmpty(this.instruments)) {
				p_220106_.add(create(this, holder));
			}
		}
	}

	@Override
	public UseAnim getUseAnimation(ItemStack p_220133_) {
		return UseAnim.TOOT_HORN;
	}
}
