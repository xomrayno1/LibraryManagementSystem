package com.tampro.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tampro.dto.Paging;
import com.tampro.dto.ProductInfoDTO;
import com.tampro.service.ProductInfoService;
import com.tampro.utils.ProductPagi;

@RestController
public class ProductsRestController {
	@Autowired
	ProductInfoService productService;


	@RequestMapping(value = "/api/products-all",produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<ProductInfoDTO> getProductsAll(){
		
		List<ProductInfoDTO> list = productService.findByProperty(null, null);
		return  list;
	}
	@RequestMapping(value = "/api/product/{id}",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
	public ProductInfoDTO getProductById(@PathVariable("id")int id){
		
		ProductInfoDTO productsDTO = productService.findById(id);
		return  productsDTO;
	}
	@RequestMapping(value = "/api/products/{page}",produces = {MediaType.APPLICATION_JSON_VALUE})
	public ProductPagi<Paging, List<ProductInfoDTO>> getProducts(Model model,@PathVariable("page") int pageIndex ,@RequestBody(required = false) ProductInfoDTO productsDTO){

		Paging paging = new Paging(6);
		paging.setIndexPage(pageIndex);
		List<ProductInfoDTO> list = productService.findAll(productsDTO, paging);
		ProductPagi<Paging, List<ProductInfoDTO>> pagination = new ProductPagi<Paging, List<ProductInfoDTO>>(paging, list);
		return  pagination;
	}

	
}
