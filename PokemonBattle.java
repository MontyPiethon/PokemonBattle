import java.util.*;

public class PokemonBattle {
    public static void main (String[] args) {
        String pokemon = battleStart(0, "Zebstrika");
    }

    public static String battleStart (int type, String pokemon) {
        if (type == 0) {
            System.out.println("A wild pokemon appeared!");
        } else if (type == 1) {
            System.out.println("A trainer is issuing a challenge!");
        }

        System.out.println(pokemon + " appeared!");

        Scanner console = new Scanner(System.in);
        System.out.print("Which Pokemon do you choose? ");
        String chosen_pokemon = console.nextLine();
        console.close();

        System.out.println("You chose " + chosen_pokemon + "!");

        System.out.println("It's a battle between " + pokemon + " and " + chosen_pokemon + "! Go!");

        return chosen_pokemon;
    }

    public static int attack (String attacking, String defending) {
        System.out.print(attacking + " attacks " + defending + "!");

        String attacking_stats = getStats(attacking, 1);
        String defending_stats = getStats(defending, 0);

        return 0;
    }

    // Note that this is not using arrays, just strings.
    //
    // position: if 0: defending, if 1: attacking
    public static String getStats (String name, int position) {
        System.out.println("What are " + name + " stats?");

        String string = "";

        if (position == 0) { // defending
            string = string + askQuestion("DefenseBase: ");
            string = string + " ";
            string = string + askQuestion("HP: ");
        } else if (position == 1) {
            string = string + askQuestion("Level: ");
            string = string + " ";
            string = string + askQuestion("AttackBase: ");
            string = string + " ";
            string = string + askQuestion("AttackName: ");
            string = string + " ";
            string = string + askQuestion("AttackPower: ");
        }

        return string;
    }

    public static String parsePsuedoList (String psuedo, int index) {
        int spaces = 0;
        String value = "";
        char character = '\0';

        for (int char_index = 0; char_index < psuedo.length(); char_index++) {
            character = psuedo.charAt(char_index);

            if (character == ' ' && spaces == index) {
                return value;
            } else if (character == ' ') {
                spaces += 1;
            } else {
                value = value + character;
            }
        }

        return value;
    }

    public static String askQuestion(String question) {
        Scanner console = new Scanner(System.in);
        System.out.print(question);
        String answer = console.nextLine();
        console.close();

        return answer;
    }
}
