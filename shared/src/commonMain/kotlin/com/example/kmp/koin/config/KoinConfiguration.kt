package com.example.kmp.koin.config

import com.example.kmp.koin.modules.CountriesModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(appModules)
    }

/**
 * TIPOS DE INSTANCIAS EN KOIN (Ciclo de Vida)
 *
 * 1. single { ... }
 *    - Significado: Singleton.
 *    - Comportamiento: Crea una instancia única que se mantiene viva durante toda
 *      la vida de la aplicación. La primera vez que se pide se crea, y luego
 *      siempre se devuelve la misma.
 *    - Uso: Repositorios, servicios de red, bases de datos o lógica compartida.
 *
 * 2. factory { ... }
 *    - Significado: Factoría / Instancia fresca.
 *    - Comportamiento: Crea una nueva instancia cada vez que se solicita la dependencia.
 *      No guarda nada en memoria.
 *    - Uso: Clases que mantienen un estado temporal, validadores o procesadores
 *      que deben reiniciarse tras cada uso.
 *
 * 3. scoped { ... }
 *    - Significado: Instancia vinculada a un ámbito (Scope).
 *    - Comportamiento: La instancia vive mientras el "ámbito" (como una pantalla específica
 *      o una sesión de usuario) exista. Cuando el ámbito se cierra, la instancia se destruye.
 *    - Uso: Flujos de registro de varios pasos, cestas de compra o datos de una
 *      sesión de usuario activa.
 *
 * EXTRA: get()
 *    - Comportamiento: Función de resolución automática. Busca en el grafo de Koin
 *      la dependencia necesaria basándose en el tipo del parámetro del constructor.
 *    - Este metodo es especificamente para el uso en constructor
 *    - Ejemplo: single { TimeTracker(get()) }
 *
 *
 * FORMAS DE RECUPERAR DEPENDENCIAS:
 *
 * 1. koinInject<T>() -> Uso exclusivo en funciones @Composable.
 *    Recupera la instancia de forma inmediata y optimizada para la UI.
 *
 * 2. by inject<T>() -> Uso en clases estándar (Activities, ViewModels, etc.).
 *    Utiliza "Lazy Delegation": la instancia solo se crea en el momento en
 *    que se accede a la variable por primera vez.
 */
val appModules = module {
    single {
        CountriesModule()
    }
}