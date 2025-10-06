Rodar o Application pelo IntelliJ.

Parte 1: Arrumei o erro de não funcionar a requisição {id}/produtos, assim como também arrumei o erro de ter uma única lista de produtos para todas as categorias.

Resolução parte 1: troquei o @RequestParam por @PathVariable("id"), no casos das listas eu estava fazendo this.produtosList = produtosList apenas adicionei um novo construtor para realizar o produtosList.add(produto). Erros simples mas na hora do nervosismo, como em uma avaliação, acontecem
