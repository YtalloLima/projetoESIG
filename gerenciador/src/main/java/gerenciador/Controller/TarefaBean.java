package gerenciador.Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import gerenciador.DAO.DaoGeneric;
import gerenciador.DAO.TarefaDao;
import gerenciador.Model.Prioridade;
import gerenciador.Model.Responsavel;
import gerenciador.Model.Situacao;
import gerenciador.Model.Tarefa;

@ManagedBean(name = "tarefaBean")
@SessionScoped
public class TarefaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Tarefa tarefa = new Tarefa();

	private DaoGeneric<Tarefa> daoGeneric = new DaoGeneric<Tarefa>();

	private List<Tarefa> tarefas;

	private Integer idTarefa = null;

	private String operacao;

	public static final String CADASTRO = "cadastro";
	public static final String LISTA = "lista";
	public static final String INDEX = "index";
		
	@PostConstruct
	private void init() {
		tarefa = new Tarefa();
		tarefa.setPrioridade(new Prioridade());
		tarefa.setResponsavel(new Responsavel());
		tarefa.setSituacao(new Situacao());
	}

	public String iniciarCadastro() {
		init();
		setOperacao("Cadastrar");
		return CADASTRO;
	}

	public String iniciarAlteracao(Tarefa tarefa) {
		this.tarefa = tarefa;
		setOperacao("Alterar");
		return CADASTRO;
	}

	public String voltar() {
		init();
		return INDEX;
	}

	public String salvar() {
		if (tarefa.getId() > 0) {
			daoGeneric.update(tarefa);
			addMensagemInfo("Tarefa alterada com sucesso!");
		} else {
			tarefa.setSituacao(new Situacao(Situacao.EM_ANDAMENTO));
			tarefa.setAtivo(true);
			daoGeneric.salvar(tarefa);
			addMensagemInfo("Tarefa criada com sucesso!");
		}
		return INDEX;
	}

	public String listar() {
		init();
		tarefa.setSituacao(new Situacao(Situacao.EM_ANDAMENTO));
		return buscar();
	}

	public String buscar() {
		TarefaDao dao = new TarefaDao();
		tarefas = dao.findByParametros(tarefa.getTitulo(), idTarefa, tarefa.getSituacao().getId(),
				tarefa.getResponsavel());
		return LISTA;
	}

	public String encerrar(Tarefa t) {
		t.setSituacao(new Situacao(Situacao.CONCLUIDA));
		daoGeneric.update(t);
		addMensagemInfo("Tarefa conclu??da com sucesso!");
		return buscar();
	}

	public String excluir(Tarefa t) {
		t.setAtivo(false);
		daoGeneric.update(t);
		addMensagemInfo("Exclus??o realizada com sucesso!");
		return buscar();
	}

	public List<SelectItem> getResponsaveis() {
		DaoGeneric<Responsavel> dao = new DaoGeneric<>();
		List<Responsavel> responsaveis = dao.findAll(Responsavel.class);
		List<SelectItem> lista = new ArrayList<>();

		responsaveis.forEach(resp -> lista.add(new SelectItem(resp.getId(), resp.getNome())));

		return lista;
	}

	public List<SelectItem> getPrioridades() {
		DaoGeneric<Prioridade> dao = new DaoGeneric<>();
		List<Prioridade> prioridades = dao.findAll(Prioridade.class);
		List<SelectItem> lista = new ArrayList<>();

		prioridades.forEach(priori -> lista.add(new SelectItem(priori.getId(), priori.getNome())));

		return lista;
	}

	public List<SelectItem> getSituacoes() {
		DaoGeneric<Situacao> dao = new DaoGeneric<>();
		List<Situacao> situacoes = dao.findAll(Situacao.class);
		List<SelectItem> lista = new ArrayList<>();

		situacoes.forEach(sit -> lista.add(new SelectItem(sit.getId(), sit.getNome())));

		return lista;
	}

	public static void addMensagemInfo(String mensagem) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public Integer getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(Integer idTarefa) {
		this.idTarefa = idTarefa;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
}
