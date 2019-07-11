package game.map.visible.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.map.visible.AbstractVisibleMapInfo;
import game.scene.npc.reource.NpcResource;
import utils.snow.IdUtil;

/**
 * @author : ddv
 * @since : 2019/7/5 下午2:51
 */

public class NpcVisibleInfo extends AbstractVisibleMapInfo {

    private static Logger logger = LoggerFactory.getLogger(NpcVisibleInfo.class);

    private long id;

    private String name;

    private boolean occupying = false;

    public static NpcVisibleInfo valueOf(NpcResource resource) {
        NpcVisibleInfo npcVisibleInfo = new NpcVisibleInfo();
        npcVisibleInfo.id = IdUtil.getLongId();
        npcVisibleInfo.name = resource.getNpcName();
        npcVisibleInfo.init(resource.getX(), resource.getY());
        return npcVisibleInfo;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getAccountId() {
        return "npc";
    }

    public boolean isOccupying() {
        return occupying;
    }

    public void setOccupying(boolean occupying) {
        this.occupying = occupying;
    }

    public String getName() {
        return this.name;
    }
}
