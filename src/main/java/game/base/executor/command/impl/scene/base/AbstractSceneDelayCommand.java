package game.base.executor.command.impl.scene.base;

/**
 * @author : ddv
 * @since : 2019/7/2 下午12:17
 */

public abstract class AbstractSceneDelayCommand extends AbstractSceneCommand {

    private long delay;

    public AbstractSceneDelayCommand(int mapId, long delay) {
        super(mapId);
        this.delay = delay;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }
}
