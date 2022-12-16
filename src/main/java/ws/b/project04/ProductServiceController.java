/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.b.project04;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
public class ProductServiceController {
   private static final Map<String, Product> productRepo = new HashMap<>();
   static {
      Product honey = new Product();
      honey.setId("1");
      honey.setName("Honey");
      honey.setPrice(5000);
      honey.setNumber(2);
      honey.setTotal();
      productRepo.put(honey.getId(), honey);
      
      Product almond = new Product();
      almond.setId("2");
      almond.setName("Almond");
      almond.setPrice(10000);
      almond.setNumber(2);
      almond.setTotal();
      productRepo.put(almond.getId(), almond);
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
       if(!productRepo.containsKey(id)){
           return new ResponseEntity<>("Product deleted not found", HttpStatus.OK);
       }
       else{
           productRepo.remove(id);
           return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
       }
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
      if(!productRepo.containsKey(id)){
           return new ResponseEntity<>("Product updated not found", HttpStatus.OK);
       }
       else{
           productRepo.remove(id);
           product.setId(id);
           productRepo.put(id, product);
           return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
      }
   }
    @RequestMapping(value = "/products", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody @Validated Product product) {
       
      if(productRepo.containsKey(product.getId())){
           return new ResponseEntity<>("Product not duplicate", HttpStatus.OK);
       }
       else{
           productRepo.put(product.getId(), product);
           return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
       }
   }
   
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
   }
}
