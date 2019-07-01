package game.user.equip.base.condition;

import java.util.Map;

import game.user.player.model.Player;

/**
 * @author : ddv
 * @since : 2019/7/1 下午8:39
 */

public abstract class AbstractConditionProcessor {

    protected Map<Object, Integer> conditionParams;

    /**
     * 检查逻辑
     *
     * @param player
     * @return
     */
    public abstract boolean doVerify(Player player);

	public Map<Object, Integer> getConditionParams() {
		return conditionParams;
	}

	public void setConditionParams(Map<Object, Integer> conditionParams) {
		this.conditionParams = conditionParams;
	}

	public AbstractConditionProcessor(Map<Object, Integer> conditionParams) {
		this.conditionParams = conditionParams;
	}
}
