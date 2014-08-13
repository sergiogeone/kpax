package uoc.edu.svrKpax.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.vo.GameImage;

public class GameImageDaoImpl extends HibernateDaoSupport implements GameImageDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<GameImage> getAllImagesGame(int idGame) {
		return getHibernateTemplate().find("from GameImage as image where image.gameId = "+idGame);
	}

	@Override
	public GameImage getImage(int idGameImage) {
		DetachedCriteria criteria = DetachedCriteria.forClass(GameImage.class);
		criteria.add(Restrictions.eq("idGameImage", idGameImage));
		return (GameImage) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}

	@Override
	public void addImageGame(GameImage image) {
		getHibernateTemplate().saveOrUpdate(image);
	}

	@Override
	public void delImageGame(GameImage image) {
		getHibernateTemplate().delete(image);
	}

}
