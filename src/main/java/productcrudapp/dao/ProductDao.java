package productcrudapp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;


import productcrudapp.model.Product;

@Component
public class ProductDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	//create
	@Transactional
	public void createProduct(Product product) {
		this.hibernateTemplate.save(product);
	}
	public List<Product> getProducts(){
		List<Product> products=this.hibernateTemplate.loadAll(Product.class);
		return products;
	}
	public void deleteProduct(int pid)
	{
		this.hibernateTemplate.load(Product.class,pid);
	}
	
}
