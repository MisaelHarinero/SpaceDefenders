# SpaceDefenders
Juego desarrolado en base java, no he usado ninguna libreria externa y los sprites son libres de internet.
## Funcionamiento de la clase de los Sprites 
### Marine / Marine.class 

  Clase que hereda de de Sprite, y que modifica sus atributos para actuar como un Marine Espacial
 En la que tenemos una tabla de estados dependiendo de los estados realizara una accion :
 
|TABLA DE ACCION   |  |
|--|--|
| W |    Cuando se encuentra en estado W nuestro personaje se mueve | 
| S |    Cuando se encuentra en estado S nuestro personaje se queda parado en el sitio |
| A |    Cuadno se encuentra en estado A nuestro personaje esta atacando |
| M |    Cuando se encuentra en estado M nuestro personaje esta muerto |
| R |    Cuando se encuentra en estad  R nuestro personaje esta recargando |



Dicha clase tiene varios metodos para controlar el correcto funcionamiento de nuestro sprite como marine,
metodos como `setSpritesForMovement()` para modificar el `BufferedImage` de nuestro marine y simular el movimiento de este. 

 
### Zerg / Zerg.class

Clase que hereda de de Sprite, y que modifica sus atributos para actuar como un Zerg
En la que tenemos una tabla de estados dependiendo de los estados realizara una accion :

|TABLA DE ACCION   |  |
|--|--|
| W | Cuando se encuentra en estado W nuestro personaje se mueve |
| S |    Cuando se encuentra en estado S nuestro personaje se queda parado en el sitio |
| A |    Cuadno se encuentra en estado A nuestro personaje esta atacando |
| M |    Cuando se encuentra en estado M nuestro personaje esta muerto |
| B |    Cuando se encuentra en estad  B nuestro personaje estara realizando su habilidad especial |

Dicha clase tiene varios metodos para controlar el correcto funcionamiento de nuestro sprite como zerg,
metodos como `doAbility()` para realizar la habilidad en la que nuestro zerg es invulnerable y recupera vida. 


