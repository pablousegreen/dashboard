package mx.open.dashboard.entity;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="PRODUCT")
@Table(name="PRODUCT")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
	@Column(name="name")
    private String name;
	@Column(name="quantity")
    private Integer quantity;
	@Column(name="price")
    private Double price;
	
//	public Optional<String> getName() {
//		return Optional.ofNullable(name);
//	}
//	public Optional<Integer> getQuantity() {
//		return Optional.ofNullable(quantity);
//	}
//	public Optional<Double> getPrice() {
//		return Optional.ofNullable(price);
//	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

}
