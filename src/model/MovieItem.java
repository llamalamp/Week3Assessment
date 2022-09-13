package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author kenne-krcutkomp
 * CIS175 - Fall 2022
 */
@Entity
@Table(name="inventory")
public class MovieItem {

	@Id
	@GeneratedValue
	@Column(name="ID")
	int ID;
	@Column(name="title")
	String movieTitle;
	@Column(name="quantity")
	int quantity;
	
	/**
	 * 
	 */
	public MovieItem() {
		// TODO Auto-generated constructor stub
	}
	public MovieItem(String movieTitle, int quantity) {
		setMovieTitle(movieTitle);
		setQuantity(quantity);
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * @return the movieTitle
	 */
	public String getMovieTitle() {
		return movieTitle;
	}

	/**
	 * @param movieTitle the movieTitle to set
	 */
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getMovie() {
		return getMovieTitle() + " qty: " + getQuantity() + " ID: "+ getID();
	}
	

}
