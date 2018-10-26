import java.util.*;

public class PokemonBattle {
	public static Scanner console;

	public static void main (String[] args) {
		console = new Scanner(System.in);
        String other_pokemon = "Zebstrika";
				other_pokemon = other_pokemon + " 60 100 Cut 50";//getStats(other_pokemon);

        String my_pokemon = battleStart(0, parsePsuedoList(other_pokemon, 0));
				my_pokemon = my_pokemon + " 60 90";//getStats(my_pokemon);

				String damage_rounds = "";
				boolean round_complete = false;

				for (int round = 0; round_complete != true; round++) {
					attack(other_pokemon, my_pokemon);
				}


				console.close();
    }

    public static String battleStart (int type, String pokemon) {
        if (type == 0) {
            System.out.println("A wild pokemon appeared!");
        } else if (type == 1) {
            System.out.println("A trainer is issuing a challenge!");
        }

        System.out.println(pokemon + " appeared!");

        String chosen_pokemon = "Arcanine";// askQuestion("Which Pokemon do you choose? ");

        System.out.println("You chose " + chosen_pokemon + "!");

        System.out.println("It's a battle between " + pokemon + " and " + chosen_pokemon + "! Go!");

        return chosen_pokemon;
    }

    public static int attack (String attacking_stats, String defending_stats) {
				String attacking = parsePsuedoList(attacking_stats, 0);
				String defending = parsePsuedoList(defending_stats, 0);

        System.out.println(attacking + " attacks " + defending + "!");

				double min = 0.5;
				double max = 1.5;
				Random r = new Random();
				double luck = min + r.nextFloat() * (max - min);

				int level = Integer.parseInt(parsePsuedoList(attacking_stats, 1));
				int attackBase = Integer.parseInt(parsePsuedoList(attacking_stats, 2));
				String attackName = parsePsuedoList(attacking_stats, 3);
				int attackPower = Integer.parseInt(parsePsuedoList(attacking_stats, 4));

				int defenseBase = Integer.parseInt(parsePsuedoList(defending_stats, 1));
				int healthPoints = Integer.parseInt(parsePsuedoList(defending_stats, 2));
				// Damage =
				// Luck * (((2*Level+10)/250) * (AttackPower * AttackBase/DefenseBase))

				int damage = (int)(luck * ((((((2 * level) / 5) + 2) * attackPower * (attackBase / defenseBase)) / 50) + 2));
				int remainingHP = healthPoints - damage;

				System.out.println(attacking + " attacked " + defending + " with " + attackName + "!");
				System.out.println(defending + " sustained " + damage + " points of damage.");
				System.out.println(defending + "'s HP is now " + remainingHP);

				return remainingHP;
    	}

    // Note that this is not using arrays, just strings.
    //
    // position: if 0: defending, if 1: attacking
    public static String getStats (String name) {
        System.out.println("What are " + name + "'s stats?");

        String string = name + " ";

        string = string + askQuestion("DefenseBase: ");
        string = string + " ";
        string = string + askQuestion("HP: ");
				string = string + " ";
        string = string + askQuestion("Level: ");
        string = string + " ";
        string = string + askQuestion("AttackBase: ");
        string = string + " ";
        string = string + askQuestion("AttackName: ");
        string = string + " ";
        string = string + askQuestion("AttackPower: ");

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
								value = "";
            } else {
                value = value + character;
            }
        }

        return value;
    }

		public static String setPsuedoList (String psuedo, int index) {

		}

    public static String askQuestion(String question) {
        System.out.print(question);
        String answer = console.nextLine();
        return answer;
    }
}
