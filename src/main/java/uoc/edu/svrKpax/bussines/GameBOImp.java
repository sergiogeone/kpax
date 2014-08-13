package uoc.edu.svrKpax.bussines;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uoc.edu.svrKpax.dao.CategoryDao;
import uoc.edu.svrKpax.dao.GameDao;
import uoc.edu.svrKpax.dao.GameViewDao;
import uoc.edu.svrKpax.util.Security;
import uoc.edu.svrKpax.vo.Game;
import uoc.edu.svrKpax.vo.GameView;

public class GameBOImp implements GameBO {

	private SessionBO sBo;
	private GameDao gDao;
	private GameViewDao gvDao;
	private CategoryDao cDao;

	@Override
	public List<Game> listGames(String campusSession) {

		if (sBo.validateSession(campusSession) != null) {
			return gDao.getAllGames();
		}

		return null;
	}
	
	

	@Override
	public List<Game> listUserGames(String username,String campusSession) {

		if (sBo.validateSession(campusSession) != null) {
			return gDao.getUserGames(username);
		}

		List<Game> list = new ArrayList<Game>();
		Game game = new Game();
		game.setIdGame(1);
		list.add(game);
		return list;
	}

	@Override
	public Boolean addGame(String campusSession, String nameGame, int idGame) {
		try {
			if (sBo.validateSession(campusSession) != null) {
				Game objGame = new Game();
				objGame.setIdGame(idGame);
				objGame.setName(nameGame);
				objGame.setPublicAcces(1);
				objGame.setSecretGame(Security.getIdGame());
				objGame.setPrivateKey(Security.getIdSession());

				gDao.addGame(objGame);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public List<Game> listGames(String campusSession, int idOrderer, int idFilterer, List<String> fields, List<String> values) {

		if (sBo.validateSession(campusSession) != null) {
			
			//Transform fields and values into Map
			if(fields.size() != values.size())
				return null;
			Map <String, String> fields_values = new HashMap <String, String>();
			for(int i = 0; i < fields.size(); i++)
			{
				String field = fields.get(i);
				if(field != null && !field.trim().equals(""))
				{
					fields_values.put(field, values.get(i)); //No duplicated fields validation. Will be overwritten.
				}
			}
			
			/*---- Validating fields and values -----*/
			try
			{
				switch (idFilterer)
				{
					case 1: // By category.
						if(!fields_values.containsKey("idCategory") || 
							fields_values.get("idCategory").trim().equals(""))
						{
							return null;
						}
						Integer.parseInt(fields_values.get("idCategory"));
						break;
					case 2: // By game name.
						if(!fields_values.containsKey("name") || 
							fields_values.get("name").trim().equals(""))
						{
							return null;
						}
						break;
					case 3: // By similar games.
						if(!fields_values.containsKey("idGame") || 
							fields_values.get("idGame").trim().equals(""))
						{
							return null;
						}
						Integer.parseInt(fields_values.get("idGame"));
						break;
				}
			}
			catch(NumberFormatException e)
			{
				return null;
			}
			
			//Getting the list
			List<GameView> gamesView = gvDao.getAllGameViews(idFilterer, idOrderer, fields_values);
			if(gamesView == null)
				return null;
			
			//Transform GameView list into GameList for returning
			List<Game> games = new ArrayList <Game>();
			for(GameView gameView: gamesView)
			{
				games.add(GameView.getGame(gameView));
			}
			return games;
		}

		return null;
	}

	@Override
	public Boolean addGame(String campusSession, String nameGame, int idGame, int idCategory, Date creationDate, String developer) {
		try {
			//Add the game if user exists, game does not exists, there is a name, there is a creation date and category exists
			Game objGame = gDao.getGame(idGame);
			if (sBo.validateSession(campusSession) != null &&
					objGame == null &&
					cDao.getCategory(idCategory) != null &&
					gDao.getGameByName(nameGame) == null &&
					!nameGame.equals(""))
			{
				objGame = new Game();
				objGame.setIdGame(idGame);
				objGame.setName(nameGame);
				objGame.setDeveloper(developer);
				objGame.setPublicAcces(1);
				objGame.setSecretGame(Security.getIdGame());
				objGame.setPrivateKey(Security.getIdSession());
				objGame.setIdCategory(idCategory);
				objGame.setCreationDate(creationDate);
				
				gDao.addGame(objGame);
			}
			else if(objGame != null){
				objGame.setName(nameGame);
				objGame.setIdCategory(idCategory);
				gDao.addGame(objGame);
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
	public Boolean delGame(String campusSession, int idGame) {
		try {
			if (sBo.validateSession(campusSession) != null) {
				//TODO Delete all dependencies: comments, tags, likeGames, instances, records...
				Game objGame = gDao.getGame(idGame);
				gDao.delGame(objGame);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Game getGame(String idGame,String campusSession) {
		Game objGame = null;

		try {
			if (Security.isIdGame(idGame))
				objGame = gDao.getGameUid(idGame);
			else{
				if(sBo.validateSession(campusSession)!=null){
					objGame = gDao.getGame(Integer.parseInt(idGame));	
				}else{
					return null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return objGame;
	}

	public void setgDao(GameDao gDao) {
		this.gDao = gDao;
	}

	public GameDao getgDao() {
		return gDao;
	}

	public SessionBO getsBo() {
		return sBo;
	}

	public void setsBo(SessionBO sBo) {
		this.sBo = sBo;
	}

	public GameViewDao getGvDao() {
		return gvDao;
	}

	public void setGvDao(GameViewDao gvDao) {
		this.gvDao = gvDao;
	}

	public CategoryDao getcDao() {
		return cDao;
	}

	public void setcDao(CategoryDao cDao) {
		this.cDao = cDao;
	}

	@Override
	public List<Game> listDeveloperGames(String campusSession, String developer) {
		if (sBo.validateSession(campusSession) != null) {
			if(!developer.equals(""))
			{
				return gDao.getDeveloperGame(developer);
			}
		}
		return null;
	}
	
	@Override
	public List<Game> listNotDeveloperGames(String campusSession, String developer) {
		if (sBo.validateSession(campusSession) != null) {
			if(!developer.equals(""))
			{
				return gDao.getNotDeveloperGame(developer);
			}
		}
		return null;
	}
}