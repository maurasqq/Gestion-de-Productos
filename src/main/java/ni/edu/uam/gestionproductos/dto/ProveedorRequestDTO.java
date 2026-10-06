package ni.edu.uam.gestionproductos.dto;

public class ProveedorRequestDTO {
    private String nombre;
    private String telefono;
    private String correo;
    private boolean activo;

    public ProveedorRequestDTO() { }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
