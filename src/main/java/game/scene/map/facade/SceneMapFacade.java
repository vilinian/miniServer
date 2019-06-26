package game.scene.map.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import game.common.I18N;
import game.common.exception.RequestException;
import game.common.packet.SM_Message;
import game.scene.map.packet.*;
import game.user.player.model.Player;
import middleware.anno.HandlerAnno;
import net.model.USession;
import net.utils.PacketUtil;
import spring.SpringContext;
import utils.SimpleUtil;

/**
 * 场景地图处理
 *
 * @author : ddv
 * @since : 2019/5/7 下午12:11
 */
@Component
public class SceneMapFacade {

    private static final Logger logger = LoggerFactory.getLogger(SceneMapFacade.class);

    /**
     * 玩家进入地图
     *
     * @param session
     * @param request
     */
    @HandlerAnno
    public void enterMap(USession session, CM_EnterMap request) {
        try {
            Player player = SimpleUtil.getPlayerFromSession(session);
            SpringContext.getSceneMapService().enterMap(player, request.getMapId());
        } catch (RequestException e) {
            PacketUtil.send(session, SM_Message.valueOf(e.getErrorCode()));
        } catch (Exception e) {
            PacketUtil.send(session, SM_Message.valueOf(I18N.SERVER_ERROR));
            e.printStackTrace();
        }
    }

    /**
     * 玩家离开地图
     *
     * @param session
     * @param request
     */
    @HandlerAnno
    public void leaveMap(USession session, CM_LeaveMap request) {
        try {
            Player player = SimpleUtil.getPlayerFromSession(session);
            SpringContext.getSceneMapService().leaveMap(player, request.getMapId());
        } catch (RequestException e) {
            PacketUtil.send(session, SM_Message.valueOf(e.getErrorCode()));
        } catch (Exception e) {
            PacketUtil.send(session, SM_Message.valueOf(I18N.SERVER_ERROR));
            e.printStackTrace();
        }
    }

    /**
     * 玩家地图内移动
     *
     * @param session
     * @param request
     */
    @HandlerAnno
    public void move(USession session, CM_MoveMap request) {
        try {
            Player player = SimpleUtil.getPlayerFromSession(session);
            SpringContext.getSceneMapService().move(player, request.getMapId(), request.getTargetX(),
                request.getTargetY());
        } catch (RequestException e) {
            PacketUtil.send(session, SM_Message.valueOf(e.getErrorCode()));
        } catch (Exception e) {
            PacketUtil.send(session, SM_Message.valueOf(I18N.SERVER_ERROR));
            e.printStackTrace();
        }
    }

    /**
     * 地图传送
     *
     * @param session
     * @param request
     */
    @HandlerAnno
    public void transfer(USession session, CM_TransferMap request) {
        try {
            Player player = SimpleUtil.getPlayerFromSession(session);
            String accountId = SimpleUtil.getAccountIdFromSession(session);
            SpringContext.getSceneMapService().transfer(player, request.getMapId());
        } catch (RequestException e) {
            PacketUtil.send(session, SM_Message.valueOf(e.getErrorCode()));
        } catch (Exception e) {
            PacketUtil.send(session, SM_Message.valueOf(I18N.SERVER_ERROR));
            e.printStackTrace();
        }
    }

    /**
     * 切换地图
     *
     * @param session
     * @param request
     */
    @HandlerAnno
    public void changeMap(USession session, CM_ChangeMap request) {
        try {
            Player player = SimpleUtil.getPlayerFromSession(session);
            SpringContext.getSceneMapService().changeMap(player, request.getMapId(), request.getTargetMapId());
        } catch (RequestException e) {
            PacketUtil.send(session, SM_Message.valueOf(e.getErrorCode()));
        } catch (Exception e) {
            PacketUtil.send(session, SM_Message.valueOf(I18N.SERVER_ERROR));
            e.printStackTrace();
        }
    }
}
