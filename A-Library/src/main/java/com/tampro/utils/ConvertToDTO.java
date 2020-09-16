package com.tampro.utils;

import java.util.HashSet;
import java.util.Set;

import com.tampro.dto.AuthDTO;
import com.tampro.dto.AuthorDTO;
import com.tampro.dto.CallCardDTO;
import com.tampro.dto.CallCardDetailDTO;
import com.tampro.dto.CategoryDTO;
import com.tampro.dto.HistoryDTO;
import com.tampro.dto.InvoiceDTO;
import com.tampro.dto.LibraryCardDTO;
import com.tampro.dto.MenuDTO;
import com.tampro.dto.ProductInStockDTO;
import com.tampro.dto.ProductInfoDTO;
import com.tampro.dto.PublisherDTO;
import com.tampro.dto.ReadersDTO;
import com.tampro.dto.RoleDTO;
import com.tampro.dto.UserRolesDTO;
import com.tampro.dto.UsersDTO;
import com.tampro.entity.Auth;
import com.tampro.entity.Authors;
import com.tampro.entity.CallCard;
import com.tampro.entity.CallCardDetail;
import com.tampro.entity.Category;
import com.tampro.entity.History;
import com.tampro.entity.Invoice;
import com.tampro.entity.LibraryCard;
import com.tampro.entity.Menu;
import com.tampro.entity.ProductInStock;
import com.tampro.entity.ProductInfo;
import com.tampro.entity.Publisher;
import com.tampro.entity.Readers;
import com.tampro.entity.Role;
import com.tampro.entity.UserRoles;
import com.tampro.entity.Users;

public class ConvertToDTO {
	
