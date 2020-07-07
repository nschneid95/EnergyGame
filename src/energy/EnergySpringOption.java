package energy;

public class EnergySpringOption implements Option {
	private static EnergySpringOption nextEnergySpring = new EnergySpringOption(0);
	
	public static Option get() {
		return nextEnergySpring;
	}
	
	private EnergySpringOption(int num) {
		this.num = num;
		int calc = 1;
		for (int i = 1; i < num; i++)
			calc *= 2;
		cost = calc;
	}

	@Override
	public String text() {
		return "Fortify the " + Format.obj.rainbow("energy spring") + ". 1 " +
				EnergyType.name(EnergyType.EARTH) + ", 1 " + 
				EnergyType.name(EnergyType.AIR) + ", 1 " +
				EnergyType.name(EnergyType.FIRE) + ", 1 " +
				EnergyType.name(EnergyType.WATER) + ", " +
				cost + " " + EnergyType.name(EnergyType.RAW) +
				" -> +" + num + " " + EnergyType.name(EnergyType.RAW) + " per day";
	}

	@Override
	public boolean isAllowed(Game game) {
		return game.hasAir(1) && game.hasEarth(1) && game.hasWater(1) && game.hasFire(1) && game.hasRaw(cost);
	}

	@Override
	public void execute(Game game) throws IllegalStateException {
		game.spend(EnergyType.AIR, 1);
		game.spend(EnergyType.EARTH, 1);
		game.spend(EnergyType.WATER, 1);
		game.spend(EnergyType.FIRE, 1);
		game.spend(EnergyType.RAW, cost);
		game.addEnergySpring();
		nextEnergySpring = new EnergySpringOption(num + 1);
	}
	final int num, cost;
}
