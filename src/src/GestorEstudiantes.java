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
        for (Estudiante e : listaEstudiantes) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
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
        for (Estudiante e: listaEstudiantes){
            if(e.getIdentificacion().equals(identificacion)){
                return e;
            }
        }
        return null;
    }

    public boolean existeIdentificacion(String identificacion) {
        return buscarPorIdentificacion(identificacion) != null;
    }

    public ArrayList<Estudiante> buscarPorNombre(String texto) {
        ArrayList<Estudiante> resultado = new ArrayList<>();
        String textoBusqueda = texto.toLowerCase().trim();
        for (Estudiante e : listaEstudiantes) {
            if (e.getNombre().toLowerCase().contains(textoBusqueda)) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public ArrayList<Estudiante> filtrarPorCarrera(String carrera) {
        ArrayList<Estudiante> resultado = new ArrayList<>();
        for (Estudiante e : listaEstudiantes) {
            if (e.getCarrera().equalsIgnoreCase(carrera)) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public ArrayList<Estudiante> filtrarPorCurso(String curso) {
        ArrayList<Estudiante> resultado = new ArrayList<>();
        for (Estudiante e : listaEstudiantes) {
            if (e.getCurso().equalsIgnoreCase(curso)) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public ArrayList<Estudiante> filtrarPorCondicion(boolean estado) {
        ArrayList<Estudiante> resultado = new ArrayList<>();
        for (Estudiante e : listaEstudiantes) {
            if (e.isEstado() == estado) {
                resultado.add(e);
            }
        }
        return resultado;
    }

    public ArrayList<Estudiante> listarTodos() {
        return listaEstudiantes;
    }

    public int obtenerTotalEstudiantes() {
        return listaEstudiantes.size();
    }

    public int obtenerTotalAprobados() {
        int total = 0;
        for (Estudiante e : listaEstudiantes) {
            if (e.isEstado()) total++;
        }
        return total;
    }

    public int obtenerTotalReprobados() {
        int total = 0;
        for (Estudiante e : listaEstudiantes) {
            if (!e.isEstado()) total++;
        }
        return total;
    }

    public double obtenerPromedioGeneral() {
        if (listaEstudiantes.isEmpty()) return 0.0;
        double suma = 0.0;
        for (Estudiante e : listaEstudiantes) {
            suma += e.getPromedio();
        }
        return suma / listaEstudiantes.size();
    }
}