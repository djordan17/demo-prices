# demo-prices

demo prices Application

## Diseño y tecnologías.
- Se implementó una arquitectura hexagonal con spring boot.
- Base de datos h2 en memoria
- Para la generación de versionado de scripts esta con con flyway.

## TODO
- Se tiene que mejorar la gestión de errores
- No se ha entendido el filtro que se quiere implementar, se ha implementado un filtro aplicando el siguiente criterio:
`startDate >= :request_date` después se ordena por el campo `priority`.
  
