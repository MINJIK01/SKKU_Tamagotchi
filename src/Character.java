
public class Character implements Status {
	private String name = "";
	private int exp = 0;
	private int energy = 50;
	private int level = 1;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public void levelUp() {
		if (getExp() > 80) {
			setExp(getExp() - 80);
			level++;
			System.out.println("·¹º§¾÷!! " + level);
			System.out.println("");
		}

	}

	@Override
	public void graduate() {

	}
	
	@Override
	public void expel() {

	}

}
