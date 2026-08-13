class Estudiante{
private String nombre;
private boolean estado;
private double promedio;
private String identificacion;
private String carrera;
private String curso;
private int id;

public Estudiante(){
    this.nombre = "Sin definir";
    this.estado = true;
    this.promedio = 0.0;
    this.identificacion = "Sin definir";
    this.carrera = "Por definir";
    this.curso = "Por definir";
}

public Estudiante(int id, String nombre, boolean estado, double promedio, String identificacion,
                  String carrera, String curso){
    this.id = id;
    this.nombre = nombre;
    this.estado = estado;
    this.promedio = promedio;
    this.identificacion = identificacion;
    this.carrera = carrera;
    this.curso = curso;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }



}

