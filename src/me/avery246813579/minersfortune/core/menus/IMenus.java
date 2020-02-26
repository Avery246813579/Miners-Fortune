package me.avery246813579.minersfortune.core.menus;

import me.avery246813579.minersfortune.core.menus.vanity.TrailMenu;
import me.avery246813579.minersfortune.core.menus.vanity.mount.ArmorMenu;
import me.avery246813579.minersfortune.core.menus.vanity.mount.ColorMenu;
import me.avery246813579.minersfortune.core.menus.vanity.mount.MountMenu;
import me.avery246813579.minersfortune.core.menus.vanity.mount.NameMenu;
import me.avery246813579.minersfortune.core.menus.vanity.mount.StrengthMenu;
import me.avery246813579.minersfortune.core.menus.vanity.mount.StyleMenu;
import me.avery246813579.minersfortune.core.menus.vanity.mount.VariantMenu;

public class IMenus {
	public static TrailMenu trailMenu = new TrailMenu();
	
	/** Mount Menus **/
	public static MountMenu mountMenu = new MountMenu();
	public static VariantMenu variantMenu = new VariantMenu();
	public static ColorMenu colorMenu = new ColorMenu();
	public static StyleMenu styleMenu = new StyleMenu();
	public static ArmorMenu armorMenu = new ArmorMenu();
	public static StrengthMenu strengthMenu = new StrengthMenu();
	public static NameMenu nameMenu = new NameMenu();
}
