import java.util.*;

public class PokemonBattle {
	private static Scanner console;

	public static void main (String[] args) {

				console = new Scanner(System.in);

				Pokemon other = new Pokemon("Zebstrika", 60, 75, 63,
					"Quick Attack\\Flame Charge\\Pursuit\\Spark",
					"40\\50\\40\\65",
					"100\\100\\100\\100");

        Pokemon mine = battleStart(0, other);

				int rounds;
				String round_results = "";
				boolean round_complete = false;

				Pokemon attacking = other;
				Pokemon defending = mine;
				Pokemon tmp;

				for (rounds = 0; true; rounds++) {
					if (mine.healthPoints <= 0) {
						break;
					}

					if (other.healthPoints <= 0) {
						break;
					}

					int initialHP = defending.healthPoints;
					defending = attack(attacking, defending);
					int damageTaken = initialHP - defending.healthPoints;

					round_results += attacking.name + " dealt " + damageTaken + " damage to " + defending.name + "\\";

					tmp = attacking;
					attacking = defending;
					defending = tmp;
				}

				if (mine.healthPoints <= 0) {
					System.out.println("You have lost the battle.");
					System.out.println("You whited out!");
				} else {
					System.out.println("You won the battle!");
				}

				System.out.println("\nRound Summary");
				System.out.println("\nROUND : RESULT");
				for (int i = 0; i < rounds; i++) {
					System.out.print(i + 1);
					System.out.print("     : ");
					System.out.println(parsePsuedoList(round_results, i));
				}

				console.close();
    }

    public static Pokemon battleStart (int type, Pokemon attacking) {
        if (type == 0) {
            System.out.println("A wild pokemon appeared!");
        } else if (type == 1) {
            System.out.println("A trainer is issuing a challenge!");
        }

        System.out.println(attacking.name + " appeared!");

        String chosen_pokemon = askQuestion("Which Pokemon do you choose? ");

        System.out.println("You chose " + chosen_pokemon + "!");

        System.out.println("It's a battle between " + attacking.name + " and " + chosen_pokemon + "! Go!");

        Pokemon n = new Pokemon(chosen_pokemon, 60, 90, 80,
					"Bite\\Fire Fang\\Thunder Fang\\Extreme Speed",
					"60\\65\\65\\80",
					"100\\95\\95\\100");

				return n;
    }

		public static Pokemon attack (Pokemon attacking, Pokemon defending) {
			System.out.println(attacking.name + " attacks " + defending.name + "!");

			double min = 0.5;
			double max = 1.5;
			Random r = new Random();
			double luck = min + r.nextFloat() * (max - min);

			int move = r.nextInt(4) + 1;

			int level = attacking.level;
			String attackName = parsePsuedoList(attacking.attackNames, move);
			int attackBase = Integer.parseInt(parsePsuedoList(attacking.attackBases, move));
			int attackPower = Integer.parseInt(parsePsuedoList(attacking.attackPowers, move));

			int defenseBase = defending.defenseBase;
			int healthPoints = defending.healthPoints;

			int damage = (int)(luck * ((((((2 * level) / 5) + 2) * attackPower * (attackBase / defenseBase)) / 50) + 2));
			int remainingHP = healthPoints - damage;

			System.out.println(attacking.name + " attacked " + defending.name + " with " + attackName + "!");
			System.out.println(defending.name + " sustained " + damage + " points of damage.");

			if (remainingHP > 0) {
				System.out.println(defending.name + "'s HP is now " + remainingHP);
			} else {
				System.out.println(defending.name + " has fainted.");
				remainingHP = 0;
			}

			defending.healthPoints = remainingHP;

			return defending;
		}

		public static String askQuestion (String question) {
				System.out.print(question);
				String answer = console.nextLine();
				return answer;
		}

			public static String parsePsuedoList (String psuedo, int index) {
					char seperator = '\\';
					int spaces = 0;
					String value = "";
					char character = '\0';

					for (int char_index = 0; char_index < psuedo.length(); char_index++) {
							character = psuedo.charAt(char_index);

							if (character == seperator && spaces == index) {
									return value;
							} else if (character == seperator) {
									spaces += 1;
									value = "";
							} else {
									value = value + character;
							}
					}

					return value;
			}
}
