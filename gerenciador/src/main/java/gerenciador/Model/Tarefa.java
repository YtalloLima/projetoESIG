package gerenciador.Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tbl_tarefa")
public class Tarefa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_tarefa")
	private int id;
	
//	@NotEmpty(message = "Título é obrigatório")
	@Column
	private String titulo;

//	@NotEmpty(message = "Descrição é obrigatória")
	@Column
	private String descricao;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@Column
	private Boolean ativo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_responsavel", nullable = false)
	private Responsavel responsavel;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_situacao", nullable = false)
	private Situacao situacao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_prioridade", nullable = false)
	private Prioridade prioridade;
	
	public boolean isPermiteConcluir() {
		return situacao.getId() == Situacao.EM_ANDAMENTO;
	}
	
	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
}
