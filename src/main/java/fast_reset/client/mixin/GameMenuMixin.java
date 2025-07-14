package fast_reset.client.mixin;

import fast_reset.client.Client;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuMixin extends Screen {
    protected GameMenuMixin(Text title) {
        super(title);
    }

    @Shadow
    protected abstract void disconnect();

    @Inject(method = "initWidgets", at = @At("TAIL"))
    private <T extends Element & Drawable & Selectable> void addFastResetWidget(CallbackInfo ci) {
        int height = 20;
        int width = 0;
        int x = 0;
        int y = 0;
        switch (Client.buttonLocation) {
            // bottom right build
            case 0 -> {
                width = 102;
                x = this.width - width - 4;
                y = this.height - height - 4;
            }
            // center build
            case 1 -> {
                width = 204;
                x = this.width / 2 - width / 2;
                y = this.height / 4 + 148 - height;
            }
        }

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.quitWorld"), (buttonWidgetX) -> {
            Client.saveOnQuit = false;
            this.disconnect();
            Client.saveOnQuit = true;
        }).dimensions(x, y, width, height).build());
    }
}
