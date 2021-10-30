package tech.justcoding.smash.ingame;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import tech.justcoding.smash.Main;

import java.util.HashMap;

public class Knockback implements Listener {
    public static HashMap<Player, Integer> playerPercentages = new HashMap<>();
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            event.setDamage(0);
            Player target = (Player) event.getEntity();
            Player player = (Player) event.getDamager();

            if (!playerPercentages.containsKey(target)) {
                playerPercentages.put(target, 0);
            }
            target.setVelocity(target.getLocation().getDirection().multiply(
                    (playerPercentages.get(target) / 100) + 0.15
            ));
            playerPercentages.put(target, playerPercentages.get(player) + Main.random.nextInt(10) + 10);
        }
    }
}
