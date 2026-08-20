import java.util.ArrayList;

public class GestorEstudiantes {
    private ArrayList<Estudiante> listaEstudiantes;
    private int siguienteId;

    public GestorEstudiantes(){
        this.listaEstudiantes = new ArrayList<>();
        this.siguienteId = 1;
    }

    public Estudiante agregarEstudiante(String nombre, boolean estado, double promedio, String identificacion,
                                        String carrera, String curso){
        Estudiante nuevo = new Estudiante(siguienteId, nombre, estado, promedio, identificacion, carrera, curso);
        listaEstudiantes.add(nuevo);
        siguienteId++;
        return nuevo;
    }

    public Estudiante buscarPorId(int id) {
        for (int i = 0; i < listaEstudiantes.size(); i++) {
            Estudiante e = listaEstudiantes.get(i);

            if (e.getId() == id) {
                return e;
            }
        }

        return null;
    }

    private Estudiante buscarPorIdRecursivo(int id, int posicion) {
        if (posicion >= listaEstudiantes.size()) {
            return null;
        }

        Estudiante e = listaEstudiantes.get(posicion);

        if (e.getId() == id) {
            return e;
        }

        return buscarPorIdRecursivo(id, posicion + 1);
    }

    public boolean eliminarEstudiante(int id){
        Estudiante estudiante = buscarPorId(id);

        if (estudiante != null){
            listaEstudiantes.remove(estudiante);
            return true;
        }

        return false;
    }

    public boolean modificarEstudiante(int id, String nombre, String identificacion, String carrera,
                                       String curso, double promedio, boolean estado) {
        Estudiante estudiante = buscarPorId(id);

        if (estudiante != null) {
            estudiante.setNombre(nombre);
            estudiante.setEstado(estado);
            estudiante.setPromedio(promedio);
            estudiante.setIdentificacion(identificacion);
            estudiante.setCarrera(carrera);
            estudiante.setCurso(curso);
            return true;
        }

        return false;
    }

    public Estudiante buscarPorIdentificacion(String identificacion){
        return buscarPorIdentificacionRecursivo(identificacion, 0);
    }

    private Estudiante buscarPorIdentificacionRecursivo(String identificacion, int posicion){
        if (posicion >= listaEstudiantes.size()) {
            return null;
        }

        Estudiante e = listaEstudiantes.get(posicion);

        if (e.getIdentificacion().equals(identificacion)){
            return e;
        }

        return buscarPorIdentificacionRecursivo(identificacion, posicion + 1);
    }

    public boolean existeIdentificacion(String identificacion) {
        return buscarPorIdentificacion(identificacion) != null;
    }

    public ArrayList<Estudiante> buscarPorNombre(String texto) {
        ArrayList<Estudiante> resultado = new ArrayList<>();
        String textoBusqueda = texto.toLowerCase().trim();

        for (int i = 0; i < listaEstudiantes.size(); i++) {
            Estudiante e = listaEstudiantes.get(i);

            if (e.getNombre().toLowerCase().contains(textoBusqueda)) {
                resultado.add(e);
            }
        }

        return resultado;
    }

    public ArrayList<Estudiante> filtrarPorCarrera(String carrera) {
        ArrayList<Estudiante> resultado = new ArrayList<>();

        for (int i = 0; i < listaEstudiantes.size(); i++) {
            Estudiante e = listaEstudiantes.get(i);

            if (e.getCarrera().equalsIgnoreCase(carrera)) {
                resultado.add(e);
            }
        }

        return resultado;
    }

    public ArrayList<Estudiante> filtrarPorCurso(String curso) {
        ArrayList<Estudiante> resultado = new ArrayList<>();

        for (int i = 0; i < listaEstudiantes.size(); i++) {
            Estudiante e = listaEstudiantes.get(i);

            if (e.getCurso().equalsIgnoreCase(curso)) {
                resultado.add(e);
            }
        }

        return resultado;
    }

    public ArrayList<Estudiante> filtrarPorCondicion(boolean estado) {
        ArrayList<Estudiante> resultado = new ArrayList<>();

        for (int i = 0; i < listaEstudiantes.size(); i++) {
            Estudiante e = listaEstudiantes.get(i);

            if (e.isEstado() == estado) {
                resultado.add(e);
            }
        }

        return resultado;
    }

    public ArrayList<Estudiante> listarTodos() {
        ArrayList<Estudiante> resultado = new ArrayList<>();

        for (int i = 0; i < listaEstudiantes.size(); i++) {
            resultado.add(listaEstudiantes.get(i));
        }

        return resultado;
    }

    public int obtenerTotalEstudiantes() {
        int total = 0;

        for (int i = 0; i < listaEstudiantes.size(); i++) {
            total++;
        }

        return total;
    }

    public int obtenerTotalAprobados() {
        return obtenerTotalAprobadosRecursivo(0);
    }

    private int obtenerTotalAprobadosRecursivo(int posicion) {
        if (posicion >= listaEstudiantes.size()) {
            return 0;
        }

        int total = 0;

        if (listaEstudiantes.get(posicion).isEstado()) {
            total++;
        }

        return total + obtenerTotalAprobadosRecursivo(posicion + 1);
    }

    public int obtenerTotalReprobados() {
        return obtenerTotalReprobadosRecursivo(0);
    }

    private int obtenerTotalReprobadosRecursivo(int posicion) {
        if (posicion >= listaEstudiantes.size()) {
            return 0;
        }

        int total = 0;

        if (!listaEstudiantes.get(posicion).isEstado()) {
            total++;
        }

        return total + obtenerTotalReprobadosRecursivo(posicion + 1);
    }

    public double obtenerPromedioGeneral() {
        if (listaEstudiantes.isEmpty()) {
            return 0.0;
        }

        double suma = obtenerSumaPromediosRecursivo(0);

        return suma / listaEstudiantes.size();
    }

    private double obtenerSumaPromediosRecursivo(int posicion) {
        if (posicion >= listaEstudiantes.size()) {
            return 0.0;
        }

        double suma = listaEstudiantes.get(posicion).getPromedio();

        return suma + obtenerSumaPromediosRecursivo(posicion + 1);
    }
}