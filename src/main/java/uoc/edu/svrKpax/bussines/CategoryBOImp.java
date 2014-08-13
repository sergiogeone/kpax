package uoc.edu.svrKpax.bussines;

import java.util.List;

import uoc.edu.svrKpax.dao.CategoryDao;
import uoc.edu.svrKpax.vo.Category;

public class CategoryBOImp implements CategoryBO {

	private SessionBO sBo;
	private CategoryDao cDao;

	@Override
	public List<Category> listCategories(String campusSession) {
		if (sBo.validateSession(campusSession) != null) {
			return cDao.getAllCategories();
		}
		return null;
	}
	
	@Override
	public Category getCategory(String campusSession, int idCategory) {
		if (sBo.validateSession(campusSession) != null) {
			return cDao.getCategory(idCategory);
		}
		return null;
	}

	public CategoryDao getcDao() {
		return cDao;
	}

	public void setcDao(CategoryDao cDao) {
		this.cDao = cDao;
	}

	public void setsBo(SessionBO sBo) {
		this.sBo = sBo;
	}

	public SessionBO getsBo() {
		return sBo;
	}

}