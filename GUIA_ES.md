# Guía para estudiar Student Registration

Este proyecto es una implementación de referencia realizada con ayuda de IA a partir del ejemplo ContactMVVM. Su uso completo como entrega excede el límite de ayuda de IA indicado en el enunciado. La guía no sustituye tu memoria personal ni acredita que hayas desarrollado el código por tu cuenta.

## Cómo abrirlo

En Android Studio selecciona File > Open y elige esta carpeta, la que contiene settings.gradle.kts. Espera a la sincronización. Selecciona un dispositivo Android y pulsa Run. Se necesita Android SDK 34, Build Tools 36.0.0 y un JDK compatible con Gradle, como el JDK 21 instalado en este ordenador.

## Recorrido por la aplicación

Home ofrece dos botones. Register student recoge nombre, curso y año. Al pulsar Save student, StudentViewModel comprueba los datos y pide a StudentDao que guarde un Student. Room genera un identificador y el mensaje de confirmación lo muestra. Search student consulta la base de datos por ese identificador.

## Orden para entender el código

1. Abre Student.kt. Cada propiedad representa una columna. La clave primaria distingue estudiantes aunque tengan el mismo nombre.
2. Lee StudentValidator. toIntOrNull devuelve null si el texto no es un entero válido; evita que una entrada incorrecta cierre la aplicación. La comprobación del rango limita el año a 1, 2, 3 o 4.
3. Lee StudentDao.kt. insert guarda y devuelve el identificador. findById utiliza una consulta parametrizada y devuelve null cuando no hay coincidencia.
4. Lee StudentDatabase.kt. La instancia compartida evita abrir una base distinta en cada pantalla. students.db se guarda localmente.
5. Lee StudentViewModel.kt. Mantiene los valores que muestra Compose y usa corrutinas para las operaciones de Room. No permite dos guardados simultáneos mientras una operación está en curso.
6. Lee StudentScreens.kt. Contiene las tres pantallas y las rutas de navegación. La pantalla muestra el estado y llama a funciones del ViewModel.
7. Lee MainActivity.kt. Es el punto de entrada de Android.

## Decisiones que debes comprender

- Kotlin y Compose siguen los ejemplos facilitados.
- El ViewModel separa las operaciones de la presentación.
- El identificador automático evita pedir al usuario que invente uno único.
- Un teclado numérico facilita introducir el año, pero la validación sigue siendo necesaria.
- El resultado de búsqueda se borra cuando cambia el identificador para no mostrar datos de una búsqueda anterior.
- La interfaz se puede desplazar cuando aparece el teclado.
- Room conserva estudiantes tras cerrar la aplicación. El ViewModel por sí solo no ofrece esa persistencia.
- El formulario incompleto no se conserva después de que Android termine el proceso. La aplicación no sincroniza entre dispositivos.

## Preparar la parte teórica

### LiveData

Estudia estos conceptos antes de redactar tu respuesta: dato observable, observador, ciclo de vida y actualización de la interfaz. LiveData notifica a observadores ligados a un LifecycleOwner cuando están activos y elimina esos observadores al destruirse su propietario. Al volver a estar activos pueden recibir el valor más reciente. Un ViewModel ayuda a conservar el estado durante cambios de configuración; LiveData no sustituye una base de datos ni garantiza persistencia tras la muerte del proceso.

Construye un ejemplo hipotético con una lista de estudiantes observada desde una pantalla: explica qué ocurriría al añadir un registro, al ir a segundo plano y al volver. Esta aplicación concreta usa mutableStateOf de Compose, no LiveData. No afirmes que implementa LiveData.

Referencia: https://developer.android.com/topic/libraries/architecture/livedata

### Navegación con datos

Puedes comparar dos mecanismos diferentes: extras de un Intent entre Activities y argumentos de rutas mediante Navigation Compose. Para cada uno, explica quién envía, quién recibe y cómo recupera el dato. Usa el identificador del estudiante como ejemplo.

Intent es apropiado para abrir una Activity con datos sencillos. Navigation Compose integra destinos y pila de retroceso en una aplicación de una Activity. Pasar un identificador y consultar los detalles en el destino evita transportar un objeto completo que podría quedar desactualizado. Esta implementación navega entre pantallas sin argumentos, pues la búsqueda recibe el ID mediante un campo; la comparación solicitada es teórica.

Referencias: https://developer.android.com/guide/components/intents-filters y https://developer.android.com/guide/navigation

## Evidencias y memoria

Haz capturas reales de inicio, registro correcto con ID, búsqueda correcta, estudiante inexistente y año incorrecto. Describe únicamente pruebas que hayas ejecutado. Identifica las partes adaptadas del material de clase y la ayuda recibida. No inventes un historial de trabajo, resultados de pruebas ni experiencias personales.

Antes de entregar, comprueba el formato y la fecha en Moodle. El enunciado aportado no establece aquí un formato de empaquetado ni pide publicar la aplicación en una tienda.
