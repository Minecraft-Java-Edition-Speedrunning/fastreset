package fast_reset.client.mixin;

import net.minecraft.client.resource.language.TranslationStorage;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

import java.util.Map;

@Mixin(TranslationStorage.class)
public class TranslationStorageMixin {
    @ModifyArg(
            method = "load(Lnet/minecraft/resource/ResourceManager;Ljava/util/List;Z)Lnet/minecraft/client/resource/language/TranslationStorage;",
            at = @At(value = "INVOKE", target = "Ljava/util/Map;copyOf(Ljava/util/Map;)Ljava/util/Map;")
    )
    private static Map<String, String> addQuitToTitleTranslation2(Map<String, String> map) {
        map.putIfAbsent("menu.quitWorld", "Quit to Title");
        return map;
    }
}
