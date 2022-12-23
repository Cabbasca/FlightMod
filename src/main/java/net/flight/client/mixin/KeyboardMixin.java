package net.flight.client.mixin;

import net.flight.client.Flight;
import net.flight.client.event.events.KeyEvent;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    public void key(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci){
        KeyEvent keyy = new KeyEvent(key);
        if(action == 1){
            Flight.INSTANCE.getBus().post(keyy);
        }
        if(keyy.isCancelled()){
            ci.cancel();
        }
    }
}
