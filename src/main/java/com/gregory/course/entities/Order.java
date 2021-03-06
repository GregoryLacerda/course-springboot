package com.gregory.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gregory.course.entities.enums.OrderStatus;

@Entity
@Table(name = "tb_order") // da um novo nome para a tabela que será criada
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	private Integer orderStatus;

	// com o @JsonIgnore aqui, permite que o json chame os pedidos associados ao
	// cliente
	// implementando o pedido para ser relacionado no banco
	@ManyToOne // muitas orders para 1 usuario
	@JoinColumn(name = "client_id") // Coloca o nome da coluna de chave estrangeira para este
	private User client;
	// Associção para 1 jpa carrega auto o associado
	
	@OneToMany(mappedBy = "id.order", fetch = FetchType.EAGER)//fetch = FetchType.LAZY para resolver o problema de lazy load
	private Set<OrderItem> items = new HashSet<>();
	
	//como é a classe principal que não precisa da exitencia do payment, não usa o MapsId
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)//CascadeType.ALL mapea para que as duas entidades tenham o mesmo id
	private Payment payment;
	
	public Order() {

	}

	public Order(Long id, Instant moment, User client, OrderStatus orderStatus) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
		
	}

	public Set<OrderItem> getItems(){
		return items;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Double getTotal() {
		double sum = 0;
		
		for (OrderItem orderItem : items) {
			sum += orderItem.getSubTotal();
		}
		
		return sum;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}


}
