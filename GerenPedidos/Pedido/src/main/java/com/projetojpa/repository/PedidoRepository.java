package com.projetojpa.repository;

import com.projetojpa.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PedidoRepository  extends JpaRepository <Pedido, Long>{

}
