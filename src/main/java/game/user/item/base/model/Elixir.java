package game.user.item.base.model;

import java.util.Map;

import game.base.message.exception.RequestException;
import game.role.player.model.Player;
import game.user.item.base.constant.ItemEffectType;
import game.user.item.base.effect.IEffectProcessor;
import game.user.item.resource.ItemResource;

/**
 *
 * 经验仙丹
 *
 * @author : ddv
 * @since : 2019/6/26 上午10:23
 */

public class Elixir extends AbstractUsableItem {
    @Override
    public void verifyCanUseThrow(Player player, int num) throws RequestException {

    }

    @Override
    public boolean verifyCanUse(Player player, int num) {
        return true;
    }

    @Override
    public void useEffect(Player player, int num) {
        ItemResource resource = getResource();
        Map<String, Long> effectParam = resource.getEffectParam();
        effectParam.forEach((type, value) -> {
            IEffectProcessor processor = ItemEffectType.getProcessor(type);
            processor.invokeItemEffect(player, value);
        });
    }

    @Override
    public String toString() {
        return "Elixir{" + "configId=" + configId + ", num=" + num + '}';
    }
}
