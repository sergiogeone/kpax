package uoc.edu.svrKpax.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.vo.Category;

public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategories() {
		return getHibernateTemplate().find("from Category as category ");
	}

	@Override
	public Category getCategory(int idCategory) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Category.class);
		criteria.add(Restrictions.eq("idCategory", idCategory));
		return (Category) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}
}
