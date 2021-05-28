package cn.dlut.dao;

import java.io.Serializable;


public interface IBaseDao<T> {
	
	/**
	 * insert object
	 * @param o object
	 * @return object
	 */
	public T insert(T o);
	/**
	 * get object
	 * @param id object id
	 * @return object
	 */
	public T get(Serializable id);
	/**
	 * update object info
	 * @param o object
	 * @return int the number of update rows
	 */
	public int update(T o);
	/**
	 * del the row
	 * @param o objet
	 * @return int the number of affect rows
	 */
	public int delete(T o);

}///~;
