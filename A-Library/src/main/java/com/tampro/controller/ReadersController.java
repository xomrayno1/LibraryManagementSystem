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
import com.tampro.validator.ReadersValidator;

@Controller
public class ReadersController {
	@InitBinder
	public void webInitBinder(WebDataBinder binder) {
		if(binder.getTarget() == null) {
			return ;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm:yy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		if(binder.getTarget().getClass() == ReadersDTO.class) {
			binder.setValidator(readerValidator);
		}
	}

	@Autowired
	ReadersValidator readerValidator;
	@Autowired
	ReadersService readersService;
	@Autowired
	LibraryCardService libraryCardService;

	@GetMapping(value = {"/readers/list/","/readers/list"})
	public String redirect() {
		return "redirect:/readers/list/1";
	}
	
	@RequestMapping("/readers/list/{page}")
	public String showReadersList(Model model,@PathVariable("page") int page ,@ModelAttribute("searchForm") ReadersDTO readersDTO,HttpSession session) {
		Paging paging = new Paging(6);
		paging.setIndexPage(page);
		List<ReadersDTO> dtos  = readersService.findAll(readersDTO ,paging);
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
 		return "readers-list";
	}
	@GetMapping("/readers/add")
	public String readersAdd(Model model) {
		model.addAttribute("submitForm",new ReadersDTO());
		model.addAttribute("title","Add");
		model.addAttribute("viewOnly",false);
 		return "readers-action";
	}
	@GetMapping("/readers/view/{id}")
	public String readersView(Model model,@PathVariable("id") int id) {
		ReadersDTO readersDTO =  readersService.findById(id);
		model.addAttribute("submitForm",readersDTO);
		model.addAttribute("title","View");
		model.addAttribute("viewOnly",true);
 		return "readers-action";
	}
	@GetMapping("/readers/edit/{id}")
	public String readersEdit(Model model,@PathVariable("id") int id) {
		ReadersDTO readersDTO =  readersService.findById(id);
		model.addAttribute("submitForm",readersDTO);
		model.addAttribute("title","Edit");
		model.addAttribute("viewOnly",false);
 		return "readers-action";
	}
	@PostMapping("/readers/delete")
	public String readersDelete(Model model,@RequestParam("id") int id,HttpSession session) {
		ReadersDTO readersDTO =  readersService.findById(id);
		List<LibraryCardDTO> libraryCardDTO = libraryCardService.findByProperty("readers.id", id);
		try {
			readersService.delete(readersDTO);
			if(!libraryCardDTO.isEmpty()) {
				libraryCardService.delete(libraryCardDTO.get(0));
			}
			session.setAttribute(Constant.MSG_SUCCESS, "Xóa thành công");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.setAttribute(Constant.MSG_ERROR, "Xóa thất bại");
		}
 		return "redirect:/readers/list/1";
	}
	@PostMapping("/readers/save")
	public String readersSave(Model model,@ModelAttribute("submitForm") @Validated ReadersDTO readersDTO,BindingResult result, HttpSession session)  {
		
		if(result.hasErrors()) {
			if(readersDTO.getId() != 0) {
				model.addAttribute("title","Edit");
			}else {
				model.addAttribute("title","Add");
			}
			return "readers-action";
		}
		if( readersDTO.getId() != 0) {
			try {
				readersService.update(readersDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Cập nhật thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Cập nhật bị lỗi");
			}
		}else {
			try {
				readersService.save(readersDTO);
				session.setAttribute(Constant.MSG_SUCCESS, "Thêm thành công");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.setAttribute(Constant.MSG_ERROR, "Thêm bị lỗi");
			}
		}
 		return "redirect:/readers/list/1";
	}

}
