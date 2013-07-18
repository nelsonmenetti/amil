package generic.fps.ranking.common;

public enum Weapons {
  
	HANDGUN ("Handgun"),
	SHOTGUN ("Shotgun"),
	WIDOWMAKER("Widowmaker Rifle"),
	KNIFE("Knife"),
	AXE("Axe"),
	BOMB("Bomb"),
	BAZUKA("Bazuka"),
	VOODOO("Voodoo Doll");
	
	private String name;
	
	Weapons (String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
