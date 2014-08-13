package uoc.edu.svrKpax.dao;

import java.util.List;

import uoc.edu.svrKpax.vo.Category;

public interface CategoryDao {
	
	public List<Category> getAllCategories();
	public Category getCategory(int idCategory);
}
