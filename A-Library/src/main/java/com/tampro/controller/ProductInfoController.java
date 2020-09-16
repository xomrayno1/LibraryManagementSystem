package com.tampro.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tampro.dto.AuthorDTO;
import com.tampro.dto.CategoryDTO;
import com.tampro.dto.Paging;
import com.tampro.dto.ProductInfoDTO;
import com.tampro.dto.PublisherDTO;
import com.tampro.service.AuthorService;
import com.tampro.service.CategoryService;
import com.tampro.service.ProductInfoService;
import com.tampro.service.PublisherService;
import com.tampro.utils.Constant;
import com.tampro.validator.ProductInfoValidator;

@Controller
public class ProductInfoController {

	@InitBinder
	public void webInitBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return ;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm:yy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		if(binder.getTarget().getClass() == ProductInfoDTO.class) {
			binder.setValidator(productInfoValidator);
		}
	}

	@Autowired
	ProductInfoValidator productInfoValidator;
	@Autowired
	ProductInfoService productInfoService;
	@Autowired
	PublisherService publisherService;
	@Autowired
	AuthorService authorService;
	@Autowired
	CategoryService categoryService;

	@GetMapping(value = {"/product-info/list/","/product-info/list"})
	public String redirect() {
		return "redirect:/product-info/list/1";
	}
	
	@RequestMapping("/product-info/list/{page}")
	public String showProductInfoList(Model model,@PathVariable("page") int page ,@ModelAttribute("searchForm") ProductInfoDTO productInfoDTO,HttpSession session) {
		Paging paging = new Paging(6);
		paging.setIndexPage(page);
		List<ProductInfoDTO> dtos  = productInfoService.findAll(productInfoDTO, paging);
		model.addAttribute("listProduct",dtos);
		model.addAttribute("pageInfo" ,paging);
		if(session.getAttribute(Constant.MSG_SUCCESS) != null) {
			model.addAttribute(Constant.MSG_SUCCESS,session.getAttribute(Constant.MSG_SUCCESS));
			session.removeAttribute(Constant.MSG_SUCCESS);
		}
		if(session.getAttribute(Constant.MSG_ERROR) != null) {
			model.addAttribute(Constant.MSG_ERROR,session.getAttribute(Constant.MSG_ERROR));
			session.removeAttribute(Constant.MSG_ERROR);
		}
		showSelect(model);
		
 		return "productInfo-list";
	}
	@GetMapping("/product-info/add")
	public String productInfoAdd(Model model) {
		model.addAttribute("submitForm",new ProductInfoDTO());		
		showSelect(model);		
		model.addAttribute("title","Add");
		model.addAttribute("viewOnly",false);
 		return "productInfo-action";
	}
	@GetMapping("/product-info/view/{id}")
	public String productInfoView(Model model,@PathVariable("id") int id) {
		ProductInfoDTO productInfoDTO =  productInfoService.findById(id);
		model.addAttribute("submitForm",productInfoDTO);
		model.addAttribute("title","View");
		model.addAttribute("viewOnly",true);
 		return "productInfo-action";
	}
	@GetMapping("/product-info/edit/{id}")
	public String productInfoEdit(Model model,@PathVariable("id") int id) {
		ProductInfoDTO productInfoDTO =  productInfoService.findById(id);
		model.addAttribute("submitForm",productInfoDTO);
		model.addAttribute("title","Edit");
		model.addAttribute("viewOnly",false);		
		showSelect(model);
 		return "productInfo-action";
	}
	@PostMapping("/product-info/delete")
	public String productInfoDelete(Model model,@RequestParam("id") int id,HttpSession session) {
		ProductInfoDTO productInfoDTO =  productInfoService.findById(id);
		try {
			productInfoService.delete(productInfoDTO);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}
 		return "redirect:/product-info/list/1";
	}
	@PostMapping("/product-info/save")
	public String productInfoSave(Model model,@ModelAttribute("submitForm") @Validated ProductInfoDTO productInfoDTO,BindingResult result, HttpSession session)  {
		
		if(result.hasErrors()) {
			if(productInfoDTO.getId() != 0) {
				model.addAttribute("title","Edit");
			}else {
				model.addAttribute("title","Add");
			}
			showSelect(model);
			return "productInfo-action";
		}
		if(productInfoDTO.getId() != 0) {
			try {
				productInfoService.update(productInfoDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật bị lỗi");
			}
		}else {
			try {
				productInfoService.save(productInfoDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm bị lỗi");
			}
		}
 		return "redirect:/product-info/list/1";
	}
	public void sortAuthor(List<AuthorDTO> authorDTOs) {
		Collections.sort(authorDTOs, new Comparator<AuthorDTO>() {
			public int compare(AuthorDTO o1, AuthorDTO o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
	}
	public void sortCategory(List<CategoryDTO> categoryDTOs) {
		Collections.sort(categoryDTOs, new Comparator<CategoryDTO>() {
			public int compare(CategoryDTO o1, CategoryDTO o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
	}
	public void sortPublisher(List<PublisherDTO> publisherDTOs) {
		Collections.sort(publisherDTOs, new Comparator<PublisherDTO>() {
			public int compare(PublisherDTO o1, PublisherDTO o2) {
				// TODO Auto-generated method stub
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
	}
	public void showSelect(Model model) {
		List<PublisherDTO> mapPublisher = publisherService.findByProperty(null, null);
		sortPublisher(mapPublisher);
		model.addAttribute("listPublisher",mapPublisher);
		
		List<AuthorDTO> authorDTOs = authorService.findByProperty(null, null);
		sortAuthor(authorDTOs);
		model.addAttribute("listAuthor", authorDTOs);
		
		List<CategoryDTO> categoryDTOs = categoryService.findByProperty(null, null);
		sortCategory(categoryDTOs);
		model.addAttribute("listCategory",categoryDTOs);
	}
}
