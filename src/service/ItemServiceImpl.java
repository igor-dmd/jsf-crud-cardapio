package service;

import java.util.List;

import dao.ItemDao;
import dao.ItemDaoImpl;
import exception.ItemInvalidoException;
import model.Item;

public class ItemServiceImpl implements ItemService {
	
	private ItemDao itemDao;
	
	public ItemServiceImpl() {
		itemDao = new ItemDaoImpl();
	}
	
	public void validarItem(Item item) throws ItemInvalidoException {
		if (item.getNome() == null || item.getNome().trim() == "" ||
				item.getDescricao() == null || item.getDescricao().trim() == "" ||
				item.getPreco() <= 0.0) {
			throw new ItemInvalidoException("Item com dados inválidos!");
		}
	}

	@Override
	public List<Item> listar() {
		return itemDao.listar();
	}

	@Override
	public void salvar(Item item) throws ItemInvalidoException {
		this.validarItem(item);
		this.itemDao.salvar(item);
	}

	@Override
	public void excluir(Item item) throws ItemInvalidoException {
		this.validarItem(item);
		this.itemDao.excluir(item);
	}

	@Override
	public void editar(Item item, int index) throws ItemInvalidoException {
		this.validarItem(item);
		this.itemDao.editar(item, index);
		
	}

	@Override
	public Item findById(int id) {
		return itemDao.findById(id);
	}

}
