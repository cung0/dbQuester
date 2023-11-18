package Node.src.Node;

import Quests.SheepShearer;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name = "Wet Quests", description = "It Quests!", author = "wettofu",
        version = 1.0, category = Category.WOODCUTTING, image = "")

public class Main extends AbstractScript {
    private Node[] nodes;
    private boolean started = true;

    @Override
    public void onStart() {
        super.onStart();
        nodes = new Node[]{
                new SheepShearer(this)
        };
    }

    @Override
    public int onLoop() {
        if (started){
            for (Node node : nodes){
                if (node.validate()){
                    return node.execute();
                }
            }
        }
        return 500;
    }
}