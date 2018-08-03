Nome do grupo:<br><br>
1. Caio Giovanni Pereira Vasconcelos<br>
2. Caio Luna Farias<br><br>
Nome do projeto:<br><br>
      Coffe<br><br>
Repositório de código no GITHUB:<br><br>
https://github.com/Kingcondor/Cafeteria.git<br><br>
Descrição geral do projeto<br><br>
Coffe é um programa que visa ser um banco de dados das operações para o estabelecimento, visando o uso do consumidor e do administrador de maneiras distintas, sendo seus modos de operar divididos em três grupos de usuários:<br>
Gerente: Organizar o registro de funcionários e modificá-los se assim desejar (Demissão e contratação de funcionários, registro de pagamento, registro de promoção salarial, registro de comparecimento dos funcionários, estatísticas detalhadas com relação a atividade diária dos clientes e dos funcionários, a quantidade de produtos no estoque, emissão de alertas a todos os usuários (De modo geral e individual), poder de invalidação do pedido mediante erro de solicitação de qualquer usuário);<br>
Funcionários:  Com a finalidade de definir um registro de sua atividade diária, registro do pagamento salarial (Alerta) pessoal do usuário, registro de promoção salarial (Alerta) pessoal do usuário, registro de comparecimento pessoal do usuário, estatísticas de trabalho diário (Histórico de vendas), quantidade de produtos no estoque, recebimento de alerta do gerente, recebimento do requerimento do pedido feito pelo cliente, poder de invalidação do pedido mediante erro de solicitação apenas de transações realizadas pelo usuário (funcionário). Além disso a classe de funcionários é dividida em dois grupos, os atendentes com acesso a todas as funcionalidades supracitadas e os comuns com acesso apenas a consulta de dados pessoais (sem direito a realização de transação ou interação com a classe cliente);<br>
Clientes: Tem acesso ao cardápio completo de produtos podendo fazer o pedido pelo próprio e marcando seu registro de pagamento, apenas para ser pago mediante a apresentação do mesmo, após a realização do pedido no aplicativo um atendente será designado para atendê-lo;<br>
Como dito nos tópicos acima, a Cafeteria tem três entidades gerais, podendo elas serem modificadas pelo usuário no caso de ter sua classe permitida:<br>
Pagamento: Está entidade apenas pode ser modificada pelo usuário gerente, se refere a transação do cliente, e ao histórico de vendas;<br>
Estoque: Entidade que contabiliza a quantidade de produtos disponíveis para venda;<br>
Produto: Objeto a ser vendido pela classe funcionário para a classe cliente, está classe tem um valor, um nome e uma descrição;<br><br>
Requisitos do projeto<br><br>
REQ1. O sistema será separado em três tipos de usuários: Gerente, cliente e funcionários (atendentes e comuns);<br>
REQ2. O sistema terá como entrada para o usuário a utilização de login e de uma senha registrados pelo usuário no momento do cadastro, podendo ser modificado;<br>
REQ3.   O sistema deve permitir a venda de produtos cadastrados no sistema, de quantidade positiva em estoque, salvar no histórico dos três usuários de forma individual;<br>
REQ4. O sistema deve permitir o gerenciamento de usuários dos tipos: cliente e funcionário através do usuário do tipo gerente;<br>
REQ5. O sistema deve registrar as transações referentes a interação entre os usuários do tipo cliente e a entidade produto;<br>
REQ6. O sistema deve registrar o debito de produtos compradas pelo usuário do tipo cliente;<br>
REQ7. O sistema deve permitir que a usuário do tipo gerente emita alertas de caráter textual, que serão mostrados, no tipo de usuário (ou usuário específico) designado pelo gerente, no momento da entrada do usuário alvo;<br>
REQ8.  O sistema deve mostrar ao usuário do tipo gerente, se solicitado, a estatística diária e mensal registrada, referente a venda dos produtos e as informações pessoais dos funcionários;<br>
REQ9. O sistema deve permitir que o usuário do tipo funcionário verifique sua atividade diária e dê sua atividade de comparecimento do dia atual como verificada;<br>
REQ10. O sistema deve permitir que o usuário do tipo cliente selecione o produto desejado e solicite a entrega para o funcionário do tipo atendente;<br>
REQ11. O sistema deve permitir que o usuário do tipo atendente tenha acesso ao registro de transações com o usuário do tipo cliente;<br>

Arquitetura<br><br>
O  programa começa na classe Main que instancia um ControladorFacade, que em caso de falhas nas funções básicas do programa um erro é exibido e o programa termina. Caso não haja erros, mas a GUI tiver algum, o modo de Terminal é iniciado no lugar.<br><br>
Tanto o Terminal quanto a GUI chamam os métodos do ControladorFacade durante a comunicação com o usuário. Essa facade une todos os controladores, que têm acesso aos repositórios que foram construídos seguindo o design pattern Sigleton. Os repositórios implementam uma interface cada um, garantindo que determinados métodos sempre vão estar disponíveis independentemente da implementação de cada repositório.<br><br>
As informações de que tipo de usuário (Conta) está sendo tratado é sempre transmitida com o enum Tipo para restringir as opções apenas às disponíveis.<br><br>
A GUI envia a própria classe Main para cada Controller iniciado, isso permite que ele tenha acesso ao ControladorFacade e um dos métodos showOnBorder que se sobrepõe ao outro tendo o parâmetro tittle como diferencial. Esse método é responsável por criar pequenas janelas (uma nova Scene) por cima da janela principal para exibir mensagens ou obter informações do usuário, enquanto o showOnBorder sem o parâmetro tittle é responsável pela janela principal.<br><br>
Os beans fazem parte do funcionamento interno do programa, sendo que a view não tem conhecimento de quais beans são utilizados (se comunicando através do enum Tipo, que dificilmente mudará pois depende da organização de uma cafeteria, não do funcionamento interno do programa).
