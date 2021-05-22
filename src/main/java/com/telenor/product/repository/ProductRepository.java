package com.telenor.product.repository;

import org.springframework.stereotype.Repository;

import com.telenor.product.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query(value="select * from Product p "
	        + "where (p.type is null or :type = '' or p.type = :type) "
	        + "and (p.properties is null or :properties = '' or p.properties = :properties) "
	        + "and (p.store_address is null or :address = '' or p.store_address LIKE :address)"
	        + "and (p.price >= :min_price and p.price <= :max_price) ",nativeQuery=true)
	List<Product> findByTypeAndProperties(@Param("type")String type,
										  @Param("properties")String properties,
										  @Param("min_price")Double minPrice,
										  @Param("max_price")Double maxPrice,
										  @Param("address")String city);



}
