package net.whg.we.main;

import net.whg.we.window.IWindow;
import net.whg.we.window.IWindowListener;
import net.whg.we.window.WindowSettings;

/**
 * This class acts as a utility class for updating the user control classes in
 * the engine, such as Screen and Input. This binds to a window to continously
 * update the settings.
 */
public final class UserControlsUpdater implements IWindowListener
{
    private static UserControlsUpdater updater = new UserControlsUpdater();

    /**
     * Binds this updater to the current window. Replaces the binding with previous
     * window, if previously binded. This updater is automaically unbound when the
     * window is destroyed.
     * 
     * @param window
     *     - The window to bind with, or null to disable current bindings.
     */
    public static void bind(IWindow window)
    {
        updater.setWindow(window);
    }

    /**
     * Gets the window which the updater is currently bound to.
     * 
     * @return The window, or null if no window is bound.
     */
    public static IWindow getBoundWindow()
    {
        return updater.getWindow();
    }

    private IWindow window;

    private UserControlsUpdater()
    {}

    /**
     * Assigns the target window to bind to.
     * 
     * @param window
     *     - The new target window.
     */
    private void setWindow(IWindow window)
    {
        if (this.window == window)
            return;

        if (this.window != null)
            this.window.removeWindowListener(this);

        this.window = window;

        if (this.window != null)
        {
            this.window.addWindowListener(this);

            WindowSettings settings = this.window.getProperties();
            Screen.updateWindowSize(settings.getWidth(), settings.getHeight());
        }
    }

    /**
     * Gets the window which this updater is currently bound to.
     * 
     * @return The window, or null if no window is bound.
     */
    private IWindow getWindow()
    {
        return window;
    }

    @Override
    public void onWindowUpdated(IWindow window)
    {
        WindowSettings settings = window.getProperties();
        onWindowResized(window, settings.getWidth(), settings.getHeight());
    }

    @Override
    public void onWindowResized(IWindow window, int width, int height)
    {
        Screen.updateWindowSize(width, height);
    }

    @Override
    public void onWindowDestroyed(IWindow window)
    {
        setWindow(null);
    }

    @Override
    public void onWindowRequestClose(IWindow window)
    {
        // Nothing to do.
    }

    @Override
    public void onMouseMove(IWindow window, float newX, float newY)
    {
        Input.setMousePos(newX, newY);
    }

    @Override
    public void onKeyPressed(IWindow window, int keyCode)
    {
        Input.setKeyState(keyCode, true);
    }

    @Override
    public void onKeyReleased(IWindow window, int keyCode)
    {
        Input.setKeyState(keyCode, false);
    }

    @Override
    public void onMousePressed(IWindow window, int mouseButton)
    {
        Input.setMouseButtonState(mouseButton, true);
    }

    @Override
    public void onMouseReleased(IWindow window, int mouseButton)
    {
        Input.setMouseButtonState(mouseButton, false);
    }

    @Override
    public void onMouseWheel(IWindow window, float scrollX, float scrollY)
    {
        Input.setScrollWheelDelta(scrollY);
    }
}