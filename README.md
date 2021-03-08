# Campfire

**Campfire** es una red social más minimalista y personal que el resto. Su objetivo es crear un ambiente más acogedor y menos invasivo en el cuál las personas puedan compartir sus experiencias y problemas de una manera más íntima.

El funcionamiento principal de **Campfire** se basa en el uso de **hogueras** que pueden ser creadas por cualquier usuario registrado. Las hogueras actúan a modo de salas de conversación con un número reducido de usuarios. Estos usuarios podrán ser amigos o gente anónima y/o desconocida. Cada usuario dispondrá de un avatar que representará su presencia en cada hoguera. Los avatares se podrán personalizar a través de un sistema de puntos que se obtendrán al participar en hogueras.

Además, los usuarios registrados tendrán la posibilidad de difundir mensajes anónimos que se mostrarán a cualquier persona que acceda a la página principal de **Campfire**, esté registrada o no.

## Entidades principales
- Usuario: Almacena la información de registro y del avatar.
- Post: Representa un mensaje público que se mostrará en la página principal.
- Hoguera: Representa una sala privada en la que un grupo de usuarios podrán conversar.
- Mensaje: Contiene información relativa a un mensaje como la hoguera a la que pertenece o el usuario que lo ha escrito.
- Filtro: Almacena las diferentes opciones de filtrado para manejar los permisos de acceso a una hoguera.
- Lista de amigos: Contiene la información referente a los amigos de un usuario.

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
Es la pantalla inicial, la que es cargada al iniciar la página. Desde esta pantalla se puede tanto iniciar sesión como registrarse. Si se hace click en el botón de Register, se llevará al usuario a la pantalla de registro. Hay dos campos donde se puede introducir texto, que corresponden al nombre de usuario y a su contraseña. Si los valores introducidos son correctos y se hace click en el botón de Log In, se avanzará hacia la pantalla Home.

### Register
![REGISTER](https://user-images.githubusercontent.com/38223068/110367869-86a6a180-8048-11eb-9e3a-0cd4c3ce646e.PNG)
En la página de Register, el usuario podrá crear una nueva cuenta dando el nickname y la contraseña deseados. Si el nickname ya ha sido escogido por algún otro usuario, el usuario deberá volver a introducir los datos. Al completar al registro se le llevará a Log In.

### Home
![HOME](https://user-images.githubusercontent.com/38223068/110367970-ae960500-8048-11eb-9dbf-b6a100a08f39.PNG)
En la página Home se le da al usuario las opciones de entrar a las 3 pantallas que se explican a continuación. Además, el usuario podrá hacer log-out, lo que le llevará a la pantalla de Log In.

### Public Post
![PUBLIC POST](https://user-images.githubusercontent.com/38223068/110368242-10ef0580-8049-11eb-9bc4-afb3c074c584.PNG)
En esta pantalla el usuario puede publicar mensajes que serán vistos por los visitantes en el Log In. Cuenta con un botón para decidir si el mensaje será público o anónimo, si es público, el nombre del autor del mensaje aparecerá, si se configura como anónimo aparecerá el nombre de autor como anónimo. Desde esta pantalla se puede volver a la pantalla de Home.

### Avatar
![MicrosoftTeams-image (2)](https://user-images.githubusercontent.com/38223068/110368392-44319480-8049-11eb-8d31-150c3ae1c58f.png)
En la pantalla de Customización del Avatar, se puede acceder a las distintas opciones de personalización de nuestro avatar. De esta manera, podremos cambiar el color principal, secundario y el de los ojos, así como el gorro y un accesorio. Cuando el usuario esté satisfecho con los cambios, puede guardarlos al pulsar el botón.

### Chat Lobby
![CHATLOBBY](https://user-images.githubusercontent.com/38223068/110368041-c7061f80-8048-11eb-8195-520e7c6fc619.PNG)
Se trata de la página en la que el usuario es capaz de crear salas de chat y unirse a las ya existentes. En ella hay un campo para introducir texto que permite la creación de salas. Más abajo, se muestra el listado de las salas creadas. Si el usuario hace click en un chat, será llevado hacia la pantalla de Chat. Desde esta pantalla se puede volver a la pantalla de Home.

### Chat
![CHATROOM](https://user-images.githubusercontent.com/38223068/110368084-d4230e80-8048-11eb-8fc6-5af4874f9120.PNG)
Es la página en la que los usuarios pueden enviarse mensaje entre ellos siempre que estén dentro de la misma sala. Hay un campo para introducir texto que permite la creación de mensajes, al enviarlos son recibidos por el resto de usuarios de esa sala y se indica el nombre del usuario que ha mandado el mensaje. Desde esta pantalla se puede retornar a la pantalla de Chat Lobby.

## Diagrama de navegación
![FlujoCampfire](https://user-images.githubusercontent.com/38223068/110373187-8067f380-804f-11eb-9f20-0d1cccedd57a.png)

## Modelo de datos
### Diagrama de clases UML
![CampfireEntityRelation](https://user-images.githubusercontent.com/38223068/110373395-bc02bd80-804f-11eb-9274-b9f7b2eb77a9.png)

### Diagrama Entidad/Relación
