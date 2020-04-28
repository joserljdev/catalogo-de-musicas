package br.com.joserlj.catalogo.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "catalogo")
public class Catalogo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "O campo Nome precisa ser preenchido.")
	@Size(min = 2, max = 60, message = "O tamanho do campo deve estar entre 2 e 60 caracteres.")
	@Column(nullable = false, length = 60)
	private String nome;

	@NotBlank(message = "O campo Descrição precisa ser preenchido.")
	@Column(nullable = false)
	private String descricao;

	@OneToMany(mappedBy = "catalogo", cascade = CascadeType.ALL)
	private List<Musica> musicas;

	@ManyToOne
	@JoinColumn(name = "id_usuario_fk")
	private Usuario usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}