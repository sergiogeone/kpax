package uoc.edu.svrKpax.bussines;

import java.util.List;

import uoc.edu.svrKpax.dao.CommentDao;
import uoc.edu.svrKpax.dao.GameDao;
import uoc.edu.svrKpax.vo.Comment;
import uoc.edu.svrKpax.vo.Game;
import uoc.edu.svrKpax.vo.User;

public class CommentBOImp implements CommentBO {

	private SessionBO sBo;
	private CommentDao comDao;
	private GameDao gDao;

	@Override
	public List<Comment> listCommentsGame(String campusSession, int idGame) {
		if (sBo.validateSession(campusSession) != null) {
			Game objGame = gDao.getGame(idGame);
			if(objGame != null)
			{
				return comDao.getAllComments(idGame);
			}
		}
		return null;
	}

	@Override
	public Boolean addComment(String campusSession, int idComment, int idGame) {
		try {
			User objUser = sBo.validateSession(campusSession);
			if (objUser != null)
			{
				Game objGame = gDao.getGame(idGame);
				if(objGame != null && 
						comDao.getComment(idComment) == null)
				{

					Comment objComment = new Comment();
					objComment.setIdGame(idGame);
					objComment.setIdComment(idComment);
					objComment.setIdUser(objUser.getIdUser());

					comDao.addCommentGame(objComment);
				}
				else
					return false;
			}
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Boolean delComment(String campusSession, int idComment) {
		try {
			User objUser = sBo.validateSession(campusSession);
			if (objUser != null) {
				Comment objComment = comDao.getComment(idComment);
				if(objComment != null)
					comDao.delCommentGame(objComment);
				else
					return false;
			}
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public SessionBO getsBo() {
		return sBo;
	}

	public void setsBo(SessionBO sBo) {
		this.sBo = sBo;
	}

	public CommentDao getComDao() {
		return comDao;
	}

	public void setComDao(CommentDao comDao) {
		this.comDao = comDao;
	}

	public GameDao getgDao() {
		return gDao;
	}

	public void setgDao(GameDao gDao) {
		this.gDao = gDao;
	}


}