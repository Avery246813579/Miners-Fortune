package me.avery246813579.minersfortune.core.menus;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftInventoryAnvil;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TextInputHelper
  implements Listener
{
  private static final ItemStack BLANK = new ItemStack(Material.NAME_TAG);

  private final Map<Player, TextInputHandler> viewingMenu = new HashMap<Player, TextInputHandler>();

  static
  {
    ItemMeta meta = BLANK.getItemMeta();
    meta.setDisplayName("Enter Text");

    BLANK.setItemMeta(meta);
  }

  public void requestInput(Player player, TextInputHandler handler) {
    player.closeInventory();

    player.setLevel(player.getLevel() + 7);
    Inventory inventory = AnvilContainer.openAnvil(player);
    inventory.setItem(0, BLANK);
    this.viewingMenu.put(player, handler);
  }

  @EventHandler
  public void onClose(InventoryCloseEvent event) {
    if (!(event.getPlayer() instanceof Player)) {
      return;
    }

    Player player = (Player)event.getPlayer();
    TextInputHandler handler = (TextInputHandler)this.viewingMenu.remove(player);
    if (handler != null) {
      player.setLevel(player.getLevel() - 7);

      event.getInventory().setItem(0, null);
      handler.cancelled();
    }
  }

  @EventHandler
  public void onClick(InventoryClickEvent event) {
    if (!(event.getWhoClicked() instanceof Player)) {
      return;
    }

    Player player = (Player)event.getWhoClicked();

    if (this.viewingMenu.containsKey(player)) {
      event.setCancelled(true);

      CraftInventoryAnvil inventory = (CraftInventoryAnvil)event.getInventory();
      ItemStack item = CraftItemStack.asBukkitCopy(inventory.getResultInventory().getItem(0));

      if (item.getType() == Material.AIR) {
        return;
      }

      event.getInventory().setItem(0, null);

      String text = item.getItemMeta().getDisplayName();
      TextInputHandler handler = (TextInputHandler)this.viewingMenu.remove(player);
      handler.textEntered(text);
      player.closeInventory();
    }
  }
}