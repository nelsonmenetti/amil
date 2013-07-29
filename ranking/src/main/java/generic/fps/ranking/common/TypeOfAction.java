package generic.fps.ranking.common;

public enum TypeOfAction {
  
	MATCH_START (".*New match .* has started"),
	MATCH_END (".*ended.*"),
	PLAYER_KILL(".*killed.*using.*"),
	WORLD_KILL("<WORLD killed * by *"),
	UNRECOGNIZED_ACTION("");
	
	private String regex;
	
	TypeOfAction (String regex){
		this.regex=regex;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}
	
	
	
}
