package Node.src.Objects;

import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.wrappers.interactive.NPC;

public interface Npc {
    public default NPC getNpc(String npcName){
        if (NPCs.closest(npcName) != null) {
            return NPCs.closest(npcName);
        }
        return null;
    }

    public default NPC getNpc(int npcID){
        if (NPCs.closest(npcID) != null) {
            return NPCs.closest(npcID);
        }
        return null;
    }

    public default NPC getNpc(int[] npcID){
        for (int npc: npcID) {
            if (NPCs.closest(npc) != null) {
                return NPCs.closest(npc);
            }
        }
        return null;
    }
}
