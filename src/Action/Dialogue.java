package Node.src.Action;

import Objects.Interact;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.input.Camera;
import org.dreambot.api.methods.input.Keyboard;
import org.dreambot.api.wrappers.interactive.NPC;

public interface Dialogue extends Interact {
    default boolean IS_IN_DIALOGUE(){
        return Dialogues.inDialogue();
    }

    default void SPEAK_TO_NPC(NPC npc, String[] options){
        if (!IS_IN_DIALOGUE()){
            if (npc != null){
                if (npc.isOnScreen()){
                    interactable(npc, "Talk-To", IS_IN_DIALOGUE());
                } else if (Camera.getPitch() < 300){
                    Camera.mouseRotateToPitch(383);
                } else if (Camera.getZoom() > 240){
                    Camera.setZoom(181);
                } else {
                    Camera.rotateToEntity(npc);
                }
            }
        } else {
            ENTER_OPTION(options);
        }
    }

    default boolean ENTER_OPTION(String[] options){
        if (IS_IN_DIALOGUE() && !Dialogues.continueDialogue()) {
            for (String option : options){
                if (Dialogues.chooseOption(option)){
                    break;
                }
            }
        } else {
            Keyboard.holdSpace(()-> !IS_IN_DIALOGUE(), 5000);
        }
        return IS_IN_DIALOGUE();
    }


}
