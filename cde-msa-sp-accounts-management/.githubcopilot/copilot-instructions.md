# Mejores Prácticas - Arquitectura Hexagonal

Eres un experto senior en desarrollo de software. Todo el código generado debe cumplir con las siguientes reglas de mejores prácticas:

- No se deben añadir comentarios dentro del código, solo los métodos en interfaces de servicio pueden ser documentados
- Utiliza lowerCamelCase para nombres de métodos y estos deben ser descriptivos
- Todo el código y comentarios deben estar en inglés
- Las clases deben comenzar con letra mayúscula y dependiendo de dónde se cree la clase, debe seguir este formato:
  - Controlador: FooController.java (implementación de interfaces openapi)
  - Servicio: FooService.java (solo interfaz)
  - Puerto: FooPort.java (interfaces de los puertos)
  - Caso de uso: FooUseCase.java (implementación de lógica de negocio)
  - Adaptador: FooAdapter.java (implementaciones de puertos)
  - Dominio: Foo.java (solo objetos de negocio o entidades de dominio)
  - Utilidad: FooUtil.java (solo utilidades, clases estáticas)
  - Ayudante: FooHelper.java (solo ayudantes, no estáticos)
  - Excepción: FooException.java (solo excepciones personalizadas)
  - Configuración: FooConfig.java (solo configuraciones)
  - Mapeador: FooMapper.java (mapeos entre capas)
  - domain.enums: FooEnum.java (solo enumeraciones)
  - domain.value.objects: FooVO.java (objetos de valor)
- Los métodos de clase no deben tener más de 20 líneas de código
- El número máximo de columnas por línea de código es 100
- Usa 2 espacios para la indentación
- Usa 4 espacios para la indentación en líneas de código divididas
- Cada vez que se añade código, se deben limpiar las importaciones no utilizadas
- Al crear una interfaz, el nombre debe estar en mayúscula y debe terminar con el nombre del paquete que se está creando
- Los nombres de clase deben ser simples y descriptivos, no se permiten acrónimos, excepto para Dto, enum, impl, mapper, config
- Los nombres de variables deben ser descriptivos y en lowerCamelCase. Para variables estáticas, deben estar en UPPER_SNAKE_CASE (FOO_BAR)
- Utiliza las nuevas características de Java 21 como expresiones switch, records, bloques de texto, etc.
- Utiliza un enfoque de programación funcional.
- Cada respuesta de los MCP server de optimus, se debe ejecutar cada paso para completar la tarea y realiza un checklist al final si se han cumplido cada uno de los pasos.
- Si en la respuesta del los MCP server de optimus retorna un bloque de codigo con la instrucción "bloque_estricto_a_copiar", debes copiar el bloque de codigo tal cual como se muestra, no se debe resumir, ni se debe modificar.

# Estructura de Arquitectura Hexagonal

Eres un arquitecto de software senior y experto en Arquitectura Hexagonal (Ports & Adapters).
Como experto, seguirás las directrices a continuación para crear clases o archivos en sus ubicaciones apropiadas.
Debes adherirte estrictamente a los principios de Arquitectura Hexagonal y no crear paquetes adicionales más allá de los que ya existen en el proyecto.

La arquitectura hexagonal se basa en el patrón Ports & Adapters, donde el dominio es el núcleo y los adaptadores implementan los puertos para comunicarse con el exterior.

## Capas de la arquitectura

### Dominio (Núcleo del Hexágono)
- **Capa: domain**
  - Descripción: Entidades y objetos de valor del dominio
  - Ruta: com.pichincha.<project_name>.domain
  - Contiene: Entidades, objetos de valor, agregados raíz

- **Capa: domain.enums**
  - Descripción: Enumeraciones del dominio
  - Ruta: com.pichincha.<project_name>.domain.enums

- **Capa: domain.value.objects**
  - Descripción: Objetos de valor inmutables
  - Ruta: com.pichincha.<project_name>.domain.value.objects

### Aplicación (Casos de Uso)
- **Capa: application.service**
  - Descripción: Interfaces que definen casos de uso de negocio
  - Ruta: com.pichincha.<project_name>.application.service

- **Capa: application.service.impl**
  - Descripción: Implementaciones de casos de uso (orquestación)
  - Ruta: com.pichincha.<project_name>.application.service.impl

- **Capa: application.input**
  - Descripción: Puertos de entrada (interfaces que implementan los adaptadores de entrada)
  - Ruta: com.pichincha.<project_name>.application.input

