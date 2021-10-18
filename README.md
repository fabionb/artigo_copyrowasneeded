# High CPU causado pelo método copyRowsAsNeeded do driver do Oracle
Este repositório foi utilizado para a escrita do artigo sobre alto uso de CPU no método copyRowsAsNeeded do Driver do Oracle

## Requisitos
Rodar um Oracle com o seguinte comando:
```
docker run -d --name oracle --network host -e ORACLE_ALLOW_REMOTE=true oracleinanutshell/oracle-xe-11g
```

## Execução
Para rodar o código, executar o seguinte comando:
```
./gradlew bootRun
```

## URL's
Para criar os registros necessários, abra a seguinte URL. O id retornado pode ser utilizado nas próximas URL's:

http://localhost:8081/create

Teste que evidencia o problema:

http://localhost:8081/test/1

Para verificar o problema, pode ser necessário rodar o seguinte comando e tirar alguns thread dump's ou fazer o profiler com o VisualVM:
```
ab -c 10 -n 100 http://localhost:8081/test/1
```

Teste que corrige o problema:

http://localhost:8081/testCorrection/1
