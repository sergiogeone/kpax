package uoc.edu.svrKpax.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "GameView")
public class GameView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idGame;
	private int publicAcces;
	private String name;
	private String secretGame;
	private String privateKey;
	private int idCategory;
	private Date creationDate;
	private int timesCommented;
	private int popularity;
	private int timesPlayed;
	private int activity;

	@Id
	@Column(name = "idGame")
	public int getIdGame() {
		return idGame;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}

	@Column(name = "grantPublicAccess")
	public int getPublicAcces() {
		return publicAcces;
	}

	public void setPublicAcces(int publicAcces) {
		this.publicAcces = publicAcces;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "secretGame")
	public String getSecretGame() {
		return secretGame;
	}

	public void setSecretGame(String secretGame) {
		this.secretGame = secretGame;
	}

	@Column(name = "privateKey")
	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
	@Column(name = "idCategory", nullable = true) //Set a "nullable=true" property only for installing on production environment. In normal case it should never be null.
	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	@Column(name = "creationDate", nullable = true) //Set a "nullable=true" property only for installing on production environment. In normal case it should never be null.
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Column(name = "timesCommented")
	public int getTimesCommented() {
		return timesCommented;
	}
	public void setTimesCommented(int timesCommented) {
		this.timesCommented = timesCommented;
	}
	
	@Column(name = "popularity")
	public int getPopularity() {
		return popularity;
	}
	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	@Column(name = "timesPlayed")
	public int getTimesPlayed() {
		return timesPlayed;
	}
	public void setTimesPlayed(int timesPlayed) {
		this.timesPlayed = timesPlayed;
	}
	
	@Column(name = "activity")
	public int getActivity() {
		return activity;
	}
	public void setActivity(int activity) {
		this.activity = activity;
	}
	
	public static Game getGame(GameView gameView)
	{
		Game game = new Game();
		game.setCreationDate(gameView.creationDate);
		game.setIdCategory(gameView.idCategory);
		game.setIdGame(gameView.idGame);
		game.setName(gameView.name);
		game.setPrivateKey(gameView.privateKey);
		game.setPublicAcces(gameView.publicAcces);
		game.setSecretGame(gameView.secretGame);
		
		return game;
	}
}