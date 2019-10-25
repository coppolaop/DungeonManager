# DungeonManager
Um sistema de auxílio para mesas de RPG

## Ponto de Partida

Você, mestre de RPG de mesa, já teve dificuldades com relação à ordenação de Iniciativa?
Está cansado de ficar rabiscando os mesmos nomes de personagens em uma folha limpa?
Não aguenta mais ficar perguntando a todo momento para seu grupo qual o HP máximo de cada personagem?

Você, jogador de RPG de mesa, está cansado do seu mestre lhe perguntando toda hora quanto dano você já tomou?
Perdeu mais uma vez o seu turno, atoa, por que pularam a sua vez sem querer?
Já ficou com a impressão de que sua magia acabou antes do que deveria apenas por seu grupo não anotar que turno estavam?

Aqui está a solução para estes pequenos problemas, o Dungeon Manager.
Este sistema ajudará o mestre a organizar os combates de sua mesa de RPG.
Observe as funcionalidades:

```
- Registro de parte da ficha dos personagens ( Nome, HP Máximo, HP Atual, CA )
- Acompanhamento de ordem de inciativa
- Log do andamento de cada combate
- Registro de quantidade de turnos
  
```

### Instalação

A aplicação é um arquivo executável, basta colocá-lo no diretório de interesse.
No inicio da primeira execução, o sistema cria um diretório chamado resourses.
Devido a isso, não recomendamos que o arquivo executável seja aberto na area de trabalho, ao invés disso, sugerimos que ele seja guardado em um diretório..

Os arquivos de propriedades com dados dos personagens ficaram em dois diretórios na pasta resources.
Os personagens dos Jogadores ficam na pasta pj e os personagens do mestre ficam na pasta pdm.
Na primeira execução, um exemplo de cada um será criado.
A aplicação cria novos arquivos com base nos personagens criados dentro dela.

O sistema gera um log a cada execução, ele ficará guardado na pasta log, dentro do diretório resources.
A pasta img é criada pelo sistema para armazenar as imagens dos personagens criados pela aplicação,
dentro dela é contida uma pasta pdm para imagens dos PDMs, e uma pj, para imagens dos PJs.

```
- DungeonManager.jar
- resources
  -- pj
  -- pdm
  -- efeito
  -- log
  -- img
    ---pj
    ---pdm
  
```

## Versionamento

Nós utilizamos [SemVer](http://semver.org/) para versionamento. Para as versões disponíveis, veja as [tags nesse repositório](https://github.com/coppolaop/DungeonManager/tags). 

## Criador

* **Marcos "Coppola" Gonçalves** - *2018* - [Coppolaop](https://github.com/coppolaop)

## Desenvolvedores

* **Marcos "Coppola" Gonçalves** - *2018-2019* - [Coppolaop](https://github.com/coppolaop)
* **Vitor Abel** - *2018-2019* - [vitor-abel](https://github.com/vitor-abel)
* **Igor Huayck** - *2019* - [Flaigor](https://github.com/Flaigor)

## Testadores

* **Marcos "Coppola" Gonçalves** - *2019-2019* - [Coppolaop](https://github.com/coppolaop)
* **Vitor Abel** - *2019-2019* - [vitor-abel](https://github.com/vitor-abel)

## Licença

Esse projeto é licensiado sobre a licença Apache-2.0 - veja a [LICENSE.md](LICENSE) para mais detalhes.
