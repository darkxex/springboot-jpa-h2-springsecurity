
# Proyecto de Springboot que crea cuentas en una base de datos h2 y usa spring security para generar un token jwt.

Para ejecutar este proyecto, descargue el repositorio y ejecutelo con Spring Tool Suite 4. En caso de error al ejecutar es debido a que debe instalar la librería Lombok en el IDE, la útilidad de Lombok es autogenerar getter and setter de las entidades. (La clase usuario que contiene nombre, contraseña y etc.)

Al presionar clic derecho en el proyecto ejecutar como "run as Spring boot app"
![image](https://github.com/darkxex/springboot-jpa-h2-springsecurity/assets/17991404/0292e755-7269-4e0d-bd28-0a62b741391a)

Si todo fue exitoso, verá una flecha hacia arriba, y el proyecto se estará ejecutando en el puerto 9090, como indica en la imagen de arriba.
ahora para hacer los llamados al Api, usaremos postman.

# Ejecutando la Api.
![image](https://github.com/darkxex/springboot-jpa-h2-springsecurity/assets/17991404/e23ba866-d06b-431c-9cd2-54792f1ff4e5)
En la imagen superior se hizo una petición del tipo Post para enviar el siguiente body.

{
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "password": "hunter2",
  "phones": [{
     "number": "1234567",
      "citycode": "1",
      "countrycode": "57"
  }
  ]
} 

La única diferencia con el indicado es que remplacé "contrycode" por "countrycode" porque deduje que estaba mal escrito.
Al ejecutar tendremos la siguiente respuesta.
![image](https://github.com/darkxex/springboot-jpa-h2-springsecurity/assets/17991404/855abf9f-6981-4ab0-aa92-e5d82dbf11dd)
donde se aprecia el token jwt que corresponde al usuario.
Para confirmar que es un token valido hiremos a el sitio jwt.io donde copiaremos el token generado y se mostrará lo que contiene.
![image](https://github.com/darkxex/springboot-jpa-h2-springsecurity/assets/17991404/6bf7da98-f5dd-4ef5-b686-512b283f76af)

Ya que estamos usando una base de datos temporal h2, si ejecutamos nuevamente la petición recibiremos esto.
![image](https://github.com/darkxex/springboot-jpa-h2-springsecurity/assets/17991404/39f1d0ba-87ae-4154-99a7-e1c8a82de59c)
Un httpstatus bad request que nos indica un error, el cual se muestra como que ya se encuentra ese correo en uno en la base de datos.

# Diagrama
![image](https://github.com/darkxex/springboot-jpa-h2-springsecurity/assets/17991404/1936b7f7-b1bd-4250-ac25-22b5b6b5a50a)



