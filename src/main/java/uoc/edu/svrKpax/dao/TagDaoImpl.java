package uoc.edu.svrKpax.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.vo.Tag;

public class TagDaoImpl extends HibernateDaoSupport implements TagDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> getAllTagsGame(int idGame) {
		return getHibernateTemplate().find("from Tag as tag where tag.idGame = "+idGame);
	}

	@Override
	public Tag getTag(int idTag) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Tag.class);
		criteria.add(Restrictions.eq("idTag", idTag));
		return (Tag) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}

	@Override
	public void addTagGame(Tag tag) {
		getHibernateTemplate().saveOrUpdate(tag);
	}

	@Override
	public void delTagGame(Tag tag) {
		getHibernateTemplate().delete(tag);
	}

}
