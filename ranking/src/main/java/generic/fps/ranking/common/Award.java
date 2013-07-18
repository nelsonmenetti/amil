package generic.fps.ranking.common;

public class Award {

	private String name;
	private String description;
	private Long matchId;
	
	public Award(String name, String description, Long matchId) {
		super();
		this.name = name;
		this.description = description;
		this.matchId = matchId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getMatchId() {
		return matchId;
	}
	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	
	
	
	
}
