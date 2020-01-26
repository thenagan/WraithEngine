package unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import net.whg.we.main.CullGameObjectsAction;
import net.whg.we.main.GameObject;
import net.whg.we.main.Scene;

public class CullGameObjectsActionTest
{
    @Test
    public void runAction()
    {
        GameObject go1 = new GameObject();
        GameObject go2 = new GameObject();
        GameObject go3 = new GameObject();

        Scene scene = new Scene();
        scene.addGameObject(go1);
        scene.addGameObject(go2);
        scene.addGameObject(go3);
        go2.markForRemoval();

        CullGameObjectsAction action = new CullGameObjectsAction();
        scene.addPipelineAction(action);

        action.run();

        assertFalse(scene.gameObjects()
                         .contains(go2));
    }

    @Test
    public void defaultPriority()
    {
        assertEquals(40000, new CullGameObjectsAction().getPriority());
    }
}
