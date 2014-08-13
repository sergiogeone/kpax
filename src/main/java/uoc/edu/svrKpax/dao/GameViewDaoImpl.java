package uoc.edu.svrKpax.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.vo.GameView;

public class GameViewDaoImpl extends HibernateDaoSupport implements GameViewDao {

	@Override
	public List<GameView> getAllGameViews(int idFilterer, int idOrderer, Map<String, String> fields_values) {
		String orderBy = ""; //Global default order: idOrderer = 0
		String join= ""; //Global Default join clause.
		String where = ""; //Global Default where clause.

		/*---- PROCESSING FILTERING -----*/
		switch (idFilterer)
		{
			case 0: //Global default filter: No filtering.
				where = ""; 
				break;
			case 1: // By category.
				where = " where gv.idCategory = " + fields_values.get("idCategory");
				break;
			case 2: // By game name.
				String name = fields_values.get("name");
				where = " where gv.name like '%" + name + "%'";
				break;
			case 3: // By similar games.
				join = " inner join TotalGameSimilitudeView tgsv on gv.idGame = tgsv.idGame ";
				where = " where tgsv.similarToIdGame = " + fields_values.get("idGame") + " ";
				orderBy = " order by tgsv.totalSimilitude desc "; //Default order to current filter.
				break;
			default: return null;
		}
		
		/*---- PROCESSING ORDERING -----*/
		switch (idOrderer)
		{
			case 0: //Global default filter: No filtering. This is the only one that doesn't overwrites current order.
				break;
			case 1: // Most played.
				orderBy = " order by gv.timesPlayed desc";
				break;
			case 2: // Most popular.
				orderBy = " order by gv.popularity desc";
				break;
			case 3: // Most newfangled.
				orderBy = " order by gv.creationDate desc ";
				break;
			case 4: // Most commented.
				orderBy = " order by gv.timesCommented desc";
				break;
			case 5: // Most active.
				orderBy = " order by gv.activity desc";
				break;
			default: return null;
		}
		
		String HQLSQL = "select {gv.*} from GameView gv " + join + where + orderBy; 
		
		try
		{
			org.hibernate.Session s = getSession();
			Transaction t = s.getTransaction();
			t.begin();
			@SuppressWarnings("unchecked")
			List<GameView> gameViews = s.createSQLQuery(HQLSQL)
			.addEntity("gv", GameView.class).list();
			t.commit();
			releaseSession(s);
			
			return gameViews;
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
