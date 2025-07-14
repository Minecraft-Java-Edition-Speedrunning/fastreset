package fast_reset.client.mixin;

import net.minecraft.client.resource.language.TranslationStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Map;

@Mixin(TranslationStorage.class)
public class TranslationStorageMixin {
    @ModifyArg(
            method = "load(Lnet/minecraft/resource/ResourceManager;Ljava/util/List;)Lnet/minecraft/client/resource/language/TranslationStorage;",
            at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap;copyOf(Ljava/util/Map;)Lcom/google/common/collect/ImmutableMap;")
    )
    private static Map<String, String> addQuitToTitleTranslation(Map<String, String> map) {
        map.putIfAbsent("menu.quitWorld", "Quit to Title");
        return map;
    }
}
