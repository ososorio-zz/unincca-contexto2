# Introduction #

EJEMPLOS JSON PARA EL USO DEL SERVIDOR


## MODULO LOGIN ##
Modulo encargado de realizar el login, cambiar la contraseña, recuperar la contraseña

### Login ###
```json


{
"ac": 0,
"op": 0,
"data": {
"cedula": "111111",
"password": "pass"
}
}
```

response
```json

{
"login": "true",
"userInfo": {
"name": "oscar",
"lastname": "osorio",
"type": "0", //si es 0 es usuario si es 1 es admin
"last_login": "0"  // 0 si nunca a tenido un login si no es las fecha en timestamp
"url_picture": "http://image" //imagen alojada en el servidor o pagina web
}
}
```

### Lost Password ###
```json

{
"ac": 0,
"op": 1,
"data": {
"cedula": "1030550748",
"nombre": "oscar",
"apellido":"osorio"
}
}
```


### Change Password ###
```json

{
"ac": 0,
"op": 2,
"data": {
"cedula": "11111111",
"oldpassword": "osorio",
"newpassword":"12345"
}
}
```







Add your content here.  Format your content with:
  * Text in **bold** or _italic_
  * Headings, paragraphs, and lists
  * Automatic links to other wiki pages