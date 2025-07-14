package fast_reset.client.mixin;

import net.minecraft.client.resource.language.TranslationStorage;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

import java.util.Map;

@Mixin(TranslationStorage.class)
public class TranslationStorageMixin {
    @Group(min = 1, max = 1)
    @ModifyArg(
            method = "load(Lnet/minecraft/resource/ResourceManager;Ljava/util/List;Z)Lnet/minecraft/client/resource/language/TranslationStorage;",
            at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap;copyOf(Ljava/util/Map;)Lcom/google/common/collect/ImmutableMap;"),
            require = 0
    )
    private static Map<String, String> addQuitToTitleTranslation(Map<String, String> map) {
        map.putIfAbsent("menu.quitWorld", "Quit to Title");
        return map;
    }

    @Group
    @Dynamic
    @ModifyArg(
            method = "load(Lnet/minecraft/resource/ResourceManager;Ljava/util/List;Z)Lnet/minecraft/client/resource/language/TranslationStorage;",
            at = @At(value = "INVOKE", target = "Ljava/util/Map;copyOf(Ljava/util/Map;)Ljava/util/Map;"),
            require = 0
    )
    private static Map<String, String> addQuitToTitleTranslation2(Map<String, String> map) {
        map.putIfAbsent("menu.quitWorld", "Quit to Title");
        return map;
    }
}
