import java.util.ArrayList;
public class GestorEstudiante {
    private ArrayList<Estudiante> listaEstudiantes;
    private int siguienteId;

    public GestorEstudiante(){
        this.listaEstudiantes = new ArrayList<>();
        this.siguienteId = 1;
    }

    //Esta funcion va a agregar un nuevo estudiante y le asigna automaticamente un id
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


}
