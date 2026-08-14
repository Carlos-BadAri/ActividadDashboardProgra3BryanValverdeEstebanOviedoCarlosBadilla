import java.util.ArrayList;
public class GestorEstudiante {
    private ArrayList<Estudiante> listaEstudiantes;
    private int siguienteId;

    public GestorEstudiante(){
        this.listaEstudiantes = new ArrayList<>();
        this.siguienteId = 1;
    }

    //Esta funcion va a agregar un nuevo estudiante y le asigna automaticamente un id.
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

    //Elimina un estudiante por id. Retorna true si lo encontro y elimino
    public boolean eliminarEstudiante(int id){
        Estudiante estudiante = buscarPorId(id);
        if (estudiante != null){
            listaEstudiantes.remove(estudiante);
            return true;
        }
        return false;
    }
    public boolean modificarEstudiante(int id, String nombre, boolean estado, double promedio, String identificacion, String carrera, String curso) {
        Estudiante estudiante = buscarPorId(id);
        if (estudiante != null) {
            estudiante.setNombre(nombre);
            estudiante.setEstado(estado);
            estudiante.setPromedio(promedio);
            estudiante.setIdentificacion(identificacion);
            estudiante.setCarrera(carrera);
            estudiante.setCurso(curso);
        }
        return true;
    }
    public Estudiante buscarId(int id){
        for (Estudiante e : listaEstudiantes) {
            if (e.getId()==id){
                return e;
            }
        }
        return null;
    }
    public Estudiante buscarPorIdentificacion(String identificacion){
        for (Estudiante e: listaEstudiantes){
            if(e.getIdentificacion() == identificacion){
                return e;
            }
        }
        return null;
    }
    // Búsqueda parcial por nombre
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
                resultado.add(e);//Si el estado es true, aprobado, si no reprobado
            }
        }
        return resultado;
    }
// Estadísticas para el Dashboard
    public int obtenerTotalEstudiantes() {
        return listaEstudiantes.size();
    }

    public int obtenerTotalAprobados() {
        int total = 0;
        for (Estudiante e : listaEstudiantes) {
            if (e.isEstado()) {
                total++;
            }
        }
        return total;
    }

    public int obtenerTotalReprobados() {
        int total = 0;
        for (Estudiante e : listaEstudiantes) {
            if (!e.isEstado()) {
                total++;
            }
        }
        return total;
    }

    public double obtenerPromedioGeneral() {
        if (listaEstudiantes.isEmpty()) {
            return 0.0;
        }
        double suma = 0.0;
        for (Estudiante e : listaEstudiantes) {
            suma += e.getPromedio();
        }
        return suma / listaEstudiantes.size();
    }

    public ArrayList<Estudiante> listarTodos() {
        return listaEstudiantes;
    }
 //esta funcion lo que hace es ver si ya hay otro estudiante con esa identificacion
    public boolean existeIdentificacion(String identificacion) {
        return buscarPorIdentificacion(identificacion) != null;
    }

}
