# Campfire

**Video demostración:** https://youtu.be/vOihchoVhcU

**Campfire** es una red social más minimalista y personal que el resto. Su objetivo es crear un ambiente más acogedor y menos invasivo en el cuál las personas puedan compartir sus experiencias y problemas de una manera más íntima.

El funcionamiento principal de **Campfire** se basa en el uso de **hogueras** que pueden ser creadas por cualquier usuario registrado. Las hogueras actúan a modo de salas de conversación con un número reducido de usuarios. Estos usuarios podrán ser amigos o gente anónima y/o desconocida. Cada usuario dispondrá de un avatar que representará su presencia en cada hoguera. Los avatares se podrán personalizar a través de un sistema de puntos que se obtendrán al participar en hogueras.

Además, los usuarios registrados tendrán la posibilidad de difundir mensajes anónimos que se mostrarán a cualquier persona que acceda a la página principal de **Campfire**, esté registrada o no.

## Entidades principales
- Usuario: almacena la información de registro y de la lista de amigos.
- Avatar: Simboliza la información de customización del avatar de cada usuario.
- Post: Representa un mensaje público que se mostrará en la página principal.
- Hoguera: Representa una sala privada en la que un grupo de usuarios podrán conversar.
- Mensaje: Contiene información relativa a un mensaje como la hoguera a la que pertenece o el usuario que lo ha escrito.

## Descripción del servicio interno
**Campfire** contará con un servicio interno de filtrado de palabras malsonantes. El mensaje se le enviará al servicio interno antes de almacenarlo en la base de datos.

## Miembros del equipo
- José Luis Murcia Gámez, jl.murcia.2017@alumnos.urjc.es, JoseLuisMurcia
- Álvaro Roger Zapata, a.roger.2017@alumnos.urjc.es, AlvaroRoger
- Germán Calcedo Pérez, g.calcedo.2017@alumnos.urjc.es, G-Calcedo

## Trello
https://trello.com/b/X43atRfn/campfire

## Páginas

