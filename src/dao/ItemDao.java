package dao;

import java.util.List;

import model.Item;

public interface ItemDao {
	public List<Item> listar();
	
	public void salvar(Item item);
	
	public void excluir(Item item);
	
	public void editar(Item item, int index);
	
	public Item findById(int id);
}
