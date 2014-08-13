package uoc.edu.svrKpax.dao;

import java.util.List;

import uoc.edu.svrKpax.vo.Tag;

public interface TagDao {
	
	public List<Tag> getAllTagsGame(int idGame);
	public Tag getTag(int idTag);
	public void addTagGame(Tag tag);
	public void delTagGame(Tag tag);
}
