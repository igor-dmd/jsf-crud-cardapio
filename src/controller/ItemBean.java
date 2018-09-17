package controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.ItemDaoImpl;
import exception.ItemInvalidoException;
import model.Item;
import service.ItemService;
import service.ItemServiceImpl;

@ManagedBean
@SessionScoped
public class ItemBean {

	private ItemService itemService;
	private Item selectedItem;
	private List<Item> itens;
	
	private boolean editavel;

	public ItemBean() {
		itemService = new ItemServiceImpl();
		selectedItem = new Item();
		
		editavel = false;
		
		itens = itemService.listar();
	}
	
	public String inserir() {
		selectedItem.setId(itens.size() + 1);
		try {
			itemService.salvar(selectedItem);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Item adicionado com sucesso!"));
	        context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (ItemInvalidoException e) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(e.getMessage()));
	        context.getExternalContext().getFlash().setKeepMessages(true);
	        
			e.printStackTrace();
		}
		
		itens = itemService.listar();
        
        selectedItem = new Item();
		
		return "index";
	}
	
	public String carregarEdicao(Item item) {
		this.selectedItem = item;
		
		editavel = true;
		
		return "adicionar";
	}
	
	public String editar() {
		try {
			itemService.editar(selectedItem, itens.indexOf(itemService.findById(selectedItem.getId())));
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Item editado com sucesso!"));
	        context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (ItemInvalidoException e) {			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(e.getMessage()));
	        context.getExternalContext().getFlash().setKeepMessages(true);
	        
			e.printStackTrace();
		}
        
        editavel = false;
        selectedItem = new Item();
		
		return "index";
	}
	
	public String excluir(Item item) {
		try {
			itemService.excluir(item);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Item excluído com sucesso!"));
	        context.getExternalContext().getFlash().setKeepMessages(true);
		} catch (ItemInvalidoException e) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(e.getMessage()));
	        context.getExternalContext().getFlash().setKeepMessages(true);
			
			e.printStackTrace();
		}
		
		itens = itemService.listar();
		
		return "index";
	}
	
	public Item getSelectedItem() {
		return selectedItem;
	}
	
	public void setSelectedItem(Item selectedItem) {
		this.selectedItem = selectedItem;
	}
	
	public List<Item> getItens() {
		return this.itens;
	}
	
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	
	public boolean isEditavel() {
		return editavel;
	}

	public void setEditavel(boolean editavel) {
		this.editavel = editavel;
	}
	
}
