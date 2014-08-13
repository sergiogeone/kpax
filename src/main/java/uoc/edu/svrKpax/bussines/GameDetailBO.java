package uoc.edu.svrKpax.bussines;

import uoc.edu.svrKpax.vo.GameDetail;

public interface GameDetailBO {

	public Boolean addDetailGame(String campusSession, int idGame, String description, String logo, String banner, String videourl);

	public GameDetail getDetailGame(String campusSession, int idGame);

}