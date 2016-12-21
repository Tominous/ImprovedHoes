package furgl.improvedHoes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ImprovedHoes.MODID, name = ImprovedHoes.MODNAME, version = ImprovedHoes.VERSION, guiFactory = "furgl.improvedHoes.ImprovedHoesGuiFactory", updateJSON = "https://raw.githubusercontent.com/Furgl/ImprovedHoes/1.11/update.json")
public class ImprovedHoes
{
	public static final String MODID = "improvedhoes";
	public static final String MODNAME = "Improved Hoes";
	public static final String VERSION = "1.1";

	@EventHandler 
	public void preInit(FMLPreInitializationEvent event)
	{
		Config.init(event.getSuggestedConfigurationFile());	
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(new ConfigChangedEvents());
		MinecraftForge.EVENT_BUS.register(new UseHoeEvents()); 
		MinecraftForge.EVENT_BUS.register(new PlayerInteractEvents()); 
		MinecraftForge.EVENT_BUS.register(new HarvestCheckEvents());
	}

	public static boolean isRegisteredHoe(ItemStack stack)
	{
		if (Config.radius0Hoes.contains(stack.getDisplayName()) || 
				Config.radius1Hoes.contains(stack.getDisplayName()) ||
				Config.radius2Hoes.contains(stack.getDisplayName()) ||
				Config.radius3Hoes.contains(stack.getDisplayName()))
			return true;
		return false;
	}

	public static int calculateRadius(ItemStack stack) 
	{
		int radius = 0;
		if (Config.radius0Hoes.contains(stack.getDisplayName()))
			radius = 0;
		else if (Config.radius1Hoes.contains(stack.getDisplayName()))
			radius = 1;
		else if (Config.radius2Hoes.contains(stack.getDisplayName()))
			radius = 2;
		else if (Config.radius3Hoes.contains(stack.getDisplayName()))
			radius = 3;
		return radius;
	}
}

