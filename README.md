# projetoESIG

#Orientações
Todas as funcionalidades solicitadas foram desenvolvidas;
Foram executados os passos A), B) e C) da tarefa;
O script de criação das tabelas do banco de dados possui o nome de banco.txt;
O servidor utilizado foi o TomCat 8.5 e java 8;


#Como executar
Criar um banco de dados com o nome projeto-esig;
Importar o projeto como projeto Maven no Eclipse;
Caso crie o banco com outro nome, favor alterar o nome do mesmo no arquivo persistence.xml;
Alterar o nome de usuário e senha dos PostgreSQL no arquivo persistence.xml. No momento está assim:

		<property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/projeto-esig"/>
		<property name="javax.persistence.jdbc.user" value="postgres"/>
		<property name="javax.persistence.jdbc.password" value="esig-project"/>