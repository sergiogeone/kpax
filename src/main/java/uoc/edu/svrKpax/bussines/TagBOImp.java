package uoc.edu.svrKpax.bussines;

import java.util.List;

import uoc.edu.svrKpax.dao.GameDao;
import uoc.edu.svrKpax.dao.TagDao;
import uoc.edu.svrKpax.vo.Game;
import uoc.edu.svrKpax.vo.Tag;
import uoc.edu.svrKpax.vo.User;

public class TagBOImp implements TagBO {

	private SessionBO sBo;
	private TagDao tDao;
	private GameDao gDao;

	@Override
	public List<Tag> listTagsGame(String campusSession, int idGame) {
		if (sBo.validateSession(campusSession) != null) {
			Game objGame = gDao.getGame(idGame);
			if(objGame != null)
			{
				return tDao.getAllTagsGame(idGame);
			}
		}
		return null;
	}

	@Override
	public Boolean addTagsGame(String campusSession, int idGame,
			List<Tag> tags) {
		try {
			User objUser = sBo.validateSession(campusSession);
			if (objUser != null)
			{
				Game objGame = gDao.getGame(idGame);
				if(objGame != null)
				{
					//Delete existing tags
					List<Tag> tagsToDelete = tDao.getAllTagsGame(idGame);
					for(Tag objTag : tagsToDelete)
					{
						tDao.delTagGame(objTag);
					}
					
					//Insert new tags
					for(Tag tag : tags)
					{
						Tag objTag = new Tag();
						objTag.setIdGame(idGame);
						objTag.setTag(tag.getTag());
	
						tDao.addTagGame(objTag);
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
	public Boolean delTagsGame(String campusSession, int idGame) {
		try {
			User objUser = sBo.validateSession(campusSession);
			if (objUser != null) {
				Game objGame = gDao.getGame(idGame);
				if(objGame != null)
				{
					List <Tag> tags = tDao.getAllTagsGame(idGame);
					if(tags != null)
					{
						for(Tag tag : tags)
						{
							tDao.delTagGame(tag);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public TagDao gettDao() {
		return tDao;
	}

	public void settDao(TagDao tDao) {
		this.tDao = tDao;
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