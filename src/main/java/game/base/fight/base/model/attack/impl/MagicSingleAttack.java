package game.base.fight.base.model.attack.impl;

import game.base.fight.base.model.attack.ActionTypeEnum;
import game.base.fight.base.model.attack.BaseActionEntry;
import game.base.fight.model.pvpunit.BaseCreatureUnit;
import game.base.skill.model.BaseSkill;

/**
 * 魔法伤害性单体攻击技能
 *
 * @author : ddv
 * @since : 2019/7/16 5:03 PM
 */

public class MagicSingleAttack extends BaseActionEntry {

    public MagicSingleAttack(BaseCreatureUnit attacker, BaseCreatureUnit defender, BaseSkill skill, long value) {
        super(attacker, defender, skill, value, ActionTypeEnum.Magic_Attack);
    }

    public static MagicSingleAttack valueOf(BaseCreatureUnit attacker, BaseCreatureUnit defender, BaseSkill skill,
        long value) {
        MagicSingleAttack attack = new MagicSingleAttack(attacker, defender, skill, value);
        return attack;
    }

    @Override
    public void doActive() {
        defenders.forEach(creatureUnit -> creatureUnit.defend(this));
    }
}
