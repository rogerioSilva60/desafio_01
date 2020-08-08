# desafio_01

## Configuração

Para executar o projeto, será necessário instalar os seguintes programas:

- [JDK 11: Necessário para executar o projeto Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3.5.4: Necessário para realizar o build do projeto Java](https://maven.apache.org/docs/3.5.4/release-notes.html)
- [Spring Tools 4 for Eclipse: Para desenvolvimento do projeto](https://spring.io/tools)
- [PostgreSql: Para conexão com o banco de dados](https://www.postgresql.org/download)

Para executar o projeto, é necessário utilizar o Spring Tools 4 for Eclipse, para que o mesmo identifique as dependências necessárias para a execução no repositório .m2 do Maven. Uma vez importado o projeto, será criado um arquivo *.classpath* que irá informar qual a classe principal para a execução
 e configurar a conexão com o banco de dados, ex:

![conexao-postgres](https://user-images.githubusercontent.com/23174611/89718422-b8616800-d994-11ea-9085-4f0f3ff1401c.png)

Por fim, basta acessar a url: http://localhost:8080/swagger-ui.html#/ na máquina que esteja executando o projeto, Terá a documentacao da api pelo Swagger.

![swagger-desafio](https://user-images.githubusercontent.com/23174611/89718176-5d7b4100-d993-11ea-85de-b064bb806698.png)
