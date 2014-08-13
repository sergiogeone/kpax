package uoc.edu.svrKpax.bussines;

import java.util.List;

import uoc.edu.svrKpax.vo.Tag;

public interface TagBO {

	public List<Tag> listTagsGame(String campusSession, int idGame);

	public Boolean addTagsGame(String campusSession, int idGame,
			List<Tag> tags);

	public Boolean delTagsGame(String campusSession, int idGame);

}
