package Node.src.Quests;

import PlayerSettings.QuestProgress;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Sleep;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.widgets.WidgetChild;

import java.util.Objects;

public class SheepShearer extends Node implements Basis{
    public SheepShearer(Main main) {
        super(main);
    }

    @Override
    public boolean validate() {
        return Validate.sheep_shearer;
    }
    int progress = QuestProgress.QUEST_PROGRESS(179); // Sheep Shearer - 179
    @Override
    public int execute() {
        switch (progress){
            case 0:
                Logger.log("hi");
                initiateQuest();
                break;
            case 1:
                Logger.log("1");
                collectWool();
                break;
            case 21:
                CLOSE_QUEST_END_SCREEN(Validate.sheep_shearer);
                break;
        }
        return 500;
    }

    private void initiateQuest(){
        if (IS_IN_AREA(FRED_HOUSE)){
            SPEAK_TO_NPC(getNpc("Fred the Farmer"), DIALOGUE_OPTIONS);
        } else {
            WALK_TO_AREA(FRED_HOUSE);
        }
    }

    private void collectWool(){
        if (Inventory.contains("Shears")){
            if (NOT_ENOUGH_WOOL()){
                if (Inventory.isFull()){
                    Inventory.dropAllExcept("Wool", "Shears");
                } else if (IS_IN_AREA(SHEEP_FIELD)){
                    SHEAR_SHEEP();
                } else if (IS_IN_AREA(STILE)){
                    WALK_TO_TILE(JUMP_TILE);
                }
            } else if (ENOUGH_WOOL()){
                if (IS_IN_AREA(SPINNER)){
                    SPIN_WOOL();
                } else {
                    WALK_TO_AREA(SPINNER);
                }
            } else if (Inventory.count("Ball of wool") >= 20){
                initiateQuest();
            }
        } else if (getGameObject("Shears") != null
        && getGameObject("Shears").distance(Players.getLocal()) < 3){
            if (getGameObject("Shears").interact("Take")){
                Sleep.sleepUntil(()-> Inventory.contains("Shears"), 5000);
            }
        } else {
            WALK_TO_TILE(getGameObject("Shears").getTile());
        }
    }

    private boolean NOT_ENOUGH_WOOL(){
        return Inventory.count("Wool") < 20 && Inventory.count("Ball of wool") == 0;
    }

    private boolean ENOUGH_WOOL(){
        return Inventory.count("Wool") >= 20;
    }

    private void SHEAR_SHEEP(){
        if (SHEEP().hasAction("Shear") && Map.canReach(SHEEP())) {
            if (SHEEP().interact("Shear")) {
                Sleep.sleep(2000, 3000);
            }
        } else {
            WALK_TO_AREA(INNER_FIELD);
        }
    }

    private NPC SHEEP(){
        return NPCs.closest(2694, 2699, 2693, 2695, 2786, 2787);
    }

    private void JUMP_STILE(){
        if (getGameObject("Stile") != null){
            if (Objects.requireNonNull(getGameObject("Stile")).interact("Climb-over")){
                Sleep.sleepUntil(() -> IS_IN_AREA(INNER_FIELD), 5000);
            }
        }
    }

    private void SPIN_WOOL() {
        if (IS_ON_TILE(SPINNER_TILE)) {
            WidgetChild widget = Widgets.getWidgetChild(270, 14);
            INTERACT_WIDGET(widget, "Spin", Inventory.count("Ball of Wool") >= 20, 30000);
            if (widget != null) {
                if (widget.interact("Spin")){
                    Sleep.sleepUntil(() -> Inventory.count("Ball of wool") >= 20, 30000);
                }
            } else if (getGameObject("Spinning wheel").interact("Spin")) {
                Sleep.sleep(2000);
            }
        } else {
            WALK_TO_TILE(SPINNER_TILE);
        }
    }

    private final String[] DIALOGUE_OPTIONS = {
            "I'm looking for a quest.",
            "Yes.",
            "Yes, okay. I can do that."
    };

    private final Area SHEEP_FIELD = new Area(3212, 3257, 3193, 3276);
    private final Area INNER_FIELD = new Area(3195, 3273, 3209, 3260);
    private final Area FRED_HOUSE = new Area(3184, 3279, 3192, 3270);
    private final Tile SPINNER_TILE = new Tile(3209, 3213, 1);
    private final Area STILE = new Area(3201, 3281, 3194, 3277);
    private final Tile JUMP_TILE = new Tile(3197, 3280);
    private final Area LUMBRIDGE_SECOND_FLOOR = new Area(3204, 3230, 3216, 3207, 1);
    private final Area SPINNER = new Area(3212, 3217, 3208, 3212, 1);
}
