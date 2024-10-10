import java.util.Scanner;

interface Sender {
    void send(String message);
}

class Mensaje implements Sender {
    public void send(String message) {
        System.out.println("Enviando mensaje de texto: " + message);
    }
}

class Email implements Sender {
    public void send(String message) {
        System.out.println("Enviando email: " + message);
    }
}

class WhatsApp implements Sender {
    public void send(String message) {
        System.out.println("Enviando mensaje de WhatsApp: " + message);
    }
}

class Usuario {
    private String email;
    private String password;
    private boolean loggedIn;

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
        this.loggedIn = false;
    }

    public boolean login(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            this.loggedIn = true;
            System.out.println("Inicio de sesión exitoso.");
            return true;
        } else {
            System.out.println("Email o contraseña incorrectos.");
            return false;
        }
    }

    public void sendMessage(Sender sender, String message) {
        if (loggedIn) {
            sender.send(message);
            System.out.println("Notificación: Mensaje enviado correctamente.");
        } else {
            System.out.println("Error: Debes iniciar sesión primero.");
        }
    }
}

public class MensajeriaApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Configuración inicial del usuario");
        System.out.print("Ingrese el email para la cuenta: ");
        String userEmail = scanner.nextLine();
        System.out.print("Ingrese la contraseña para la cuenta: ");
        String userPassword = scanner.nextLine();

        Usuario usuario = new Usuario(userEmail, userPassword);

        System.out.println("\nBienvenido al sistema de mensajería");
        boolean loginSuccessful = false;

        while (!loginSuccessful) {
            System.out.print("Ingrese su email: ");
            String email = scanner.nextLine();
            System.out.print("Ingrese su contraseña: ");
            String password = scanner.nextLine();

            loginSuccessful = usuario.login(email, password);

            if (!loginSuccessful) {
                System.out.println("¿Desea intentar nuevamente? (s/n): ");
                String retry = scanner.nextLine();
                if (!retry.equalsIgnoreCase("s")) {
                    System.out.println("Gracias por usar el sistema de mensajería. ¡Hasta luego!");
                    return;
                }
            }
        }

        while (true) {
            System.out.println("\nElija una opción de envío:");
            System.out.println("1. Mensaje de texto");
            System.out.println("2. Email");
            System.out.println("3. WhatsApp");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (opcion == 4) {
                System.out.println("Gracias por usar el sistema de mensajería. ¡Hasta luego!");
                break;
            }

            System.out.print("Escriba su mensaje: ");
            String mensaje = scanner.nextLine();

            Sender sender;
            switch (opcion) {
                case 1:
                    sender = new Mensaje();
                    break;
                case 2:
                    sender = new Email();
                    break;
                case 3:
                    sender = new WhatsApp();
                    break;
                default:
                    System.out.println("Opción no válida.");
                    continue;
            }

            usuario.sendMessage(sender, mensaje);
        }

        scanner.close();
    }
}