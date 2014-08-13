package uoc.edu.svrKpax.dao;

import java.util.List;
import java.util.Map;

import uoc.edu.svrKpax.vo.GameView;

public interface GameViewDao {
	
	public List<GameView> getAllGameViews(int idFilterer, int idOrderer, Map<String, String> fields_values);
}
