package game.base.buff.model.impl;

import game.base.buff.model.BaseCreatureBuff;
import game.base.buff.model.BuffParamEnum;

/**
 * @author : ddv
 * @since : 2019/7/24 5:35 PM
 */

public class PoisonCycleBuff extends BaseCycleBuff {
    /**
     * 层数
     */
    private int level;
    /**
     * 伤害
     */
    private long damage;

    @Override
    protected void doInit() {
        level = context.getParam(BuffParamEnum.POISON_LEVEL);
        damage = context.getParam(BuffParamEnum.POISON_DAMAGE);
        modifyContext();
    }

    private void modifyContext() {
        this.context.addParam(BuffParamEnum.POISON_LEVEL, level);
        this.context.addParam(BuffParamEnum.POISON_DAMAGE, damage);
    }

    @Override
    public void merge(BaseCreatureBuff buff) {
        super.merge(buff);
        if (buff instanceof PoisonCycleBuff) {
            PoisonCycleBuff newBuff = (PoisonCycleBuff)buff;
            this.level += newBuff.level;
            this.damage += newBuff.damage;

            modifyContext();
        }
    }

}
