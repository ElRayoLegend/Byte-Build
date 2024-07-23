# Para los de Front-End y el SCRUM Master

**NOTA:** Este README.md no es el final. Todavía no he hecho el README del sitio
web, y este README es para que los que vayan a mi rama sepan detalles sobre como
estoy organizando el front-end (en algún punto tenía que empezar, ¿no?). En fin,
el documento donde tengo los cambios de mi rama es [este](CAMBIOS.md) Por favor,
no me crucifiquen tenía trabajo atrasado y el Wicho cerote no se ponía a 
trabajar para consentir los sueños húmedos del Harol.

> **Actualización:** Para los que no entiendan qué mierda hice acá, básicamente
> Tengo **creados** los `.jsp`. Así que ya están para que cada quién empiece a 
> trabajar. De preferencia, esperar al index para que empiecen y así tengan la
> estructura. Para más información, vayan a la `NOTA IMPORTANTE`.

> *El contenido de este README.md es temporal; no ha sido aprobado por el SCRUM 
> Master aún y está sujeto a cambios. Cualquier duda, consulta, amenaza de 
> muerte... va dirigida al SCRUM Master.*

Acá les dejo el index con la estructura de folders que usaremos los de 
front-end para trabajar. Voy a tratar de ser lo más conciso posible y dejar el 
index lo mejor documentado posible. Cualquier duda, preguntarle al SCRUM Master 
para que me ~~nalguee~~ informe de como solucionarlo. Si quieren, me preguntan 
una que otra cosa a mí, pero ahí se los dejo.

Para empezar a trabajar nuestro hermoso front-end empieza por insertar los 
siguientes comandos en tu terminal (de preferencia, Git Bash porque tiene mejor 
soporte y te dice en qué rama estás): 
`git clone https://github.com/ElRayoLegend/Byte-Build.git`,
`git checkout paulo2023238` (me enteré de que Git ya te clona *TODAS* las ramas 
por defecto, pero no las muestra hasta que te cambias a ellas). Luego copia los 
archivos de mi rama, copia y pega este comando 
`git checkout <reemplazar-con-tu-rama>`.

Yo ya generé los jsp. Solo tienen que trabajar el suyo y, en el caso de un par, 
ponerse de acuerdo para que los dos trabajen su respectiva parte de un JSP (por 
ejemplo, uno se pone a hacer la página de tienda (productos; listar por defecto) 
y otro la de agregar que está en el mismo .jsp).

## Estructura del Sitio:

El sitio tendrá una barra de navegación donde estarán los menús principales. 
Cualquier menú secundario será accedido dentro del menú principal al que se 
asocia.

Por ejemplo: El carrito se accede desde la barra de navegación, pero la factura y
las compras se acceden desde carrito.

Todo eso se verá marcado dentro de la siguiente lista donde, el nivel, explica 
desde donde se accede.

La barra de menú se debe poder acceder desde todos los menús. Los que tengan que
hacer un menú por el que se accede a otro, crean un botón para acceder a él.

Por ejemplo: En Productos, puede haber un botón de "Agregar Producto" y, por ahí,
se accede al formulario para agregar un producto (de preferencia que esté arriba
para mejor experiencia de usuario).

- Barra de Navegación:
  - Index
  - Productos
    - Agregar Producto
      - Agregar Proveedor
      - Listar Proveedor
  - Carrito
    - Factura
    - Compras
  - Clientes
    - Agregar Cliente
  - Soporte Técnico

---

## NOTA IMPORTANTE

El proyecto de la rama `Developer` ya lo copié acá para que puedan ejecutar el
proyecto y probar si les funciona o no. Ahora, en este commit, no he colocado el
trabajo de los de back-end *aún*, así que hasta que haga eso, no podremos ver si
lo nuestro funciona con lo de back-end. Pero de eso me encargaré yo... Bueno, 
depende de qué tan jodido esté el problema.

### NOTA NO TAN IMPORTANTE

No vayan a copiar esto porfa. Sé que el SCRUM Master pidió un TXT, pero el 
README.md aparece primero sin tener que abrirlo. Esto solo es para medio 
documentar lo poco que logré avanzar hoy.