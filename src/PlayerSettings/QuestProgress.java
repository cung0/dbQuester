package Node.src.PlayerSettings;

import org.dreambot.api.methods.settings.PlayerSettings;

public interface QuestProgress {
    static int QUEST_PROGRESS(int config){
        return PlayerSettings.getConfig(config);
    }
}
