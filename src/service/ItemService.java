package service;

import java.util.List;

import exception.ItemInvalidoException;
import model.Item;

public interface ItemService {
	public List<Item> listar();
	
	public void salvar(Item item) throws ItemInvalidoException;
	
	public void excluir(Item item) throws ItemInvalidoException;
	
	public void editar(Item item, int index) throws ItemInvalidoException;
	
	public Item findById(int id);
}
