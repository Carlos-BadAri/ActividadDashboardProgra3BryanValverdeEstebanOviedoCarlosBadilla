import javax.print.event.PrintJobAttributeListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DashBoardControlAcademico extends JFrame {

    //Apartado de color

    private final Color COLOR_MENU = new Color(28, 58, 82);
    private final Color COLOR_MENU_ACTIVO = new Color(42, 116, 220);
    private final Color COLOR_FONDO = new Color(247, 249, 252);
    private final Color COLOR_AZUL = new Color(35, 111, 214);
    private final Color COLOR_VERDE = new Color(42, 166, 78);
    private final Color COLOR_NARANJA = new Color(255, 153, 0);
    private final Color COLOR_ROJO = new Color(239, 76, 70);
    private final Color COLOR_MORADO = new Color(126, 63, 218);
    private final Color COLOR_BORDE = new Color(218, 223, 230);

    //Partes del formulario

    private JTextField txtIdentificacion;
    private JTextField txtNombre;
    private JTextField txtCurso;
    private JTextField txtPromedio;
    private JTextField txtBuscar;
    private JComboBox<String> cambiarCarrera;
    private JComboBox<String> cambiarEstado;

    private JTable tablaEstudiantes;
    private DefaultTableModel modeloTabla;
    private TableRowSorter<DefaultTableModel> sorter;

    //Array de los estudiantes

    private ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
    private int siguienteId = 1;

    //Constructor del dashboard

    public DashBoardControlAcademico() {

        setTitle("Control Académico de Estudiantes");
        setSize(1400, 850);
        setMinimumSize(new Dimension(1100, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inicializarDatos();

        add(crearMenuLateral(), BorderLayout.WEST);
       // add(crearContenidoPrincipal(), BorderLayout.CENTER);
       // add(crearBarraEstado(), BorderLayout.SOUTH);


    }

    //Datos quemados para mostrar

    private void inicializarDatos() {
        listaEstudiantes.add(new Estudiante(siguienteId++, "Juan Murillo", true, 100,
                "12345", "Coleccionista de titulos", "Programacion III"));


        listaEstudiantes.add(new Estudiante(siguienteId++, "Bryan Valverde", true, 70,
                "119150219", "Ingenieria en decadencia", "Estructuras Discretas"));


        listaEstudiantes.add(new Estudiante(siguienteId++, "Carlos Badilla", true, 80,
                "112233", "Ingenieria en sistemas", "Programacion III"));


        listaEstudiantes.add(new Estudiante(siguienteId++, "Esteban Oviedo", false, 90,
                "111222333", "Ingenieria en chatgpt", "Estructuras de datos"));


    }

    //Menu lateral

    private JPanel crearMenuLateral() {
        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(230, 0));
        menu.setBackground(COLOR_MENU);
        menu.setLayout(new BorderLayout());

        //Perfil
        JPanel pnlPerfil = new JPanel();
        pnlPerfil.setOpaque(false);
        pnlPerfil.setLayout(new BoxLayout(pnlPerfil, BoxLayout.Y_AXIS));
        pnlPerfil.setBorder(new EmptyBorder(25, 15, 25, 15));

        JLabel lblAvatar = new JLabel("●");
        lblAvatar.setFont(new Font("Arial", Font.BOLD, 60));
        lblAvatar.setForeground(new Color(56, 130, 230));
        lblAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitulo = new JLabel("Panel Académico");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblRol = new JLabel("Coordinación Académica");
        lblRol.setForeground(new Color(220, 225, 230));
        lblRol.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblRol.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlPerfil.add(lblAvatar);
        pnlPerfil.add(Box.createVerticalStrut(8));
        pnlPerfil.add(lblTitulo);
        pnlPerfil.add(Box.createVerticalStrut(5));
        pnlPerfil.add(lblRol);

        menu.add(pnlPerfil, BorderLayout.NORTH);

        //Opciones
        JPanel opciones = new JPanel();
        opciones.setOpaque(false);
        opciones.setLayout(new BoxLayout(opciones, BoxLayout.Y_AXIS));
        opciones.setBorder(new EmptyBorder(5, 12, 5, 12));

        opciones.add(crearBotonMenu("⌂  Inicio", true));
        opciones.add(Box.createVerticalStrut(7));
        opciones.add(crearBotonMenu("☺  Estudiantes", false));
        opciones.add(Box.createVerticalStrut(7));
        opciones.add(crearBotonMenu("□  Carreras", false));
        opciones.add(Box.createVerticalStrut(7));
        opciones.add(crearBotonMenu("▥  Cursos", false));
        opciones.add(Box.createVerticalStrut(7));
        opciones.add(crearBotonMenu("▤  Reportes", false));
        opciones.add(Box.createVerticalStrut(7));
        opciones.add(crearBotonMenu("⚙  Configuración", false));
        opciones.add(Box.createVerticalStrut(7));
        opciones.add(crearBotonMenu("ⓘ  Acerca de", false));

        menu.add(opciones, BorderLayout.CENTER);

        return menu;
    }

    private JButton crearBotonMenu(String texto, boolean activo) {

        JButton boton = new JButton(texto);

        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        boton.setPreferredSize(new Dimension(200, 50));
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setFont(new Font("SansSerif", Font.PLAIN, 15));
        boton.setForeground(Color.WHITE);
        boton.setBackground(activo ? COLOR_MENU_ACTIVO : COLOR_MENU);
        boton.setBorder(new EmptyBorder(0, 18, 0, 10));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!activo) {
                    boton.setBackground(new Color(38, 79, 108));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!activo) {
                    boton.setBackground(COLOR_MENU);
                }
            }
        });

        boton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        DashBoardControlAcademico.this,
                        "Seleccionó: " + texto.replaceAll("[^A-Za-zÁÉÍÓÚáéíóúñÑ ]", "").trim()
                )
        );

        return boton;
    }
}


