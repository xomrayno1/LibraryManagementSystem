package com.tampro.controller;

import java.text.SimpleDateFormat;
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

import com.tampro.dto.LibraryCardDTO;
import com.tampro.dto.Paging;
import com.tampro.dto.ReadersDTO;
import com.tampro.service.LibraryCardService;
import com.tampro.service.ReadersService;
import com.tampro.utils.Constant;
import com.tampro.validator.LibraryCardValidator;

@Controller
public class LibraryCardController {

	@InitBinder
	public void webInitBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return ;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		if(binder.getTarget().getClass() == LibraryCardDTO.class) {
			binder.setValidator(libraryCardValidator);
		}
	}
	@Autowired
	LibraryCardValidator libraryCardValidator;
	@Autowired
	LibraryCardService libraryCardService;
	@Autowired
	ReadersService readersService;

	@GetMapping(value = {"/library-card/list/","/library-card/list"})
	public String redirect() {
		return "redirect:/library-card/list/1";
	}
	
	@RequestMapping("/library-card/list/{page}")
	public String showCardList(Model model,@PathVariable("page") int page ,@ModelAttribute("searchForm") LibraryCardDTO libraryCardDTO,HttpSession session) {
		Paging paging = new Paging(6);
		paging.setIndexPage(page);
		List<LibraryCardDTO> dtos  = libraryCardService.findAll(libraryCardDTO ,paging);
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
 		return "libraryCard-list";
	}
	@GetMapping("/library-card/add/{id}")
	public String cardAdd(Model model,@PathVariable("id") int id) {
		List<LibraryCardDTO> libraryCardDTOs = libraryCardService.findByProperty("readers.id", id);
		if(libraryCardDTOs.isEmpty()) {
			ReadersDTO readersDTO = readersService.findById(id);
			LibraryCardDTO libraryCardDTO  = new LibraryCardDTO();
			libraryCardDTO.setReadersDTO(readersDTO);
			model.addAttribute("submitForm",libraryCardDTO);
			model.addAttribute("title","Add");
			model.addAttribute("viewOnly",false);
	 		return "libraryCard-action";
		}else {
			int idCard = libraryCardDTOs.get(0).getId();
			return "redirect:/library-card/view/"+idCard;
		}
	}
	@GetMapping("/library-card/view/{id}")
	public String cardView(Model model,@PathVariable("id") int id) {
		LibraryCardDTO libraryCardDTO =  libraryCardService.findById(id);
		model.addAttribute("submitForm",libraryCardDTO);
		model.addAttribute("title","View");
		model.addAttribute("viewOnly",true);
 		return "libraryCard-action";
	}
	@GetMapping("/library-card/edit/{id}")
	public String cardEdit(Model model,@PathVariable("id") int id) {
		LibraryCardDTO libraryCardDTO =  libraryCardService.findById(id);
		model.addAttribute("submitForm",libraryCardDTO);
		model.addAttribute("title","Edit");
		model.addAttribute("viewOnly",false);
 		return "libraryCard-action";
	}
	@PostMapping("/library-card/delete")
	public String cardDelete(Model model,@RequestParam("id") int id,HttpSession session) {
		LibraryCardDTO libraryCardDTO =  libraryCardService.findById(id);
		try {
			libraryCardService.delete(libraryCardDTO);
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}
 		return "redirect:/library-card/list/1";
	}
	@PostMapping("/library-card/save")
	public String cardSave(Model model,@ModelAttribute("submitForm") @Validated LibraryCardDTO libraryCardDTO,BindingResult result, HttpSession session)  {
		
		if(result.hasErrors()) {
			if(libraryCardDTO.getId() != 0) {
				model.addAttribute("title","Edit");
			}else {
				model.addAttribute("title","Add");
			}
			return "libraryCard-action";
		}
		if( libraryCardDTO.getId() != 0) {
			try {
				List<ReadersDTO> readersDTOs = readersService.findByProperty("mssv", libraryCardDTO.getReadersDTO().getMssv());
				if(readersDTOs!=null) {
					libraryCardDTO.setReadersDTO(readersDTOs.get(0));
				}
				libraryCardService.update(libraryCardDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật bị lỗi");
			}
		}else {
			try {
				List<ReadersDTO> readersDTOs = readersService.findByProperty("mssv", libraryCardDTO.getReadersDTO().getMssv());
				if(readersDTOs!=null) {
					libraryCardDTO.setReadersDTO(readersDTOs.get(0));
				}
				libraryCardService.save(libraryCardDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm bị lỗi");
			}
		}
 		return "redirect:/library-card/list/1";
	}
}