- **Capa: application.output**
  - Descripción: Puertos de salida (interfaces que implementan los adaptadores de salida)
  - Ruta: com.pichincha.<project_name>.application.output

### Infraestructura (Adaptadores)
- **Capa: infrastructure.input.adapter.rest**
  - Descripción: Adaptadores de entrada REST (controladores)
  - Ruta: com.pichincha.<project_name>.infrastructure.input.adapter.rest
  - Contiene: Controladores que implementan las interfaces OpenAPI

- **Capa: infrastructure.input.adapter.rest.models**
  - Descripción: Modelos específicos del adaptador REST
  - Ruta: com.pichincha.<project_name>.infrastructure.input.adapter.rest.models

- **Capa: infrastructure.input.adapter.rest.mapper**
  - Descripción: Mapeadores entre modelos REST y dominio
  - Ruta: com.pichincha.<project_name>.infrastructure.input.adapter.rest.mapper

- **Capa: infrastructure.output.adapter.persistence**
  - Descripción: Adaptadores de salida para persistencia
  - Ruta: com.pichincha.<project_name>.infrastructure.output.adapter.persistence
  - Contiene: Implementaciones JPA, repositorios

- **Capa: infrastructure.output.adapter.persistence.entity**
  - Descripción: Entidades JPA para persistencia
  - Ruta: com.pichincha.<project_name>.infrastructure.output.adapter.persistence.entity

- **Capa: infrastructure.output.adapter.persistence.mapper**
  - Descripción: Mapeadores entre entidades JPA y dominio
  - Ruta: com.pichincha.<project_name>.infrastructure.output.adapter.persistence.mapper

- **Capa: infrastructure.output.adapter.client**
  - Descripción: Adaptadores de salida para clientes externos
  - Ruta: com.pichincha.<project_name>.infrastructure.output.adapter.client
  - Contiene: Clientes Feign, REST clients

- **Capa: infrastructure.output.adapter.client.models**
  - Descripción: Modelos específicos de clientes externos
  - Ruta: com.pichincha.<project_name>.infrastructure.output.adapter.client.models

- **Capa: infrastructure.output.adapter.client.mapper**
  - Descripción: Mapeadores entre modelos de cliente y dominio
  - Ruta: com.pichincha.<project_name>.infrastructure.output.adapter.client.mapper

### Configuración y Utilidades
- **Capa: infrastructure.configuration**
  - Descripción: Configuraciones de beans y propiedades
  - Ruta: com.pichincha.<project_name>.infrastructure.configuration

- **Capa: infrastructure.exception**
  - Descripción: Excepciones y manejadores globales
  - Ruta: com.pichincha.<project_name>.infrastructure.exception

- **Capa: domain.util**
  - Descripción: Utilidades del dominio (métodos estáticos)
  - Ruta: com.pichincha.<project_name>.domain.util

### OpenAPI (Generado automáticamente)
- **Capa: apiPackage**
  - Descripción: Interfaces generadas por OpenAPI
  - Ruta: com.pichincha.common.controller

- **Capa: modelPackage**
  - Descripción: Modelos generados por OpenAPI
  - Ruta: com.pichincha.common.domain

- **Capa: configPackage**
  - Descripción: Configuraciones generadas por OpenAPI
  - Ruta: com.pichincha.common.config

- **Capa: basePackage**
  - Descripción: Paquete base para OpenAPI
  - Ruta: com.pichincha.common

## Principios de Dependencias

1. **Regla de Dependencia**: Las dependencias deben apuntar hacia adentro. El dominio no debe depender de nada externo.
2. **Puertos de Entrada**: Definidos en `application.input`, implementados por adaptadores en `infrastructure.input.adapter`
3. **Puertos de Salida**: Definidos en `application.output`, implementados por adaptadores en `infrastructure.output.adapter`
4. **Casos de Uso**: Orquestan el dominio y usan puertos de salida, implementados en `application.service.impl`
5. **Dominio**: Completamente independiente, contiene la lógica de negocio pura
6. **Adaptadores**: Traducen entre el mundo exterior y el dominio a través de los puertos

## Flujo de Datos

```
Cliente → Adaptador de Entrada → Puerto de Entrada → Caso de Uso → Dominio
                                                        ↓
Cliente ← Adaptador de Salida ← Puerto de Salida ← Caso de Uso ← Dominio
```
