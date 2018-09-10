package controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.ItemDao;
import model.Item;

@ManagedBean
@SessionScoped
public class ItemBean {

	private ItemDao itemDao;
	private Item selectedItem;
	private List<Item> itens;
	
	private boolean editavel;

	public ItemBean() {
		itemDao = new ItemDao();
		selectedItem = new Item();
		
		editavel = false;
		
		itens = itemDao.listar();
	}
	
	public String inserir() {
		selectedItem.setId(itens.size() + 1);
		itemDao.salvar(selectedItem);
		
		itens = itemDao.listar();
		
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Item adicionado com sucesso!"));
        context.getExternalContext().getFlash().setKeepMessages(true);
        
        selectedItem = new Item();
		
		return "index";
	}
	
	public String carregarEdicao(Item item) {
		this.selectedItem = item;
		
		editavel = true;
		
		return "adicionar";
	}
	
	public String editar() {
		itemDao.editar(selectedItem, itens.indexOf(itemDao.findById(selectedItem.getId())));
		
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Item editado com sucesso!"));
        context.getExternalContext().getFlash().setKeepMessages(true);
        
        editavel = false;
        selectedItem = new Item();
		
		return "index";
	}
	
	public String excluir(Item item) {
		itemDao.excluir(item);
		
		itens = itemDao.listar();
		
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Item excluído com sucesso!"));
        context.getExternalContext().getFlash().setKeepMessages(true);
		
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
