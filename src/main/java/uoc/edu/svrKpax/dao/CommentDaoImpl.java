package uoc.edu.svrKpax.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import uoc.edu.svrKpax.vo.Comment;

public class CommentDaoImpl extends HibernateDaoSupport implements CommentDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getAllComments(int idGame) {
		return getHibernateTemplate().find("from Comment as comment where comment.idGame = "+idGame);
	}

	@Override
	public Comment getComment(int idComment) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Comment.class);
		criteria.add(Restrictions.eq("idComment", idComment));
		return (Comment) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.findByCriteria(criteria));
	}

	@Override
	public void addCommentGame(Comment tag) {
		/*
		 * With save method an insert command is ensured,
		 * otherwise with saveOrUpdate method an update
		 * will be done when exists a comment done
		 * by the same user-game.
		 */ 
		getHibernateTemplate().save(tag);
	}

	@Override
	public void delCommentGame(Comment tag) {
		getHibernateTemplate().delete(tag);
	}

}
