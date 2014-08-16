package uoc.edu.svrKpax.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.vo.Game;

import org.hibernate.*;

public class GameDaoImpl extends HibernateDaoSupport implements GameDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Game> getAllGames() {
		return getHibernateTemplate().find("from Game where grantPublicAccess = 1");
	}

	@Override
	public Game getGame(int idGame) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Game.class);
		criteria.add(Restrictions.eq("idGame", idGame));
		return (Game) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}

	@Override
	public void addGame(Game objGame) {
		getHibernateTemplate().saveOrUpdate(objGame);
	}

	@Override
	public void delGame(Game objGame) {
		getHibernateTemplate().delete(objGame);
	}

	@Override
	public Game getGameUid(String uidGame) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Game.class);
		criteria.add(Restrictions.eq("secretGame", uidGame));
		return (Game) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}
	
	@Override
	public Game getGameByName(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Game.class);
		criteria.add(Restrictions.eq("name", name));
		return (Game) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}

    @Override
	public List<Game> getUserGames(String username) {
		String SQL="SELECT g.* FROM `game` g, `gamedeveloper` gd, `user` u WHERE u.login=:username AND u.idUser = gd.idUser AND gd.idGame = g.idGame;";
		SQLQuery query=getSession().createSQLQuery(SQL);
    
    	query.addEntity(Game.class);
        query.setParameter("username", username);
        List<Game> list= query.list();
        return list;
	}

	@Override
	public List<Game> getDeveloperGame(String developer) {
		DetachedCriteria criteria =  
	               DetachedCriteria.forClass(Game.class);
	   criteria.add(Restrictions.eq("developer", developer));
	   return (List<Game>)getHibernateTemplate().findByCriteria(criteria);
	}
	
	@Override
	public List<Game> getNotDeveloperGame(String developer) {
		DetachedCriteria criteria =  
	               DetachedCriteria.forClass(Game.class);
	   criteria.add(Restrictions.ne("developer", developer)).add(Restrictions.eq("publicAcces", 1));
	   return (List<Game>)getHibernateTemplate().findByCriteria(criteria);
	}
	
	@Override
	public List<Game> getUnauthorizedGame(){
		DetachedCriteria criteria =  
	               DetachedCriteria.forClass(Game.class);
	   criteria.add(Restrictions.eq("publicAcces", 0));
	   return (List<Game>)getHibernateTemplate().findByCriteria(criteria);
	}

}
