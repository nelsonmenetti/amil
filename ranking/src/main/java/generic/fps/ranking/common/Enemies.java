package generic.fps.ranking.common;

public enum Enemies {

	DROWN ("Drown"),
	ELF ("Elf"),
	DRAGON("Dragon"),
	GWYN("Gwyn Lord of Cinders"),
	AXEGOLEM("Axe Golem"),
	QUELANA("Quelana of Izalith"),
	CAPRADEMON("Capra Demon"),
	SMOUGH("Executioner Smough");
	
	private String name;
	
	Enemies (String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
