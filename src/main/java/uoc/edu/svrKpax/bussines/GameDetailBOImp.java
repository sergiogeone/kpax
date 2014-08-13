package uoc.edu.svrKpax.bussines;

import uoc.edu.svrKpax.dao.GameDetailDao;
import uoc.edu.svrKpax.vo.GameDetail;
import uoc.edu.svrKpax.vo.User;

public class GameDetailBOImp implements GameDetailBO {

	private SessionBO sBo;
	private GameDetailDao dDao;
	
	@Override
	public GameDetail getDetailGame(String campusSession, int idGame) {
		GameDetail objGame = null;
		try {
			User objUser = sBo.validateSession(campusSession);
			if (objUser != null) {
				objGame = dDao.getDetailGame(idGame);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return objGame;
	}

	@Override
	public Boolean addDetailGame(String campusSession, int idGame, String description, String logo, String banner, String videourl) {
		try {
			User objUser = sBo.validateSession(campusSession);
			if (objUser != null) {
				GameDetail objGame = dDao.getDetailGame(idGame);
				if ((objGame == null)) {					
					objGame = new GameDetail();				
					objGame.setGameId(idGame);
				}				
				objGame.setDescription(description);
				objGame.setBanner(banner);
				objGame.setLogo(logo);
				objGame.setVideourl(videourl);
				
				dDao.addGameDetail(objGame);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void setdDao(GameDetailDao lDao) {
		this.dDao = lDao;
	}

	public GameDetailDao getdDao() {
		return dDao;
	}

	public void setsBo(SessionBO sBo) {
		this.sBo = sBo;
	}

	public SessionBO getsBo() {
		return sBo;
	}

}