package game.scene.map.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import game.base.map.IMap;
import game.common.Ii8n;
import game.common.exception.RequestException;
import game.scene.map.resource.NoviceVillage;
import game.scene.map.resource.StormWind;

/**
 * 地图配置先在这里写死,等待后续增加静态资源功能后修改 1: 新手村
 *
 * @author : ddv
 * @since : 2019/5/7 上午10:34
 */
@Component
public class SceneMapManager {

    private Map<Long, IMap> sceneMaps = new ConcurrentHashMap<>();

    // 对应玩家-存在地图状态表
    private Map<Long, Long> playerMaps = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        IMap noviceVillage = NoviceVillage.valueOf(1L, 6, 6);
        sceneMaps.put(noviceVillage.getCurrentMapId(), noviceVillage);
        sceneMaps.put(2L, StormWind.valueOf(2, 9, 9));
    }

    public IMap getMapByMapId(long mapId) {
        IMap map = sceneMaps.get(mapId);

        if (map == null) {
            RequestException.throwException(Ii8n.MAP_NOT_EXIST);
        }
        return map;
    }

    public Map<Long, IMap> getSceneMaps() {
        return sceneMaps;
    }

    public boolean existInMap(long playerId) {
        return playerMaps.containsKey(playerId);
    }

    public Map<Long, Long> getPlayerMaps() {
        return playerMaps;
    }

    public Long getPlayerInMap(long playerId) {
        return playerMaps.get(playerId);
    }

}
