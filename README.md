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
