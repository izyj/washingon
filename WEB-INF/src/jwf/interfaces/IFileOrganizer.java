package jwf.interfaces;

public interface IFileOrganizer<E> {
	
	
	/*
	 * Sert a ajouter un élément a la file
	 */
	public void push(E file) ;
	
	/**
	 * Sert a recuperer un élements de la file
	 */
	public  IFileOrganizer<?> pop();
	
	/**
	 * Supprime le dernier element ajouter
	 */
	public void delete();
}
