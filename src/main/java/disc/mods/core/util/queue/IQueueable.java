package disc.mods.core.util.queue;

public interface IQueueable<T extends IQueueHandler>
{
    void setHandler(T handler);
}
