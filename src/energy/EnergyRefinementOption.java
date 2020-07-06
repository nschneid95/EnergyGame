package energy;

import java.util.List;

public class EnergyRefinementOption implements Option {
	public static EnergyRefinementOption fire = new EnergyRefinementOption(EnergyType.FIRE);
	public static EnergyRefinementOption water = new EnergyRefinementOption(EnergyType.WATER);
	public static EnergyRefinementOption earth = new EnergyRefinementOption(EnergyType.EARTH);
	public static EnergyRefinementOption air = new EnergyRefinementOption(EnergyType.AIR);
	
	public static List<EnergyRefinementOption> all = List.of(fire, water, earth, air);
	
	private EnergyRefinementOption(EnergyType type) {
		this.type = type;
	}
	
	@Override
	public String text() {
		return "Refine 2 " + EnergyType.name(EnergyType.RAW) + " energy into 1 " + EnergyType.name(type) + " energy";
	}
	
	@Override
	public boolean isAllowed(Game game) {
		return game.hand.getOrDefault(EnergyType.RAW, 0) >= 2;
	}
	
	@Override
	public void execute(Game game) throws IllegalStateException {
		game.spend(EnergyType.RAW, 2);
		game.insert(type, 1);
	}
	
	private final EnergyType type;

}