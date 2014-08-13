package uoc.edu.svrKpax.dao;

import java.util.List;

import uoc.edu.svrKpax.vo.Comment;

public interface CommentDao {
	
	public List<Comment> getAllComments(int idGame);
	public Comment getComment(int idComment);
	public void addCommentGame(Comment comment);
	public void delCommentGame(Comment comment);
}
