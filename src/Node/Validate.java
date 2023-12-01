package Node.src.Node;

public class Validate {
    public static boolean sheep_shearer = false;
    public static boolean romeo_juliet = false;
    public static void activate_quest(String questName){
        switch (questName) {
            case "Sheep Shearer":
                sheep_shearer = true;
                break;
            case "Romeo & Juliet":
                romeo_juliet = true;
                break;
        }
    }
}
