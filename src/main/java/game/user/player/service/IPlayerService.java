package game.user.player.service;

import game.user.player.entity.PlayerEnt;
import game.user.player.model.Player;
import game.user.player.resource.PlayerResource;

/**
 * @author : ddv
 * @since : 2019/5/14 下午8:29
 */

public interface IPlayerService {
    /**
     * 根据账户id获取player
     *
     * @param accountId
     * @return
     */
    Player getPlayerByAccountId(String accountId);

    /**
     * 加载玩家属性容器
     *
     * @param player
     */
    void loadPlayerAttribute(Player player);

    /**
     * 玩家增加经验
     *
     * @param player
     * @param exception
     */
    void addException(Player player, long exception);

    /**
     * 玩家升级并且重新计算属性与同步
     *
     * @param player
     */
    void playerLevelUp(Player player);

    /**
     * 保存玩家信息
     *
     * @param playerEnt
     */
    void savePlayer(PlayerEnt playerEnt);

    /**
     * 获取玩家资源文件
     *
     * @param id
     * @return
     */
    PlayerResource getResource(int id);

    /**
     * 获取玩家信息
     *
     * @param player
     * @return
     */
    PlayerEnt getPlayerEnt(Player player);
}
