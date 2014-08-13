package uoc.edu.svrKpax.bussines;

import java.util.List;

import uoc.edu.svrKpax.vo.GameImage;

public interface GameImageBO {

	public List<GameImage> listImagesGame(String campusSession, int idGame);

	public Boolean addImagesGame(String campusSession, int idGame,
			List<GameImage> images);

	public Boolean delImageGame(String campusSession, int idGameImage);

}
