package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import model.MovieItem;

/**
 * @author kenne-krcutkomp CIS175 - Fall 2022
 */
public class MovieItemHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Week3Assessment");

	public MovieItemHelper() {
		super();
	}

	public void insertItem(MovieItem mi) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(mi);
		em.getTransaction().commit();
		em.close();
	}

	public List<MovieItem> showAllItems() {
		EntityManager em = emfactory.createEntityManager();
		List<MovieItem> allItems = em.createQuery("SELECT i FROM MovieItem i").getResultList();
		return allItems;
	}

	public void deleteItem(MovieItem toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<MovieItem> typedQuery = em.createQuery(
				"select mi from MovieItem mi where mi.ID =:selectedID",
				MovieItem.class);
		//typedQuery.setParameter("selectedTitle", toDelete.getMovieTitle());
		typedQuery.setParameter("selectedID", toDelete.getID());
		typedQuery.setMaxResults(1);
		MovieItem result = typedQuery.getSingleResult();
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param idToEdit
	 * @return
	 */

	public MovieItem searchForItemById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		MovieItem found = em.find(MovieItem.class, idToEdit);
		em.close();
		return found;
	}
	
	public List<MovieItem> searchForItemByIdx(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<MovieItem> typedQuery = em.createQuery("select mi from MovieItem mi where mi.ID = :selectedID",
				MovieItem.class);
		typedQuery.setParameter("selectedID", idToEdit);
		List<MovieItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}

	/**
	 * @param toEdit
	 */
	public void updateItem(MovieItem toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();

	}

	/**
	 * @param storeName
	 * @return
	 */
	public List<MovieItem> searchForItemByTitle(String movieTitle) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<MovieItem> typedQuery = em.createQuery("select mi from MovieItem mi where mi.title = :selectedTitle",
				MovieItem.class);
		typedQuery.setParameter("selectedTitle", movieTitle);
		List<MovieItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	


	public void cleanUp() {
		emfactory.close();
	}

}