### Log In
![INI](https://user-images.githubusercontent.com/38223068/110367837-7b537600-8048-11eb-9ce4-99f4d85c46da.PNG)
<br/>
Es la pantalla inicial, la que es cargada al iniciar la página. Desde esta pantalla se puede tanto iniciar sesión como registrarse. Si se hace click en el botón de Register, se llevará al usuario a la pantalla de registro. Hay dos campos donde se puede introducir texto, que corresponden al nombre de usuario y a su contraseña. Si los valores introducidos son correctos y se hace click en el botón de Log In, se avanzará hacia la pantalla Home.

### Register
![REGISTER](https://user-images.githubusercontent.com/38223068/110367869-86a6a180-8048-11eb-9e3a-0cd4c3ce646e.PNG)
<br/>
En la página de Register, el usuario podrá crear una nueva cuenta dando el nickname y la contraseña deseados. Si el nickname ya ha sido escogido por algún otro usuario, el usuario deberá volver a introducir los datos. Al completar al registro se le llevará a Log In.

### Home
![HOME](https://user-images.githubusercontent.com/38223068/110367970-ae960500-8048-11eb-9dbf-b6a100a08f39.PNG)
<br/>
En la página Home se le da al usuario las opciones de entrar a las 3 pantallas que se explican a continuación. Además, el usuario podrá hacer log-out, lo que le llevará a la pantalla de Log In.

### Friend List
![FriendList_screenshot](https://user-images.githubusercontent.com/49962993/110463190-e301d380-80d1-11eb-9bdd-09ca0d04c078.png)
<br/>
En esta pantalla los usuarios podrán visualizar su lista de amigos actual. También podrán enviar peticiones de amistad a otros usuarios que todavía no sean sus amigos introduciendo su nombre de usuario. Por último, se muestran las peticiones de amistad pendientes. Para cada una, se presenta el nombre de usuario de la petición entrante y la posibilidad de aceptar o rechazar la petición. Si la petición es rechazada, simplemente se eliminará de la lista. Si por el contrario es aceptada, a partir de ese momento ambos usuarios involucrados incluirán al otro en su lista de amigos.

### Friend Request
![FriendRequest_screenshot](https://user-images.githubusercontent.com/49962993/110463202-e5642d80-80d1-11eb-9bc4-fb28ee1e0572.png)
<br/>
En esta página se muestra el resultado del envío de la petición de amistad. Si el nombre de usuario introducido existe y todavía no es amigo del usuario que envía la petición, se enviará pues dicha petición de amistad. En cambio, si el usuario no existe, ya es amigo nuestro, tiene una petición nuestra pendiente o nos intentamos mandar una invitación a nosotros mismos, la página nos informará de que no ha sido posible enviar la petición.

### Public Post
![PUBLIC POST](https://user-images.githubusercontent.com/38223068/110368242-10ef0580-8049-11eb-9bc4-afb3c074c584.PNG)
<br/>
En esta pantalla el usuario puede publicar mensajes que serán vistos por los visitantes en el Log In. Cuenta con un botón para decidir si el mensaje será público o anónimo, si es público, el nombre del autor del mensaje aparecerá, si se configura como anónimo aparecerá el nombre de autor como anónimo. Desde esta pantalla se puede volver a la pantalla de Home.

### Avatar
![MicrosoftTeams-image (2)](https://user-images.githubusercontent.com/38223068/110368392-44319480-8049-11eb-8d31-150c3ae1c58f.png)
<br/>
En la pantalla de Customización del Avatar, se puede acceder a las distintas opciones de personalización de nuestro avatar. De esta manera, podremos cambiar el color principal, secundario y el de los ojos, así como el gorro y un accesorio. Cuando el usuario esté satisfecho con los cambios, puede guardarlos al pulsar el botón.

### Chat Lobby
![CHATLOBBY](https://user-images.githubusercontent.com/38223068/110368041-c7061f80-8048-11eb-8195-520e7c6fc619.PNG)
<br/>
Se trata de la página en la que el usuario es capaz de crear salas de chat y unirse a las ya existentes. En ella hay un campo para introducir texto que permite la creación de salas. Más abajo, se muestra el listado de las salas creadas. Si el usuario hace click en un chat, será llevado hacia la pantalla de Chat. Desde esta pantalla se puede volver a la pantalla de Home.

### Chat
![CHATROOM](https://user-images.githubusercontent.com/38223068/110368084-d4230e80-8048-11eb-8fc6-5af4874f9120.PNG)
<br/>
Es la página en la que los usuarios pueden enviarse mensaje entre ellos siempre que estén dentro de la misma sala. Hay un campo para introducir texto que permite la creación de mensajes, al enviarlos son recibidos por el resto de usuarios de esa sala y se indica el nombre del usuario que ha mandado el mensaje. Desde esta pantalla se puede retornar a la pantalla de Chat Lobby. Además, durante la estancia de un usuario en la sala, sus propios mensajes enviados aparecerán en azul y los de sus amigos en rojo. Los mensajes de usuarios que no estén en su lista de amigos aparecerán en negro.

## Diagrama de navegación
![FlujoCampfire_2](https://user-images.githubusercontent.com/49962993/110462849-7f77a600-80d1-11eb-9567-1f7d82b1409e.png)

## Modelo de datos
### Diagrama de clases UML
![CampfireUML_2](https://user-images.githubusercontent.com/49962993/110462880-8999a480-80d1-11eb-8ee6-cafaa9e1e9ad.png)

### Diagrama Entidad/Relación
![CampfireEntityRelation_2](https://user-images.githubusercontent.com/49962993/110462909-928a7600-80d1-11eb-97d6-5c296e3e3cfc.png)

## Diagrama de clases
![Campfire](https://user-images.githubusercontent.com/49962993/114434976-e8b47280-9bc3-11eb-869f-3a144d8f7844.png)

## Instrucciones para desplegar la aplicación
Se parte de una máquina virtual vacía que solo tiene el Sistema Operativo, en este caso Ubuntu, instalado.

### Generar .jar
Para ello se debe navegar hasta el directorio de la aplicación donde se encuentre el pom.xml y ejecutar: **.\mvnw package**
Esto genera un .jar que se encuentra en la carpeta /target, luego este .jar se pasa a la máquina virtual.
Para pasar el .jar a la máquina virtual, se ha creado una carpeta en el SO anfitrión que se ha compartido al SO guest.

### Instalar JRE y JDK
Una vez se tiene el .jdk en la máquina virtual, va a hacer falta tener tanto el JRE y el JDK instalados, para ello,
se ejecutarán los comandos: **sudo apt install default-jre** y **sudo apt install default-jdk**.

### Instalar y configurar MySQL 
Con el JRE y JDK no basta para ejecutar la aplicación principal, ya que hace uso de una BD MySQL, por lo que también debe ser instalada, para ello se ejecuta el comando:
**sudo apt install mysql-server**.
Una vez se ha instalado MySQL, hay que configurarlo, elegir contraseña y usuario, para ello ejecutar: **sudo mysql_secure_installation**
<br/>
Con esa configuración realizada, hay que ejecutar MySQL y para ello: **sudo mysql**. Una vez estamos dentro, como la aplicación hace uso de una contraseña, hay que cambiar la contraseña del usuario root por la indicada en el archivo application.properties de la aplicación principal.
Para ello ese ejecuta el comando: **ALTER USER 'root'@'localhost' IDENTIFIED WITH caching_sha2_password BY 'distribuidas';**
<br/>
El último paso es crear el esquema o BBDD, para ello se utiliza el comando: **CREATE DATABASE campfire;** y es importante indicar a MySQL que esa va a ser la BBDD que se utilice, para ello: **USE campfire;** y ya podemos salir de MySQL.

### Ejecutar la aplicación
Con todos los pasos anteriores cumplidos, ejecutar la aplicación deseada requiere el siguiente comando: **java -jar nombre_archivo.jar**, en nuestro caso, se han abierto dos terminales distintas para ejecutar tanto la aplicación principal como el servicio interno de manera independiente.

## Interfaz Servicio Interno
El servicio interno, Langfilter, es usado para filtrar los mensajes enviados por los usuarios en los chats. Esto se logra comparando los mensajes con una lista de palabras malsonantes y eliminándolas. Por ello, la interfaz del servicio interno únicamente expone un método RPC.

- String filter(String msg): devuelve msg filtrado. El filtro elimina palabras malsonantes.

![InterfazSI](https://user-images.githubusercontent.com/49457798/118026546-2f32f380-b361-11eb-8402-9f8d76282159.png)

## Arquitectura Docker
![ArchDAD](https://user-images.githubusercontent.com/49457798/118027208-f6dfe500-b361-11eb-8050-a1dd0d66fefb.png)

