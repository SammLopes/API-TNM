# API-TNM
API DE GERENCIAMENTO DE PEDIDOS E CAIXA

Essa API está incompleta ela tem o objetivo de armazenar, recuperar, atulizar e deletar pedidos, produtos clientes e etc..., e também gerenciar pedidos de um estabeleciemnto, também pensamos futuramente fazer o caixa de todas as vendas do dia, e realizar o controle fincanceiro do estabelecimento, 
Rotas disponíveis
A api tem uma rota com autenticação que é a admin/usuario, e rota de controle de produtos /produtos.
A ape está rodando no localhost8083: logo para acessar as rotas vai ser da seguinte forma:

URL: admin
```
'http://localhost:8083/admin/usuario/"subrota"
```
URL: produto
```
'http://localhost:8083/produto/"subrota"
```
Basta utilizar  um metodo de para realizar requisições para a API, podemos por exemplo utilizar no JQuery com o metodo Ajax, e passamos essa url e teremos um JSON como esse de respost:

JSON
```
[
	{
		"produtoId": 2,
		"name": "Churros Morango",
		"descricao": "Churros crocantes com cobertura de morango",
		"preco": 3.75
	},
	{
		"produtoId": 3,
		"name": "Churros Doce de Leite",
		"descricao": "Churros crocantes com cobertura de doce de leite",
		"preco": 3.75
	},
	{
		"produtoId": 4,
		"name": "Espetinho",
		"descricao": "Churrasco no espeto",
		"preco": 8.0
	},
	{
		"produtoId": 5,
		"name": "Porção de 20",
		"descricao": "Tigelacom vinagrate farofa feijão e carne",
		"preco": 8.0
	}
]
```
