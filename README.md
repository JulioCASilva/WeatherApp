# Weather App

Aplicativo Android de clima que consome a [OpenWeather API](https://openweathermap.org/api),
exibindo temperatura, umidade, pressão e condições de várias cidades.

Este projeto é a solução de um desafio técnico: **correção dos bugs** do projeto original
e **evolução** com novas telas e funcionalidades em **Kotlin + Jetpack Compose**.

<p align="center">
  <img src="weather_app.gif" alt="Demonstração do Weather App" height="500">
</p>

> Fork do desafio original: [karinegmg/WeatherApp](https://github.com/karinegmg/WeatherApp)

> O design da interface foi prototipado com o auxílio do **Figma Make**.

---

## Tecnologias

- **Kotlin** + **Jetpack Compose**
- Arquitetura **MVVM** (ViewModel + LiveData)
- **Retrofit** (consumo da API)
- **SharedPreferences** (persistência das cidades)
- **FusedLocationProvider** (localização atual)

---

## Como rodar

1. Gere uma chave gratuita em https://openweathermap.org/api
2. No arquivo `local.properties` (raiz do projeto), adicione:
   `WEATHER_API_KEY=sua_chave_aqui`
3. Sincronize o Gradle e rode o app.

> A chave da OpenWeather pode levar até ~2h para ativar após ser criada.

---

## Correção dos bugs (listados no desafio)

**1. Chave da API (local.properties)**
A chave não estava configurada, então a requisição voltava com **401 (não autorizado)**.
Bastou gerar a chave na OpenWeather e adicionar no `local.properties`.

**2. Pressão fixa (hardcode)**
O código mostrava sempre o mesmo valor fixo (`1008.2`). Passei a ler o valor real da API
(`main.pressure`), então cada cidade exibe a pressão correta.

**3. Botão de Refresh**
O botão só mostrava um Toast "Not Implemented". Criei o método `refresh()` no ViewModel,
que cancela a busca automática agendada (`removeCallbacks`) — para não duplicar ciclos —
e dispara uma nova busca na hora.

**4. Ícones quebrados** (eram dois problemas)
- **Nome do arquivo errado:** o código procurava `weather_icon_03d`, mas o arquivo estava
  salvo como `weather_icon_03dd` (com "dd" a mais), caindo no ícone padrão do Android.
- **Faltavam tratamentos:** o switch só cobria de `02d` a `09n`. Criei a função `iconVector()`,
  que mapeia todos os tipos de clima e sempre devolve um ícone válido.

**5. Dados duplicados**
O `getLocalizations()` retornava coordenadas repetidas, fazendo a mesma cidade aparecer
mais de uma vez. Corrigi no ViewModel com um `LinkedHashSet`, que remove as duplicatas
e preserva a ordem original.

---

## Bugs extras encontrados (não estavam na lista)

**1. Conversão de temperatura errada**
A conta de Kelvin para Celsius usava `275.15` em vez de `273.15`, deixando toda
temperatura 2 graus mais fria. Corrigi para o valor certo.

**2. Card principal mudava sozinho no refresh**
As cidades são buscadas em paralelo, e o código montava a lista na ordem em que as
respostas chegavam (imprevisível), fazendo o card do topo trocar a cada refresh.
Corrigi guardando cada resultado em uma posição fixa, garantindo ordem determinística.

---

## Melhorias e novas funcionalidades

- **Migração para Jetpack Compose:** reconstruí a UI (antes XML + RecyclerView),
  deixando o código componentizado e organizado.
- **Tela de detalhes:** ao tocar num card, abre uma tela com mais informações da cidade
  (navegação funcional).
- **Fundo dinâmico:** o gradiente muda conforme o clima da cidade (e dia/noite).
- **Idioma e país reais:** descrições em pt-BR (`lang=pt_br`) e país lido da API
  (`sys.country`), em vez de fixo.
- **Adicionar / remover cidades:** modal com busca (capitais do Brasil) e botão para excluir.
- **Persistência:** as cidades monitoradas são salvas com SharedPreferences.
- **Estado vazio:** quando não há cidades, exibe um botão para adicionar.
- **Localização atual (GPS):** o card principal usa a localização real via
  FusedLocationProvider, com permissão em runtime e fallback se negada.
- **Splash screen e ícone** customizados (SplashScreen API).
- **Limpeza:** removido todo o código legado da migração (adapter, layouts XML,
  classes utilitárias e recursos órfãos).

---

## Arquitetura

O app segue **MVVM**:
- **Model:** Repository, Retrofit, modelos de dados.
- **ViewModel:** mantém o estado (`LiveData`) e expõe ações (refresh, addCity, removeCity).
- **View:** telas em Compose, que observam o estado e disparam ações — sem acessar
  o Repository diretamente.

---

## Decisões de escopo

Priorizei corrigir e validar todos os bugs pedidos antes dos extras, garantindo que cada
correção estava sólida. As funcionalidades adicionais foram entregues completas e testadas,
preferindo qualidade a quantidade.



