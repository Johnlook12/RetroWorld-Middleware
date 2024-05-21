package com.pinguela.retroworld.service;
import java.util.List;

import com.pinguela.retroworld.dao.DataException;
import com.pinguela.retroworld.model.Pedido;

public interface PedidoService {
	public void create(Pedido p) throws DataException;
	public Double calculatePrecio(Pedido p);
	public Pedido findById(Long id) throws DataException;
	public List<Pedido> findByUsuario(Long idUsuario) throws DataException;
	public boolean update (Pedido p) throws DataException;
}
