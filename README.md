# cake-world
	El negocio es una pequeña aplicación que funciona como negocio para la venta de tortas.
	Existe una dependencia con una librería que no se encuentra en el repositorio de maven 
	y que debe ser instalada localmente para que la aplicación funcione correctamente. El codigo 
	fuente de la misma esta en https://github.com/cyriux/dot-diagram y mediante maven se debe 
	instalar en el repositorio local.

	El repositorio está organizado en pequeños módulos.
	- annotation-doclet: doclet que genera el glosario desde el código fuente.
	- cake-rest-layer: Capa de servicios rest de ejemplo.
	- cake-world-domain: Paquete que contiene las entidades que mapean con el modelo de negocio. 
	- living-docs-annotations: Pequeña librería que guarda las distintas anotaciones que se 
	utilizarán para aumentar la información que provee el código fuente.
	- maven-living-diagram-plugin: Plugin de maven que analiza el código en búsqueda de ciertas 
	anotaciones que están dentro del paquete living-docs-annotations y genera un diagrama de clases simplificado.
