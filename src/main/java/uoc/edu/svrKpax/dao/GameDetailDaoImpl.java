package uoc.edu.svrKpax.dao;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.vo.GameDetail;

public class GameDetailDaoImpl extends HibernateDaoSupport implements GameDetailDao {
	
	@Override
	public void addGameDetail(GameDetail objGameDetail) {
		getHibernateTemplate().saveOrUpdate(objGameDetail);
	}
	
	@Override
	public GameDetail getDetailGame(int idGame) {
		DetachedCriteria criteria = DetachedCriteria.forClass(GameDetail.class);
		criteria.add(Restrictions.eq("gameId", idGame));
		return (GameDetail) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}
}
