package game.user.player.model;

import game.base.game.attribute.model.PlayerAttributeContainer;
import game.base.object.AbstractCreature;
import game.user.equip.model.EquipStorage;
import game.user.pack.model.Pack;
import game.user.player.entity.PlayerEnt;
import spring.SpringContext;
import utils.IdUtil;

/**
 * 做业务的对象
 *
 * @author : ddv
 * @since : 2019/5/6 下午8:51
 */

public class Player extends AbstractCreature<Player> {

    private String accountId;

    private long playerId;

    private int level;
    /**
     * 黄金
     */
    private int gold;
    /**
     * 当前地图id
     */
    private int currentMapId;

    private boolean changingMap;

    private Player() {}

    // 初始给1000黄金
    public static Player valueOf(String accountId) {
        Player player = new Player();
        player.accountId = accountId;
        player.playerId = IdUtil.getLongId();
        player.level = 1;
        player.gold = 1000;
        player.changingMap = false;
        player.setAttributeContainer(new PlayerAttributeContainer(player));
        return player;
    }

    @Override
    public PlayerAttributeContainer getAttributeContainer() {
        return (PlayerAttributeContainer)super.getAttributeContainer();
    }

    public Pack getPack() {
        return SpringContext.getPackService().getPlayerPack(this, false);
    }

    public PlayerEnt getPlayerEnt() {
        return SpringContext.getPlayerService().getPlayerEnt(this);
    }

    public EquipStorage getEquipStorage() {
        return SpringContext.getEquipService().getEquipStorage(this);
    }

    // get and set
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public boolean isChangingMap() {

        return changingMap;
    }

    public void setChangingMap(boolean changingMap) {
        this.changingMap = changingMap;
    }

    public int getCurrentMapId() {
        return currentMapId;
    }

    public void setCurrentMapId(int currentMapId) {
        this.currentMapId = currentMapId;
    }

    @Override
    public String toString() {
        return "Player{" + "accountId='" + accountId + '\'' + ", playerId=" + playerId + ", level=" + level + '}';
    }

}
