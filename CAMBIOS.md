# Cambios de Mi Rama:

## Commit: 5

En este commit empiezo a documentar cambios en mi rama. Para resumir, estos son
los cambios de commits anteriores:

- Creado: Folders con JSPs.
- Agregado: El proyecto de la rama `Developer` para que podamos previsualizar
  nuestro trabajo.
- Cambiado: Nombres de los JSP para que sea más fácil de entender qué hacen (a
  expensas de la legibilidad del URL final).
- Terminado: El [index](ByteAndBuild/src/main/webapp/index.jsp) en su versión
  preliminar. Cambios se pueden realizar, pero, de momento, lo considero bien 
  hecho para la funcionalidad limitada de nuestra "tienda de hardware".

Ahora, con los cambios de *este* commit:

- Creado: Este documento.
- Inicio: De la documentación de mis cambios (APRENDAN CEROTES, ASÍ SE HACE UNA
  DOCUMENTACIÓN CLARA Y CONCISA).
- Agregado: Imágenes para el Index y *tal vez* otros elementos del front-end.
- Inicio: Realización de la pestaña de [Soporte Técnico](ByteAndBuild/src/main/webapp/index.jsp).

Para entender mejor la estructura de archivos (para que aprendan los de back-end),
explicaré cómo separé los archivos y por qué es objetivamente mejor hacerlo así.

Empezando por la estructura de la URL, uno tiene que pensar en qué forma quiere
uno que un usuario navegue por su sitio web. Por eso es importante analizar
quiénes usarán tu producto y a qué están familiarizados antes de tirar cualquier
cosa (pero eso es marketing y no progra). La URL del sitio web sigue la siguiente
estructura:

`localhost:8080/ByteAndBuild/page/categoria/sitio.jsp`

Tomemos de ejemplo el Index y la página de Soporte Técnico:

`localhost:8080/ByteAndBuild/index.jsp`  
`localhost:8080/ByteAndBuild/ayuda/soporte-tecnico.jsp`

¿Lo ven? Tiene más sentido eso que esto:

`localhost:8080/ByteAndBuild/soporte-tecnico/soporte-tecnico.jsp`

Ni la plataforma de Kinal es así de desorganizada... y eso que tienen una de las
peores plataformas virtuales (como que se les olvidó su propio slogan).

De este mismo modo organicé la carpeta de [assets](ByteAndBuild/src/main/webapp/assets).
Tenemos un espacio para recursos propios de nuestra [assets](ByteAndBuild/src/main/webapp/assets/brand)
(BYTE & Build Ltd.) como logos, y otro de [imágenes](ByteAndBuild/src/main/webapp/assets/image)
para todo lo que sean imágenes externas (las que usé en el [index](ByteAndBuild/src/main/webapp/index.jsp)).

Eso es todo para el Commit 5. Adrian, mi precioso SCRUM Master, LEÉ ESTO PORFAAAAAAAAA.
No me puse chambear para que nadie lea esto. Soy mero pendejo para hablar, pero
en este medio expreso mejor y de forma más clara mis ideas y forma de pensar
al chambear en un proyecto.

---

## Commit: 6

~~En este commit, oficialmente terminé lo mío.~~ Faltaba el README.md del proyecto

- Terminado: [Index](ByteAndBuild/src/main/webapp/index.jsp). Ya tiene todo y
funciona bien.
- Terminado: [Soporte Técnico](ByteAndBuild/src/main/webapp/page/ayuda/soporte-tecnico.jsp) y
listar (llamado [problemas](ByteAndBuild/src/main/webapp/page/ayuda/problemas.jsp)). 
Ya todo está listo para funcionar con el código de Josué.

---

## Commit: 7 (Verdadero Final, Espero)

Hoy sí ya terminé. Y antes de media noche (a la hora de escribir esto; me tomé
casi 3 tazas de café hoy y me pongo más raro de lo usual cuando no duermo aiuda
;-;). Y creyeron que no me daría tiempo, JAJAJAJAJAJAJAJAJAJA... ok ya paro :v

# EL [README](README(final).md)

Mi queridísimo SCRUM Master, solo ponga ese archivo en la rama `Developer` y quite
el "(final)" del nombre del archivo. Espero no tengamos que hacer más; solo falta
el video que me fumaré el Jueves ~~junto con todo lo de Química (Prof. Palacios,
si lee esto, perdone que haya abusado de su amabilidad este bimestre después de
haberle entregado todo la primera semana el bimestre anterior).~~