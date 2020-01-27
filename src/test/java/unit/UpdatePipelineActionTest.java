package unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.Test;
import net.whg.we.main.AbstractBehavior;
import net.whg.we.main.IUpdateable;
import net.whg.we.main.UpdatePipelineAction;

public class UpdatePipelineActionTest
{
    @Test
    public void ensurePipelinePriority()
    {
        assertEquals(0, new UpdatePipelineAction().getPriority());
    }

    @Test
    public void updateBehaviors()
    {
        UpdatePipelineAction action = new UpdatePipelineAction();
        action.enableBehavior(mock(AbstractBehavior.class)); // To make sure no casting issues occur

        UpdatableAction behavior = new UpdatableAction();
        assertEquals(0, behavior.calls);

        action.enableBehavior(behavior);
        action.run();
        assertEquals(1, behavior.calls);

        action.disableBehavior(behavior);
        action.run();
        assertEquals(1, behavior.calls);
    }

    class UpdatableAction extends AbstractBehavior implements IUpdateable
    {
        int calls = 0;

        @Override
        public void update()
        {
            calls++;
        }
    }
}