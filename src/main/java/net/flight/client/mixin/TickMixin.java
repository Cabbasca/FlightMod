package net.flight.client.mixin;

import net.flight.client.Flight;
import net.flight.client.event.events.TickEvent;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class TickMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void onTick(CallbackInfo ci){
        TickEvent event = new TickEvent();
        Flight.INSTANCE.getBus().post(event);
        if(event.isCancelled()){
            ci.cancel();
        }
    }
}
