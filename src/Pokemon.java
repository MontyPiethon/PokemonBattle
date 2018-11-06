public class Pokemon {
	public String name;
	public int level;
	public int healthPoints;
	public int defenseBase;
	public String attackNames;
	public String attackPowers;
	public String attackBases;

	public Pokemon(String nm, int lvl, int hp, int db, String an, String ap, String ab) {
		name         = nm;
		level        = lvl;
		healthPoints = hp;
		defenseBase  = db;
		attackNames  = an;
		attackPowers = ap;
		attackBases  = ab;
	}
}
