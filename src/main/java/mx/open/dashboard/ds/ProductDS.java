package mx.open.dashboard.ds;

import java.io.Serializable;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDS implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Integer quantity;
	private Double price;
	
	public Integer getId() {
		return id;
	}
		
	public Optional<String> getName() {
		return Optional.ofNullable(name);
	}
	public Optional<Integer> getQuantity() {
		return Optional.ofNullable(quantity);
	}
	public Optional<Double> getPrice() {
		return Optional.ofNullable(price);
	}


}
