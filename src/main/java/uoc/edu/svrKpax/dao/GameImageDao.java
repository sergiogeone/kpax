package uoc.edu.svrKpax.dao;

import java.util.List;

import uoc.edu.svrKpax.vo.GameImage;

public interface GameImageDao {
	
	public List<GameImage> getAllImagesGame(int idGame);
	public GameImage getImage(int idGameImage);
	public void addImageGame(GameImage image);
	public void delImageGame(GameImage image);
}
