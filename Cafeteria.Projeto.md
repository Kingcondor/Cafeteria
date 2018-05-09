## Nome do grupo:

1. Caio Giovanni Pereira Vasconcelos
2. Caio Luna Farias

## Nome do projeto:

      Cafeteria

## Repositório de código no GITHUB:

https://github.com/Kingcondor/Cafeteria.git

## Descrição geral do projeto

Cafeteria é um programa que visa ser um banco de dados das operações para o estabelecimento, visando o uso do consumidor e do administrador de maneiras distintas, sendo seus modos de operar divididos em três grupos de usuários:

- Chefe: Organizar o registro de funcionários e modifica-los se assim desejar (Demissão e contratação de funcionários, registro de pagamento, registro de promoção salarial, registro de comparecimento dos funcionários, estatísticas detalhadas com relação a atividade diária dos clientes e dos funcionários, a quantidade de produtos no estoque, emissão de alertas a todos os usuários (De modo geral e individual), poder de invalidação do pedido mediante erro de solicitação de qualquer usuário);
- Funcionários:  Com a finalidade de definir um registro de sua atividade diária, registro do pagamento salarial (Alerta) pessoal do usuário, registro de promoção salarial (Alerta) pessoal do usuário, registro de comparecimento pessoal do usuário, estatísticas de trabalho diário (Histórico de vendas), quantidade de produtos no estoque, recebimento de alerta do chefe, recebimento do requerimento do pedido feito pelo cliente, poder de invalidação do pedido mediante erro de solicitação apenas de transações realizadas pelo usuário (funcionário). Além disso a classe de funcionários é dividida em dois grupos, a primaria com acesso a todas as funcionalidades supracitadas e a segundaria com acesso apenas a consulta de dados pessoais (sem direito a realização de transação ou interação com a classe cliente);
- Clientes: Tem acesso ao cardápio completo de produtos podendo fazer o pedido pelo próprio e marcando seu registro de pagamento, apenas para ser pago mediante a apresentação do mesmo, após a realização do pedido no aplicativo um atendente será designado para atende-lo;

Como dito nos tópicos acima, a Cafeteria tem três entidades gerais, podendo elas serem modificadas pelo usuário no caso de ter sua classe permitida:

- Pagamento: Está entidade apenas pode ser modificada pelo usuário chefe e funcionário primário, se refere a transação do cliente, e ao histórico de vendas;
- Estoque: Entidade que contabiliza a quantidade de produtos disponíveis para venda;
- Produto: Objeto a ser vendido pela classe funcionário para a classe cliente, está classe tem um valor, um nome e uma descrição;

## Requisitos do projeto

Lista de requisitos

REQ1. O sistema será separado em três tipos de usuários: Chefe, cliente e funcionários (Primários e secundários);

REQ2. O sistema terá como entrada para o usuário a utilização de login e de uma senha registrados pelo usuário no momento do cadastro, podendo ser modificado;

REQ3.   O sistema deve permitir a venda de produtos cadastrados no sistema, de quantidade positiva em estoque, salvar no histórico dos três usuários de forma individual;

REQ4. O sistema deve permitir o gerenciamento de usuários dos tipos: cliente e funcionário através do usuário do tipo chefe;

REQ5.  O sistema deve permitir corrigir erros de registro por meio dos usuários do tipo: cliente e funcionário primário;

REQ6. O sistema deve registrar as transações referentes a interação entre os usuários do tipo funcionários e os usuários do tipo cliente;

REQ7. O sistema deve registrar o debito de produtos compradas pelo usuário do tipo cliente;

REQ8. O sistema deve permitir que a usuário do tipo chefe emita alertas de caráter textual, que serão mostrados, no tipo de usuário (ou usuário especifico) designado pelo chefe , no momento da entrada do usuário alvo;

REQ9.  O sistema deve mostrar ao usuário do tipo chefe, se solicitado, a estatística diária e mensal registrada, referente a venda dos produtos e as informações pessoais dos funcionários;

REQ10. O sistema deve permitir que o usuário do tipo funcionário verifique sua atividade diária e dê sua atividade de comparecimento do dia atual como verificada;

REQ11. O sistema deve permitir que o usuário do tipo cliente selecione o produto desejado e solicite a entrega para o usuário da classe funcionário primário;

REQ12. O sistema deve permitir que o usuário do tipo funcionário receba o requerimento do usuário do tipo cliente. Este requerimento apenas será designado a um usuário do tipo funcionário primário cujo não esteja com nenhum requerimento pendente e que tenha efetuado sua entrada;

REQ13. O sistema deve permitir que o usuário do tipo funcionário primário tenha acesso ao registro de transações com o usuário do tipo cliente;
