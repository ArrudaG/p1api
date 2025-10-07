Parte 1: Arrumei o erro de não funcionar a requisição {id}/produtos, assim como também arrumei o erro de ter uma única lista de produtos para todas as categorias.

Resolução parte 1: Dentro do controller troquei o @RequestParam por @PathVariable("id"). Para concertar o problema das listas, dentro do service, adicionei ao construtor um produtosList.add(produto).

Parte 2: Foi pedido para a avaliação que quando tivesse uma requisição get no /categoria ouvesse um return da lista de todas as categorias e caso fosse utilizado o /categoria?nome=<nome>, fosse filtrado e retornado apenas a categoria desejada.

Resolução parte 2: Dentro do controller troquei o antigo return para que retornasse um ResponseEntity<Object> que retorna tanto um Optional<Categoria> quanto uma List<Categoria>. Com o objetivo de retornar apenas a categoria desejada fiz um construtor simples no service que filtra os nomes das categorias e retorna a que tiver o mesmo valor da requisição.
