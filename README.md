Este documento descreve as correções e melhorias realizadas no projeto após a entrega inicial, detalhando as alterações feitas em cada parte do código.

Parte 1: Corrigi o erro que impedia o funcionamento correto da requisição {id}/produtos, assim como também arrumei o problema de ter uma lista de produtos para todas as categorias.

Resolução parte 1: No controller troquei o @RequestParam por @PathVariable("id"). Para corrigir o problema das listas, dentro do Service, substitui a lógica incorreta de produtoList = produto para produtosList.add(produto).

Parte 2: Foi pedido pela avaliação que quando houver uma requisição GET em /categoria deve ser retornado uma lista de todas as categorias. Caso seja utilizada a rota /categoria?nome=<nome>, deve ser retornado apenas a categoria informada.

Resolução parte 2: No controller alterei o retorno para ResponseEntity<Object> que retorna tanto um Optional<Categoria> quanto uma List<Categoria>. Para retornar apenas a categoria desejada implementei um método no service que filtra os nomes das categorias e retorna a que tiver o mesmo valor da requisição.

Parte 3: Criei métodos de persistência que salvam as informações adicionadas pelas requisições POST, tanto para produtos quanto para categorias. Também fiz com que o método Delete de produto, além de deletar da list principal que é a de produtos, também remova da lista de produtos dentro da respectiva categoria.

Resolução parte 3: Dentro de cada Service adicionei um método que, para salvar os dados, possui um nome de arquivo .json e utiliza o Mapper e o File. Para deletar adicionei um laço for que quando encontrar uma categoria com o mesmo id do idCategoria do produto, delete esse mesmo produto pegando a id dele como referência.