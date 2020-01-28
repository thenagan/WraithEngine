package unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.Test;
import net.whg.we.main.PipelineConstants;
import net.whg.we.main.PollEventsAction;
import net.whg.we.window.IWindow;

public class PollEventsActionTest
{
    @Test
    public void defaultPriority()
    {
        assertEquals(PipelineConstants.POLL_WINDOW_EVENTS, new PollEventsAction(mock(IWindow.class)).getPriority());
    }

    @Test
    public void pollEvents()
    {
        IWindow window = mock(IWindow.class);
        PollEventsAction action = new PollEventsAction(window);

        action.run();

        verify(window).pollEvents();
    }
}
