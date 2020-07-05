package com.bluefood.domain.restaurante;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.bluefood.infrastructure.web.validator.UploadConstraint;
import com.bluefood.util.FileType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@Table(name = "item_cardapio")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemCardapio implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O nome não pode ser vazio")
	@Size(max  = 50)
	private String nome;
	
	@NotBlank(message = "A descrição não pode ser vazia")
	@Size(max = 150, message = "A descrição deve ter no máximo 150 caracteres")
	private String descricao;
	
	@NotBlank(message = "A Categoria não pode ser vazia")
	@Size(max = 25)
	private String categoria;
	
	@Size(max  = 50)
	private String imagem;
	
	@NotNull(message = "O preço não pode ser vazio")
	@Min(0)
	private BigDecimal preco;
	
	@NotNull
	private Boolean destaque;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "restaurante_id")
	private Restaurante restaurante;
	
	@UploadConstraint(acceptedTypes = FileType.PNG, message = "O Arquivo não é um arquivo de imagem válido")
	private transient MultipartFile imagemFile;
	
	public void setImageFileName() {
		if(getId() == null) {
			throw new IllegalStateException("O objeto precisa primeiro ser criado");
		}
		
		this.imagem = String.format("%04d-comidas.%s", getId(), FileType.of(imagemFile.getContentType()).getExtension());
	}
}
