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
    private JTextField txtNota;
    private JTextField txtBuscar;
    private JComboBox<String> cambiarCarrera;
    private JComboBox<String> cambiarEstado;

    private JTable tablaEstudiantes;
    private DefaultTableModel modeloTabla;
    private TableRowSorter<DefaultTableModel> sorter;

    //Tarjetas del dashboard
    private JLabel lblTotalEstudiantes;
    private JLabel lblAprobados;
    private JLabel lblReprobados;
    private JLabel lblPromedioGeneral;

    //Botones de acción
    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JButton btnBuscar;

    //Filtros
    private JComboBox<String> filtroCarrera;
    private JComboBox<String> filtroCurso;
    private JComboBox<String> filtroCondicion;

    //Barra de estado
    private JLabel lblBarraEstado;

    private int idSeleccionado = -1;

    //Gestor de la lógica de negocio
    private GestorEstudiantes gestor = new GestorEstudiantes();

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
        add(crearContenidoPrincipal(), BorderLayout.CENTER);
        add(crearBarraEstado(), BorderLayout.SOUTH);

        actualizarTabla();
        actualizarTarjetasResumen();
    }

    //Datos quemados para mostrar
    private void inicializarDatos() {
        gestor.agregarEstudiante("Juan Murillo", true, 100,
                "12345", "Ingenieria en sistemas", "Programacion III");

        gestor.agregarEstudiante("Bryan Valverde", true, 70,
                "119150219", "Ingenieria en sistemas", "Estructuras Discretas");

        gestor.agregarEstudiante("Carlos Badilla", true, 80,
                "112233", "Ingenieria en sistemas", "Programacion III");

        gestor.agregarEstudiante("Esteban Oviedo", false, 90,
                "111222333", "Ingenieria en sistemas", "Estructuras de datos");

        gestor.agregarEstudiante("Juan Perez", false, 50,
                "1112223334", "Ingenieria en sistemas",
                "Arquitectura de computadores");
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

        opciones.add(crearBotonMenu("⌂", " Inicio", true));
        opciones.add(Box.createVerticalStrut(7));
        opciones.add(crearBotonMenu("☺", " Estudiantes", false));
        opciones.add(Box.createVerticalStrut(7));
        opciones.add(crearBotonMenu("□" , " Carreras", false));
        opciones.add(Box.createVerticalStrut(7));
        opciones.add(crearBotonMenu("▥", " Cursos", false));
        opciones.add(Box.createVerticalStrut(7));
        opciones.add(crearBotonMenu("▤", " Reportes", false));
        opciones.add(Box.createVerticalStrut(7));
        opciones.add(crearBotonMenu("⚙", " Configuración", false));
        opciones.add(Box.createVerticalStrut(7));
        opciones.add(crearBotonMenu("ⓘ", " Acerca de", false));

        menu.add(opciones, BorderLayout.CENTER);

        return menu;
    }

    private JButton crearBotonMenu(String icono, String texto, boolean activo) {

        JButton boton = new JButton(icono + " " + texto);

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
                        "Seleccionó: " + texto)
        );

        return boton;
    }

    private JPanel crearContenidoPrincipal() {
        JPanel contenido = new JPanel();
        contenido.setLayout(new BorderLayout(15, 15));
        contenido.setBackground(COLOR_FONDO);
        contenido.setBorder(new EmptyBorder(20, 20, 20, 20));

        contenido.add(crearTarjetasResumen(), BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(15, 15));
        centro.setOpaque(false);
        centro.add(crearPanelBusquedaYFiltros(), BorderLayout.NORTH);
        centro.add(new JScrollPane(crearTablaEstudiantes()), BorderLayout.CENTER);
        centro.add(crearFormulario(), BorderLayout.SOUTH);

        contenido.add(centro, BorderLayout.CENTER);

        return contenido;
    }

    //Tarjetas de resumen (total, aprobados, reprobados, promedio general)
    private JPanel crearTarjetasResumen() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 15, 0));
        panel.setOpaque(false);

        lblTotalEstudiantes = new JLabel("0", SwingConstants.CENTER);
        lblAprobados = new JLabel("0", SwingConstants.CENTER);
        lblReprobados = new JLabel("0", SwingConstants.CENTER);
        lblPromedioGeneral = new JLabel("0.0", SwingConstants.CENTER);

        panel.add(crearTarjeta("Total Estudiantes", lblTotalEstudiantes, COLOR_AZUL));
        panel.add(crearTarjeta("Aprobados", lblAprobados, COLOR_VERDE));
        panel.add(crearTarjeta("Reprobados", lblReprobados, COLOR_ROJO));
        panel.add(crearTarjeta("Promedio General", lblPromedioGeneral, COLOR_MORADO));

        return panel;
    }

    private JPanel crearTarjeta(String titulo, JLabel lblValor, Color color) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_BORDE, 1, true),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblTitulo.setForeground(new Color(100, 110, 120));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblValor.setFont(new Font("SansSerif", Font.BOLD, 30));
        lblValor.setForeground(color);
        lblValor.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblValor.setHorizontalAlignment(SwingConstants.LEFT);

        tarjeta.add(lblTitulo);
        tarjeta.add(Box.createVerticalStrut(8));
        tarjeta.add(lblValor);

        return tarjeta;
    }

    //Tabla de estudiantes
    private JTable crearTablaEstudiantes() {
        String[] columnas = {"ID", "Identificación", "Nombre", "Carrera", "Curso", "Nota", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaEstudiantes = new JTable(modeloTabla);
        tablaEstudiantes.setRowHeight(28);
        tablaEstudiantes.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaEstudiantes.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        tablaEstudiantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        sorter = new TableRowSorter<>(modeloTabla);
        tablaEstudiantes.setRowSorter(sorter);

        tablaEstudiantes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaEstudiantes.getSelectedRow() != -1) {
                cargarEstudianteEnFormulario(tablaEstudiantes.getSelectedRow());
            }
        });

        return tablaEstudiantes;
    }

    //Formulario de datos del estudiante
    private JPanel crearFormulario() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_BORDE, 1, true),
                new EmptyBorder(15, 15, 15, 15)
        ));
        panel.setLayout(new GridLayout(2, 6, 10, 10));

        txtIdentificacion = new JTextField();
        txtNombre = new JTextField();
        cambiarCarrera = new JComboBox<>(new String[]{
                "Ingenieria en sistemas", "Ingenieria en decadencia",
                "Coleccionista de titulos", "Ingenieria en chatgpt"});
        cambiarCarrera.setEditable(true);
        txtCurso = new JTextField();
        txtNota = new JTextField();
        cambiarEstado = new JComboBox<>(new String[]{"Aprobado", "Reprobado"});

        panel.add(crearCampoConEtiqueta("Identificación", txtIdentificacion));
        panel.add(crearCampoConEtiqueta("Nombre", txtNombre));
        panel.add(crearCampoConEtiqueta("Carrera", cambiarCarrera));
        panel.add(crearCampoConEtiqueta("Curso", txtCurso));
        panel.add(crearCampoConEtiqueta("Nota", txtNota));
        panel.add(crearCampoConEtiqueta("Estado", cambiarEstado));

        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        btnAgregar.addActionListener(e -> agregarEstudianteDesdeFormulario());
        btnModificar.addActionListener(e -> modificarEstudianteSeleccionado());
        btnEliminar.addActionListener(e -> eliminarEstudianteSeleccionado());
        btnLimpiar.addActionListener(e -> limpiarFormulario());

        panel.add(btnAgregar);
        panel.add(btnModificar);
        panel.add(btnEliminar);
        panel.add(btnLimpiar);

        return panel;
    }

    private JPanel crearCampoConEtiqueta(String etiqueta, JComponent campo) {
        JPanel panel = new JPanel(new BorderLayout(2, 2));
        panel.setOpaque(false);
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 12));
        panel.add(lbl, BorderLayout.NORTH);
        panel.add(campo, BorderLayout.CENTER);
        return panel;
    }

    //Panel de búsqueda y filtros
    private JPanel crearPanelBusquedaYFiltros() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setOpaque(false);

        txtBuscar = new JTextField(18);
        btnBuscar = new JButton("Buscar");

        filtroCarrera = new JComboBox<>(new String[]{"Todas"});
        filtroCurso = new JComboBox<>(new String[]{"Todos"});
        filtroCondicion = new JComboBox<>(new String[]{"Todos", "Aprobado", "Reprobado"});

        btnBuscar.addActionListener(e -> aplicarFiltros());
        filtroCarrera.addActionListener(e -> aplicarFiltros());
        filtroCurso.addActionListener(e -> aplicarFiltros());
        filtroCondicion.addActionListener(e -> aplicarFiltros());

        panel.add(new JLabel("Buscar:"));
        panel.add(txtBuscar);
        panel.add(btnBuscar);
        panel.add(new JLabel("  Carrera:"));
        panel.add(filtroCarrera);
        panel.add(new JLabel("Curso:"));
        panel.add(filtroCurso);
        panel.add(new JLabel("Condición:"));
        panel.add(filtroCondicion);

        return panel;
    }

    //Barra de estado inferior
    private JPanel crearBarraEstado() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_MENU);
        panel.setBorder(new EmptyBorder(6, 15, 6, 15));

        lblBarraEstado = new JLabel("Listo.");
        lblBarraEstado.setForeground(Color.WHITE);
        lblBarraEstado.setFont(new Font("SansSerif", Font.PLAIN, 12));

        panel.add(lblBarraEstado, BorderLayout.WEST);
        return panel;
    }

     private void actualizarTabla() {
        actualizarTabla(gestor.listarTodos());
    }

    private void actualizarTabla(ArrayList<Estudiante> lista) {
        modeloTabla.setRowCount(0);
        for (Estudiante e : lista) {
            modeloTabla.addRow(new Object[]{
                    e.getId(),
                    e.getIdentificacion(),
                    e.getNombre(),
                    e.getCarrera(),
                    e.getCurso(),
                    e.getPromedio(),
                    e.isEstado() ? "Aprobado" : "Reprobado"
            });
        }
    }

    private void actualizarTarjetasResumen() {
        lblTotalEstudiantes.setText(String.valueOf(gestor.obtenerTotalEstudiantes()));
        lblAprobados.setText(String.valueOf(gestor.obtenerTotalAprobados()));
        lblReprobados.setText(String.valueOf(gestor.obtenerTotalReprobados()));
        lblPromedioGeneral.setText(String.format("%.1f", gestor.obtenerPromedioGeneral()));
    }

    private void agregarEstudianteDesdeFormulario() {
        if (!validarCampos()) {
            return;
        }

        String identificacion = txtIdentificacion.getText().trim();
        if (gestor.existeIdentificacion(identificacion)) {
            JOptionPane.showMessageDialog(this,
                    "Ya existe un estudiante con esa identificación.",
                    "Identificación duplicada", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = txtNombre.getText().trim();
        String carrera = (String) cambiarCarrera.getEditor().getItem();
        String curso = txtCurso.getText().trim();
        double nota = Double.parseDouble(txtNota.getText().trim());
        boolean estado = cambiarEstado.getSelectedItem().equals("Aprobado");

        gestor.agregarEstudiante(nombre, estado, nota, identificacion, carrera, curso);

        actualizarTabla();
        actualizarTarjetasResumen();
        limpiarFormulario();
        lblBarraEstado.setText("Estudiante agregado correctamente.");
    }

    private void cargarEstudianteEnFormulario(int filaVista) {
        int filaModelo = tablaEstudiantes.convertRowIndexToModel(filaVista);

        idSeleccionado = (int) modeloTabla.getValueAt(filaModelo, 0);
        txtIdentificacion.setText((String) modeloTabla.getValueAt(filaModelo, 1));
        txtNombre.setText((String) modeloTabla.getValueAt(filaModelo, 2));
        cambiarCarrera.getEditor().setItem(modeloTabla.getValueAt(filaModelo, 3));
        txtCurso.setText((String) modeloTabla.getValueAt(filaModelo, 4));
        txtNota.setText(String.valueOf(modeloTabla.getValueAt(filaModelo, 5)));
        cambiarEstado.setSelectedItem(modeloTabla.getValueAt(filaModelo, 6));
    }

    private void modificarEstudianteSeleccionado() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un estudiante de la tabla para modificar.",
                    "Sin selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarCampos()) {
            return;
        }

        String nombre = txtNombre.getText().trim();
        String identificacion = txtIdentificacion.getText().trim();
        String carrera = (String) cambiarCarrera.getEditor().getItem();
        String curso = txtCurso.getText().trim();
        double nota = Double.parseDouble(txtNota.getText().trim());
        boolean estado = cambiarEstado.getSelectedItem().equals("Aprobado");

        gestor.modificarEstudiante(idSeleccionado, nombre, identificacion, carrera, curso, nota, estado);

        actualizarTabla();
        actualizarTarjetasResumen();
        limpiarFormulario();
        lblBarraEstado.setText("Estudiante modificado correctamente.");
    }

    private void eliminarEstudianteSeleccionado() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un estudiante de la tabla para eliminar.",
                    "Sin selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Seguro que desea eliminar este estudiante?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            gestor.eliminarEstudiante(idSeleccionado);
            actualizarTabla();
            actualizarTarjetasResumen();
            limpiarFormulario();
            lblBarraEstado.setText("Estudiante eliminado correctamente.");
        }
    }

    private void limpiarFormulario() {
        idSeleccionado = -1;
        txtIdentificacion.setText("");
        txtNombre.setText("");
        cambiarCarrera.getEditor().setItem("");
        txtCurso.setText("");
        txtNota.setText("");
        cambiarEstado.setSelectedIndex(0);
        tablaEstudiantes.clearSelection();
    }

    private boolean validarCampos() {
        if (txtIdentificacion.getText().trim().isEmpty() ||
                txtNombre.getText().trim().isEmpty() ||
                txtCurso.getText().trim().isEmpty() ||
                txtNota.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos los campos son obligatorios.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            double nota = Double.parseDouble(txtNota.getText().trim());
            if (nota < 0 || nota > 100) {
                JOptionPane.showMessageDialog(this,
                        "La nota debe estar entre 0 y 100.",
                        "Nota inválida", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "La nota debe ser un número válido.",
                    "Nota inválida", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private void aplicarFiltros() {
        String texto = txtBuscar.getText().trim();

        ArrayList<Estudiante> resultado;
        if (!texto.isEmpty()) {
            resultado = gestor.buscarPorNombre(texto);
        } else {
            resultado = new ArrayList<>(gestor.listarTodos());
        }

        String carrera = (String) filtroCarrera.getSelectedItem();
        if (carrera != null && !carrera.equals("Todas")) {
            resultado.retainAll(gestor.filtrarPorCarrera(carrera));
        }

        String curso = (String) filtroCurso.getSelectedItem();
        if (curso != null && !curso.equals("Todos")) {
            resultado.retainAll(gestor.filtrarPorCurso(curso));
        }

        String condicion = (String) filtroCondicion.getSelectedItem();
        if (condicion != null && !condicion.equals("Todos")) {
            boolean estado = condicion.equals("Aprobado");
            resultado.retainAll(gestor.filtrarPorCondicion(estado));
        }

        actualizarTabla(resultado);
        lblBarraEstado.setText(resultado.size() + " estudiante(s) encontrado(s).");
    }
}


