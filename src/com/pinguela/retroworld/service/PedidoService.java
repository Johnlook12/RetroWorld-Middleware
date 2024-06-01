package com.pinguela.retroworld.service;
import java.util.List;

import com.pinguela.DataException;
import com.pinguela.retroworld.model.Pedido;

public interface PedidoService {
	public void create(Pedido p) throws DataException;
	public Double calculatePrecio(Pedido p);
	public List<Pedido> findByAll() throws DataException;
	public Pedido findById(Long id) throws DataException;
	public List<Pedido> findByUsuario(Long idUsuario) throws DataException;
	public boolean update (Pedido p) throws DataException;
}
