package insa.demo.item;

import org.springframework.data.repository.CrudRepository;

/**
 * ItemRepository
 * 
 * @author  Birkan Yildiz & Nicolas Martin
 * @version 1.0
 */
public interface ItemRepository extends CrudRepository<Item, Long> {

}