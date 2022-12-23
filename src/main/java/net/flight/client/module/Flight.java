package net.flight.client.module;

import com.google.common.eventbus.Subscribe;
import net.flight.client.event.events.PacketSendEvent;
import net.flight.client.event.events.TickEvent;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.lwjgl.glfw.GLFW;

public class Flight extends Module {
    int timer = 0;
    public Flight() {
        super("Flight", "fly", Category.MOTION);
        this.setKey(GLFW.GLFW_KEY_G);
    }

    @Override
    public void onTick(TickEvent e) {
        mc.player.getAbilities().allowFlying = true;
        mc.player.getAbilities().setFlySpeed(0.2f);
    }

    @Override
    public void onDisable() {
        mc.player.getAbilities().allowFlying = false;
        mc.player.getAbilities().flying = false;
    }

    @Subscribe
    public void onSendPacket(PacketSendEvent e){
        if(e.getPacket() instanceof PlayerMoveC2SPacket){
            timer++;
        }
        if(e.getPacket() instanceof PlayerMoveC2SPacket && timer > 5) {
            e.cancel();
            PlayerMoveC2SPacket packet = new PlayerMoveC2SPacket.Full(mc.player.getX(), mc.player.getY() - 0.04, mc.player.getZ(), 0, 0, false);
            mc.getNetworkHandler().sendPacket(packet);
            timer = 0;
        }
    }
}
