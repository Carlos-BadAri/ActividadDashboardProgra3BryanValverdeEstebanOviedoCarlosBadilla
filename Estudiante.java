class Estudiante{
private String nombre;
private boolean estado;
private int promedio;
private string identificacion;
private string carrera;
private string curso;

public Estudiante(){
    this.nombre = "Sin definir";
    this.estado = true;
    this.promedio = 0;
    this.identificacion = "Sin definir";
    this.carrera = "Por definir";
    this.curso = "Por definir";
}

public Estudiante(String nombre, boolean estado, int promedio, String identificacion,
                  String carrera, String curso){
    this.nombre = nombre;
    this.estado = estado;
    this.promedio = promedio;
    this.identificacion = identificacion;
    this.carrera = carrera;
    this.curso = curso;
}
}