	public static UsersDTO convertUsersEntityToDTO(Users users) {
		UsersDTO usersDTO = new UsersDTO();
		usersDTO.setActiveFlag(users.getActiveFlag());
		usersDTO.setCmnd(users.getCmnd());
		usersDTO.setCreateDate(users.getCreateDate());
		usersDTO.setFullName(users.getFullName());
		usersDTO.setId(users.getId());
		usersDTO.setPassword(users.getPassword());
		usersDTO.setUsername(users.getUsername());
		usersDTO.setSdt(users.getSdt());
		usersDTO.setUpdateDate(users.getUpdateDate());
		Set<UserRolesDTO> userRolesDTOs = new HashSet<UserRolesDTO>();
		for(UserRoles userRoles : users.getUserRoles()) {
			UserRolesDTO userRolesDTO = convertUserRolesEntityToDTO(userRoles);
			userRolesDTOs.add(userRolesDTO);
		}
		usersDTO.setIdRoles(users.getUserRoles().iterator().next().getRole().getId());
		usersDTO.setUserRolesDTOs(userRolesDTOs);
		return usersDTO;
	}
	public static UserRolesDTO convertUserRolesEntityToDTO(UserRoles userRoles) {
		UserRolesDTO usersRoleDTO = new UserRolesDTO();
		usersRoleDTO.setActiveFlag(userRoles.getActiveFlag());
		usersRoleDTO.setCreateDate(userRoles.getCreateDate());
		usersRoleDTO.setId(userRoles.getId());
		usersRoleDTO.setIdUsers(userRoles.getUsers().getId());
		RoleDTO roleDTO = convertRoleEntityToDTO(userRoles.getRole());
		usersRoleDTO.setRoleDTO(roleDTO);
		usersRoleDTO.setUpdateDate(userRoles.getUpdateDate());
		return usersRoleDTO;
	}
	public static RoleDTO convertRoleEntityToDTO(Role role) {
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setActiveFlag(role.getActiveFlag());
		Set<AuthDTO> authDTOs = new HashSet<AuthDTO>();
		for(Auth auth : role.getAuths()) {
			AuthDTO authDTO = convertAuthEntityToDTO(auth);
			authDTOs.add(authDTO);
		}
		roleDTO.setAuths(authDTOs);
		roleDTO.setCreateDate(role.getCreateDate());
		roleDTO.setDescription(role.getDescription());
		roleDTO.setId(role.getId());
		roleDTO.setName(role.getName());
		roleDTO.setUpdateDate(role.getUpdateDate());
		return roleDTO;
	}
	public static AuthDTO convertAuthEntityToDTO(Auth auth) {
		AuthDTO authDTO = new AuthDTO();
		authDTO.setActiveFlag(auth.getActiveFlag());
		authDTO.setCreateDate(auth.getCreateDate());
		authDTO.setId(auth.getId());
		authDTO.setIdRole(auth.getRole().getId());
		MenuDTO menuDTO = convertMenuEntityToDTO(auth.getMenu());
		authDTO.setMenuDTO(menuDTO);
		authDTO.setPermission(auth.getPermission());
		authDTO.setUpdateDate(auth.getUpdateDate());
		return authDTO;
	}
	public static MenuDTO convertMenuEntityToDTO(Menu menu) {
		MenuDTO menuDTO = new MenuDTO();
		menuDTO.setActiveFlag(menu.getActiveFlag());
		menuDTO.setCreateDate(menu.getCreateDate());
		menuDTO.setId(menu.getId());
		menuDTO.setName(menu.getName());
		menuDTO.setParentId(menu.getParentId());
		menuDTO.setUpdateDate(menu.getUpdateDate());
		menuDTO.setUrl(menu.getUrl());
		menuDTO.setOrderIndex(menu.getOrderIndex());
		return menuDTO;
	}
	public static CategoryDTO convertCategoryEntityToDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setActiveFlag(category.getActiveFlag());
		categoryDTO.setCode(category.getCode());
		categoryDTO.setCreateDate(category.getCreateDate());
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		categoryDTO.setUpdateDate(category.getUpdateDate());
		return categoryDTO;
	}
	public static AuthorDTO convertAuthorEntityToDTO(Authors author) {
		AuthorDTO authorDTO = new AuthorDTO();
		authorDTO.setDescription(author.getDescription());
		authorDTO.setId(author.getId());
		authorDTO.setName(author.getName());
		authorDTO.setActiveFlag(author.getActiveFlag());
		authorDTO.setCode(author.getCode());
		authorDTO.setCreateDate(author.getCreateDate());
		return authorDTO;
	}
	public static PublisherDTO convertPublisherEntityToDTO(Publisher publisher) {
		PublisherDTO publisherDTO = new PublisherDTO();
		publisherDTO.setAddress(publisher.getAddress());
		publisherDTO.setCode(publisher.getCode());
		publisherDTO.setEmail(publisher.getEmail());
		publisherDTO.setId(publisher.getId());
		publisherDTO.setInfo(publisher.getInfo());
		publisherDTO.setName(publisher.getName());
		publisherDTO.setPhone(publisher.getPhone());
		publisherDTO.setActiveFlag(publisher.getActiveFlag());
		publisherDTO.setCreateDate(publisher.getCreateDate());
		publisherDTO.setUpdateDate(publisher.getUpdateDate());
		return publisherDTO;
	}
	public static ProductInfoDTO convertProductInfoEntityToDTO(ProductInfo productInfo) {
		ProductInfoDTO productInfoDTO = new ProductInfoDTO();
		productInfoDTO.setActiveFlag(productInfo.getActiveFlag());
		AuthorDTO authorDTO = convertAuthorEntityToDTO(productInfo.getAuthors());
		productInfoDTO.setAuthorDTO(authorDTO);
		CategoryDTO categoryDTO = convertCategoryEntityToDTO(productInfo.getCategory());
		productInfoDTO.setCategoryDTO(categoryDTO);
		productInfoDTO.setCreateDate(productInfo.getCreateDate());
		productInfoDTO.setId(productInfo.getId());
		productInfoDTO.setImgUrl(productInfo.getImgUrl());
		productInfoDTO.setName(productInfo.getName());
		PublisherDTO publisherDTO = convertPublisherEntityToDTO(productInfo.getCompany());
		productInfoDTO.setPublisherDTO(publisherDTO);
		productInfoDTO.setUpdateDate(productInfo.getUpdateDate());
		productInfoDTO.setCode(productInfo.getCode());
		productInfoDTO.setCateId(productInfo.getCategory().getId());
		productInfoDTO.setPublisherId(productInfo.getCompany().getId());
		productInfoDTO.setAuthorId(productInfo.getAuthors().getId());
		return productInfoDTO;
		
	}
	public static ReadersDTO convertReadersEntityToDTO(Readers  readers) {
		ReadersDTO readersDTO = new ReadersDTO();
		readersDTO.setActiveFlag(readers.getActiveFlag());
		readersDTO.setAddress(readers.getAddress());
		readersDTO.setCreateDate(readers.getCreateDate());
		readersDTO.setId(readers.getId());
		readersDTO.setMssv(readers.getMssv());
		readersDTO.setName(readers.getName());
		readersDTO.setNameClass(readers.getNameClass());
		readersDTO.setPhone(readers.getPhone());
		readersDTO.setUpdateDate(readers.getUpdateDate());
		return readersDTO;
	}
	public static LibraryCardDTO convertLibraryCardEntityToDTO(LibraryCard  libraryCard) {
		LibraryCardDTO libraryCardDTO = new LibraryCardDTO();
		libraryCardDTO.setActiveFlag(libraryCard.getActiveFlag());
		libraryCardDTO.setCreateDate(libraryCard.getCreateDate());
		libraryCardDTO.setDescription(libraryCard.getDescription());
		libraryCardDTO.setEndDay(libraryCard.getEndDay());
		libraryCardDTO.setId(libraryCard.getId());
		ReadersDTO readersDTO = convertReadersEntityToDTO(libraryCard.getReaders());
		libraryCardDTO.setReadersDTO(readersDTO);
		libraryCardDTO.setStartDay(libraryCard.getStartDay());
		libraryCardDTO.setUpdateDate(libraryCard.getUpdateDate());
		libraryCardDTO.setStatus(libraryCard.getStatus());
		return libraryCardDTO;
	}
	public static ProductInStockDTO convertProductInStockEntityToDTO(ProductInStock productInStock) {
		ProductInStockDTO productInStockDTO = new ProductInStockDTO();
		productInStockDTO.setActiveFlag(productInStock.getActiveFlag());
		productInStockDTO.setCreateDate(productInStock.getCreateDate());
		productInStockDTO.setId(productInStock.getId());
		ProductInfoDTO productInfoDTO = convertProductInfoEntityToDTO(productInStock.getProductInfo());
		productInStockDTO.setProductInfoDTO(productInfoDTO);
		productInStockDTO.setQuantity(productInStock.getQuanity());
		productInStockDTO.setUpdateDate(productInStock.getUpdateDate());
		return productInStockDTO;
	}
	public static HistoryDTO convertHistoryEntityToDTO(History history) {
		HistoryDTO historyDTO = new HistoryDTO();
		historyDTO.setActionName(history.getActionName());
		historyDTO.setActiveFlag(history.getActiveFlag());
		historyDTO.setCreateDate(history.getCreateDate());
		historyDTO.setId(history.getId());
		ProductInfoDTO productInfoDTO = convertProductInfoEntityToDTO(history.getProductInfo());
		historyDTO.setProductInfoDTO(productInfoDTO);
		historyDTO.setQuantity(history.getQuantity());
		historyDTO.setType(history.getType());
		historyDTO.setUpdateDate(history.getUpdateDate());
		UsersDTO usersDTO = convertUsersEntityToDTO(history.getUsers());
		historyDTO.setUsersDTO(usersDTO);
		return historyDTO;
	}
	public static InvoiceDTO convertInvoiceEntityToDTO(Invoice invoice) {
		InvoiceDTO invoiceDTO  = new InvoiceDTO();
		invoiceDTO.setActiveFlag(invoice.getActiveFlag());
		invoiceDTO.setCreateDate(invoice.getCreateDate());
		invoiceDTO.setId(invoice.getId());
		ProductInfoDTO infoDTO = convertProductInfoEntityToDTO(invoice.getProductInfo());
		invoiceDTO.setIdProduct(invoice.getProductInfo().getId());
		invoiceDTO.setProductInfoDTO(infoDTO);
		invoiceDTO.setQuantity(invoice.getQuantity());
		invoiceDTO.setType(invoice.getType());
		invoiceDTO.setUpdateDate(invoice.getUpdateDate());
		UsersDTO usersDTO = convertUsersEntityToDTO(invoice.getUsers());
		invoiceDTO.setUsersDTO(usersDTO);
		return invoiceDTO;
	}

	public static CallCardDTO convertCallCardEntityToDTO(CallCard callCard) {
		CallCardDTO callCardDTO = new CallCardDTO();
		callCardDTO.setActiveFlag(callCard.getActiveFlag());
		Set<CallCardDetailDTO> set = new HashSet<CallCardDetailDTO>();
		for(CallCardDetail detail : callCard.getCallCardDetails()) {
			CallCardDetailDTO cardDetailDTO = convertCallCardDetailEntityToDTO(detail);
			set.add(cardDetailDTO);
		}
		callCardDTO.setCardDetailDTOs(set);
		callCardDTO.setCreateDate(callCard.getCreateDate());
		callCardDTO.setDateIssue(callCard.getDateIssue());
		callCardDTO.setId(callCard.getId());
		LibraryCardDTO libaryCardDTO = convertLibraryCardEntityToDTO(callCard.getLibaryCard());
		callCardDTO.setLibaryCardDTO(libaryCardDTO);
		callCardDTO.setStatus(callCard.getStatus());
		callCardDTO.setUpdateDate(callCard.getUpdateDate());
		UsersDTO usersDTO = convertUsersEntityToDTO(callCard.getUsers());
		callCardDTO.setUsersDTO(usersDTO);
		return callCardDTO;
	}
	public static CallCardDetailDTO convertCallCardDetailEntityToDTO(CallCardDetail callCardDetail) {
		CallCardDetailDTO callCardDetailDTO = new CallCardDetailDTO();
		callCardDetailDTO.setActiveFlag(callCardDetail.getActiveFlag());
		callCardDetailDTO.setCallCardId(callCardDetail.getCallCard().getId());
		callCardDetailDTO.setCreateDate(callCardDetail.getCreateDate());
		callCardDetailDTO.setDueDate(callCardDetail.getDueDate());
		callCardDetailDTO.setId(callCardDetail.getId());
		ProductInfoDTO productInfoDTO = convertProductInfoEntityToDTO(callCardDetail.getProductInfo());
		callCardDetailDTO.setProductInfoDTO(productInfoDTO);
		callCardDetailDTO.setReturnDate(callCardDetail.getReturnDate());
		callCardDetailDTO.setStatus(callCardDetail.getStatus());
		callCardDetailDTO.setUpdateDate(callCardDetail.getUpdateDate());
		LibraryCardDTO libraryCardDTO = convertLibraryCardEntityToDTO(callCardDetail.getCallCard().getLibaryCard());
		callCardDetailDTO.setLibraryCardDTO(libraryCardDTO);
		return callCardDetailDTO;
	}
}
