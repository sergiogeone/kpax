package uoc.edu.svrKpax.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "TotalGameSimilitudeView")
public class TotalGameSimilitudeView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idGame;
	private int similarToIdGame;
	private int totalSimilitude;
	
	@Id
	@Column(name = "idGame")
	public int getIdGame() {
		return idGame;
	}
	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}
	
	@Id
	@Column(name = "similarToIdGame")
	public int getSimilarToIdGame() {
		return similarToIdGame;
	}
	public void setSimilarToIdGame(int similarToIdGame) {
		this.similarToIdGame = similarToIdGame;
	}
	
	@Column(name = "totalSimilitude")
	public int getTotalSimilitude() {
		return totalSimilitude;
	}
	public void setTotalSimilitude(int totalSimilitude) {
		this.totalSimilitude = totalSimilitude;
	}
	
}