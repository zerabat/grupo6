/* esto se ejecuta como un script cada vez que quiero crear una base de datos nueva 
 mantener este escript fiel a lo que hay realmente en la base 
 hacer un respaldo del esquema de tablas en el sql workbench o lo que usen y copien aca en caso 
 de que modifiquen algo en la base  */


CREATE TABLE `ticket_type` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`fecha_evento` DATETIME NOT NULL,
	`nombre_evento` VARCHAR(50) NOT NULL,
	INDEX `id` (`id`)
)
ENGINE=InnoDB
;