package uoc.edu.svrKpax.dao;


import uoc.edu.svrKpax.vo.GameDetail;

public interface GameDetailDao {
	
	public void addGameDetail(GameDetail objGameDetail);
	public GameDetail getDetailGame(int idGame);
}
