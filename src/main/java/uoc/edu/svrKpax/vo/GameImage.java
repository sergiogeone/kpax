package uoc.edu.svrKpax.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "gameimage")
public class GameImage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idGameImage;
	private int gameId;
	private String image;
	
	@Id
	@Column(name = "idGameImage")
	public int getIdGameImage() {
		return idGameImage;
	}
	public void setIdGameImage(int idGameImage) {
		this.idGameImage = idGameImage;
	}
	
	@Column(name = "gameId")
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	@Column(name = "image")
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	
}