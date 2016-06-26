# Alunos:

Felipe de Assis Pinto

Fernando Wagner Watanabe Chudzik


## Descrição

O sistema desenvolvido será um sistema de locação de carros que será chamado de Locar.

## Calculos e Testes

O sistema possuirá 3 cálculos no qual devem ser criado os testes.

---

#### Cálculo multa por atraso

Este método recebe 1 parametro apenas sendo ele do tipo Date. O método deve cálcular a diferença de dias entre a data de locação (parametro do método) com a data atual e caso o valor seja maior do que zero (0), a multa deve ser calculada multiplicando este valor por 50,00. Este valor total será retornado, sendo ele o valor da multa a ser paga.

Exemplo de retorno: "R$ 250,00"

    public String calcularMulta(Date data_dev)

---

#### Cálculo de tempo restante para devolução de carro

Este método recebe 1 parametro apenas sendo ele do tipo Date. O método deve cálcular a diferença de dias entre a data de devolução (parametro do método) com a data atual e caso o valor seja maior do que zero (0), a método deve cálcular a diferença em dias, horas, minutos e segundos entre as 2 datas e retornar esses valores em formato de String.

Exemplo de retorno: "20 dias, 5 horas, 10 minutos e 23 segundos."

    public String calcularTempoRestante(Date data_dev)

---

#### Cálculo de estabelecimento aberto/fechado

Este método não possui nenhum parametro e retorna a String "(Aberto)"/"(Fechado)" caso o estabelecimento esteja Aberto/Fechado. O método deve verificar se o horario atual esta entre os horarios de funcionamento do estabelecimento e retornar TRUE/FALSE.

Horário de Funcionamento: Todos os dias ás 10:00h ás 20:00h.

Exemplo de retorno: "(Aberto)"

    public String calcularHorarioFuncionamento()
