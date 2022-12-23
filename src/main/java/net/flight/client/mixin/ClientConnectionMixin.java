package net.flight.client.mixin;

import net.flight.client.Flight;
import net.flight.client.event.events.PacketSendEvent;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketCallbacks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    @Inject(method = "send(Lnet/minecraft/network/Packet;Lnet/minecraft/network/PacketCallbacks;)V", at = @At("HEAD"), cancellable = true)
    private void thekisser(Packet<?> packet, PacketCallbacks callbacks, CallbackInfo ci) {
        PacketSendEvent s = new PacketSendEvent(packet);
        Flight.INSTANCE.getBus().post(s);
        if (s.isCancelled()) {
            ci.cancel();
        }
    }
}
