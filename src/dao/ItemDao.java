package dao;

import java.util.ArrayList;
import java.util.List;

import model.Item;

public class ItemDao {

	private List<Item> itemList;
	
	public ItemDao () {
		itemList = new ArrayList<Item>();
		
		Item item1 = new Item(1, "Costela Suína", 
				"Costela temperada e assada no forno, acompanhada de batatas assadas e molho barbecue", 35.90);
		Item item2 = new Item(2, "Batata Frita", 
				"Porção de 350g de Batatas Fritas", 12.00);
		Item item3 = new Item(3, "Bobó de Camarão", 
				"Prato de Bobó de Camarão acompanhado de Arroz Branco e Legumes salteados, serve 2 pessoas", 28.90);
		
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
	}
	
	public List<Item> listar() {
		return itemList;
	}
	
	public void salvar(Item item) {
		itemList.add(item);
	}
	
	public void excluir(Item item) {
		itemList.remove(item);
	}
	
	public void editar(Item item, int index) {
		itemList.set(index, item);
	}
	
	public Item findById(int id) {
		for (Item item : itemList) {
			if (item.getId() == id) {
				return item;
			}
		}
		
		return null;
	}
	
}
