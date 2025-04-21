# Manual de Usuario – VelozerFit 2.0

## 1. Introducción

VelozerFit 2.0 es una aplicación móvil diseñada para facilitar la realización de rutinas de ejercicio desde un dispositivo Android. Ofrece una experiencia guiada por grupos musculares, incluyendo videos de referencia, cronómetro y control de series.

Este manual está dirigido a los usuarios finales para explicar el funcionamiento básico de la aplicación.

---

## 2. Instalación

### Requisitos del sistema:
- Dispositivo Android 5.0 (Lollipop) o superior.
- Conexión a internet para ver videos de ejercicios.
- Al menos 50 MB de almacenamiento disponible.

### Proceso de instalación:
1. Descarga el archivo APK desde el repositorio oficial o desde Play Store (si está disponible).
2. Habilita la opción de “Instalar aplicaciones desde fuentes desconocidas” si usas APK.
3. Ejecuta la instalación y abre la aplicación.

---

## 3. Registro de usuario

1. Abre la aplicación y selecciona la opción “Registrarse”.
2. Llena el formulario con:
   - Nombre de usuario
   - Correo electrónico
   - Contraseña
   - Confirmación de contraseña
   - Selección de género
3. Pulsa el botón “Registrar”.
4. Si los datos son válidos, se guardarán en la base de datos local y el usuario será redirigido a la pantalla principal.

---

## 4. Inicio de sesión

1. Selecciona la opción “Iniciar sesión”.
2. Ingresa tu nombre de usuario (o correo) y contraseña.
3. Si los datos coinciden con los almacenados, accederás a la pantalla principal con un mensaje de bienvenida personalizado.

---

## 5. Pantalla principal

Tras iniciar sesión correctamente, el usuario verá:

- Un saludo personalizado según el género registrado.
- Opciones disponibles:
   - Entrenar
   - Calcular IMC

Selecciona “Entrenar” para acceder a los grupos musculares.

---

## 6. Selección de grupo muscular

La aplicación permite trabajar los siguientes grupos:

- Hombros
- Bíceps
- Pecho
- Espalda
- Abdomen
- Piernas

Cada grupo contiene 5 ejercicios con nombre, descripción y acceso a un video explicativo en YouTube.

---

## 7. Rutina de ejercicio

### Iniciar rutina:
1. Selecciona el número de series con los botones “+” o “–”.
2. Pulsa “Iniciar Rutina”.
3. Comenzará un cronómetro y se indicará la serie actual.

### Controles:
- **Pausar / Retomar:** Detiene o continúa el cronómetro.
- **Siguiente Serie:** Pasa a la siguiente serie.
- **Finalizar Rutina:** Termina la sesión actual.

### Finalización:
Al completar las series seleccionadas, aparecerá un mensaje indicando que la rutina ha sido completada.

---

## 8. Cálculo de IMC (Índice de Masa Corporal)

VelozerFit incluye una herramienta para calcular tu **Índice de Masa Corporal (IMC)**, lo cual te ayuda a tener una referencia de tu estado físico general basado en tu peso y estatura.

### ¿Qué es el IMC?

El **IMC** es una fórmula utilizada mundialmente para estimar si una persona tiene un peso saludable.  
La fórmula es: IMC = peso (kg) / (estatura (m))²

Por ejemplo: si pesas 70 kg y mides 1.75 m, tu IMC sería:
IMC = 70 / (1.75 × 1.75) ≈ 22.86


---

### Cómo calcular tu IMC en VelozerFit:

1. Desde el menú principal, pulsa el botón **IMC**.
2. Se abrirá una pantalla con dos campos:
   - **Peso en kilogramos**
   - **Estatura en metros**
3. Introduce tus datos y pulsa **Calcular IMC**.
4. La aplicación mostrará:
   - Tu IMC numérico.
   - Una interpretación de tu estado según la tabla estándar.

---

### Interpretación de resultados:

| IMC | Clasificación       |
|-----|---------------------|
| Menor a 18.5 | Bajo peso        |
| 18.5 – 24.9 | Peso normal      |
| 25 – 29.9   | Sobrepeso        |
| 30 o más    | Obesidad         |

---

### Recomendaciones:

- El IMC es un **valor estimado**, no un diagnóstico médico.
- Puede variar según tu complexión o masa muscular.
- Úsalo como punto de referencia para ajustar tu rutina de entrenamiento.
- Consulta con un profesional de la salud si tienes dudas sobre tu resultado.





## 9. Errores comunes y soluciones

| Situación | Posible causa | Solución |
|-----------|---------------|----------|
| No se puede iniciar sesión | Usuario o contraseña incorrectos | Verifica que los datos sean correctos |
| El video no carga | No hay conexión a internet | Activa Wi-Fi o datos móviles |
| No se guarda el registro | Campos incompletos o duplicados | Verifica todos los campos y que el usuario no exista previamente |

---

## 10. Datos almacenados

La información de los usuarios (nombre, correo, género y contraseña) se almacena de forma local en una base de datos SQLite interna.

---

## 11. Recomendaciones

- Para una mejor experiencia, asegúrate de tener conexión a internet antes de reproducir videos.
- Utiliza la opción de pausa si necesitas descansar entre series.
- Reinicia la rutina si deseas repetirla.

---

## 12. Soporte

Para dudas o sugerencias, visita el repositorio oficial:  
[https://github.com/velozerick/velozerFit2.0](https://github.com/velozerick/velozerFit2.0)

---

