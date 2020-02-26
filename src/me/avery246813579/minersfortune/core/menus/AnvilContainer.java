package me.avery246813579.minersfortune.core.menus;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.minecraft.server.v1_7_R4.ContainerAnvil;
import net.minecraft.server.v1_7_R4.EntityHuman;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.PacketPlayOutOpenWindow;


public class AnvilContainer extends ContainerAnvil
{
  public AnvilContainer(EntityHuman entity)
  {
    super(entity.inventory, entity.world, 0, 0, 0, entity);
  }

  public static Inventory openAnvil(Player player) {
    EntityPlayer p = ((CraftPlayer)player).getHandle();
    AnvilContainer container = new AnvilContainer(p);

    int c = p.nextContainerCounter();
    p.playerConnection.sendPacket(new PacketPlayOutOpenWindow(c, 8, "Repairing", 9, true));
    p.activeContainer = container;
    p.activeContainer.windowId = c;
    p.activeContainer.addSlotListener(p);

    return container.getBukkitView().getTopInventory();
  }

  public boolean a(EntityHuman entityhuman)
  {
    return true;
  }
}