package uoc.edu.svrKpax.bussines;

import java.util.List;

import uoc.edu.svrKpax.dao.GameDao;
import uoc.edu.svrKpax.dao.GameImageDao;
import uoc.edu.svrKpax.vo.Game;
import uoc.edu.svrKpax.vo.GameImage;
import uoc.edu.svrKpax.vo.User;

public class GameImageBOImp implements GameImageBO {

	private SessionBO sBo;
	private GameImageDao imDao;
	private GameDao gDao;

	@Override
	public List<GameImage> listImagesGame(String campusSession, int idGame) {
		if (sBo.validateSession(campusSession) != null) {
			Game objGame = gDao.getGame(idGame);
			if(objGame != null)
			{
				return imDao.getAllImagesGame(idGame);
			}
		}
		return null;
	}

	@Override
	public Boolean addImagesGame(String campusSession, int idGame,
			List<GameImage> images) {
		try {
			User objUser = sBo.validateSession(campusSession);
			if (objUser != null)
			{
				Game objGame = gDao.getGame(idGame);
				if(objGame != null)
				{
					//Insert new images
					for(GameImage image : images)
					{
						GameImage objImage = new GameImage();
						objImage.setGameId(idGame);
						objImage.setImage(image.getImage());
	
						imDao.addImageGame(objImage);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean delImageGame(String campusSession, int idGameImage) {
		try {
			User objUser = sBo.validateSession(campusSession);
			if (objUser != null) {
				GameImage objGameImage = imDao.getImage(idGameImage);
				if(objGameImage != null)
				{
					imDao.delImageGame(objGameImage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public GameImageDao getimDao() {
		return imDao;
	}

	public void setimDao(GameImageDao imDao) {
		this.imDao = imDao;
	}

	public GameDao getgDao() {
		return gDao;
	}

	public void setgDao(GameDao gDao) {
		this.gDao = gDao;
	}

	public void setsBo(SessionBO sBo) {
		this.sBo = sBo;
	}

	public SessionBO getsBo() {
		return sBo;
	}

}