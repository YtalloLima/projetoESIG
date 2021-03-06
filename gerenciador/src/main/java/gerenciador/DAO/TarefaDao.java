package gerenciador.DAO;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import gerenciador.Model.Responsavel;
import gerenciador.Model.Tarefa;
import gerenciador.Utils.JPAUtil;


public class TarefaDao extends DaoGeneric<Tarefa> implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Tarefa> findByParametros(String titulo, Integer id, long idSituacao, Responsavel responsavel){
		StringBuilder consulta = new StringBuilder();
		consulta.append(" FROM Tarefa t ");
		consulta.append(" WHERE t.ativo = true ");
		
		if(titulo != null && !titulo.isEmpty())
			consulta.append(" AND upper(t.titulo) like '%"+titulo.toUpperCase()+"%'");
		if(id != null && id > 0)
			consulta.append(" AND t.id = "+id);
		if(idSituacao > 0)
			consulta.append(" AND t.situacao.id = "+idSituacao);
		if(responsavel != null && responsavel.getId() > 0)
			consulta.append(" AND t.responsavel.id = "+responsavel.getId());
		
		consulta.append(" ORDER BY t.id ");
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		return entityManager.createQuery(consulta.toString()).getResultList();
	}
}