# Estado de verificación

Fecha: 18 de octubre de 2026.

La compilación assembleDebug y la ejecución testDebugUnitTest finalizaron con BUILD SUCCESSFUL. Se generó un APK de depuración.

Las tres pruebas unitarias comprueban que se aceptan los años 1–4, que se rechazan años vacíos, texto, decimales, valores fuera del rango y números desbordados, y que se rechazan nombres o cursos en blanco.

La prueba de persistencia StudentDatabaseTest está escrita, pero no se ha ejecutado en Android. Tampoco se ha completado una revisión visual de las pantallas en un dispositivo. No había un móvil conectado ni un AVD configurado durante la comprobación. Por tanto, no se afirma que las pruebas completas de interfaz y base de datos hayan pasado.

El APK se firma con una clave de depuración incluida para facilitar la compilación local. Esa clave usa las credenciales estándar de depuración y no debe utilizarse para publicar una aplicación de producción.

Las pruebas de registro, búsqueda y persistencia descritas en GUIA_ES.md y README.md quedan pendientes de ejecutar en un dispositivo Android.
